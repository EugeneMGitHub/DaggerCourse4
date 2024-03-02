package com.example.daggerlesson4components.component

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun contextFun( context: Context): Builder

        @BindsInstance
        fun appId(@AppId appName: String): Builder

        @BindsInstance
        fun theLink(@TheLink theLink: String): Builder

        fun build(): AppComponent

    }

}

@Module
class AppModule(){

    @Provides
    fun provideSomethingUsingContext(context: Context): Context {
        return context
    }
}

// Это старый способ передачи зависимости в Module
class DaggerApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .contextFun(context = this)
            .build()
    }
}



@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppId

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TheLink


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainHost

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope