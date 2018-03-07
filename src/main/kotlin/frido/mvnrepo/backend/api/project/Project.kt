package frido.mvnrepo.backend.api.project

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonValue
import org.bson.Document

class Project(result: Document) {
    private var result = result

    @JsonGetter("description")
    fun getDescription(): String? {
        return result.getString("description");
    }

}