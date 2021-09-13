package com.senyk.volodymyr.schedulesapp.data.datasource.database.entity

import androidx.room.*
import com.senyk.volodymyr.schedulesapp.domain.entity.DayType
import com.senyk.volodymyr.schedulesapp.domain.entity.Pair
import com.senyk.volodymyr.schedulesapp.domain.entity.PairType
import com.senyk.volodymyr.schedulesapp.domain.entity.WeekType

@Entity(
    tableName = PairDbo.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = ScheduleDbo::class,
        parentColumns = arrayOf(ScheduleDbo.ID),
        childColumns = arrayOf(PairDbo.SCHEDULE_ID),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = arrayOf(PairDbo.SCHEDULE_ID))]
)
internal data class PairDbo(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = ID) val id: Long = 0,
    @ColumnInfo(name = SCHEDULE_ID) val scheduleId: Long = 0,
    @ColumnInfo(name = WEEK_TYPE) val weekType: WeekType = WeekType.SIMPLE,
    @ColumnInfo(name = DAY_TYPE) val dayType: DayType = DayType.MONDAY,
    @ColumnInfo(name = START_TIME_IN_MILLIS) val startTimeInMillis: Long = 0,
    @ColumnInfo(name = END_TIME_IN_MILLIS) val endTimeInMillis: Long = 0,
    @ColumnInfo(name = NAME) val name: String = "",
    @ColumnInfo(name = TEACHER) val teacher: String = "",
    @ColumnInfo(name = TYPE) val type: PairType = PairType.NOT_STATED,
    @ColumnInfo(name = PLACE) val place: String = "",
    @ColumnInfo(name = INFO) val additionalInfo: String = ""
) {

    companion object {
        const val TABLE_NAME = "pairs"
        const val ID = "pair_id"
        const val SCHEDULE_ID = "schedule_id"
        const val WEEK_TYPE = "week_type"
        const val DAY_TYPE = "day_type"
        const val START_TIME_IN_MILLIS = "pair_start_time"
        const val END_TIME_IN_MILLIS = "pair_end_time"
        const val NAME = "pair_name"
        const val TEACHER = "pair_teacher"
        const val TYPE = "pair_type"
        const val PLACE = "pair_place"
        const val INFO = "pair_info"
    }
}

internal fun PairDbo.toPair(): Pair = Pair(
    id = id.toString(),
    startTimeInMillis = startTimeInMillis,
    endTimeInMillis = endTimeInMillis,
    name = name,
    teacher = teacher,
    type = type,
    place = place,
    additionalInfo = additionalInfo
)

internal fun Pair.toPairDataEntity(
    scheduleId: Long,
    weekType: WeekType,
    dayType: DayType
): PairDbo = PairDbo(
    id = id.toLong(),
    scheduleId = scheduleId,
    weekType = weekType,
    dayType = dayType,
    startTimeInMillis = startTimeInMillis,
    endTimeInMillis = endTimeInMillis,
    name = name,
    teacher = teacher,
    type = type,
    place = place,
    additionalInfo = additionalInfo
)
