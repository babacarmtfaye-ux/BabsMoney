package com.babsmoney.service;

import com.babsmoney.data.WalletData;
import com.babsmoney.utils.WalletUtils;
import com.babsmoney.utils.WalletValidator;

public class WalletService {
    
    public String createWallet(String owner, String phone, String code, double balance) {
        String error = validWallet(owner, phone, code, balance);
        if (error != null) return error;

        int i = WalletData.walletCount;
        WalletData.owners[i]   = owner;
        WalletData.phones[i]   = phone;
        WalletData.codes[i]    = hash(code);
        WalletData.balances[i] = balance;
        WalletData.walletCount++;

        return "Wallet créé avec succès";
    }

    public String deposit(String phone, String code, double amount) {
        int index = WalletUtils.findWalletByPhone(phone);
        if (index == -1)                                  return "Erreur : Wallet introuvable";
        if (!WalletData.codes[index].equals(hash(code))) return "Erreur : Code incorrect";
        if (!WalletValidator.isValidAmount(amount))       return "Erreur : Montant invalide";

        WalletData.balances[index] += amount;
        return "Dépôt effectué";
    }

    public String withdraw(String phone, String code, double amount) {
        int index = WalletUtils.findWalletByPhone(phone);
        if (index == -1)                                  return "Erreur : Wallet introuvable";
        if (!WalletData.codes[index].equals(hash(code))) return "Erreur : Code incorrect";
        if (!WalletValidator.isValidAmount(amount))       return "Erreur : Montant invalide";

        double fees  = Math.min(amount * 0.01, 5000);
        double total = amount + fees;

        if (WalletData.balances[index] < total) return "Erreur : Solde insuffisant";

        WalletData.balances[index] -= total;
        return "Retrait effectué - Frais : " + fees;
    }

    private String validWallet(String owner, String phone, String code, double balance) {
        if (!WalletValidator.isValidName(owner))        return "Erreur : Titulaire invalide";
        if (phone.isEmpty())                            return "Erreur : Téléphone obligatoire";
        if (code.isEmpty())                             return "Erreur : Code obligatoire";
        if (balance < 0)                                return "Erreur : Solde invalide";
        if (WalletUtils.findWalletByPhone(phone) != -1) return "Erreur : Téléphone déjà utilisé";
        if (WalletData.walletCount >= WalletData.MAX)   return "Erreur : Capacité maximale atteinte";
        return null;
    }

    private String hash(String value) {
        return String.valueOf(value.hashCode());
    }
}