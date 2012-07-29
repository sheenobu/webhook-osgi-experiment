package net.sheenobu.webhook;

/**
 * Interface for handling a webhook event.
 * @author sheena
 *
 */
public interface EventHandler<T> {

	public void handle(T data, Event description);
	
}
