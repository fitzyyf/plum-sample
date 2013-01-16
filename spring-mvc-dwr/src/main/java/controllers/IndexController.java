package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * .
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2013-01-16 8:41 AM
 * @since JDK 1.5
 */
@Controller
public class IndexController {

	@RequestMapping(value = "/index/{log_name}", method = RequestMethod.GET)
	public String index(Model model,@PathVariable String log_name) {
		model.addAttribute("log_name",log_name);
		return "index";
	}
}
