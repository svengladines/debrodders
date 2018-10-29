package be.occam.debrodders.application.run;

import static be.occam.utils.spring.web.Client.postJSON;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import be.occam.debrodders.match.MatchStatus.Status;
import be.occam.debrodders.web.dto.MatchStatusDTO;
import be.occam.debrodders.web.dto.TeamDTO;
import be.occam.test.jtest.JTest;

public class DoMatchStatusUpdate extends JTest {
	
	public DoMatchStatusUpdate() {
		super("/debrodders-backend");
		
	}

	@Test
	public void doesItSmoke() {
		
		String url
			= new StringBuilder( this.baseAPIURL() ).append( "/match-statuses" ).toString(); 
		
		MatchStatusDTO status
			= new MatchStatusDTO();
		
		TeamDTO home
			= new TeamDTO( ).setName( "De Brodders" );
		
		TeamDTO visitor
			= new TeamDTO().setName( "VC Torenploeg" );
		
		status.setHomeTeam( home );
		status.setVisitorTeam( visitor );
		status.setHomeGoals( 3 );
		status.setVisitorGoals( 1 );
		status.setStatus( Status.NOT_YET_STARTED );
		status.setAnnouncement( "De Brodders are on a roll!" );
		
		ResponseEntity<MatchStatusDTO> postResponse
			= postJSON( url, status );
		
		assertEquals( HttpStatus.OK , postResponse.getStatusCode() );
		
	}

}
