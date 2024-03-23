package by.kiok.tgbot.util;

public class RegexpValidConstants {
    public static final String PASSWORD_REGXP = "^.*(?=.{8,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&?_\"]).*$";
    public static final String USERNAME_REGXP = "^(?=[a-zA-Z0-9._]{4,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";
}
