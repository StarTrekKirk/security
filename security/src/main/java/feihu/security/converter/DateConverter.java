package feihu.security.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 当表单项中有Date字段时，spring不能将客户端提交的表单内容装在成实体对象，原因是无法转换Date字段
 * @author heihuhu
 * @createdate 2018年2月12日
 */
public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		return new Date();
	}

}
