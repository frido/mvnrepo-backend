package frido.mvnrepo.backend.api.project

import com.mongodb.Function
import frido.mvnrepo.backend.api.BaseService
import frido.mvnrepo.backend.api.artifact.Artifact
import frido.mvnrepo.backend.api.pom.Pom
import org.bson.Document
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
open class PomService: BaseService<Pom>() {

    override var log = LoggerFactory.getLogger(PomService::class.java)

    override fun map(): Function<Document, Pom> {
        return Function<Document, Pom> { t: Document -> Pom(t) }
    }

    override fun getCollection(): String {
        return "pom"
    }
}
