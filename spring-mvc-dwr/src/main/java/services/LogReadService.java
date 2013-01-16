package services;

import java.util.List;

/**
 * <p>
 * Read the log file.
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2013-01-16 7:07 AM
 * @since JDK 1.5
 */
public interface LogReadService {

	/**
	 * Read the log file named <code>log_name</code>
	 *
	 * @param log_name log file name.
	 * @param line     read line.
	 * @return log file content.
	 */
	List<String> read_log(String log_name, int line);

	String sayHello(String abc);
}
