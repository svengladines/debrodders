package be.occam.debrodders.application.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

import org.datanucleus.api.jpa.PersistenceProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import be.occam.utils.spring.configuration.ConfigurationProfiles;

@Configuration
public class DeBroddersApplicationConfigForDev {
	
	@Configuration
	@Profile({ConfigurationProfiles.DEV})
	static class UtilConfigForDev {
		
		@Bean
		String supportEmailAddress() {
			
			return "sven.gladines@gmail.com"; 
			
		}
		
	}
	
}