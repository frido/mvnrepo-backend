package frido.mvnrepo.backend.api.project

import io.swagger.annotations.Api
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponses
import io.swagger.annotations.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value="/api/projects")
@Api(value = "projects")
open class ProjectController {

    var log = LoggerFactory.getLogger(ProjectController::class.java)

    @Autowired
    lateinit var service: ProjectService

    
    @GetMapping("top")
    @ApiOperation(value="top")
    @ApiResponses(
        ApiResponse(code = 200, message = "Given library found"),
	    ApiResponse(code = 400, message = "Bad request") )
    open fun top(
            @RequestParam("attribute", required = true) attribute: String?
    ): Response {
        log.info(attribute)
        val response = Response(service.top(attribute.orEmpty()))
        return response
    }

}