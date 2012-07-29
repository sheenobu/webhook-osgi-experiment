package net.sheenobu.webhook;

import java.io.IOException;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

public interface WebhookService {

	public abstract Map<String,Bundle> getBundles();

	public abstract Bundle getBundle(String id);

	public abstract void start() throws Exception;

	public abstract void stop() throws Exception;

	public abstract void createWebhook(String accountName, String httpUri,
			String httpMethod, String event) throws BundleException,
			IOException;

	public abstract void callWebhook(String arg);

}