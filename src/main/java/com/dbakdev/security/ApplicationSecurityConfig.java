package com.dbakdev.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig{

	private final PasswordEncoder passwordEncoder;

    ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        		.csrf().disable() 
                .authorizeHttpRequests()
                .antMatchers("/",  "index", "/css/*", "/js/*" ).permitAll()
				.antMatchers("/api/**").hasRole(ApplicationUserRole.ADMIN.name())
				.antMatchers(HttpMethod.GET, "/management/apt/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name())
				.antMatchers(HttpMethod.POST, "/management/apt/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name())
				.antMatchers(HttpMethod.DELETE, "/management/apt/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name())
				.antMatchers(HttpMethod.PUT, "/management/apt/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name())
				.antMatchers("/management/apt/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMIN_TRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(withDefaults());
        return http.build();
	}
    
    @Bean
    protected UserDetailsService userDetailsService() {
    	// This is how you will retrieve users from the database
    UserDetails dilipUser = 	User.builder()
								    		.username("dilipparajuli")
								    		.password(passwordEncoder.encode("password"))
								    		.roles(ApplicationUserRole.STUDENT.name())  //ROLE STUDENT
								    		.build();
    // Create another user
		   UserDetails rojiUser =  User.builder()
									    		.username("rojidhimal")
									    		.password(passwordEncoder.encode("password1234"))
									    		.roles(ApplicationUserRole.ADMIN.name())
									    		.build();
	   // Admin trainee with limited permission
		UserDetails sisanUser = User.builder().username("sisanparajuli")
				.password(passwordEncoder.encode("sisan1234"))
				.roles(ApplicationUserRole.ADMIN_TRAINEE.name())
				.build();
		   
    	return new InMemoryUserDetailsManager(
    				rojiUser, dilipUser, sisanUser
    			);
    }



}
