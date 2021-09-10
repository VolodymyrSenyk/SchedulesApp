package com.senyk.volodymyr.schedulesapp.domain.entity

enum class PairType {
    NOT_STATED, LECTURE, PRACTICE, LABORATORY, SPORT;

    companion object {
        const val NOT_STATED_TYPE_INDEX = 0
        const val LECTURE_TYPE_INDEX = 1
        const val PRACTICE_TYPE_INDEX = 2
        const val LABORATORY_TYPE_INDEX = 3
        const val SPORT_TYPE_INDEX = 4
    }
}
