package com.abhi.runningtracker.di

import javax.inject.Qualifier


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher