package feihu.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import feihu.security.component.Login;
import feihu.security.entity.Permission;

/**
 * 提供权限列表查看界面
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Controller
public class PermissionController {

	@Autowired
	private Login login;

	@ModelAttribute
	public void preparePermission(Model model) {
		Map<String, Boolean> permissions = new HashMap<String, Boolean>(4);
		permissions.put("add", false);
		permissions.put("remove", false);
		permissions.put("update", false);
		permissions.put("query", true);
	}

	@RequestMapping("premissions")
	public String getPermissions(Model model) {
		login.checkPermission(Permission.P_QUERY_PERMISSION);
		model.addAttribute("columns", new String[] { "ID", "权限名称", "权限值" });
		Permission[] permissions = Permission.values();
		List<Object[]> data = new ArrayList<Object[]>(permissions.length);
		for (Permission permission : permissions) {
			data.add(new Object[] { permission.toString(), permission.getKey(), permission.getValue() });
		}
		model.addAttribute("data", data);
		return "premissions.ftl";
	}
}
