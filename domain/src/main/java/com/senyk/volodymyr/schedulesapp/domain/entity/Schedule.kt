package com.senyk.volodymyr.schedulesapp.domain.entity

data class Schedule(
    val name: String,
    val dateOfCreation: Long,
    val numberOfDays: Int,
    val numberOfWeeks: Int
)
