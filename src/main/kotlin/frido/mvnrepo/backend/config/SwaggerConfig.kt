package frido.mvnrepo.backend.config

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
open class SwaggerConfig {

    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("frido.mvnrepo.backend"))
                //.paths(PathSelectors.any())
                .paths(PathSelectors.regex("/api.*"))
                .build().apiInfo(apiInfo())
    }

    private fun apiInfo(): ApiInfo { // TODO: use not deprecated solution
        return ApiInfo("mvnRepo", "Collection of open source java libraries uploaded on maven repository.", "1.0", null, "", "", "")
    }
}

