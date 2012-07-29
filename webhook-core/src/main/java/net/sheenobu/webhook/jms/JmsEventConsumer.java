package net.sheenobu.webhook.jms;

import org.apache.camel.builder.RouteBuilder;

import net.sheenobu.webhook.EventConsumer;
import net.sheenobu.webhook.EventHandler;

public class JmsEventConsumer<T> extends RouteBuilder implements EventConsumer {

	private String eventName;
	private EventHandler<T> eventHandler;
	
	public void setEventHandler(EventHandler<T> eventHandler) {
		this.eventHandler = eventHandler;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	@Override
	public void configure() throws Exception {
		from("jms:events." + eventName)
			.bean(eventHandler);
	}

}
