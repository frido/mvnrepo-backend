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
import org.springframework.beans.factory.annotation.Autowired

@Controller
@ComponentScan
@EnableAutoConfiguration(exclude = arrayOf(org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration::class/*, org.springframework.boot.autoconfigure.mongo.MongoDataAutoConfiguration::class*/))
class AppController {

    @Autowired
    lateinit var client: MongoClient

    @RequestMapping("test/")
    @ResponseBody
    fun test(): String {
        val db = client.getDatabase("repo")
        println("Connect to database successfully")
        return "ok"
    }

}

fun main(args: Array<String>){
    print("Hello World: ");
    SpringApplication.run(arrayOf(AppController::class.java), args);
}