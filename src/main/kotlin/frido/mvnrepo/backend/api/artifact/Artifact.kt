package frido.mvnrepo.backend.api.artifact

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonView
import frido.mvnrepo.backend.api.Views
import org.bson.BsonArray
import org.bson.Document
import java.util.*

class Artifact(result: Document) {
    private var result = result

    @JsonGetter("_id")
    @JsonView(Views.Simple::class)
    fun getId(): String? = result.getObjectId("_id")?.toString()

    @JsonGetter("groupId")
    @JsonView(Views.Simple::class)
    fun getGroupId(): String? = result.getString("groupId")

    @JsonGetter("artifactId")
    @JsonView(Views.Simple::class)
    fun getArtifactId(): String? = result.getString("artifactId")

    @JsonGetter("version")
    @JsonView(Views.Simple::class)
    fun getVersion(): String? = result.getString("version")

    @JsonGetter("latest")
    @JsonView(Views.Simple::class)
    fun getLatest(): String? {
        val versioning = result.get("versioning") as Document?
        var latest: Optional<String> = Optional.ofNullable(versioning?.getString("latest"))
        return latest.orElseGet { getLastVersion() }
    }

    @JsonGetter("release")
    @JsonView(Views.Simple::class)
    fun getRelease(): String? {
        val versioning = result.get("versioning") as Document?
        return versioning?.getString("release")
    }

    @JsonGetter("lastUpdated")
    @JsonView(Views.Simple::class)
    fun getLastUpdated(): String? {
        val versioning = result.get("versioning") as Document?
        return versioning?.getString("lastUpdated")
    }

    private fun getLastVersion(): String? {
        val versioning = result.get("versioning") as Document?
        val versions: BsonArray?  = versioning?.get("versions") as BsonArray?
        if(versions != null && !versions.isEmpty()){
            var last = versions.last() as String
            return last
        }
        return null
    }

}