package company.priorify.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/projects.html").hasRole("USER")
                    .antMatchers("/project").hasRole("ADMIN")
                    .antMatchers("/project/{id}").hasRole("ADMIN")
                    .antMatchers("/projects").hasRole("USER")
                .and()
                    .formLogin()
                .defaultSuccessUrl("/projects.html", true);
                httpSecurity.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/index.html");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Admin").password("{noop}Admin").roles("ADMIN", "USER")
        .and()
                .withUser("User").password("{noop}User").roles("USER");
    }
}
