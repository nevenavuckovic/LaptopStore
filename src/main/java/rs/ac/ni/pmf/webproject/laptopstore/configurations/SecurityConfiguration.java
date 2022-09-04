package rs.ac.ni.pmf.webproject.laptopstore.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rs.ac.ni.pmf.webproject.laptopstore.models.Role;
import rs.ac.ni.pmf.webproject.laptopstore.services.impl.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Role[] roles = Role.values();
        final String ADMIN = roles[0].getCode();
        final String EMPLOYEE = roles[1].getCode();
        final String BUYER = roles[2].getCode();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user", "/user/", "/user/search*").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.DELETE, "/user/*").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.PUT, "/user/*").hasAuthority(ADMIN)
                .antMatchers(HttpMethod.POST, "/laptop/search*").hasAnyAuthority(ADMIN, EMPLOYEE, BUYER)
                .antMatchers(HttpMethod.GET, "/laptop/**").hasAnyAuthority(ADMIN, EMPLOYEE, BUYER)
                .antMatchers(HttpMethod.POST, "/laptop/**").hasAnyAuthority(ADMIN, EMPLOYEE)
                .antMatchers(HttpMethod.PUT, "/laptop/**").hasAnyAuthority(ADMIN, EMPLOYEE)
                .antMatchers(HttpMethod.POST, "/ordered-laptop", "/ordered-laptop/").hasAuthority(BUYER)
                .antMatchers(HttpMethod.PUT, "/ordered-laptop/**").hasAuthority(EMPLOYEE)
                .antMatchers("/ordered-laptop/**").hasAnyAuthority(ADMIN, EMPLOYEE)
                .antMatchers("/report/laptop", "/report/laptop/").hasAnyAuthority(ADMIN, EMPLOYEE)
                .antMatchers("/report/**").hasAuthority(ADMIN)
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/laptop").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.setContentType("application/json");

                    final String path = request.getMethod() + " " + request.getServletPath();

                    final Problem problem = Problem.create()
                            .withStatus(HttpStatus.FORBIDDEN)
                            .withTitle("You do not have rights to access the requested resource: " + path)
                            .withDetail(accessDeniedException.getMessage());

                    response.getOutputStream().println(objectMapper.writeValueAsString(problem));
                })
                .and()
                .httpBasic()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setContentType("application/json");

                    final Problem problem = Problem.create()
                            .withStatus(HttpStatus.UNAUTHORIZED)
                            .withTitle("Authentication failed")
                            .withDetail(authException.getMessage());

                    response.getOutputStream().println(objectMapper.writeValueAsString(problem));
                });
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                .antMatchers("/user/register", "/user/register/")
                .antMatchers("/user/forgotten-password", "/user/forgotten-password/")
                .antMatchers("/user/change-password/**")
                .antMatchers("/user/reset-password/**")
                .antMatchers("/api-docs", "/api-docs.yaml", "/api-docs/**");
    }


}
