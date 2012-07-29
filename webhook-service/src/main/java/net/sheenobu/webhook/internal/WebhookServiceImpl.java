package net.sheenobu.webhook.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sheenobu.webhook.WebhookService;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * Service for performing operations on webhook 
 * bundles.
 * 
 * @author sheena
 *
 */
public class WebhookServiceImpl implements WebhookService {

	private BundleContext bundleContext;
	private Map<String,Bundle> bundles = new HashMap<String,Bundle>();
	private CamelContext camelContext;
	
	public WebhookServiceImpl(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}
	
	
	
	/* (non-Javadoc)
	 * @see net.sheenobu.webhook.WebhookService#getBundles()
	 */
	@Override
	public Map<String, Bundle> getBundles() { 
		return Collections.unmodifiableMap(bundles);
	}
	
	/* (non-Javadoc)
	 * @see net.sheenobu.webhook.WebhookService#getBundle(java.lang.String)
	 */
	@Override
	public Bundle getBundle(String id) 
	{
		return this.bundles.get(id);
	}
	
	
	/* (non-Javadoc)
	 * @see net.sheenobu.webhook.WebhookService#start()
	 */
	@Override
	public void start() throws Exception {
		camelContext = new DefaultCamelContext();
		
		ActiveMQComponent amq = new ActiveMQComponent();
		amq.setBrokerURL("tcp://localhost:61616");
		
		camelContext.addComponent("jms",amq);
		
		camelContext.start();
	}
	
	/* (non-Javadoc)
	 * @see net.sheenobu.webhook.WebhookService#stop()
	 */
	@Override
	public void stop() throws Exception {
		
		for(Bundle b : bundles.values()) {
			b.stop();
			b.uninstall();
		}
		
		camelContext.stop();
		
	}
	
	/* (non-Javadoc)
	 * @see net.sheenobu.webhook.WebhookService#createWebhook(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createWebhook(String accountName, String httpUri, String httpMethod, String event) throws BundleException, IOException
	{
		
		InputStream is = BundleUtility.createBundle();
		
		String id = String.format("webhook_%s_%s",accountName,event);
		
		Bundle b = bundleContext.installBundle(
				id, 
				is);
		
		bundles.put(id,b);
		b.start();
		
		is.close();
	}

	/* (non-Javadoc)
	 * @see net.sheenobu.webhook.WebhookService#callWebhook(java.lang.String)
	 */
	@Override
	public void callWebhook(String arg) {
		String event = (String) getBundle(arg).getHeaders().get("Webhook-Event");
		camelContext.createProducerTemplate().sendBody("jms:events." + event, "");
	}
	
}
