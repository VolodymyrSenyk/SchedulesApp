package com.senyk.volodymyr.schedulesapp.domain.entity

data class Schedule(
    val id: String = "",
    val name: String,
    val createdAtInMillis: Long = System.currentTimeMillis(),
    val updatedAtInMillis: Long = System.currentTimeMillis(),
    val isSaturdayWorkingDay: Boolean,
    val isNumeratorDenominatorSystem: Boolean,
    val isCurrent: Boolean
)
