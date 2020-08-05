package com.kotlin.corornastats.hiltwithdagger2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

class ConstructorInjectionActivity : AppCompatActivity() {

    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
    }
}

class SomeClass
@Inject
constructor(
    private val someInterfaceImpl: SomeInterface
) {
    fun doAThing(): String {
        return "Look I got: ${someInterfaceImpl.getAThing()}"
    }
}

class SomeInterfaceImpl
@Inject
constructor() : SomeInterface {
    override fun getAThing(): String {
        return "A Thing"
    }

}

interface SomeInterface {
    fun getAThing(): String
}
//Using binds for providing complex dependencies
/*@InstallIn(ActivityComponent::class)
@Module
abstract class MyModule {

    @ActivityScoped
    @Binds
    abstract fun bindSomeDependency(
        someInterfaceImpl: SomeInterfaceImpl
    ): SomeInterface
}*/

//Using provides for providing complex dependencies
@InstallIn(ApplicationComponent::class)
@Module
abstract class MyModule {

    @Singleton
    @Provides
    fun provideSomInterface(): SomeInterface {
        return SomeInterfaceImpl()
    }
}