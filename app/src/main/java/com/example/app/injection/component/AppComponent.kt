package com.example.app.injection.component

import com.example.app.injection.binding.TaskBindings
import com.example.app.injection.module.AppModule
import com.example.app.view.App
import com.example.base.core.injection.BaseModule
import com.example.base.core.injection.scopes.PerApplication
import com.example.library.todos.injection.TodosModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [
        AppModule::class,

        AndroidSupportInjectionModule::class,
        TaskBindings::class,
        BaseModule::class,
        TodosModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance app: App): AppComponent
    }
}
