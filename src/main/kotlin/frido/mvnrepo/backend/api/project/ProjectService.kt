package frido.mvnrepo.backend.api.project

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoDatabase
import frido.mvnrepo.backend.services.MongoService
import org.bson.BSON
import org.bson.Document
import org.bson.conversions.Bson
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

const val COLLECTION: String = "projects";

@Repository
open class ProjectService {

    var log = LoggerFactory.getLogger(ProjectService::class.java)

    @Autowired
    lateinit var mongo: MongoService

    /**
     * Search for most popular projects
     */
    fun top(attribute: String): List<Project> {
        val filter = Document()
        val sort = Document(attribute, -1) //{"stargazers.totalCount":-1}
        return mongo
                .find(filter, sort, COLLECTION )
                .map { result: Document -> log.info(result.toString()); Project(result) }
                .toList();
    }

}
