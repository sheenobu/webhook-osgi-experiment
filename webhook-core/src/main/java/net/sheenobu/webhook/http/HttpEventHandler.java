package net.sheenobu.webhook.http;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.sheenobu.webhook.Event;
import net.sheenobu.webhook.EventHandler;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;

public class HttpEventHandler<T> implements EventHandler<T>, CamelContextAware {

	private CamelContext camelContext;
	private ProducerTemplate producerTemplate;
	private String httpUri;
	private String httpMethod;
	
	@Override
	public void handle(Event<T> event) throws InterruptedException, ExecutionException, TimeoutException {

		Map<String, Object> headers = new HashMap<String, Object>();
		
		//build headers
		headers.put(Exchange.HTTP_METHOD,httpMethod);
		headers.put(Exchange.CONTENT_TYPE,"application/json");
	
		//TODO: check for SSL, cert specification/ignoring.
		//TODO: allow credentials in webhook.
		//TODO: convert the getBody to json

		
		//send body
		Future<String> results = producerTemplate.asyncRequestBodyAndHeaders(
				httpUri, 
				event.getBody().toString(), 
				headers,
				String.class);
		
		//wait 5 seconds for a result.
		String result = results.get(5000, TimeUnit.MILLISECONDS);
		
		//TODO: parse results
		
	}

	
	
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	
	public void setHttpUri(String httpUri) {
		this.httpUri = httpUri;
	}
	
	public CamelContext getCamelContext() {
		return camelContext;
	}
	
	public void setCamelContext(CamelContext camelContext) {
		this.camelContext = camelContext;
		producerTemplate = camelContext.createProducerTemplate();
	}
	

}
