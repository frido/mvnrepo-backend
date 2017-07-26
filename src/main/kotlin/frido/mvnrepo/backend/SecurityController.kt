package frido.mvnrepo.backend

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity

@Configuration
@EnableWebSecurity
open class SecurityController : WebSecurityConfigurerAdapter(false) {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().permitAll();
    }


}