package be.occam.debrodders.application.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import be.occam.debrodders.domain.job.UpdateCalendarJob;
import be.occam.debrodders.domain.people.Editor;
import be.occam.debrodders.domain.people.Eventualist;
import be.occam.debrodders.domain.people.MailMan;
import be.occam.debrodders.domain.people.MatchStatusManager;
import be.occam.debrodders.domain.people.Publisher;
import be.occam.debrodders.domain.service.JobService;
import be.occam.debrodders.domain.service.MatchStatusService;
import be.occam.debrodders.util.DataGuard;
import be.occam.debrodders.util.DevGuard;
import be.occam.debrodders.util.NoopGuard;
import be.occam.utils.one.OneDotComClient;
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
	static class UtilConfigForProduction {
		
		@Bean
		String supportEmailAddress() {
			
			return "sven.gladines@gmail.com"; 
			
		}
		
	}
	
	@Configuration
	//@EnableTransactionManagement
	public static class ServiceConfigShared {
		
		/*
		@Bean
		public EventService eventService( MatchStatusKeeper matchStatusKeeper ) {
			
			EventService eventService 
				= new EventService( );
			
			return eventService;
			
		}
		@Bean
		public MatchStatusService matchStatusService( ) {
			
			MatchStatusService matchStatusService 
				= new MatchStatusService( );
			
			return matchStatusService;
			
		}
		*/
		
		@Bean
		public JobService jobService() {
			
			return new JobService();
			
		}
	}
		
	@Configuration
	public static class PeopleConfigShared {
			
		@Bean
		public MailMan mailMan() {
			return new MailMan();
		}
		
		/*
		@Bean
		Eventualist eventualist() {
			return new Eventualist();
		}
		
		@Bean
		MatchStatusManager matchStatusManager() {
			return new MatchStatusManager();
		}
		*/
		
		@Bean
		Editor editor() {
			return new Editor();
		}
		
		@Bean
		Publisher publisher() {
			return new Publisher();
		}
			
	}
	
	@Configuration
	public static class JobConfigShared {
		
		@Bean UpdateCalendarJob updateCalendarJob() {
			return new UpdateCalendarJob();
		}
		
	}
	
	@Configuration
	public static class UtilConfigShared {
		
		@Bean
		public JavaMailSender javaMailSender () {
			
			JavaMailSenderImpl sender
				= new JavaMailSenderImpl();
			return sender;
			
		}
		
		@Bean
		public OneDotComClient oneDotComClient( @Value("${one.domain}") String domain, @Value("${one.userName}") String user, @Value("${one.passWord}") String pw ) {
			return new OneDotComClient( domain, user, pw );
		}
		
		@Bean
		public ObjectMapper objectMapper() {
			return new ObjectMapper();
		}
		
	}
	
	
			
	
}