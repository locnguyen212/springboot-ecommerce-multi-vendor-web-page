package com.dodo.api.configurations;

import static com.dodo.api.staticVariable.SecurityRequestMatcherUrl.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dodo.api.IServices.IUserService;
import com.dodo.api.filters.JwtAuthenticationFilter;


//để spring tự động tìm configuration, ta cần annotation @Configuration
//để sử dụng security, ta cần annotation @EnableWebSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfiguration implements WebMvcConfigurer {	
	@Autowired
	IUserService userService;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception  {
		httpSecurity
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(USER.getUrls()).hasAnyRole(USER.getRoles())
			            .requestMatchers(SHOPOWNER.getUrls()).hasAnyRole(SHOPOWNER.getRoles())
						.requestMatchers(SUPERADMIN.getUrls()).hasAnyRole(SUPERADMIN.getRoles())
			            .requestMatchers(ADMIN_SUPERADMIN.getUrls()).hasAnyRole(ADMIN_SUPERADMIN.getRoles())	
						.requestMatchers(USER_SHOPOWNER.getUrls()).hasAnyRole(USER_SHOPOWNER.getRoles())
			            .requestMatchers(ADMIN_SUPERADMIN_SHOPOWNER.getUrls()).hasAnyRole(ADMIN_SUPERADMIN_SHOPOWNER.getRoles())	
			            .requestMatchers(ADMIN_SUPERADMIN_SHOPOWNER_USER.getUrls()).hasAnyRole(ADMIN_SUPERADMIN_SHOPOWNER_USER.getRoles())
			            .requestMatchers(WHITE_LIST.getUrls()).permitAll()
			            .anyRequest().authenticated()	            
		        )
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
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
