package be.occam.debrodders.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import be.occam.debrodders.web.controller.api.EventsController;
import be.occam.debrodders.web.controller.api.MatchStatusesController;

@Configuration
@EnableWebMvc
public class MvcConfig {
	
	@Configuration
	public static class DispatcherConfig {
		
		@Bean
		public InternalResourceViewResolver internalResourceViewResolver() {
			InternalResourceViewResolver resolver
				= new InternalResourceViewResolver();
			resolver.setPrefix( "/WEB-INF/jsp/" );
			resolver.setSuffix( ".jsp" );
			return resolver;
		}
		
	}
	
	@Configuration
	public static class ControllerConfig {
		
		/*
		@Bean
		public EventsController eventsController() {
			
			return new EventsController();
			
		}
		*/
		
		@Bean
		public MatchStatusesController matchStatusesController() {
			
			return new MatchStatusesController();
			
		}
		
	}
	
	@Configuration
	public static class FormatConfig {
		
		@Bean
		DateFormatter dateFormatter() {
			
			DateFormatter dateFormatter
				= new DateFormatter();
			
			dateFormatter.setPattern("dd/MM/yyyy");
			
			return dateFormatter;
			
		}
	}

	/*
	@Bean
	MultipartResolver multipartResolver() {
		
		GMultipartResolver resolver
			= new GMultipartResolver();
		
		return resolver;
		
	}
	*/
	
}
