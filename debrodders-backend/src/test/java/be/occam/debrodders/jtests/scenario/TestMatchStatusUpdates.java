package be.occam.debrodders.jtests.scenario;

import static be.occam.utils.spring.web.Client.postJSON;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import be.occam.debrodders.web.dto.MatchStatusDTO;
import be.occam.debrodders.web.dto.TeamDTO;
import be.occam.test.jtest.JTest;

public class TestMatchStatusUpdates extends JTest {
	
	public TestMatchStatusUpdates() {
		super("/debrodders-backend");
		
	}

	@Test
	public void doesItSmoke() {
		
		String url
			= new StringBuilder( this.baseAPIURL() ).append( "/match-statuses" ).toString(); 
		
		MatchStatusDTO status
			= new MatchStatusDTO();
		
		TeamDTO home
			= new TeamDTO( ).setName( "VV Rotselaar" );
		
		TeamDTO visitor
			= new TeamDTO().setName( "De Brodders" );
		
		status.setHomeTeam( home );
		status.setVisitorTeam( visitor );
		status.setHomeGoals( 2 );
		status.setVisitorGoals( 0 );
		
		ResponseEntity<MatchStatusDTO> postResponse
			= postJSON( url, status );
		
		assertEquals( HttpStatus.OK , postResponse.getStatusCode() );
		
	}

}
