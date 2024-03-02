package com.example.daggerlesson4components.component

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides


/**Этот вариант передачи зависимостей стар и задеприкэйчен
 * ПРАВИЛЬНЫЙ ВАРИАНТ В ФАЙЛЕ N2ComponentsDesiredWay
 * */

@Component(modules = [AppModule0::class])
interface AppComponent0 {

    @Component.Builder
    interface Builder {
        fun appModule0(appModule0: AppModule0): Builder
        fun build(): AppComponent0

    }

}

@Module
class AppModule0(private val context: Context){

    @Provides
    fun provideContext(): Context{
        return context
    }
}

// Это старый способ передачи зависимости в Module
class DaggerApp0 : Application() {

    private lateinit var appComponent: AppComponent0

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent0
            .builder()
            .appModule0(AppModule0(context = this))
            .build()

    }
}