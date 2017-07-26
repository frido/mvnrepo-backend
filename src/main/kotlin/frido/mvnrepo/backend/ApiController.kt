package frido.mvnrepo.backend

import org.springframework.web.bind.annotation.ResponseBody
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping


@RestController
@Api(value = "test")
open class ApiController {

    @Autowired
    lateinit var backend: MongoService

    @GetMapping("api/test")
    @ResponseBody
    @ApiOperation(value="test")
    open fun test(): String {
        return backend.getRepo("central").findFirst().orElse(Document()).toJson()
    }

}