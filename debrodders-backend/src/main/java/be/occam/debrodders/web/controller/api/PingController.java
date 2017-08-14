package be.occam.debrodders.web.controller.api;

import static be.occam.utils.spring.web.Controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/ping")
public class PingController {
	
	@RequestMapping(method=RequestMethod.GET) 
	public ResponseEntity<String> retrieve( ) {
		
		return response( "Pong", HttpStatus.OK );
		
	}
	
}
