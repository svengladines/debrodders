package be.occam.debrodders.application.run;

import org.junit.Test;

import be.occam.test.jtest.JTest;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

public class RunDeBrodders_Development extends JTest {
	
	public RunDeBrodders_Development() {
		super( "/", 8069, ConfigurationProfiles.DEV );
		
	}
		
	@Test
	public void doesItSmoke() throws Exception {
		
		System.setSecurityManager( null );
		Thread.sleep( 10000000 );
		
	}

}
