package frido.mvnrepo.backend.api

import frido.mvnrepo.backend.mongo.MongoService
import frido.mvnrepo.backend.mongo.SearchCriteria
import frido.mvnrepo.backend.mongo.schema.Library
import frido.mvnrepo.backend.mongo.schema.Repo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value="/api/")
@Api(value = "lib")
open class LibController {

    @Autowired
    lateinit var backend: MongoService

    @ApiOperation(value="lib")
    @GetMapping("lib")
    open fun search(
            @RequestParam("query", required = false) query: String?,
            @RequestParam("groupId", required = false) groupId: String?,
            @RequestParam("artifactId", required = false) artifactId: String?,
            @RequestParam("version", required = false) version: String?,
            @RequestParam("psize", required = false) psize: String? = "20",
            @RequestParam("pnumber", required = false) pnumber: String? = "0"
    ): List<Library> {
        val search: SearchCriteria = SearchCriteria()
        search.addCriteria("query", query)
        search.addCriteria("groupId", groupId)
        search.addCriteria("artifactId", artifactId)
        search.addCriteria("version", version)
        search.pnumber
        search.psize
        return backend.findLibs(search)
        //return backend.findLibs()
    }

}