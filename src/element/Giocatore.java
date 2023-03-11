package element;

import java.io.Serializable;

public class Giocatore implements Serializable{
	
	private static final long serialVersionUID = 3096689762046491744L;
	
	private int currPos;  
	private int lastPos;  
	private int cardinal;
	private int lastScore;
	private int roundsToWait;
	private boolean noStopCard;
	
	public Giocatore() {
		currPos=1;
		noStopCard=false;
		lastPos=0;
		roundsToWait=0;
	}
	
	public void reset() {
		currPos=1;
		noStopCard=false;
		lastPos=0;
		roundsToWait=0;
	}
	
	public int lancioDadi() {
		int ret=Dadi.INSTANCE.lancioDadi(this);
		this.setLastScore(ret);
		return ret;
	}
	
	public boolean hasNoStopCard() {return noStopCard;}
	
	public void noStopCardTaken() {noStopCard=true;}
	
	public void noStopCardConsumed() {
		if(!hasNoStopCard())
			throw new IllegalStateException("Il giocatore non ha carte di tipo SOSTA!");
		noStopCard=false;
	}
	
	public void moveTo(int pos) {
		lastPos=currPos;
		currPos=pos;
	}
	
	public void moveForward(int k) {
		lastPos=currPos;
		currPos+=k;
	}
	
	public void moveBackward(int k) {
		lastPos=currPos;
		currPos-=k;
	}

	public int getCurrPos() {
		return currPos;
	}

	public void setCurrPos(int currPos) {
		this.currPos = currPos;
	}

	public int getLastPos() {
		return lastPos;
	}

	public void setLastPos(int lastPos) {
		this.lastPos = lastPos;
	}

	public int getCardinal() {
		return cardinal;
	}

	public void setCardinal(int cardinal) {
		this.cardinal = cardinal;
	}

	public int getLastScore() {
		return lastScore;
	}

	public void setLastScore(int lastScore) {
		this.lastScore = lastScore;
	}

	public int getRoundsToWait() {
		return roundsToWait;
	}

	public void setRoundsToWait(int roundsToWait) {
		this.roundsToWait = roundsToWait;
	}

	public boolean isNoStopCard() {
		return noStopCard;
	}

	public void setNoStopCard(boolean noStopCard) {
		this.noStopCard = noStopCard;
	}
	
	@Override
	public String toString() {
		return "Giocatore " + cardinal;
	}

}
