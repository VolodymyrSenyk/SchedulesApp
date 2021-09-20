package com.senykvolodymyr.core.di.annotation.scope

import javax.inject.Scope

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER
)
@Scope
@Retention
annotation class ActivityScope