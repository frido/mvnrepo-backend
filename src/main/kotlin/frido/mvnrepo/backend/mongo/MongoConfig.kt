package frido.mvnrepo.backend.mongo

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoDatabase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MongoConfig {

    @Value("\${mongo_url}")
    lateinit private var link: String

    @Bean
    open fun getMongoClientURI(): MongoClientURI = MongoClientURI(link)

    @Bean
    open fun getMongoClient(): MongoClient = MongoClient(getMongoClientURI())

    @Bean
    open fun getMongoDatabase(): MongoDatabase = getMongoClient().getDatabase(getMongoClientURI().database)
}