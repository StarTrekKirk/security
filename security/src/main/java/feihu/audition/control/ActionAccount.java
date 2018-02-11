package feihu.audition.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import feihu.audition.entity.Account;
import feihu.audition.entity.Permission;
import feihu.audition.service.AccountService;
import feihu.audition.service.AuthService;
import feihu.audition.service.FormParseService;
import feihu.audition.service.ParamService;

@Controller
public class ActionAccount {

	@Autowired
	private AccountService accountService;

	@Autowired
	private AuthService aService;

	@Autowired
	private Login login;

	@Autowired
	private FormParseService fService;

	@Autowired
	private ParamService pService;

	@RequestMapping("/accounts")
	public String getAccounts(Model model) {
		Account user = login.getUser();
		if (!aService.hasPermission_user(user.getName(), Permission.P_QUERY_USER)) {
			return "";
		}
		List<Account> accouts = accountService.queryAccounts();
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
		Account user = login.getUser();
		if (!aService.hasPermission_user(user.getName(), Permission.P_QUERY_USER)) {
			return "";
		}
		Account account = (id == null ? new Account() : accountService.queryAccount(id));
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
		Account user = login.getUser();
		int id = account.getId();
		if (id == 0) {//新增
			if (!aService.hasPermission_user(user.getName(), Permission.P_ADD_USER)) {
				return 0;
			}
			return accountService.addAccount(account, roles);
		}
		else {//更新
			if (!aService.hasPermission_user(user.getName(), Permission.P_UPDATE_USER)) {
				return 0;
			}
			return accountService.updateAccount(account, roles);
		}
	}

	@ResponseBody
	@RequestMapping("/deleteAccount")
	public int deleteAccount(@RequestParam String id) {
		List<Integer> ids = pService.getIds(id);
		if (id == null) {
			return 0;
		}
		Account user = login.getUser();
		if (!aService.hasPermission_user(user.getName(), Permission.P_REMOVE_USER)) {
			return 0;
		}
		return accountService.deleteAccount(ids);
	}
}
