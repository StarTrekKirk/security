package feihu.audition.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerInfos {

	public static Map<String, Integer> accountCount = new ConcurrentHashMap<String, Integer>();

	public static AtomicInteger sessionCount = new AtomicInteger(0);
}
