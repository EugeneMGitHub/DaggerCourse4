package com.example.daggerlesson4components.component

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides


/**ПЕРВЫЙ СПОСОБ СОЗДАНИЯ КОМПОНЕНТА С ЗАВИСИМОСТЯМИ*/
class AppWithDep: Application(){
    private lateinit var componentWithDependencies: ComponentWithDependencies

    override fun onCreate() {
        super.onCreate()
        componentWithDependencies = DaggerComponentWithDependencies.builder()
            .appDeps(appDeps = AppDepsImpl())
            .build()

    }

    private inner class AppDepsImpl : AppDeps {
        override val context: Context = this@AppWithDep
    }
}


/**ВТОРОЙ СПОСОБ СОЗДАНИЯ КОМПОНЕНТА С ЗАВИСИМОСТЯМИ*/
class AppWithDepV2: Application(), AppDeps{
    private lateinit var componentWithDependencies: ComponentWithDependencies

    override fun onCreate() {
        super.onCreate()
        componentWithDependencies = DaggerComponentWithDependencies.builder()
            .appDeps(appDeps = this)
            .build()

    }

    override val context: Context = this

}




@Component(modules = [AppModule2::class], dependencies = [AppDeps::class])
interface ComponentWithDependencies {

    @Component.Builder
    interface Builder {
       fun appDeps(appDeps: AppDeps): Builder
       fun build(): ComponentWithDependencies
    }

}



interface  AppDeps {
    val context: Context
}



@Module
class AppModule2(){

    @Provides
    fun provideSomethingUsingContext(context: Context): Context {
        return context
    }
}