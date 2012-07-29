package net.sheenobu.webhook;

import java.util.Date;

public class Event {

	private final Date occurence;
	private final String type;

	public Event(Date occurence, String type) {
		super();
		this.occurence = occurence;
		this.type = type;
	}

	public Date getOccurence() {
		return occurence;
	}
	
	public String getType() {
		return type;
	}
	
}
