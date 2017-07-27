package frido.mvnrepo.backend.mongo.schema

import org.bson.Document

data class Library(
        val id: String? = null,
        val groupId: String? = null,
        val artifactId: String? = null,
        val version: String? = null,
        val versioning: Versions? =null,
        val latest: String? = null,
        val description: String? = null,
        val repo: String? = null
) {
    constructor(doc: Document) : this(
            doc.getObjectId("_id").toString(),
            doc.getString("groupId"),
            doc.getString("artifactId"),
            doc.getString("version"),
            Versions(doc.getOrDefault(key = "versions", defaultValue = Document()) as Document),
            doc.getString("latest"),
            doc.getString("description"),
            doc.getString("repo")
    )
}
