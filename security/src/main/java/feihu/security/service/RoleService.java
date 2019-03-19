package feihu.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feihu.security.dao.RoleDao;
import feihu.security.entity.Role;

/**
 * 角色服务类，提供角色增删改查功能
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Service
public class RoleService {

	@Autowired
	private RoleDao dao;

	public Role queryRole(String name) {
		return dao.queryRole(name);
	}

	public Role queryRole(int id) {
		return dao.queryRoleById(id);
	}

	public int addRole(Role role) {
		return dao.addRole(role);
	}

	public int deleteRole(List<Integer> ids) {
		int cnt = 0;
		if (ids.size() == 1) {
			int id = ids.get(0);
			cnt = dao.deleteRole(id);
		}
		else {
			cnt = dao.deleteRoles(ids);
		}
		return cnt;
	}

	public int updateRole(Role role) {
		return dao.updateRole(role);
	}

	public List<Role> queryRoles() {
		return dao.queryRoles();
	}
}
