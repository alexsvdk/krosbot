package com.github.alexsvdk.graphit.mongo

import com.github.alexsvdk.graphit.state.StateManager
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.*
import kotlin.reflect.KClass

class MongoStateManager<T: Any>(
    db: MongoDatabase,
    kClass: KClass<T>
) : StateManager<T> {

    companion object {
        inline fun <reified T: Any> of(db: MongoDatabase) = MongoStateManager(db, T::class)
    }

    private val collection = db.getCollection(kClass.qualifiedName!!, kClass.java)

    override fun get(id: String): T? =
        collection.findOneById(id)

    override fun set(id: String, data: T) {
        collection.deleteOneById(id)
        collection.insertOne(data)
    }

}