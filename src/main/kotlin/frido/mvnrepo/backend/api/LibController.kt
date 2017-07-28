package frido.mvnrepo.backend.api

import frido.mvnrepo.backend.mongo.MongoService
import frido.mvnrepo.backend.mongo.SearchCriteria
import frido.mvnrepo.backend.mongo.schema.Library
import frido.mvnrepo.backend.mongo.schema.Repo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value="/api/lib")
@Api(value = "lib")
open class LibController {

    @Autowired
    lateinit var backend: MongoService

    @ApiOperation(value="search")
    @GetMapping("search")
    open fun search(
            @RequestParam("query", required = false) query: String?,
            @RequestParam("groupId", required = false) groupId: String?,
            @RequestParam("artifactId", required = false) artifactId: String?,
            @RequestParam("version", required = false) version: String?,
            @ApiParam(value = "items on one page", required = false) @RequestParam("psize", required = false, defaultValue="20") psize: Int,
            @ApiParam(value = "number of page", required = false) @RequestParam("pnumber", required = false, defaultValue="0") pnumber: Int
    ): List<Library> {
        val search: SearchCriteria = SearchCriteria()
        search.addCriteria("query", query)
        search.addCriteria("groupId", groupId)
        search.addCriteria("artifactId", artifactId)
        search.addCriteria("version", version)
        search.pnumber = pnumber //!!.toInt() // TODO: nemusi byt cislo
        search.psize = psize //!!.toInt() // TODO: nemusi byt cislo
        return backend.findLibs(search)
    }
    
    @ApiOperation(value="objectId")
    @GetMapping("objectId")
    open fun objectId(
            @RequestParam("id", required = true) id: String
    ): Library {
        val search: SearchCriteria = SearchCriteria()
        search.addCriteria("id", id)
        return backend.findLibs(search).first()
    }
    
    @ApiOperation(value="id")
    @GetMapping("id")
    open fun id(
            @RequestParam("groupId", required = true) groupId: String,
            @RequestParam("artifactId", required = true) artifactId: String,
            @RequestParam("version", required = true) version: String
    ): Library {
        val search: SearchCriteria = SearchCriteria()
        search.addCriteria("groupId", groupId)
        search.addCriteria("artifactId", artifactId)
        search.addCriteria("version", version)
        search.pnumber = 0
        search.psize = 1
        return backend.findLibs(search).first()
    }

}