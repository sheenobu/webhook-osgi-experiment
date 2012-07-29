package net.sheenobu.webhook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.sheenobu.webhook.internal.BundleUtility;

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
public class WebhookService {

	private BundleContext bundleContext;
	private List<Bundle> bundles = new ArrayList<Bundle>();
	
	public WebhookService(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}
	
	public void start() {

	}
	
	public void stop() throws BundleException {
		
		for(Bundle b : bundles) {
			b.stop();
			b.uninstall();
		}
		
	}
	
	public void createWebhook(String accountName, String httpUri, String httpMethod, String event) throws BundleException, IOException
	{
		
		InputStream is = BundleUtility.createBundle();
		
		Bundle b = bundleContext.installBundle(
				String.format("webhook_%s_%s",accountName,event), 
				is);
		
		bundles.add(b);
		b.start();
		
		is.close();
	}
	
}
