package be.occam.debrodders.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import be.occam.debrodders.util.DataGuard;
import be.occam.debrodders.util.NoopGuard;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

@Configuration
public class DeBroddersGoogleConfig {
	
	@Configuration
	@EnableTransactionManagement
	@Profile(ConfigurationProfiles.PRODUCTION)
	static class UtilConfigForProduction {
		
		@Bean
		DataGuard dataGuard() {
			
			return new NoopGuard();
			
		}
		
	}
	
}
