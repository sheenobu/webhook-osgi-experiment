package net.sheenobu.webhook.internal;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sheenobu.webhook.internal.web.WebApplication;

import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.ext.servlet.ServerServlet;

public class WebInterface {

	HttpService http;
	
	public void start(HttpService httpService) throws ServletException, NamespaceException{
		
		http = httpService;
		
		ServerServlet servlet = new WebInterfaceServerServlet();
		httpService.registerServlet("/webhook", servlet, null,null);
	}
	
	public void stop() {
		http.unregister("/webhook");
	}
	
}

class WebInterfaceServerServlet extends ServerServlet {

	@Override
	protected Application createApplication(Context arg0) {
		return new WebApplication(arg0);
	}
	
}


