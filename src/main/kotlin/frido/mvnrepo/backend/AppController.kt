package frido.mvnrepo.backend

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@ComponentScan
@EnableAutoConfiguration
class AppController {

}

fun main(args: Array<String>){
    print("Hello World");
    SpringApplication.run(arrayOf(AppController::class.java), args);
}