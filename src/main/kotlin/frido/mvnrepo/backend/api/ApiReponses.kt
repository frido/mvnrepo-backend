package frido.mvnrepo.backend.api

import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.FUNCTION)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(
        ApiResponse(code = 200, message = "Given library found PETER"),
        ApiResponse(code = 400, message = "Bad request PETER") )
annotation class ApiResponsesStandard