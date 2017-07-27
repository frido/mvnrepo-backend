package frido.mvnrepo.backend.mongo

import org.bson.Document

class SearchCriteria {
    val filter: Document = Document()
    val psize: Int = 20
    val pnumber: Int = 0

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