package luqmanmohammad.U2D10SpringBootCompanydevices.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


//1. start from SecurityConfig as a class that return SecurityFilterChain
//SecurityFilterChain want as a parameter HttpSecurity as a object and HttpSecurity permit 
//to configure the security so i can authorize or refuse configuration with a lambda expression
//.authenticated mean authentification for using, in reality in default everything need authentification
//example: all the enpoint /auth and with this /** it mean also subsequences 
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(c -> c.disable());
		http.csrf(c -> c.disable());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/employee/**").authenticated());
		
		//disactivated session because we are using in this case JWT so whit stateless it mean without state/session 
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); 

		return http.build(); // when you finish all the configuration settings is important return http.build
	}
}