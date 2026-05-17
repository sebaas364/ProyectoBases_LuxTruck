package co.edu.unbosque.backLuxtruck.security;


import org.apache.commons.codec.digest.DigestUtils;

public class SecurityConfig {

	
	public SecurityConfig() {
		// TODO Auto-generated constructor stub
	}
	
	public  String hashingToSHA256(String content) {
		return DigestUtils.sha256Hex(content);
	}
}
