package com.finance.shiro.token;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * 自定义UsernamePasswordTokem
 */
public class UsernamePasswordTokenModel implements HostAuthenticationToken, RememberMeAuthenticationToken {

    private String corpCode;//自定义corpCode

    private String username;

    private char[] password;

    private String cert;//证书

    private boolean rememberMe;
    private String host;



    public UsernamePasswordTokenModel(String username, char[] password){
        this((String)null,username, (char[])password,(String)null, false, (String)null);
    }
    public UsernamePasswordTokenModel(String username, String password){
        this((String)null,username, (char[])(password != null ? password.toCharArray() : null),(String)null, false, (String)null);
    }
    public UsernamePasswordTokenModel(String corpCode, String username, String password){
        this(corpCode,username, (char[])(password != null ? password.toCharArray() : null),(String)null, false, (String)null);
    }
    public UsernamePasswordTokenModel(String corpCode, String username, String password, String cert){
        this(corpCode,username, (char[])(password != null ? password.toCharArray() : null),cert, false, (String)null);
    }
    public UsernamePasswordTokenModel(String corpCode, String username, String password, String cert, boolean rememberMe){
        this(corpCode,username, (char[])(password != null ? password.toCharArray() : null),cert, rememberMe, (String)null);
    }
    public UsernamePasswordTokenModel(String corpCode, String username, String password, String cert, boolean rememberMe, String host){
        this((String)null,username, (char[])(password != null ? password.toCharArray() : null),(String)null, rememberMe, host);
    }


    public UsernamePasswordTokenModel(String corpCode, String username, char[] password, String cert, boolean rememberMe, String host){
            this.rememberMe = false;
            this.corpCode = corpCode;
            this.username = username;
            this.password = password;
            this.cert = cert;
            this.rememberMe = rememberMe;
            this.host = host;
    }


    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }


    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    //实现当前用户
    public Object getPrincipal() {
        return this.getUsername();
    }

    //当前密码
    public Object getCredentials() {
        return this.getPassword();
    }

}
