package frido.mvnrepo.backend

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MongoConfig {

    // TODO: seperate to more beans (MongoService)
    // TODO: mongo_url as property
    @Bean
    open fun getMongoClient(): MongoClient {
        val link = System.getenv("MONGO_URL")
        print(link)
        val uri = MongoClientURI(link)
        val client = MongoClient(uri)
        return client;
    }
}