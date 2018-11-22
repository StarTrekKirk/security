package feihu.security.dao.entity;

import java.io.Serializable;
import java.util.Date;

import feihu.security.tool.annotation.Field;
import feihu.security.tool.annotation.FieldType;
import feihu.security.tool.annotation.Form;

/**
 * 角色实体
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Form
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Field(type = FieldType.HIDDEN)
	private int id;

	@Field(caption = "角色名")
	private String name;

	@Field(type = FieldType.HIDDEN, caption = "创建时间")
	private Date createtime;

	private int permission;

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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "[id=" + id + ",name=" + name + ",permission=" + permission + "]";
	}
}
