package net.sheenobu.webhook.internal.web;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class WebApplication extends Application {

	public WebApplication(Context ctx) {
		super(ctx);
	}
	
	
	@Override
	public Restlet getInboundRoot() {
		Router router = new Router();
		router.attach("", FirstServerResource.class);
		return router;
	}
}
