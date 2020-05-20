package com.example.base.core.exception

import retrofit2.Response

class NetworkException(response: Response<*>) : Exception(response.errorBody()?.string())
