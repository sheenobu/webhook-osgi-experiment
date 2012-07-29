package net.sheenobu.webhook.karaf.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

@Command(scope = "webhook", name = "create", description="Create a webhook")
public class CreateCommand extends WebhookCommandBase {
	
    @Argument(index = 0, name = "account", 
    		description = "The account of the command", 
    		required = true,
    		multiValued = false)
    String account = null;
	
    @Override
    protected Object doExecute() throws Exception {
    	webhookService.createWebhook(account, "", "", "");
    	return null;
    }
    
}
