package be.occam.debrodders.domain.object;

import java.util.Date;

public class Match {
	
	protected Date kickOff;
	protected String homeTeam;
	protected String awayTeam;
	protected int homeGoals;
	protected int awayGoals;
	
	public Date getKickOff() {
		return kickOff;
	}
	
	public void setKickOff(Date kickOff) {
		this.kickOff = kickOff;
	}
	
	public String getHomeTeam() {
		return homeTeam;
	}
	
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	
	public String getAwayTeam() {
		return awayTeam;
	}
	
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(int homeGoals) {
		this.homeGoals = homeGoals;
	}

	public int getAwayGoals() {
		return awayGoals;
	}

	public void setAwayGoals(int awayGoals) {
		this.awayGoals = awayGoals;
	}
	
}
