package frido.mvnrepo.backend.api.project

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonValue
import org.bson.BsonArray
import org.bson.BsonValue
import org.bson.Document

class Project(result: Document) {
    private var result = result

    @JsonGetter("_id")
    fun getId(): String? = result.getString("_id")

    @JsonGetter("name")
    fun getName(): String? = result.getString("name") //TODO: calculate name

    @JsonGetter("createdAt")
    fun getCreatedAt(): String? = result.getString("createdAt")

    @JsonGetter("homepageUrl")
    fun getHomepageUrl(): String? = result.getString("homepageUrl")

    @JsonGetter("pushedAt")
    fun getPushedAt(): String? = result.getString("pushedAt")

    @JsonGetter("description")
    fun getDescription(): String? = result.getString("description")

    @JsonGetter("stargazers")
    fun getStargazers(): Int? {
        var stargazers: Document = result.get("stargazers") as Document
        return stargazers.getInteger("totalCount")
    }

    @JsonGetter("watchers")
    fun getWatchers(): Int? {
        var stargazers: Document = result.get("watchers") as Document
        return stargazers.getInteger("totalCount")
    }

    @JsonGetter("forks")
    fun getForks(): Int? {
        var stargazers: Document = result.get("forks") as Document
        return stargazers.getInteger("totalCount")
    }

//    @JsonGetter("artifacts")
//    fun getArtifacts(): List<Metadata> {
//        var artifacts: BsonArray = result.get("artifacts") as BsonArray
//        return artifacts.map { doc: BsonValue -> Metadata(doc as Document) }.toList()
//    }


}