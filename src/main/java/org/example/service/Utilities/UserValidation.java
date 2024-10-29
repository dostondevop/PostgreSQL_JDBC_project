package org.example.service.Utilities;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class UserValidation {
    private static final String PASSWORD_REGEX =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&^])[A-Za-z\\d@$!%*?&^]{8,}$";

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(gmail\\.com|mail\\.ru)$";

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidPassword(String password) {
        if (password == null) return false;
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }
}