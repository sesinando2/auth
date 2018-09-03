package net.dlcruz.auth

import net.dlcruz.auth.service.implementation.BootstrapService
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@SpringBootApplication
class AuthApplication {

	static void main(String[] args) {
		def context = SpringApplication.run AuthApplication, args

		BootstrapService bootstrapService = context.getBean('bootstrapService')
		bootstrapService.initialiseDefaults()
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		new BCryptPasswordEncoder(11)
	}
}
