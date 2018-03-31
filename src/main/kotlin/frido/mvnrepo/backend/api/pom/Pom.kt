package frido.mvnrepo.backend.api.pom

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonUnwrapped
import com.fasterxml.jackson.annotation.JsonView
import frido.mvnrepo.backend.api.Views
import org.bson.BsonArray
import org.bson.Document
import java.util.*

class Pom(result: Document) {
    private var result = result

    @JsonGetter("_id")
    @JsonView(Views.Simple::class)
    fun getId(): String? = result.getObjectId("_id")?.toString()

    @JsonUnwrapped
    //@JsonGetter("data")
    @JsonView(Views.Simple::class)
    fun getPom(): Document = result

}