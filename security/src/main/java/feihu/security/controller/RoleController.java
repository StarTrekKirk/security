package feihu.security.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import feihu.security.component.Login;
import feihu.security.entity.Permission;
import feihu.security.entity.Role;
import feihu.security.service.FormParseService;
import feihu.security.service.ParamService;
import feihu.security.service.RoleService;

/**
 * 提供角色查看界面、角色增删改功能
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Controller
public class RoleController {

	@Autowired
	private RoleService rService;

	@Autowired
	private Login login;

	@Autowired
	private FormParseService fService;

	@Autowired
	private ParamService pService;

	@ModelAttribute
	public void preparePermission(Model model) {
		Map<String, Boolean> permissions = new HashMap<String, Boolean>(4);
		permissions.put("add", login.checkPermission(Permission.P_ADD_ROLE, false));
		permissions.put("remove", login.checkPermission(Permission.P_REMOVE_ROLE, false));
		permissions.put("update", login.checkPermission(Permission.P_UPDATE_ROLE, false));
		permissions.put("query", login.checkPermission(Permission.P_QUERY_ROLE, false));
		model.addAttribute("permissions", permissions);
	}

	@RequestMapping("/roles")
	public String getRoles(HttpServletRequest request, Model model) {
		login.checkPermission(Permission.P_QUERY_ROLE);
		List<Role> roles = rService.queryRoles();
		model.addAttribute("columns", new String[] { "ID", "角色名称", "权限", "创建时间" });
		List<Object[]> data = new ArrayList<Object[]>(roles.size());
		for (Role role : roles) {
			data.add(new Object[] { role.getId(), role.getName(), role.getPermission(), role.getCreatetime().toString() });
		}
		model.addAttribute("data", data);
		return "roles.ftl";
	}

	@RequestMapping("/role")
	public String getRole(@RequestParam(required = false) Integer id, Model model) {
		login.checkPermission(Permission.P_QUERY_ROLE);
		Role account = (id == null ? new Role() : rService.queryRole(id));
		List<Map<String, Object>> fields = Collections.emptyList();
		try {
			fields = fService.parseRole(account);
		}
		catch (Exception e) {
		}
		model.addAttribute("fields", fields);
		if (id == null) {
			model.addAttribute("location", "新增角色");
			model.addAttribute("title", "新增角色");
		}
		else {
			model.addAttribute("location", "编辑角色");
			model.addAttribute("title", "编辑角色");
		}
		return "role.ftl";
	}

	@ResponseBody
	@RequestMapping("/saveRole")
	public int saveRole(Role role) {
		int id = role.getId();
		if (id == 0) {//新增
			login.checkPermission(Permission.P_ADD_ROLE);
			return rService.addRole(role);
		}
		else {//更新
			login.checkPermission(Permission.P_UPDATE_ROLE);
			return rService.updateRole(role);
		}
	}

	@ResponseBody
	@RequestMapping("/deleteRole")
	public int deleteRole(@RequestParam String id) {
		List<Integer> ids = pService.getIds(id);
		if (id == null) {
			return 0;
		}
		login.checkPermission(Permission.P_REMOVE_ROLE);
		return rService.deleteRole(ids);
	}

}
