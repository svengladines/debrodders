package be.occam.debrodders.domain.people;

import java.util.UUID;

import javax.annotation.Resource;

import be.occam.debrodders.Match;
import be.occam.debrodders.match.MatchStatus;
import be.occam.debrodders.match.MatchStatus.Status;

public class MatchStatusManager {
	
	@Resource
	Popper popper;
	
	public MatchStatus create( String matchUuid ) {
		
		MatchStatus status
			= new MatchStatus();
		
		status.setUuid( UUID.randomUUID().toString() );
		status.setMatchUuid( matchUuid );
		status.setStatus( Status.TO_BE_STARTED );
		return status;
		
	}
	
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
	
}
