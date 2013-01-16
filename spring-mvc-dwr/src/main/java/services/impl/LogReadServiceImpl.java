package services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import services.LogReadService;

/**
 * <p>
 * Read the log file Service.
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2013-01-16 7:09 AM
 * @since JDK 1.5
 */
@Service("logreadService")
@RemoteProxy(name = "logReadDwr")
public class LogReadServiceImpl implements LogReadService {

	/** Log folder. */
	@Value(value = "#{settings['logFolder']}")
	private String log_folder;

	public void setLog_folder(String log_folder) {
		this.log_folder = log_folder;
	}

	@Override
	@RemoteMethod
	public List<String> read_log(String log_name, int line) {

		try {
			List<String> lines = Files.readLines(new File(log_folder + File.separator + log_name + ".txt"), Charset.defaultCharset());
			return lines.subList(line, lines.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@RemoteMethod
	public String sayHello(String abc){
		return "汉字:"+abc;
	}


}
