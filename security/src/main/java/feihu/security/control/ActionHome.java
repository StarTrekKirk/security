package feihu.security.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import feihu.security.component.Login;

/**
 * 系统主页
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Controller
public class ActionHome {

	@Autowired
	private Login login;

	@RequestMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("location", "主页");
		model.addAttribute("title", "主页");
		return "home.ftl";
	}

}
