package com.example.feature.main.injection

import com.example.feature.sample.screen.injection.ExampleListBindings
import dagger.Module

@Module(includes = [ExampleListBindings::class])
abstract class MainTaskBindings
