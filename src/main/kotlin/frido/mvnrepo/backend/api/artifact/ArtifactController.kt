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

@RestController
@RequestMapping(value="/api/artifacts")
@Api(value = "artifacts")
open class ArtifactController {

    var log = LoggerFactory.getLogger(ArtifactController::class.java)

    @Autowired
    lateinit var service: ArtifactService

    @GetMapping("top")
    @ApiOperation(value="top")
    @ApiResponsesStandard
    open fun top(
            @RequestParam("attribute", required = true) attribute: String?,
            @RequestParam("size", required = false) size: Int?
    ): Response {
        log.info(attribute)
        val response = Response(service.top(attribute.orEmpty(), size))
        return response
    }

    @GetMapping("id")
    @ApiOperation(value="id")
    @ApiResponsesStandard
    open fun id(
            @RequestParam("id", required = true) id: String?
    ): Response {
        log.info(id)
        val response = Response(service.id(id.orEmpty()))
        return response
    }

    @GetMapping("search")
    @ApiOperation(value="search")
    @ApiResponsesStandard
    open fun search(
            @RequestParam("pattern", required = true) pattern: String?,
            @RequestParam("size", required = false) size: Int?
    ): Response {
        log.info(pattern)
        val response = Response(service.search("artifactId", pattern.orEmpty(), size))
        return response
    }
}