package feihu.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 参数解析服务
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Service
public class ParamService {
	public List<Integer> getIds(String ids) {
		List<Integer> ids2 = new ArrayList<Integer>();
		if (ids != "") {
			for (String id : ids.split(";")) {
				ids2.add(Integer.parseInt(id));
			}
		}
		return ids2;
	}
}
