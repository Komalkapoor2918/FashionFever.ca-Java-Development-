package ca.sheridancollege.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/*SecurityConfig class*/

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	UserDetailsServiceImpl userDetailsService; 

	@Autowired
	private LoginAccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		//list protections from most secure to least secure
		http.authorizeRequests()
		.antMatchers("/admin/**").hasAnyRole("ADMIN","MEMBER") 
		.antMatchers("/member/**").hasAnyRole("MEMBER")
		
		.antMatchers(HttpMethod.POST,"/somePostURL").hasRole("SOMEROLE")
		.antMatchers(HttpMethod.POST,"/register").permitAll()

		//.antMatchers(HttpMethod.POST,"/somePostURL").hasRole("SOMEROLE")
		.antMatchers("/","/css/**","/images/**","/js/**","/**").permitAll()  //permission to all(always at end)
		.anyRequest().authenticated()
		.and()    ///from here its describing the custom login page
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout()    
		.invalidateHttpSession(true)   //clear session
		.clearAuthentication(true)		//clear cookies
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll()
		.and()    //access denied 
		.exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler);

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());

	}



}
