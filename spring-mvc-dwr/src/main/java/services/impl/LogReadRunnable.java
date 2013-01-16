package services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import models.ReadLog;
import org.apache.commons.lang3.StringUtils;
import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 读取日志的线程，暴露给JS调用.
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2013-01-16 11:50 AM
 * @since JDK 1.5
 */
@Service("runable")
@RemoteProxy(name = "readlog")
public class LogReadRunnable implements Runnable {

	/** 缓存读取的信息 */
	private static final Map<String, ReadLog> READ_CONTENT_MAP = Maps.newHashMap();
	/** 读取文件队列 */
	private static final Set<String> READ_LOG = Sets.newHashSet();
	/** Log folder. */
	@Value(value = "#{settings['logFolder']}")
	private String log_folder;

	public LogReadRunnable() {
		final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		executor.scheduleAtFixedRate(this, 1, 1, TimeUnit.SECONDS);
	}

	/**
	 * 提供给其他的应用接口调用，暴露该接口，删除某个读取的日志
	 *
	 * @param log_name 日志文件
	 */
	public static void remove(String log_name) {
		READ_LOG.remove(log_name);
		READ_CONTENT_MAP.remove(log_name);
	}

	@Override
	public void run() {

		for (String run_log_name : READ_LOG) {
			final ReadLog log = READ_CONTENT_MAP.get(run_log_name);
			boolean status = log.isStatus();
			if (status) {
				List<String> lines;
				try {
					int line = log.getLine();
					lines = Files.readLines(new File(log_folder + File.separator + run_log_name + ".txt"), Charset.forName("UTF-8"));
					List<String> content_lines = lines.subList(line, lines.size());
					String output = (content_lines.size() < 1) ? null : StringUtils.join(content_lines, "<br/>");
					log.setContent(output);
					log.setLine(lines.size());
				} catch (IOException e) {
					String output = e.getMessage();
					log.setContent(output);
				}
				setLogContent();
			}
		}
	}

	/**
	 * 客户端调用,开启某个日志文件的读取。
	 *
	 * @param log_name 日志文件名称
	 */
	public synchronized void toggle(String log_name) {
		ReadLog readLog = READ_CONTENT_MAP.get(log_name);
		if (readLog == null) {
			final ReadLog log = new ReadLog();
			boolean active = log.isStatus();
			System.out.println("已启动");
			log.setStatus(active);
			log.setLine(0);
			log.setContent(null);
			READ_LOG.add(log_name);
			READ_CONTENT_MAP.put(log_name, log);
		}


	}

	/**
	 * 客户端调用，停止某个日志文件的读取
	 *
	 * @param log_name 日志文件
	 */
	public synchronized void stop(String log_name) {
		System.out.println("停止");
		READ_LOG.remove(log_name);
		READ_CONTENT_MAP.remove(log_name);
	}

	/** Actually alter the clients. */
	public void setLogContent() {

		for (final String read_login_name : READ_LOG) {
			final ReadLog readLog = READ_CONTENT_MAP.get(read_login_name);
			final String output = readLog.getContent();
			if (!Strings.isNullOrEmpty(output)) {

				String page = ServerContextFactory.get().getContextPath() + "/index/" + read_login_name;
				Browser.withPage(page, new Runnable() {
					public void run() {
						ScriptSessions.addFunctionCall("showContent", output);
					}
				});
				//清理已经输出的内容
				readLog.setContent(null);
			}
		}

	}

}
