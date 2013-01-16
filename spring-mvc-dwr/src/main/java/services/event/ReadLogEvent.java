package services.event;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * Read Log Event.
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2013-01-16 3:23 PM
 * @since JDK 1.5
 */
public class ReadLogEvent extends ApplicationEvent {
	/**
	 * Create a new ApplicationEvent.
	 *
	 * @param source the component that published the event (never <code>null</code>)
	 */
	public ReadLogEvent(Object source) {
		super(source);
	}
}
