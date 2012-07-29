package net.sheenobu.webhook.karaf.shell;

import java.util.Map.Entry;

import org.apache.felix.gogo.commands.Command;
import org.osgi.framework.Bundle;

@Command(scope = "webhook", name = "list", description="Lists the installed webhooks.")
public class ListCommand extends WebhookCommandBase {

    @Override
    protected Object doExecute() throws Exception {

    	System.out.println("ID - Description");
    	for(Entry<String, Bundle> b : webhookService.getBundles().entrySet())
    	{
    		System.out.println(
    				b.getKey() + " - " +
    				b.getValue().getHeaders().get("Bundle-Name")
    				);
    	}

    	return null;
    }
 
}
