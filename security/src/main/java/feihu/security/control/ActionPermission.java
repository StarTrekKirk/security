package feihu.security.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import feihu.security.component.Login;
import feihu.security.entity.Account;
import feihu.security.entity.Permission;
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

	@Autowired
	private Login login;

	@RequestMapping("premissions")
	public String getPermissions(Model model) {
		Account user = login.getUser();
		if (!aService.hasPermission_user(user.getName(), Permission.P_QUERY_PERMISSION)) {
			return "";
		}
		Map<String, Integer> permissionOperates = Permission.getPermissionOperates();
		model.addAttribute("columns", new String[] { "权限名称", "权限值" });
		List<Object[]> data = new ArrayList<Object[]>(permissionOperates.size());
		for (Map.Entry<String, Integer> entry : permissionOperates.entrySet()) {
			data.add(new Object[] { entry.getKey(), entry.getValue() });
		}
		model.addAttribute("data", data);
		return "premissions.ftl";
	}
}
