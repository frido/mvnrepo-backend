package frido.mvnrepo.backend

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

// TODO: upravit podla knihy
@ComponentScan(basePackages = arrayOf("frido.mvnrepo"))
@EnableAutoConfiguration(exclude = arrayOf(org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration::class/*, org.springframework.boot.autoconfigure.mongo.MongoDataAutoConfiguration::class*/))
@Configuration
open class AppController {

}

fun main(args: Array<String>){
    print("Hello World: ");
    SpringApplication.run(arrayOf(AppController::class.java), args);
}