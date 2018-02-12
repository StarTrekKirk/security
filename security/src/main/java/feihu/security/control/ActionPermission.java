package feihu.security.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import feihu.security.service.AuthService;

/**
 * 提供权限列表查看界面
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Controller
public class ActionPermission {

	@Autowired
	private AuthService aService;

	@RequestMapping("premissions")
	public String getPermissions(Model model) {
		return "premissions.ftl";
	}
}
