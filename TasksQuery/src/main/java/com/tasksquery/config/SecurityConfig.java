package com.tasksquery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception 
    {        
        auth.inMemoryAuthentication().withUser("admin")
        	.password(passwordEncoder().encode("123")).roles("ADMIN");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests()
				.antMatchers("/tasksQuery", "/welcome", "/createTask")
				.access("hasRole('ADMIN') or hasRole('ANONYMOUS')") 
		.antMatchers("/submitTasks").access("hasRole('ADMIN')")
		.and()
			.formLogin().loginPage("/loginPage")
			.defaultSuccessUrl("/tasksQuery")
			.failureUrl("/loginPage?error")
			.usernameParameter("username").passwordParameter("password")				
		.and()
			.logout().logoutSuccessUrl("/loginPage?logout"); 
        
        http.cors().and().csrf().disable();
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
