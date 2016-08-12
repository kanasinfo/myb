package com.myb.portal.util;

import com.myb.portal.model.account.MybAccount;

public abstract class PasswordUtil {
	public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;
    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    public static void entryptPassword(MybAccount account) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        account.setLoginPwdSalt(Encodes.encodeHex(salt));
        byte[] hashPassword = Digests.sha1(account.getLoginPass().getBytes(), salt, HASH_INTERATIONS);
        account.setLoginPass(Encodes.encodeHex(hashPassword));
    }
    public static String entryptPassword(String pwd, String salt) {
        byte[] hashPassword = Digests.sha1(pwd.getBytes(), Encodes.decodeHex(salt), HASH_INTERATIONS);
        return Encodes.encodeHex(hashPassword);
    }
    public static void main(String[] args) {
//    	PasswordUtil passwordUtil = new PasswordUtil();
//    	MybAccount mybAccount = new MybAccount();
//    	mybAccount.setLoginPass("111");
//    	passwordUtil.entryptPassword(mybAccount);
//    	System.out.println(mybAccount.getLoginPass());
//    	System.err.println(mybAccount.getLoginPwdSalt());
//    	System.err.println(passwordUtil.entryptPassword("111", mybAccount.getLoginPwdSalt()));
	}
}
