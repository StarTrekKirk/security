package feihu.audition.dao;

import java.util.List;

import feihu.audition.entity.Role;

public interface RoleDao {

	public Role queryRoleById(int id);

	public Role queryRole(String name);

	public int addRole(Role role);

	public int updateRole(Role role);

	public int deleteRole(int id);

	public int deleteRoles(List<Integer> ids);

	public List<Role> queryRoles();

}