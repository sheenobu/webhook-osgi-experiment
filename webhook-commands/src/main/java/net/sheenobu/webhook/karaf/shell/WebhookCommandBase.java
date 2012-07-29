package net.sheenobu.webhook.karaf.shell;

import net.sheenobu.webhook.WebhookService;

import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.ServiceReference;

public abstract class WebhookCommandBase extends OsgiCommandSupport{

	protected WebhookService webhookService;
	

	public void setWebhookService(WebhookService webhookService) {
		this.webhookService = webhookService;
	}
  
}
