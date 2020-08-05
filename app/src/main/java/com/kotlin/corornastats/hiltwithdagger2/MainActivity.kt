package com.kotlin.corornastats.hiltwithdagger2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint //AndroidEntryPoint annotation helps to inject dependencies without adding them externally
class MainActivity : AppCompatActivity() {

    //feild injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
       // println(someClass.doSomeOtherThing())
    }
}

// Reference/Study: https://developer.android.com/training/dependency-injection/hilt-android

@ActivityScoped
class SomeClass1
@Inject
constructor(
        private val someOtherClass: SomeOtherClass
) {
    fun doAThing(): String {
        return "Look I did a thing"
    }

    fun doSomeOtherThing(): String {
        return someOtherClass.doSomeOtherThing()
    }
}

@AndroidEntryPoint
/*AndroidEntryPoint annotation says that this class is the potential
entry point/class which uses the dependency*/
class MyFragment : Fragment() {
    @Inject
    lateinit var someClass: SomeClass
}


class SomeOtherClass
@Inject
constructor() {
    fun doSomeOtherThing(): String {
        return "Look I did some other thing"
    }
}