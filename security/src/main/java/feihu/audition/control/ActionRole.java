package feihu.audition.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import feihu.audition.entity.Account;
import feihu.audition.entity.Permission;
import feihu.audition.entity.Role;
import feihu.audition.service.AuthService;
import feihu.audition.service.FormParseService;
import feihu.audition.service.ParamService;
import feihu.audition.service.RoleService;

@Controller
public class ActionRole {

	@Autowired
	private RoleService rService;

	@Autowired
	private Login login;

	@Autowired
	private AuthService aService;

	@Autowired
	private FormParseService fService;

	@Autowired
	private ParamService pService;

	@RequestMapping("/roles")
	public String getRoles(HttpServletRequest request, Model model) {
		Account user = login.getUser();
		if (!aService.hasPermission_user(user.getName(), Permission.P_QUERY_ROLE)) {
			return "";
		}
		List<Role> roles = rService.queryRoles();
		model.addAttribute("columns", new String[] { "ID", "角色名称", "权限", "创建时间" });
		List<Object[]> data = new ArrayList<Object[]>(roles.size());
		for (Role role : roles) {
			data.add(new Object[] { role.getId(), role.getName(), role.getPermission(), role.getCreatetime().toString() });
		}
		model.addAttribute("data", data);
		model.addAttribute("user", user.getName());
		return "roles.ftl";
	}

	@RequestMapping("/role")
	public String getRole(@RequestParam(required = false) Integer id, Model model) {
		Account user = login.getUser();
		if (!aService.hasPermission_user(user.getName(), Permission.P_QUERY_ROLE)) {
			return "";
		}
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
		Account user = login.getUser();
		if (!aService.hasPermission_user(user.getName(), Permission.P_ADD_ROLE)) {
			return 0;
		}
		int id = role.getId();
		if (id == 0) {//新增
			if (!aService.hasPermission_user(user.getName(), Permission.P_ADD_ROLE)) {
				return 0;
			}
			return rService.addRole(role);
		}
		else {//更新
			if (!aService.hasPermission_user(user.getName(), Permission.P_UPDATE_ROLE)) {
				return 0;
			}
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
		Account user = login.getUser();
		if (!aService.hasPermission_user(user.getName(), Permission.P_REMOVE_ROLE)) {
			return 0;
		}
		return rService.deleteRole(ids);
	}

}
