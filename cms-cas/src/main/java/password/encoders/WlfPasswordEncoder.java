package password.encoders;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lizhi on 2018/3/30.
 */
public class WlfPasswordEncoder implements PasswordEncoder {
    private String salt="sadmin";

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String encode(CharSequence password) {
        if(password == null) {
            password = "";
        }

        if(salt != null && !"".equals(salt)) {
            password = password + "{" + salt.toString() + "}";
        }

        return md5hex(password.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if(encode(rawPassword).equals(encodedPassword)){
            return true;
        }
        return false;
    }
    public static String md5hex(String str) {
        return hex(md5(str));
    }
    public static String hex(byte[] obj) {
        StringBuffer rtn = new StringBuffer();

        for(int i = 0; i < obj.length; ++i) {
            String hex = Integer.toHexString(obj[i] & 255);
            if(hex.length() == 1) {
                rtn.append("0");
            }

            rtn.append(hex);
        }

        return rtn.toString();
    }
    public static byte[] md5(String str) {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(str.getBytes());
            return e.digest();
        } catch (NoSuchAlgorithmException var2) {
            var2.printStackTrace();
            return new byte[0];
        }
    }
}
