package be.occam.debrodders.web.dto;

import java.util.List;

public class MatchStatusDTO {
	
	protected int minute;
	protected TeamDTO brodders;
	protected TeamDTO opponent;
	protected int brodderScore;
	protected int opponentScore;
	protected boolean homeGame;
	protected boolean inProgress;
	
	protected List<EventDTO> brodderEvents;
	protected List<EventDTO> opponentEvents;
	
	public int getMinute() {
		return minute;
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public TeamDTO getBrodders() {
		return brodders;
	}
	
	public void setBrodders(TeamDTO brodders) {
		this.brodders = brodders;
	}
	
	public TeamDTO getOpponent() {
		return opponent;
	}
	
	public void setOpponent(TeamDTO opponent) {
		this.opponent = opponent;
	}
	
	public int getBrodderScore() {
		return brodderScore;
	}
	
	public void setBrodderScore(int brodderScore) {
		this.brodderScore = brodderScore;
	}
	
	public int getOpponentScore() {
		return opponentScore;
	}
	
	public void setOpponentScore(int opponentScore) {
		this.opponentScore = opponentScore;
	}
	
	public boolean isHomeGame() {
		return homeGame;
	}
	
	public void setHomeGame(boolean homeGame) {
		this.homeGame = homeGame;
	}
	
	public List<EventDTO> getBrodderEvents() {
		return brodderEvents;
	}
	
	public void setBrodderEvents(List<EventDTO> brodderEvents) {
		this.brodderEvents = brodderEvents;
	}
	
	public List<EventDTO> getOpponentEvents() {
		return opponentEvents;
	}
	
	public void setOpponentEvents(List<EventDTO> opponentEvents) {
		this.opponentEvents = opponentEvents;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}
	

}
