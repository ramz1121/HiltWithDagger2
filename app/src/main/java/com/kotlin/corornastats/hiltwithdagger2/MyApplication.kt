package com.kotlin.corornastats.hiltwithdagger2

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // This annotation helps to generate dagger components without creating it manually.
class MyApplication: Application() {
}