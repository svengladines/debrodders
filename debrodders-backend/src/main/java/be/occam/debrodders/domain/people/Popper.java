package be.occam.debrodders.domain.people;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.occam.debrodders.domain.repository.JsonObject;
import be.occam.debrodders.domain.repository.JsonObjectRepository;

public class Popper {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );

	protected final ObjectMapper objectMapper;
	
	@Resource
	JsonObjectRepository jsonObjectRepository;

	public Popper() {
		
		this.objectMapper = new ObjectMapper();
		this.objectMapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );
		
	} 
	
	public <T> T pop( Class<T> tClz, String uuid ) {
		
		JsonObject jsonObject
			= this.jsonObjectRepository.findOneByUuid( uuid );
		
		String json
			= jsonObject.getJson();
	
		logger.info( "json = {}", json );
		
		T t
			= null;
	
		try {
	
			t = this.objectMapper.reader( tClz ).readValue( json );
		
		} catch (Exception e) {
			logger.warn( "d'oh", e );
		}
		
		return t;
		
	}

}
