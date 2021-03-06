package feihu.security.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import feihu.security.component.Login;
import feihu.security.service.AccountService;

/**
 * 提供账户登录注销功能
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Controller
public class LoginController {

	@Autowired
	private AccountService aService;

	@Autowired
	private Login login;

	@ResponseBody
	@RequestMapping("/login")
	public int login(HttpServletRequest req, @RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "password", required = false) String password) throws IOException {
		return login.login(name, password);
	}

	@RequestMapping("/logout")
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		resp.setCharacterEncoding("utf-8");
		PrintWriter writer = resp.getWriter();
		if (session == null) {
			writer.print("do not need logout");
		}
		else {
			session.invalidate();
			resp.sendRedirect(req.getContextPath() + "/resources/html/login.html");
		}
	}

}
