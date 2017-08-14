package be.occam.debrodders.domain.people;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.debrodders.Event;
import be.occam.debrodders.SimpleEvent;

public class Eventualist {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final ObjectMapper objectMapper;
	
	public Eventualist() {
		
		this.objectMapper = new ObjectMapper();
		this.objectMapper.configure( org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false );
		
	} 
	
	public <S> SimpleEvent<S> simple(Event<String, String> event, Class<S> clz ) {
		
		String jsonPrimary
			= event.getPrimary();
	
		String jsonSecondary
			= event.getSecondary();
	
		logger.info( "primary json = {}", jsonPrimary );
		logger.info( "secondary json = {}", jsonSecondary );
		
		SimpleEvent<S> ev
			= null;
	
		try {
	
			S s 
				= this.objectMapper.reader( clz ).readValue( jsonPrimary );
			
			ev= new SimpleEvent( event.getType(), s );
			
		
		} catch (Exception e) {
			logger.warn( "d'oh", e );
		}
		
		return ev;
	}
	
	public <S,T> Event<S,T> base(Event<String, String> event, Class<S> primaryClz, Class<T> secondaryClz ) {
		
		String jsonPrimary
			= event.getPrimary();
	
		String jsonSecondary
			= event.getSecondary();
	
		logger.info( "primary json = {}", jsonPrimary );
		logger.info( "secondary json = {}", jsonSecondary );
		
		Event<S,T> ev
			= null;
	
		try {
	
			S s 
				= this.objectMapper.reader( primaryClz ).readValue( jsonPrimary );
			
			T t
				= this.objectMapper.reader( secondaryClz ).readValue( jsonSecondary );
			
			ev = new Event<S,T>( event.getType(), s, t );
			
		
		} catch (Exception e) {
			logger.warn( "d'oh", e );
		}
		
		return ev;
	}
	
}
