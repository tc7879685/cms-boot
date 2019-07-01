package com.finance.shiro.authc;
 
import org.apache.shiro.authc.AuthenticationToken;

/**
 * 登录成功后返回
 * @Author tangc
 * @create 2018-07-12 15:19
 * @desc
 **/
public class JwtToken implements AuthenticationToken {
	
	private static final long serialVersionUID = 1L;
	private String token;
 
    public JwtToken(String token) {
        this.token = token;
    }
 
    @Override
    public Object getPrincipal() {
        return token;
    }
 
    @Override
    public Object getCredentials() {
        return token;
    }
}
