package com.babsmoney.service;

import com.babsmoney.data.WalletData;

public class MerchantService {

    public void initMerchants() {
    if (WalletData.merchantCount > 0) return;
    addMerchant("ISM", "1111");
    addMerchant("WOYOFAL", "2222");
}
    private void addMerchant(String name, String code) {
        WalletData.merchantNames[WalletData.merchantCount] = name;
        WalletData.merchantCodes[WalletData.merchantCount] = code;
        WalletData.merchantBalances[WalletData.merchantCount] = 0;
        WalletData.merchantCount++;
    }

    public int findMerchants(String code) {
        for (int i = 0; i < WalletData.merchantCount; i++) {
            if (WalletData.merchantCodes[i].equals(code)) {
                return i;
            }
        }
        return -1;
    }
}
