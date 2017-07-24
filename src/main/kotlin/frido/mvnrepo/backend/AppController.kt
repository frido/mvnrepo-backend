package frido.mvnrepo.backend

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMapping
import com.mongodb.DB
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.web.bind.annotation.GetMapping


// TODO: upravit podla knihy
// TODO: hlada len v tomto subore, to je zle, ma hladat vsade
@RestController
@ComponentScan
@EnableAutoConfiguration(exclude = arrayOf(org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration::class/*, org.springframework.boot.autoconfigure.mongo.MongoDataAutoConfiguration::class*/))
//@Configuration
@EnableSwagger2
@EnableWebSecurity
@Api(value = "test")
open class AppController : WebSecurityConfigurerAdapter(false) {

    @Autowired
    lateinit var backend: MongoService

    @GetMapping("api/test")
    @ResponseBody
    @ApiOperation(value="test")
    open fun test(): String {
        return backend.getRepo("central").findFirst().orElse(Document()).toJson()
    }

    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("frido"))
                //.paths(PathSelectors.any())
                .paths(PathSelectors.regex("/*"))
                .build()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/**").permitAll()
            .anyRequest().permitAll();
    }


}

fun main(args: Array<String>){
    print("Hello World: ");
    SpringApplication.run(arrayOf(AppController::class.java), args);
}