package be.occam.debrodders.web.controller.api;

import static be.occam.utils.spring.web.Controller.response;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import be.occam.debrodders.domain.service.MatchStatusService;
import be.occam.debrodders.web.dto.MatchStatusDTO;

@Controller
@RequestMapping(value="/match-statuses")
public class MatchStatusesController {
	
	private final Logger logger 
		= LoggerFactory.getLogger( MatchStatusesController.class );
	
	@Resource
	MatchStatusService matchSatusService;
	
	@RequestMapping( method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<MatchStatusDTO> get( WebRequest request ) {
		
		logger.info( "GET reveived" );
		
		return response( new MatchStatusDTO(), HttpStatus.OK );
			
	}
	
	@RequestMapping( method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_VALUE } )
	@ResponseBody
	public ResponseEntity<MatchStatusDTO> post( @RequestBody MatchStatusDTO matchStatusDTO, WebRequest request ) {
		
		logger.info( "Match status received: [{}]", matchStatusDTO );
		
		HttpHeaders httpHeaders
			= new HttpHeaders();

		httpHeaders.add("Access-Control-Allow-Origin", "*" ) ;
		httpHeaders.add("Access-Control-Allow-Methods", "GET,OPTIONS" );
		httpHeaders.add("Access-Control-Allow-Credentials","true");
		
		this.matchSatusService.guard().consume( matchStatusDTO );

		ResponseEntity<MatchStatusDTO> response
			= new ResponseEntity<MatchStatusDTO>( matchStatusDTO , httpHeaders, HttpStatus.OK );


		return response;

}

	@RequestMapping( value="/**", method = { RequestMethod.OPTIONS } )
	@ResponseBody
	public ResponseEntity<String> options() {

		logger.info( "options!" );
	
		HttpHeaders httpHeaders
			= new HttpHeaders();
		
		httpHeaders.add("Access-Control-Allow-Origin", "*" ) ;
		httpHeaders.add("Access-Control-Allow-Methods", "GET,OPTIONS,POST" );
		httpHeaders.add("Access-Control-Allow-Credentials","true");
		httpHeaders.add("Access-Control-Allow-Headers","Content-Type");
		
		ResponseEntity<String> response;
		
		response = new ResponseEntity<String>( "okelidokeli", httpHeaders, HttpStatus.OK );
		
		return response;
	}


	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleServiceException(IllegalArgumentException e, WebRequest webRequest ) {
		logger.warn("server error", e);
		return response( e.getMessage(), HttpStatus.BAD_REQUEST );
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleServiceException(Exception e, WebRequest webRequest ) {
		logger.warn("server error", e);
		return response( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
}
