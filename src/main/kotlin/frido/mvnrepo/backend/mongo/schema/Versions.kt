package frido.mvnrepo.backend.mongo.schema

import org.bson.Document

data class Versions (
    val latest: String? = null,
    val release: String? = null,
    val lastUpdated: String? = null,
    val versions: List<String>? = null
) {
    constructor(doc: Document) : this(
            doc.getString("latest"),
            doc.getString("release"),
            doc.getString("lastUpdated"),
            doc.getOrDefault("versions", emptyList<String>()) as List<String>
    )
}
