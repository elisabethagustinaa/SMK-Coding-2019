package com.elisabethagustina.smkcoding.database

data class DatabaseContract(
    val id : Long? = null,
    val title : String? = null,
    val posterPath : String? = null,
    val rating : Double? = null,
    val description : String? = null
)