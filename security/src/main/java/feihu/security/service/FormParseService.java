package feihu.security.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feihu.security.annotation.Form;
import feihu.security.entity.Account;
import feihu.security.entity.Permission;
import feihu.security.entity.Role;

/**
 * 表单内容解析服务
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Service
public class FormParseService {

	@Autowired
	private AuthService aService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private RoleService rService;

	private Map<String, Object> parseField(Field field, Object form) throws IllegalArgumentException,
			IllegalAccessException {
		feihu.security.annotation.Field annotation = field.getAnnotation(feihu.security.annotation.Field.class);
		if (annotation == null) {
			return null;
		}
		Map<String, Object> property = new HashMap<String, Object>(5);
		String name = annotation.name();
		String fName = field.getName();
		property.put("name", name.equals("") ? fName : name);
		String caption = annotation.caption();
		property.put("caption", caption.equals("") ? fName.toUpperCase() : caption);
		property.put("type", annotation.type().toString().toLowerCase());
		field.setAccessible(true);
		Object value = field.get(form);
		if (value instanceof Date) {
			value = ((Date) value).getTime();
		}
		property.put("options", annotation.options());
		property.put("value", value);
		return property;

	}

	public List<Map<String, Object>> parseForm(Object form) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = form.getClass();
		Form annotation = clazz.getAnnotation(Form.class);
		if (annotation == null) {
			return null;
		}
		Field[] fields = clazz.getDeclaredFields();
		List<Map<String, Object>> meta = new ArrayList<Map<String, Object>>(fields.length);
		for (Field field : fields) {
			Map<String, Object> property = parseField(field, form);
			if (property == null) {
				continue;
			}
			meta.add(property);
		}
		return meta;
	}

	public List<Map<String, Object>> parseAccount(Account account) throws IllegalArgumentException,
			IllegalAccessException {
		List<Map<String, Object>> meta = parseForm(account);
		Map<String, Object> property = new HashMap<String, Object>(5);
		property.put("name", "role");
		property.put("caption", "角色");
		property.put("type", "checkbox");
		List<Role> roles = rService.queryRoles();
		List<String> options = new ArrayList<String>(roles.size());
		for (Role role : roles) {
			options.add(role.getName() + ":" + role.getId());
		}
		property.put("options", options);
		List<Role> aRoles = accountService.queryRoles(account);
		List<Integer> value = new ArrayList<Integer>(aRoles.size());
		for (Role role : aRoles) {
			value.add(role.getId());
		}
		property.put("value", value);
		meta.add(property);
		return meta;
	}

	public List<Map<String, Object>> parseRole(Role role) throws IllegalArgumentException, IllegalAccessException {
		List<Map<String, Object>> meta = parseForm(role);
		Map<String, Object> property = new HashMap<String, Object>(5);
		property.put("name", "permission");
		property.put("caption", "权限");
		property.put("type", "checkbox");
		property.put("options", Permission.getgetPermissionOperatesOptions());
		Map<String, Integer> operates = Permission.getPermissionOperates();
		int permission = role.getPermission();
		List<Integer> permissions = new ArrayList<Integer>(10);
		for (Map.Entry<String, Integer> operate : operates.entrySet()) {
			Integer value = operate.getValue();
			if ((permission & value) == value) {
				permissions.add(value);
			}
		}
		property.put("value", permissions);
		meta.add(property);
		return meta;
	}
}
