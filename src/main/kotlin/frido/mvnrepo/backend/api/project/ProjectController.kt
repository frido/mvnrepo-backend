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

@Api(value = "/projects", description = "Operations about projects. Project is set of data about repository collected from GitHub")
@RestController
@RequestMapping(value="/api/projects", produces=["application/json"])
open class ProjectController {

    var log = LoggerFactory.getLogger(ArtifactController::class.java)

    @Autowired
    lateinit var service: ProjectService
    @Autowired
    lateinit var artifactService: ArtifactService

    @ApiOperation(value="Get list of projects ordered by attribute in desc order")
    @ApiResponsesStandard
    @GetMapping("top")
    @JsonView(Views.Simple::class)
    open fun top(
            @ApiParam(value = "Attribute of project to use for ordering", allowableValues="createdAt,pushedAt,stargazers.totalCount,watchers.totalCount,forks.totalCount")
            @RequestParam("attribute", required = true, defaultValue = "stargazers.totalCount")
            attribute: String,
            @ApiParam(value = "Maximum number of projects in the response")
            @RequestParam("size", required = false, defaultValue = "50")
            size: Int
    ): Response {
        log.info(attribute)
        val response = Response(service.top(attribute, size))
        return response
    }

    @ApiOperation(value="Get Project according to id")
    @ApiResponsesStandard
    @GetMapping("id")
    @JsonView(Views.Full::class)
    open fun id(
            @ApiParam(value = "Id of project")
            @RequestParam("id", required = true, defaultValue = "")
            id: String
    ): Response {
        log.info(id)
        var project = service.id(id);
        project.setArtifacts(artifactService.match("projectUrl", project.getId().orEmpty(), null))
        val response = Response(project);
        return response
    }

    @ApiOperation(value="Get list of projects what match string pattern")
    @ApiResponsesStandard
    @GetMapping("search")
    @JsonView(Views.Simple::class)
    open fun search(
            @ApiParam(value = "Pattern to search in attribute description") 
            @RequestParam("pattern", required = true, defaultValue = "")
            pattern: String,
            @ApiParam(value = "Maximum number of projects in the response")
            @RequestParam("size", required = false, defaultValue = "50")
            size: Int
    ): Response {
        log.info(pattern)
        val response = Response(service.search("description", pattern, size))
        return response
    }
}