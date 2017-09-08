package be.occam.debrodders.domain.people;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.debrodders.match.MatchStatus;
import be.occam.utils.one.OneDotComClient;

public class MatchStatusManager {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	ObjectMapper objectMapper;
	
	@Resource
	OneDotComClient oneDotComClient;
	
	public MatchStatus update( MatchStatus matchStatus ) {
		
		try {
			
			String json
				= this.objectMapper.writeValueAsString( matchStatus );
			
			String path
				= "/svekke/match-status.json";
			
			this.oneDotComClient.store( path, json );
			
		}
		catch( Exception e ) {
			logger.warn( "failed to store json", e );
		}
		
		return matchStatus;
		
	}
	
	/*
	public MatchStatus findOneByUuid( String uuid ) {
		
		MatchStatus status
			= this.popper.pop( MatchStatus.class , uuid );
		
		return status;
		
	}
	
	public MatchStatus findOneByMatchUuid( String uuid ) {
		
		MatchStatus status
			= this.popper.pop( MatchStatus.class , uuid );
		
		return status;
		
	}
	*/
	
}
