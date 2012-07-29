package net.sheenobu.webhook;

import java.util.Date;

public class Event<T> {

	private final Date occurence;
	private final String type;
	private final T body;
	
	public Event(Date occurence, String type, T body) {
		super();
		this.occurence = occurence;
		this.type = type;
		this.body = body;
	}

	public T getBody() {
		return body;
	}
	
	public Date getOccurence() {
		return occurence;
	}
	
	public String getType() {
		return type;
	}
	
}
