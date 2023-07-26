package ru.pariy.model.auth;

import ru.pariy.entity.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @brief Текущий класс необходим для хэширования пароля пользователей
 */
public class PasswordEncryptionService {

    public static String getHashPassword(String password, String salt) {
        String strPass = password + salt;
        byte[] bytesOfPassword = strPass.getBytes(StandardCharsets.UTF_8);
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] theMD5digest = md.digest(bytesOfPassword);
        return new String(theMD5digest, StandardCharsets.UTF_8);
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        return new String(bytes);
    }

    public static boolean checkPassword(String password, User user) {
        String hashPassword = getHashPassword(password, user.getSalt());
        return hashPassword.equals(user.getPassword());
    }
}
