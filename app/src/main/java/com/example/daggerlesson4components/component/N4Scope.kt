package com.example.daggerlesson4components.component

import android.app.Application
import android.content.Context
import com.example.daggerlesson4components.appComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope
import javax.inject.Singleton


@Component(modules = [AppModule4::class])
@Singleton
interface AppComponent4 {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun contextFun( context: Context): Builder

        fun build(): AppComponent4

    }

}

@Module
class AppModule4(){

    @Provides
    @Singleton
    fun provideSomethingUsingContext(context: Context): Context {
        return context
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope4



// Это старый способ передачи зависимости в Module
class DaggerApp4 : Application() {

    lateinit var appComponent4: AppComponent4
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent4 = DaggerAppComponent4
            .builder()
            .contextFun(context = this)
            .build()
    }
}
