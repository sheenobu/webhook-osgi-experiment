package net.sheenobu.webhook.internal.web;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class FirstServerResource extends ServerResource {  


   @Get  
   public String toString() {  
      return "hello, world";  
   }

}  