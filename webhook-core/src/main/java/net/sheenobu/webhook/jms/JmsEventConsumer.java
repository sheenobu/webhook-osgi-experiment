package net.sheenobu.webhook.jms;

import net.sheenobu.webhook.EventConsumer;
import net.sheenobu.webhook.EventHandler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

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
		System.out.println("listening on jms:events." + eventName);
		from("jms:events." + eventName)
			.process(new Processor() {
				@Override
				public void process(Exchange arg0) throws Exception {
					System.out.println("got event " + eventName);
				}
			});
	}

}
