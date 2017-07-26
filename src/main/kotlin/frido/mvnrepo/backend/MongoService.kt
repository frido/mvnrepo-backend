package frido.mvnrepo.backend

import com.mongodb.client.MongoDatabase
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.stream.Stream
import java.util.stream.StreamSupport

@Repository
open class MongoService{

    @Autowired
    lateinit var mongo: MongoDatabase

    fun getRepo(name: String): Stream<Document> {
        val cursor = mongo.getCollection("repo").find(Document("name", name)).spliterator()
        return StreamSupport.stream(cursor, false)
    }

}
