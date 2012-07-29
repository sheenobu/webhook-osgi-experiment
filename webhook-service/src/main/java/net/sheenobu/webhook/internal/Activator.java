package net.sheenobu.webhook.internal;

import java.io.IOException;
import java.util.Hashtable;

import net.sheenobu.webhook.WebhookService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	WebhookService webhookService;
	ServiceRegistration serviceRegistration;
	
    public void start(BundleContext context) throws BundleException, IOException {

    	webhookService = new WebhookService(context);
    	webhookService.start();
    	
    	serviceRegistration = context.registerService(WebhookService.class.getCanonicalName(), 
        		(Object) webhookService, 
        		new Hashtable<String,Object>());
    }

    public void stop(BundleContext context) throws BundleException {
    	webhookService.stop();
    	serviceRegistration.unregister();
    }

}