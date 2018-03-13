package frido.mvnrepo.backend.api

import com.fasterxml.jackson.annotation.JsonView


class Response(data: Any) {
    @JsonView(Views.Simple::class)
    var data: Any = data
}