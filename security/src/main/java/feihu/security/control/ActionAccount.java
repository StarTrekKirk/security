package feihu.security.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import feihu.security.component.Login;
import feihu.security.entity.Account;
import feihu.security.entity.Permission;
import feihu.security.service.AccountService;
import feihu.security.service.FormParseService;
import feihu.security.service.ParamService;

/**
 * 提供用户查看界面、用户增删改功能
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Controller
public class ActionAccount {

	@Autowired
	private AccountService aService;

	@Autowired
	private Login login;

	@Autowired
	private FormParseService fService;

	@Autowired
	private ParamService pService;

	@ModelAttribute
	public void preparePermission(Model model) {
		Map<String, Boolean> permissions = new HashMap<String, Boolean>(4);
		permissions.put("add", login.checkPermission(Permission.P_ADD_USER, false));
		permissions.put("remove", login.checkPermission(Permission.P_REMOVE_USER, false));
		permissions.put("update", login.checkPermission(Permission.P_UPDATE_USER, false));
		permissions.put("query", login.checkPermission(Permission.P_QUERY_USER, false));
		model.addAttribute("permissions", permissions);
	}

	@RequestMapping("/accounts")
	public String getAccounts(Model model) {
		login.checkPermission(Permission.P_QUERY_USER);
		List<Account> accouts = aService.queryAccounts();
		model.addAttribute("columns", new String[] { "ID", "账户名", "部门", "账号状态", "创建时间" });
		List<Object[]> data = new ArrayList<Object[]>(accouts.size());
		for (Account account : accouts) {
			data.add(new Object[] { account.getId(), account.getName(), account.getDepartment(), account.isEnable(),
					account.getCreatetime().toString() });
		}
		model.addAttribute("data", data);
		return "accounts.ftl";
	}

	@RequestMapping("/account")
	public String getAccount(@RequestParam(required = false) Integer id, Model model) {
		login.checkPermission(Permission.P_QUERY_USER);
		Account account = (id == null ? new Account() : aService.queryAccount(id));
		List<Map<String, Object>> fields = Collections.emptyList();
		try {
			fields = fService.parseAccount(account);
		}
		catch (Exception e) {
		}
		model.addAttribute("fields", fields);
		if (id == null) {
			model.addAttribute("location", "新增用户");
			model.addAttribute("title", "新增用户");
		}
		else {
			model.addAttribute("location", "编辑用户");
			model.addAttribute("title", "编辑用户");
		}
		return "account.ftl";
	}

	@ResponseBody
	@RequestMapping("/saveAccount")
	public int saveAccount(Account account, String role) {
		List<Integer> roles = pService.getIds(role);
		int id = account.getId();
		if (id == 0) {//新增
			login.checkPermission(Permission.P_ADD_USER);
			return aService.addAccount(account, roles);
		}
		else {//更新
			login.checkPermission(Permission.P_UPDATE_USER);
			return aService.updateAccount(account, roles);
		}
	}

	@ResponseBody
	@RequestMapping("/deleteAccount")
	public int deleteAccount(@RequestParam String id) {
		List<Integer> ids = pService.getIds(id);
		if (id == null) {
			return 0;
		}
		login.checkPermission(Permission.P_REMOVE_USER);
		return aService.deleteAccount(ids);
	}
}
