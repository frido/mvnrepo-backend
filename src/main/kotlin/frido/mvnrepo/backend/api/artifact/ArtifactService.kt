package frido.mvnrepo.backend.api.project

import com.mongodb.Function
import frido.mvnrepo.backend.api.BaseService
import frido.mvnrepo.backend.api.artifact.Artifact
import org.bson.Document
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
open class ArtifactService: BaseService<Artifact>() {

    override var log = LoggerFactory.getLogger(ArtifactService::class.java)

    override fun map(): Function<Document, Artifact> {
        return Function<Document, Artifact> { t: Document -> Artifact(t) }
    }

    override fun getCollection(): String {
        return "metadata"
    }
}
