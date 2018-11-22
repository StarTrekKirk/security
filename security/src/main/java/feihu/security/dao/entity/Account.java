package feihu.security.dao.entity;

import java.io.Serializable;
import java.util.Date;

import feihu.security.tool.annotation.Field;
import feihu.security.tool.annotation.FieldType;
import feihu.security.tool.annotation.Form;

/**
 * 账户实体
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Form
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Field(type = FieldType.HIDDEN)
	private int id;

	@Field(caption = "账户名")
	private String name;

	@Field(caption = "密码")
	private String password;

	@Field(caption = "部门")
	private String department;

	@Field(type = FieldType.RADIO, caption = "账号状态", options = { "启用:true", "禁用:false" })
	private boolean enable;

	@Field(type = FieldType.HIDDEN, caption = "创建时间")
	private Date createtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createdate) {
		this.createtime = createdate;
	}

	@Override
	public String toString() {
		return "[id=" + id + ",name=" + name + ",enable=" + enable + "]";
	}

}
