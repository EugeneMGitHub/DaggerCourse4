package com.example.daggerlesson4components.component

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton



/** 1 способ организации связи между компонентами*/

/** Здесь AppComponent6 выступает зависимостью для AppComponent7*
 * Что позволяет для AppComponent7
 *
 */

@Component
interface JustComponent1 {

    fun application(): Application

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): JustComponent1
    }

}


@Component(dependencies = [JustComponent1::class])
interface DependentComponent1 {

    @Component.Builder
    interface Builder {

        fun justComponent1(justComponent1: JustComponent1): Builder

        fun build(): DependentComponent1

    }

}




/** 2 способ организации связи между компонентами*/
/**Подход через интерфейс*/


@Component
interface JustComponent2 : InterfaceForDependencies {

    override fun application(): Application

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): JustComponent2

    }

}



interface InterfaceForDependencies {
    fun application() : Application
}


@Component(dependencies = [InterfaceForDependencies::class])
interface DependentComponent2 {

    @Component.Builder
    interface Builder {

        fun deps(deps: InterfaceForDependencies) : Builder

        fun build(): DependentComponent2
    }

}



