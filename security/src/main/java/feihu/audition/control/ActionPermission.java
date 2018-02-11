package feihu.audition.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import feihu.audition.service.AuthService;

@Controller
public class ActionPermission {

	@Autowired
	private AuthService aService;

	@RequestMapping("premissions")
	public String getPermissions(Model model) {
		return "premissions.ftl";
	}
}
