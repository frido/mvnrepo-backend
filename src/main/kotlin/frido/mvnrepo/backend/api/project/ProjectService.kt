package frido.mvnrepo.backend.api.project

import com.mongodb.Function
import frido.mvnrepo.backend.api.BaseService
import frido.mvnrepo.backend.services.MongoService
import org.bson.Document
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

const val COLLECTION: String = "projects";

@Repository
open class ProjectService: BaseService<Project>() {

    override var log = LoggerFactory.getLogger(ProjectService::class.java)

    override fun map(): Function<Document, Project> {
        return Function<Document, Project> { t: Document -> Project(t) }
    }

    override fun getCollection(): String {
        return COLLECTION
    }
}
