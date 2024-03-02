package com.example.daggerlesson4components.component

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Subcomponent
import javax.inject.Scope
import javax.inject.Singleton


class DaggerApp5 : Application() {

    lateinit var appComponent5: AppComponent5
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent5 = DaggerAppComponent5
            .builder()
            .context(context = this)
            .build()
    }

}

fun main5(appComponent5: AppComponent5) {
    val featureComponent = appComponent5.featureComponent()
        .build()
}



@Component(modules = [AppModule5::class])
@Singleton
interface AppComponent5 {

    fun featureComponent(): FeatureComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent5
    }

}

@Module(subcomponents = [FeatureComponent::class])
class AppModule5


@Feature
@Subcomponent(modules = [FeatureModule::class])
interface FeatureComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): FeatureComponent
    }

}

@Module
class FeatureModule

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class Feature


