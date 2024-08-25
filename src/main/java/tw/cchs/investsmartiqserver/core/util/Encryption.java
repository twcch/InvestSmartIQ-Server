package tw.cchs.investsmartiqserver.core.util;

import org.springframework.util.DigestUtils;

public class Encryption {

    public String getHashPassword(String password) {

        return DigestUtils.md5DigestAsHex(password.getBytes());

    }

}
