package net.sheenobu.webhook.internal;

import java.util.Hashtable;

import net.sheenobu.webhook.WebhookService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.http.HttpService;

public class Activator implements BundleActivator {

	WebhookService webhookService;
	ServiceRegistration serviceRegistration;
	WebInterface webInterface = new WebInterface();
	
    public void start(BundleContext context) throws Exception {

    	webhookService = new WebhookServiceImpl(context);
    	webhookService.start();
    	
    	serviceRegistration = context.registerService(WebhookService.class.getCanonicalName(), 
        		(Object) webhookService, 
        		new Hashtable<String,Object>());
    	
    	webInterface.start(
    		(HttpService)
    		context.getService(
    			context.getServiceReference(HttpService.class.getCanonicalName())));
    }

    public void stop(BundleContext context) throws Exception {
    	
    	webInterface.stop();
    	webhookService.stop();
    	serviceRegistration.unregister();
    }

}