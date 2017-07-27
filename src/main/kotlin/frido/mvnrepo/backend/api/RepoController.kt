package frido.mvnrepo.backend.api

import frido.mvnrepo.backend.mongo.MongoService
import frido.mvnrepo.backend.mongo.schema.Repo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@RestController
@RequestMapping(value="/api/")
@Api(value = "repo")
open class RepoController {

    @Autowired
    lateinit var backend: MongoService

    @ApiOperation(value="repo")
    @GetMapping("repo")
    open fun repo(): List<Repo> {
        return backend.findAllRepos()
    }

}