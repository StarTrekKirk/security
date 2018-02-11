package feihu.audition.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private Date birthday;

	private float money;

	private String password;

	public User() {
	}

	public User(int id, String name, Date birthday, float money) {
		this.setId(id);
		this.setName(name);
		this.setBirthday(birthday);
		this.setMoney(money);
	}

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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "[id=" + id + ",name=" + name + ",password=" + password + ",birthday=" + birthday + ",money=" + money
				+ "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}
		User user = (User) obj;
		return this.name.equals(user.getName());
	}

	@Override
	public int hashCode() {
		return this.id + this.name.hashCode();
	}

}
