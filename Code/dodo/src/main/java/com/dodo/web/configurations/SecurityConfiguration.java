package com.dodo.web.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dodo.web.IServices.IUserService;
import com.dodo.web.handlers.CustomSuccessHandler;


//để spring tự động tìm configuration, ta cần annotation @Configuration
//để sử dụng security, ta cần annotation @EnableWebSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfiguration implements WebMvcConfigurer {
	@Autowired
	IUserService userService;
	
	@Autowired
    CustomSuccessHandler customSuccessHandler;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception  {
		httpSecurity
				.cors(cors -> cors.disable())
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/user/become-a-vendor/**").hasAnyRole("USER")
						.requestMatchers("/user/account/**").hasAnyRole("SHOPOWNER", "USER")
			            .requestMatchers("/admin/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
			            .requestMatchers("/shop-owners/**").hasAnyRole("SHOPOWNER")			          	            
			            .requestMatchers(
			            		"/"
			            		, "/login/**"
			            		, "/account/**"
			            		, "/users/**"
			            		, "/user/**"
			            		, "/jquery/**"
			            		, "/favicon.ico"
			            		, "/error/**"
			            		, "/upload/**"
			            		, "/admins/**").permitAll()
			            .anyRequest().authenticated()	            
		        )
				.formLogin(form -> form
						.loginPage("/login")
						.loginProcessingUrl("/login/processing-login")
						.usernameParameter("username")
						.passwordParameter("password")
						.successHandler(customSuccessHandler)
						.failureUrl("/login?error")
				)
				.logout(logout -> logout 
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login")
				)
				.exceptionHandling(exception -> exception
						.accessDeniedPage("/error/404")//khai báo đường link khi access denied
//						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				)
				;
		return httpSecurity.build();
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userService);
	}
}
