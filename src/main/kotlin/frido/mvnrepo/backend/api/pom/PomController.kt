package frido.mvnrepo.backend.api.project

import com.fasterxml.jackson.annotation.JsonView
import frido.mvnrepo.backend.api.ApiResponsesStandard
import frido.mvnrepo.backend.api.Response
import frido.mvnrepo.backend.api.Views
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Api(value = "/pom", description = "Operations about pom. Pom is set of data about pom of library collected from repository")
@RestController
@RequestMapping(value="/api/pom", produces=["application/json"])
open class PomController {

    var log = LoggerFactory.getLogger(PomController::class.java)

    @Autowired
    lateinit var service: PomService

    @GetMapping("top")
    @ApiOperation(value="Get list of poms ordered by attribute in desc order")
    @ApiResponsesStandard
    @JsonView(Views.Simple::class)
    open fun top(
            @ApiParam(value = "Attribute of pom to use for ordering", allowableValues="Id")
            @RequestParam("attribute", required = true, defaultValue = "Id")
            attribute: String,
            @ApiParam(value = "Maximum number of artifacts in the response")
            @RequestParam("size", required = false, defaultValue = "50") 
            size: Int
    ): Response {
        log.info(attribute)
        val response = Response(service.top(attribute, size))
        return response
    }

    
    @ApiOperation(value="Get pom according to id")
    @ApiResponsesStandard
    @GetMapping("id")
    @JsonView(Views.Full::class)
    open fun id(
            @ApiParam(value = "Id of pom")
            @RequestParam("id", required = true, defaultValue = "") 
            id: String
    ): Response {
        log.info(id)
        val response = Response(service.id(id))
        return response
    }

    @ApiOperation(value="Get list of poms what match string pattern")
    @ApiResponsesStandard
    @GetMapping("search")
    @JsonView(Views.Simple::class)
    open fun search(
            @ApiParam(value = "Pattern to search in attribute artifactId") 
            @RequestParam("pattern", required = true, defaultValue = "") 
            pattern: String,
            @ApiParam(value = "Maximum number of artifacts in the response")
            @RequestParam("size", required = false, defaultValue = "50") 
            size: Int
    ): Response {
        log.info(pattern)
        val response = Response(service.search("ArtifactId", pattern, size))
        return response
    }
}