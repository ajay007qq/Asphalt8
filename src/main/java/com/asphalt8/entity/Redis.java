package com.asphalt8.entity;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Redis {

	private final String ip;
	private final int port;
	private final JedisPool jedisPool;

	public Redis(String ip, int port) {
		this.ip = ip;
		this.port = port;
		jedisPool = new JedisPool(ip, port);
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public Jedis getResource() {
		return jedisPool.getResource();
	}

}
