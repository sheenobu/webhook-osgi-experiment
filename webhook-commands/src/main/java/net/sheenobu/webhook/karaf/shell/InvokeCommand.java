package net.sheenobu.webhook.karaf.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

@Command(scope = "webhook", name = "call", description="Calls a webhook")
public class InvokeCommand extends WebhookCommandBase {
	
    @Argument(index = 0, name = "webhook", 
    		description = "The webhook to call", 
    		required = true,
    		multiValued = false)
    String arg = null;
	
    @Override
    protected Object doExecute() throws Exception {
    	webhookService.callWebhook(arg);
    	return null;
    }
    
}
