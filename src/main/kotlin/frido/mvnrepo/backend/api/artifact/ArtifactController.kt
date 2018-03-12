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

@Api(value = "/artifacts", description = "Operations about artifacts. Artifact is set of data about java library collected from repository metadata")
@RestController
@RequestMapping(value="/api/artifacts", produces="application/json")
open class ArtifactController {

    var log = LoggerFactory.getLogger(ArtifactController::class.java)

    @Autowired
    lateinit var service: ArtifactService

    @GetMapping("top")
    @ApiOperation(value="Get list of artifacts ordered by attribute in desc order")
    @ApiResponsesStandard
    open fun top(
            @ApiParam(value = "Attribute of artifact to use for ordering", allowableValues="a,b,c") 
            @RequestParam("attribute", required = true) 
            attribute: String?,
            @ApiParam(value = "Maximum number of artifacts in the response")
            @RequestParam("size", required = false) 
            size: Int?
    ): Response {
        log.info(attribute)
        val response = Response(service.top(attribute.orEmpty(), size))
        return response
    }

    
    @ApiOperation(value="Get artifact according to id")
    @ApiResponsesStandard
    @GetMapping("id")
    open fun id(
            @ApiParam(value = "Id of artifact") 
            @RequestParam("id", required = true) 
            id: String?
    ): Response {
        log.info(id)
        val response = Response(service.id(id.orEmpty()))
        return response
    }

    @ApiOperation(value="Get list of artifacts what match string pattern")
    @ApiResponsesStandard
     @GetMapping("search")
    open fun search(
            @ApiParam(value = "Pattern to search in attribute groupId") 
            @RequestParam("pattern", required = true) 
            pattern: String?,
            @ApiParam(value = "Maximum number of artifacts in the response")
            @RequestParam("size", required = false) 
            size: Int?
    ): Response {
        log.info(pattern)
        val response = Response(service.search("artifactId", pattern.orEmpty(), size))
        return response
    }
}