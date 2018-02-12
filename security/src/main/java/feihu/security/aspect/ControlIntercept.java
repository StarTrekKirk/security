package feihu.security.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import feihu.security.component.Login;
import feihu.security.entity.Account;

/**
 * 用于给controller添加公共的model属性
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Aspect
@Service
public class ControlIntercept {

	@Autowired
	private Login login;

	@Pointcut("execution(* feihu.security.control.Action*.*(..))")
	public void execute() {
	}

	@Before("execute()")
	public void exeBefore(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		Model model = null;
		for (Object arg : args) {
			if (arg instanceof Model) {
				model = (Model) arg;
				break;
			}
		}
		if (model == null) {
			return;
		}
		Account user = login.getUser();
		if (user != null) {
			model.addAttribute("user", user.getName());
		}
		model.addAttribute("ctx", "/app");
	}
}
