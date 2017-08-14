package be.occam.debrodders.application.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

import org.datanucleus.api.jpa.PersistenceProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import be.occam.debrodders.domain.people.Eventualist;
import be.occam.debrodders.domain.people.MailMan;
import be.occam.debrodders.domain.people.MatchStatusKeeper;
import be.occam.debrodders.domain.service.EventService;
import be.occam.debrodders.web.util.DataGuard;
import be.occam.debrodders.web.util.NoopGuard;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

@Configuration
public class DeBroddersApplicationConfig {
	
	final static Logger logger
		= LoggerFactory.getLogger( DeBroddersApplicationConfig.class );

	final static String BASE_PKG 
		= "be.occam.debrodders";
	
	static class propertiesConfigurer {
		
		@Bean
		@Scope("singleton")
		public static PropertySourcesPlaceholderConfigurer propertiesConfig() {
			return new PropertySourcesPlaceholderConfigurer();
		}
		
	}
	
	@Configuration
	@Profile({ConfigurationProfiles.PRODUCTION})
	static class DomainConfigForProduction {
		
		@Bean
		DataGuard dataGuard() {
			
			return new NoopGuard();
			
		}
		
		@Bean
		String acsiDigitaalEmailAddress() {
			
			return "sven.gladines@gmail.com"; 
			
		}
		
	}
	
	@Configuration
	@EnableTransactionManagement
	public static class DomainConfigShared {
		
		@Bean
		public MailMan mailMan() {
			return new MailMan();
		}
		
		@Bean
		public JavaMailSender javaMailSender () {
			
			JavaMailSenderImpl sender
				= new JavaMailSenderImpl();
			return sender;
			
		}
		
		@Bean
		public EventService eventService( MatchStatusKeeper matchStatusKeeper ) {
			
			EventService eventService 
				= new EventService( );
			
			return eventService;
			
		}
		
		@Bean
		Eventualist eventualist() {
			return new Eventualist();
		}
		
		/*
		@Bean
		FTPClient ftpClient( @Value("${ftp.user}") String ftpUser, @Value("${ftp.password}") String ftpPassword ) {  
			return new FTPClient( "ftp.debrodders.be", ftpUser, ftpPassword );
		}
		*/
		
	}
	
		@Configuration
		@Profile(ConfigurationProfiles.PRODUCTION)
		@EnableJpaRepositories(BASE_PKG)
		static class EntityManagerConfigForProduction {
			
			@Bean
			public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(PersistenceProvider persistenceProvider ) {
				
				LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
				factory.setPackagesToScan( BASE_PKG );
				factory.setPersistenceProvider( persistenceProvider );
				// factory.setDataSource(jpaDataSource);
				factory.setPersistenceUnitName("debrodders-backend");
				factory.getJpaPropertyMap().put( "datanucleus.jpa.addClassTransformer", "false" );
				factory.getJpaPropertyMap().put( "datanucleus.appengine.datastoreEnableXGTransactions", "true" );
				factory.getJpaPropertyMap().put( "datanucleus.metadata.allowXML", "false" );
				factory.afterPropertiesSet();
				return factory;
			}
			
			@Bean
			PersistenceProvider persistenceProvider() {
				
				PersistenceProviderImpl provider
					= new PersistenceProviderImpl();
				
				return provider;
				
			}

			@Bean
			public EntityManagerFactory entityManagerFactory(LocalContainerEntityManagerFactoryBean factory) {
				return factory.getObject();
			}

			@Bean
			public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
				return new PersistenceExceptionTranslationPostProcessor();
			}

			@Bean
			public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
				JpaTransactionManager transactionManager = new JpaTransactionManager();
				transactionManager.setEntityManagerFactory(entityManagerFactory);
				return transactionManager;
			}
			
			@Bean
			DataGuard dataGuard() {
				
				return new NoopGuard();
				
			}
			
		}

	
}