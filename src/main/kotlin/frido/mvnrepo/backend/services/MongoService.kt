package frido.mvnrepo.backend.services

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoDatabase
import org.bson.BSON
import org.bson.Document
import org.bson.conversions.Bson
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
open class MongoService{

    var log = LoggerFactory.getLogger(MongoService::class.java)

    @Autowired
    lateinit var mongo: MongoDatabase

    fun find(filter: Bson, sort: Bson?, collection: String): FindIterable<Document> {
        log.info(filter.toString()); //TODO: fix log message
        log.info(collection);
        return mongo
                .getCollection(collection)
                .find(filter)
                .sort(sort)
                .skip(0)
                .limit(50)
    }

}
