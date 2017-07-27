package frido.mvnrepo.backend.mongo

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoDatabase
import frido.mvnrepo.backend.mongo.schema.Library
import frido.mvnrepo.backend.mongo.schema.Repo
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
open class MongoService{

    @Autowired
    lateinit var mongo: MongoDatabase

    private val REPO = "repo"
    private val LIB = "lib"

    fun findAllRepos(): List<Repo> {
        return mongo
                .getCollection(REPO)
                .find()
                .map{ doc: Document -> Repo.of(doc) }
                .toList()
    }

    fun findRepos(search: SearchCriteria): List<Repo> {
        return find(search, REPO).map{ doc: Document -> Repo.of(doc) }.toList()
    }

    fun findLibs(search: SearchCriteria): List<Library> {
        return find(search, LIB).map{ doc: Document -> Library(doc) }.toList()
    }

    fun find(search: SearchCriteria, collection: String): FindIterable<Document> {
        return mongo
                .getCollection(collection)
                .find(search.filter)
                .skip(search.pnumber * search.psize)
                .limit(search.psize)
    }

}
