package frido.mvnrepo.backend.mongo.schema

import org.bson.Document

class Repo(val id: String, val name: String, val base: String, val updatedAt: Long?) {
    companion object Factory {
        fun of(doc: Document): Repo {
            val id = doc.getObjectId("_id").toString()
            val name = doc.getString("name")
            val base = doc.getString("base")
            val updatedAt = doc.getLong("updatedAt")
            return Repo(id, name, base, updatedAt);
        }
    }
}