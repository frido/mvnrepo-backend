package frido.mvnrepo.backend.mongo

import org.bson.Document

class SearchCriteria {
    val filter: Document = Document()
    var psize: Int = 20
    var pnumber: Int = 0

    fun addCriteria(key: String, value: String?){
        if(value != null) {
            filter.set(key, value)
        }
    }

    fun addCriteria(key: String, value: Int?){
        if(value != null) {
            filter.set(key, value)
        }
    }
}