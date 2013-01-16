package services.impl;

import models.ReadLog;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import services.event.ReadLogEvent;

/**
 * <p>
 * .
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2013-01-16 3:24 PM
 * @since JDK 1.5
 */
public class ReadLogService implements ApplicationContextAware {
	private ApplicationContext ctx;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
	}


	public void sendMessage(ReadLog log) {
		//发布事件
		ctx.publishEvent(new ReadLogEvent(log));
	}
}
