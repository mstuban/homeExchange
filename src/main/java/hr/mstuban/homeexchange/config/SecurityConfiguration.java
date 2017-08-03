package hr.mstuban.homeexchange.config;

import hr.mstuban.homeexchange.services.CustomUserDetailsService;
import hr.mstuban.homeexchange.services.MessageService;
import hr.mstuban.homeexchange.services.UserService;
import hr.mstuban.homeexchange.services.impl.MessageServiceImpl;
import hr.mstuban.homeexchange.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableGlobalAuthentication
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home", "/homes", "/home/new", "/profile", "/editProfile", "/home/edit", "/searchForHomes", "/searchForMyHomes", "/message/*", "/message/delete", "/message/new", "/upload")
                .fullyAuthenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/?loginSuccess=true")
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling()
                .and()
                .csrf()
                .and().rememberMe().tokenValiditySeconds(1209600);
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MessageService messageService() {
        return new MessageServiceImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

}