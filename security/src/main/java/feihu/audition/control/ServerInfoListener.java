package feihu.audition.control;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import feihu.audition.service.ServerInfos;

public class ServerInfoListener implements HttpSessionListener, HttpSessionAttributeListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServerInfos.sessionCount.incrementAndGet();
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		ServerInfos.sessionCount.decrementAndGet();
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		if (!"account".equals(se.getName())) {
			return;
		}
		String name = (String) se.getValue();
		Integer count = ServerInfos.accountCount.get(name);
		if (count == null) {
			ServerInfos.accountCount.put(name, 1);
		}
		else {
			ServerInfos.accountCount.put(name, count + 1);
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		if (!"account".equals(se.getName())) {
			return;
		}
		String name = (String) se.getValue();
		Integer count = ServerInfos.accountCount.get(name);
		if (count != null) {
			if (count == 1) {
				ServerInfos.accountCount.remove(name);
			}
			else {
				ServerInfos.accountCount.put(name, count - 1);
			}
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
	}

}
