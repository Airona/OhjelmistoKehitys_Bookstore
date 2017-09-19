package fi.swd.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

//import fi.swd.Bookstore.web.UserDetailServiceImpl;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailServiceImpl userDetailsService;	
//	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//        .authorizeRequests().antMatchers("/signup", "/saveuser").permitAll()
//        .and()
        .authorizeRequests().antMatchers("/css/**").permitAll()
        .and()
      .authorizeRequests()
    	.antMatchers("/").permitAll()
        .anyRequest().authenticated()
        .and()
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/list")
          .permitAll()
          .and()
      .logout()
          .permitAll();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth
    		.inMemoryAuthentication()
    			.withUser("user").password("pwd").roles("USER");
    	auth
		.inMemoryAuthentication()
			.withUser("admin").password("adminpwd").roles("ADMIN");
    	//        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}