package com.babsmoney.utils;

import com.babsmoney.data.WalletData;

public class WalletUtils {

    public static int findWalletByPhone(String phone) {
        for (int i = 0; i < WalletData.walletCount; i++) {
            if (WalletData.phones[i].equalsIgnoreCase(phone.trim())) {
                return i;
            }
        }
        return -1;
    }

    public static double calculateFees(double amount) {
        return amount * 0.02; // 2% de frais
    }
}