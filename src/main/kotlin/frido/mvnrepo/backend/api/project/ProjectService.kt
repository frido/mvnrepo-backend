package frido.mvnrepo.backend.api.project

import com.mongodb.Function
import frido.mvnrepo.backend.api.BaseService
import org.bson.Document
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
open class ProjectService: BaseService<Project>() {

    override var log = LoggerFactory.getLogger(ArtifactService::class.java)

    override fun map(): Function<Document, Project> {
        return Function { t: Document -> Project(t) }
    }

    override fun getCollection(): String {
        return "projects"
    }
}
