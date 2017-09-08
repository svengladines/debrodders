package be.occam.debrodders.web.dto;

import java.util.Date;

import be.occam.debrodders.Team;
import be.occam.debrodders.match.MatchStatus;
import be.occam.debrodders.match.MatchStatus.Status;

public class MatchStatusDTO {
	
	protected TeamDTO homeTeam;
	protected TeamDTO visitorTeam; 
	protected String uuid;
	protected String matchUuid;
	protected Status status;
	protected int homeGoals;
	protected int visitorGoals;
	protected int minutes;
	protected Date lastWhistle;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getHomeGoals() {
		return homeGoals;
	}
	public void setHomeGoals(int homeGoals) {
		this.homeGoals = homeGoals;
	}
	public int getVisitorGoals() {
		return visitorGoals;
	}
	public void setVisitorGoals(int visitorGoals) {
		this.visitorGoals = visitorGoals;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public String getMatchUuid() {
		return matchUuid;
	}
	public void setMatchUuid(String matchUuid) {
		this.matchUuid = matchUuid;
	}
	public Date getLastWhistle() {
		return lastWhistle;
	}
	public void setLastWhistle(Date lastWhistle) {
		this.lastWhistle = lastWhistle;
	}
	
	public TeamDTO getHomeTeam() {
		return homeTeam;
	}
	public MatchStatusDTO setHomeTeam(TeamDTO homeTeam) {
		this.homeTeam = homeTeam;
		return this;
	}
	public TeamDTO getVisitorTeam() {
		return visitorTeam;
	}
	public MatchStatusDTO setVisitorTeam(TeamDTO visitorTeam) {
		this.visitorTeam = visitorTeam;
		return this;
	}
	public static MatchStatusDTO dto( MatchStatus f ) {
		
		MatchStatusDTO t
			= new MatchStatusDTO();
		t.setStatus( f.getStatus() );
		t.setMinutes( f.getMinutes() );
		
		return t;
		
	}
	
	public static MatchStatus status( MatchStatusDTO f ) {
		
		MatchStatus t
			= new MatchStatus();
		
		t.setStatus( f.getStatus() );
		t.setMinutes( f.getMinutes() );
		t.setHomeGoals( f.getHomeGoals() );
		t.setVisitorGoals( f.getVisitorGoals() );
		
		Team homeTeam
			= TeamDTO.team( f.getHomeTeam() );
		
		t.setHomeTeam( homeTeam );
		
		Team visitorTeam
			= TeamDTO.team( f.getVisitorTeam() );
	
		t.setVisitorTeam( visitorTeam );
		
		return t;
		
	}
	
}
