package com.lukehalan.myblog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandler deniedHandler;

    final DataSource ds;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Value("${spring.admin.username}")
    private String adminUsername;

    @Value("${spring.admin.username}")
    private String adminPassword;


    @Autowired
    public WebSecurityConfiguration(AccessDeniedHandler deniedHandler, DataSource ds) {
        this.deniedHandler = deniedHandler;
        this.ds = ds;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/home", "/registration", "/blog/**", "/post/**", "/error").permitAll()
                .antMatchers("/createComment/**", "/newPost/**", "/commentPost/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(deniedHandler)
                .and().headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(ds)
                .passwordEncoder(passwordEncoder());
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser(adminUsername).password(adminPassword).roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
