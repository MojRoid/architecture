package com.example.base.core.global

import com.example.base.core.BuildConfig

object Environment {

    var current: Values = BuildConfig.ENVIRONMENT // Access environment using this
    fun isProduction() = current == Production
    fun isTest() = current == Test

    object Test : Values() {

        override val endpoint: Endpoint = object : Endpoint {
            override val exampleApi: String = BuildConfig.PRODUCTION_ENDPOINT_EXAMPLE
        }

        override val key: Key = object : Key {
            override val exampleKey: String = BuildConfig.PRODUCTION_KEY_EXAMPLE
        }
    }

    object Production : Values() {

        override val endpoint: Endpoint = object : Endpoint {
            override val exampleApi: String = BuildConfig.PRODUCTION_ENDPOINT_EXAMPLE
        }

        override val key: Key = object : Key {
            override val exampleKey: String = BuildConfig.PRODUCTION_KEY_EXAMPLE
        }
    }

    abstract class Values {

        abstract val endpoint: Endpoint
        abstract val key: Key

        interface Endpoint {
            val exampleApi: String
        }

        interface Key {
            val exampleKey: String
        }
    }
}
