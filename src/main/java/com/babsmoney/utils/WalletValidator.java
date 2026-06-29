package com.babsmoney.utils;

public class WalletValidator {
    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    public static boolean isValidName(String name) {
        return name != null && name.length() >= 2 && name.matches("[a-zA-À-ÿ]+");
    }
}
