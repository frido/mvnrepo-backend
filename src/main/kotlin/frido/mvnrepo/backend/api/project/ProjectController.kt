package frido.mvnrepo.backend.api.project

import frido.mvnrepo.backend.api.ApiResponsesStandard
import frido.mvnrepo.backend.api.Response
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Api(value = "/projects", description = "Operations about projects. Project is set of data about repository collected from GitHub")
@RestController
@RequestMapping(value="/api/projects", produces="application/json")
open class ProjectController {

    var log = LoggerFactory.getLogger(ArtifactController::class.java)

    @Autowired
    lateinit var service: ProjectService

    @ApiOperation(value="Get list of projects ordered by attribute in desc order")
    @ApiResponsesStandard
    @GetMapping("top")
    open fun top(
            @ApiParam(value = "Attribute of project to use for ordering", allowableValues="a,b,c") 
            @RequestParam("attribute", required = true) 
            attribute: String?,
            @ApiParam(value = "Maximum number of projects in the response")
            @RequestParam("size", required = false) 
            size: Int?
    ): Response {
        log.info(attribute)
        val response = Response(service.top(attribute.orEmpty(), size))
        return response
    }

    @ApiOperation(value="Get Project according to id")
    @ApiResponsesStandard
    @GetMapping("id")
    open fun id(
            @ApiParam(value = "Id of project") 
            @RequestParam("id", required = true) 
            id: String?
    ): Response {
        log.info(id)
        val response = Response(service.id(id.orEmpty()))
        return response
    }

    @ApiOperation(value="Get list of projects what match string pattern")
    @ApiResponsesStandard
    @GetMapping("search")
    open fun search(
            @ApiParam(value = "Pattern to search in attribute description") 
            @RequestParam("pattern", required = true) 
            pattern: String?,
            @ApiParam(value = "Maximum number of projects in the response")
            @RequestParam("size", required = false) 
            size: Int?
    ): Response {
        log.info(pattern)
        val response = Response(service.search("description", pattern.orEmpty(), size))
        return response
    }
}