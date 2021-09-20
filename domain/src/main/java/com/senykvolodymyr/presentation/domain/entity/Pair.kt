package com.senykvolodymyr.presentation.domain.entity

class Pair(
    val id: String,
    val startTimeInMillis: Long,
    val endTimeInMillis: Long,
    val name: String,
    val teacher: String,
    val type: PairType,
    val place: String,
    val additionalInfo: String
)
