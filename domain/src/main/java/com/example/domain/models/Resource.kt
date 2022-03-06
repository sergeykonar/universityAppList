package com.example.domain.models

import java.lang.Exception

sealed class Resource<out T>{
    object Loading: Resource<Nothing>()
    class Error(var exception: Exception): Resource<Exception>()
    class Data<T>(var data: T): Resource<T>()
}
