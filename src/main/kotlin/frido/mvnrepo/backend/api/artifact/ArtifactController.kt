package frido.mvnrepo.backend.api.project

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

@Api(value = "/artifacts", description = "Operations about artifacts. Artifact is set of data about java library collected from repository metadata")
@RestController
@RequestMapping(value="/api/artifacts", produces=["application/json"])
open class ArtifactController {

    var log = LoggerFactory.getLogger(ArtifactController::class.java)

    @Autowired
    lateinit var service: ArtifactService

    @GetMapping("top")
    @ApiOperation(value="Get list of artifacts ordered by attribute in desc order")
    @ApiResponsesStandard
    @JsonView(Views.Simple::class)
    open fun top(
            @ApiParam(value = "Attribute of artifact to use for ordering", allowableValues="versioning.lastUpdated")
            @RequestParam("attribute", required = true, defaultValue = "versioning.lastUpdated") 
            attribute: String,
            @ApiParam(value = "Maximum number of artifacts in the response")
            @RequestParam("size", required = false, defaultValue = "50") 
            size: Int
    ): Response {
        log.info(attribute)
        val response = Response(service.top(attribute, size))
        return response
    }

    
    @ApiOperation(value="Get artifact according to id")
    @ApiResponsesStandard
    @GetMapping("id")
    @JsonView(Views.Full::class)
    open fun id(
            @ApiParam(value = "Id of artifact") 
            @RequestParam("id", required = true, defaultValue = "") 
            id: String
    ): Response {
        log.info(id)
        val response = Response(service.id(id))
        return response
    }

    @ApiOperation(value="Get list of artifacts what match string pattern")
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
        val response = Response(service.search("artifactId", pattern, size))
        return response
    }
}