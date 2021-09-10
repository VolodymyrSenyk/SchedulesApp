package com.senyk.volodymyr.schedulesapp.domain.entity

class Pair(
    val time: Long,
    val name: String,
    val teacher: String,
    val type: PairType,
    val place: String,
    val additionalInfo: String
)
