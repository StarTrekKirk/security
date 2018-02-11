package feihu.audition.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonIOException;

import feihu.audition.service.AccountService;
import feihu.audition.service.ServerInfos;

@Controller
public class ActionServerInfo {

	@Autowired
	private AccountService aService;

	@ResponseBody
	@RequestMapping("/serverinfo")
	public Object info(HttpServletRequest req) throws JsonIOException, IOException {
		Map<String, Object> infos = new HashMap<String, Object>();
		HttpSession session = req.getSession();
		if (session != null) {
			String account = (String) session.getAttribute("account");
			if (account != null)
				infos.put("user", account);
		}
		infos.put("sessionCount", ServerInfos.sessionCount);
		infos.put("userCount", ServerInfos.accountCount.size());
		infos.put("userCounts", ServerInfos.accountCount);
		return infos;
	}
}
