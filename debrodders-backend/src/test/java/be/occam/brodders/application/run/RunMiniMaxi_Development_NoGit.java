package be.occam.minimaxi.application.run;

import org.junit.Test;

import be.occam.test.jtest.JTest;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

public class RunMiniMaxi_Development_NoGit extends JTest {
	
	public RunMiniMaxi_Development_NoGit() {
		super( "/", 8069, ConfigurationProfiles.DEV );
		
		System.setProperty( "ftp.user", "debrodders.be");
		System.setProperty( "ftp.password", "debrodders2230" );
		
	}
		
	@Test
	public void doesItSmoke() throws Exception {
		
		System.setSecurityManager( null );
		Thread.sleep( 10000000 );
		
	}

}
