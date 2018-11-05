package be.occam.debrodders.domain.people;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.debrodders.domain.object.Match;

public class Editor {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final ObjectMapper objectMapper;
	
	public Editor() {
		
		this.objectMapper = new ObjectMapper();
		
	}
	
	public String writeMatches( List<Match> matches ) {
		
		try {
			
			String json 
				= this.objectMapper.writeValueAsString( matches );
			
			return json;
			
		} catch (IOException e) {
			
			throw new RuntimeException( e );
			
		}
		
	}

}
