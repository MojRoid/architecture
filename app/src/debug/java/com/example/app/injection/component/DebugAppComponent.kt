package com.example.app.injection.component

import com.example.app.injection.binding.DebugTaskBindings
import com.example.app.injection.binding.TaskBindings
import com.example.app.injection.module.DebugAppModule
import com.example.app.view.DebugApp
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
        DebugTaskBindings::class,
        DebugAppModule::class,

        AndroidSupportInjectionModule::class,
        TaskBindings::class,
        BaseModule::class,
        TodosModule::class
    ]
)
interface DebugAppComponent : AndroidInjector<DebugApp> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance app: DebugApp): DebugAppComponent
    }
}
