package frido.mvnrepo.backend.api.project

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.annotation.JsonView
import frido.mvnrepo.backend.api.Views
import frido.mvnrepo.backend.api.artifact.Artifact
import org.bson.BsonArray
import org.bson.BsonValue
import org.bson.Document

class Project(result: Document) {
    private val result = result
    private var artifacts: List<Artifact>? = null

    @JsonGetter("_id")
    @JsonView(Views.Simple::class)
    fun getId(): String? = result.getObjectId("_id")?.toString()

    @JsonGetter("name")
    @JsonView(Views.Simple::class)
    fun getName(): String? = result.getString("name")

    @JsonGetter("createdAt")
    @JsonView(Views.Simple::class)
    fun getCreatedAt(): String? = result.getString("createdAt")

    @JsonGetter("homepageUrl")
    @JsonView(Views.Simple::class)
    fun getHomepageUrl(): String? = result.getString("homepageUrl")

    @JsonGetter("pushedAt")
    @JsonView(Views.Simple::class)
    fun getPushedAt(): String? = result.getString("pushedAt")

    @JsonGetter("description")
    @JsonView(Views.Simple::class)
    fun getDescription(): String? = result.getString("description")

    @JsonGetter("stargazers")
    @JsonView(Views.Simple::class)
    fun getStargazers(): Int? {
        var stargazers: Document = result.get("stargazers") as Document
        return stargazers.getInteger("totalCount")
    }

    @JsonGetter("watchers")
    @JsonView(Views.Simple::class)
    fun getWatchers(): Int? {
        var stargazers: Document = result.get("watchers") as Document
        return stargazers.getInteger("totalCount")
    }

    @JsonGetter("forks")
    @JsonView(Views.Simple::class)
    fun getForks(): Int? {
        var stargazers: Document = result.get("forks") as Document
        return stargazers.getInteger("totalCount")
    }

    @JsonGetter("artifacts")
    @JsonView(Views.Full::class)
    fun getArtifacts(): List<Artifact>? {
        return this.artifacts
    }

    open fun setArtifacts(artifacts: List<Artifact>) {
        this.artifacts = artifacts;
    }


}