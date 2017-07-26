package frido.mvnrepo.backend

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
open class SwaggerConfig {

    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("frido.mvnrepo"))
                //.paths(PathSelectors.any())
                .paths(PathSelectors.regex("/api.*"))
                .build()
    }

    /*
    @Bean
    func api(): Docket { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build()  
        // return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("org.baeldung.web.controller")).paths(PathSelectors.ant("/foos/*")).build().apiInfo(apiInfo()).useDefaultResponseMessages(false)
        // .globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder().code(500).message("500 message").responseModel(new ModelRef("Error")).build(), new ResponseMessageBuilder().code(403).message("Forbidden!!!!!").build()));
    }
    
    func apiInfo(): ApiInfo {
        ApiInfo apiInfo = new ApiInfo("My REST API", "Some custom description of API.", "API TOS", "Terms of service", "myeaddress@company.com", "License of API", "API license URL");
        return apiInfo;
    }
    */
    */
}