package frido.mvnrepo.backend

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer



// TODO: upravit podla knihy // TODO: Akej knihy
@SpringBootApplication
@ComponentScan(basePackages = arrayOf("frido.mvnrepo.backend"))
@EnableAutoConfiguration(exclude = arrayOf(org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration::class/*, org.springframework.boot.autoconfigure.mongo.MongoDataAutoConfiguration::class*/))
@Configuration
open class AppController {
    //companion object {
    //            @JvmStatic fun main(args: Array<String>) {
    //                print("Hello World1: ");
    //                SpringApplication.run(arrayOf(AppController::class.java), args);
    //            }
    //        }
    @Bean
    open fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurerAdapter() {
            override fun addCorsMappings(registry: CorsRegistry?) {
                registry!!.addMapping("/**").allowedOrigins("*")
            }
        }
    }
}

fun main(args: Array<String>){
    print("Hello World2: ");
    SpringApplication.run(arrayOf(AppController::class.java), args);
}