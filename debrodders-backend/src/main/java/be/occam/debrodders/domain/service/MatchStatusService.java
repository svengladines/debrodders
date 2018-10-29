package be.occam.debrodders.domain.service;

import static be.occam.utils.spring.web.Controller.response;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import be.occam.debrodders.domain.people.MatchStatusManager;
import be.occam.debrodders.match.MatchStatus;
import be.occam.debrodders.util.DataGuard;
import be.occam.debrodders.web.dto.MatchStatusDTO;

public class MatchStatusService {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	MatchStatusManager matchStatusManager;
	
	@Resource
	DataGuard dataGuard;
	
	public MatchStatusService() {
	}
	
	@Transactional( readOnly=false )
	public ResponseEntity<MatchStatusDTO> consume( MatchStatusDTO matchStatusDTO ) {
		
		logger.debug( "consume [{}]", matchStatusDTO );

		MatchStatus matchStatus 
			= MatchStatusDTO.status( matchStatusDTO );
			
		matchStatus = matchStatusManager.update( matchStatus );
		
		return response( matchStatusDTO, HttpStatus.OK );
			
	}
	
	public MatchStatusService guard() {
		this.dataGuard.guard();
		return this;
	}

}
