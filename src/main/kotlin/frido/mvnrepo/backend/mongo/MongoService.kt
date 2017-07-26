package frido.mvnrepo.backend.mongo

import com.mongodb.client.MongoDatabase
import frido.mvnrepo.backend.mongo.schema.Repo
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
open class MongoService{

    @Autowired
    lateinit var mongo: MongoDatabase

    private val REPO = "repo"

    fun findAllRepos(): List<Repo> {
        return mongo
                .getCollection(REPO)
                .find()
                .map{ doc: Document -> Repo.of(doc) }
                .toList()
    }

    fun findRepos(filter: Document): List<Repo> {
        return mongo
                .getCollection(REPO)
                .find(filter)
                .map{ doc: Document -> Repo.of(doc) }
                .toList()
    }

}
