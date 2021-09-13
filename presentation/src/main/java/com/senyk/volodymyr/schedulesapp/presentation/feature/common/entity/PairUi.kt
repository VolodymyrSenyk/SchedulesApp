package com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity

import androidx.annotation.ColorInt
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.domain.entity.Pair
import com.senyk.volodymyr.schedulesapp.domain.entity.PairType
import com.senyk.volodymyr.schedulesapp.presentation.core.provider.ResourcesProvider
import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.listitem.ListItem
import com.senyk.volodymyr.schedulesapp.presentation.core.util.DateFormatterUtil

data class PairUi(
    val id: String = "-1",
    val startTimeInMillis: Long = -1,
    val startTime: String = "",
    val endTimeInMillis: Long = -1,
    val endTime: String = "",
    val name: String = "",
    val teacher: String = "",
    val type: String = "",
    @ColorInt val typeColorInt: Int = 0,
    val place: String = "",
    val additionalInfo: String = ""
) : ListItem {
    override val viewType: Int = this::class.hashCode()
    override val listId: String = id
}

internal fun Pair.toPairUi(resourcesProvider: ResourcesProvider): PairUi = PairUi(
    id = id,
    name = name,
    startTimeInMillis = startTimeInMillis,
    startTime = DateFormatterUtil.getShortTime(
        startTimeInMillis,
        resourcesProvider.getCurrentLocale()
    ),
    endTimeInMillis = endTimeInMillis,
    endTime = DateFormatterUtil.getShortTime(
        endTimeInMillis,
        resourcesProvider.getCurrentLocale()
    ),
    teacher = teacher,
    type = when (type) {
        PairType.NOT_STATED -> resourcesProvider.getString(R.string.pair_type_not_stated)
        PairType.LECTURE -> resourcesProvider.getString(R.string.pair_type_lecture)
        PairType.PRACTICE -> resourcesProvider.getString(R.string.pair_type_practice)
        PairType.LABORATORY -> resourcesProvider.getString(R.string.pair_type_laboratory)
        PairType.SPORT -> resourcesProvider.getString(R.string.pair_type_sport)
    },
    typeColorInt = resourcesProvider.getColorInt(
        when (type) {
            PairType.NOT_STATED -> R.color.colorLecture
            PairType.LECTURE -> R.color.colorPractice
            PairType.PRACTICE -> R.color.colorLaboratory
            PairType.LABORATORY -> R.color.colorSport
            PairType.SPORT -> R.color.colorNotStated
        }
    ),
    place = place,
    additionalInfo = additionalInfo
)

internal fun PairUi.toPair(resourcesProvider: ResourcesProvider): Pair = Pair(
    id = id,
    name = name,
    startTimeInMillis = startTimeInMillis,
    endTimeInMillis = endTimeInMillis,
    teacher = teacher,
    type = when (type) {
        resourcesProvider.getString(R.string.pair_type_lecture) -> PairType.LECTURE
        resourcesProvider.getString(R.string.pair_type_practice) -> PairType.PRACTICE
        resourcesProvider.getString(R.string.pair_type_laboratory) -> PairType.LABORATORY
        resourcesProvider.getString(R.string.pair_type_sport) -> PairType.SPORT
        else -> PairType.NOT_STATED
    },
    place = place,
    additionalInfo = additionalInfo
)
