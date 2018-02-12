package feihu.security.dao;

import java.util.List;

import feihu.security.entity.Role;

/**
 * 角色Dao接口
 * @author heihuhu
 * @createdate 2018年2月12日
 */
public interface RoleDao {

	public Role queryRoleById(int id);

	public Role queryRole(String name);

	public int addRole(Role role);

	public int updateRole(Role role);

	public int deleteRole(int id);

	public int deleteRoles(List<Integer> ids);

	public List<Role> queryRoles();

}