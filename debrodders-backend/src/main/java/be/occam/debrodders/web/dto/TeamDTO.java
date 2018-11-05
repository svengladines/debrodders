package be.occam.debrodders.web.dto;

import be.occam.debrodders.Team;

public class TeamDTO {
	
	protected String name;

	public String getName() {
		return name;
	}

	public TeamDTO setName(String name) {
		this.name = name;
		return this;
	}

	public static Team team( TeamDTO f ) {
		
		Team t
			= new Team();
		t.setName( f.getName() );
		
		return t;
	}
	
}
