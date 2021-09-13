package com.senyk.volodymyr.schedulesapp.di.annotation.scope

import javax.inject.Scope

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER
)
@Scope
@Retention
annotation class FragmentScope
