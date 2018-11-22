package feihu.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import feihu.security.component.Login;
import feihu.security.dao.entity.Permission;

/**
 * 系统主页
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Controller
public class HomeController {

	@Autowired
	private Login login;

	@RequestMapping("/home")
	public String getHome(Model model) {
		Map<String, Boolean> permissions = new HashMap<String, Boolean>(4);
		permissions.put("queryuser", login.checkPermission(Permission.P_QUERY_USER, false));
		permissions.put("queryrole", login.checkPermission(Permission.P_QUERY_ROLE, false));
		permissions.put("querypermission", login.checkPermission(Permission.P_QUERY_PERMISSION, false));
		model.addAttribute("permissions", permissions);
		return "home.ftl";
	}

}
