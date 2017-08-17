package be.occam.debrodders.match;

import java.util.Date;

public class MatchStatus {
	
	public static enum Status {
		NOT_YET_STARTED, FIRST_HALF, INTERMISSION, SECOND_HALF, FINISHED
	};
	
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
	
}