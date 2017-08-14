package be.occam.debrodders.domain.people;

import java.util.UUID;

import javax.annotation.Resource;

import be.occam.debrodders.Match;
import be.occam.debrodders.match.MatchStatus;

public class MatchStatusManager {
	
	@Resource
	Popper popper;
	
	public MatchStatus create( Match m ) {
		
		MatchStatus status
			= new MatchStatus();
		
		status.setUuid( UUID.randomUUID().toString() );
		
		return status;
		
	}
	
	public MatchStatus findOneByUuid( String uuid ) {
		
		MatchStatus status
			= this.popper.pop( MatchStatus.class , uuid );
		
		return status;
		
	}
	
}
