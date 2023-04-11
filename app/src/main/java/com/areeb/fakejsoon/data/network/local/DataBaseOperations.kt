package com.areeb.fakejsoon.data.network.local

interface DataBaseOperations {
    suspend fun getAllPersons(): DbResult<List<Person>>
    suspend fun insertAllPersons(listOfPerson: List<Person>)
}
