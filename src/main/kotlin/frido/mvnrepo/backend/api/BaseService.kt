package frido.mvnrepo.backend.api

import frido.mvnrepo.backend.api.project.Project
import frido.mvnrepo.backend.services.MongoService
import org.bson.Document
import org.bson.types.ObjectId
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

abstract class BaseService<T> {

    open var log = LoggerFactory.getLogger(BaseService::class.java)

    @Autowired
    lateinit var mongo: MongoService

    abstract fun getCollection(): String
    abstract fun map(): com.mongodb.Function<Document, T>
    /**
     * Search for most popular projects
     */
    fun top(attribute: String, size: Int?): List<T> {
        val filter = Document()
        val sort = Document(attribute, -1) //{"stargazers.totalCount":-1}
        var number = 50 // TODO: constant
        if(size != null) {
            number = size;
        }
        return mongo
                .find(getCollection(), filter, sort, number)
                .map(map())
                .toList();
    }

    /**
     * Search for id
     */
    fun id(id: String): T {
        val filter = Document("_id", ObjectId(id))
        return mongo
                .find(getCollection(), filter)
                .map(map())
                .first();
    }

    // TODO: hladanie $or pre viac attributov
    fun search(attribute: String, pattern: String, size: Int?): List<T> {
        val regex = ".*$pattern.*"
        val options: HashMap<String, Any> = hashMapOf("\$regex" to regex, "\$options" to "i")
        val filter = Document(attribute, Document(options)) // { "genre": { "$regex":"Hip.*Hop","$options":"i" } }
        val sort = null
        var number = 50 // TODO: constant
        if(size != null) {
            number = size;
        }
        return mongo
                .find(getCollection(), filter, sort, number)
                .map(map())
                .toList();
    }

    fun match(attribute: String, pattern: String, size: Int?): List<T> {
        val filter = Document(attribute, pattern) // { "genre": { "$regex":"Hip.*Hop","$options":"i" } }
        val sort = null
        var number = 50 // TODO: constant
        if(size != null) {
            number = size;
        }
        return mongo
                .find(getCollection(), filter, sort, number)
                .map(map())
                .toList();
    }
}
