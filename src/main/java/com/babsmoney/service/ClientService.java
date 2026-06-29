package com.babsmoney.service;

import com.babsmoney.data.WalletData;
import com.babsmoney.utils.WalletUtils;
import com.babsmoney.utils.WalletValidator;

public class ClientService {

    public String authenticate(String phone, String code) {
        int index = WalletUtils.findWalletByPhone(phone);
        if (index == -1)                                 
            return "Erreur : Wallet introuvable";
        if (!WalletData.codes[index].equals(hash(code))) 
            return "Erreur : Code incorrect";
        return "Bienvenue " + WalletData.owners[index] + " - Solde : " + WalletData.balances[index];
    }

    public String transfer(String fromPhone, String toPhone, double amount) {
        int fromIndex = WalletUtils.findWalletByPhone(fromPhone);
        int toIndex   = WalletUtils.findWalletByPhone(toPhone);

        if (fromIndex == -1)                        
            return "Erreur : Wallet expéditeur introuvable";
        if (toIndex == -1)                          
            return "Erreur : Wallet destinataire introuvable";
        if (fromPhone.equals(toPhone))              
            return "Erreur : Transfert vers soi-même interdit";
        if (!WalletValidator.isValidAmount(amount)) 
            return "Erreur : Montant invalide";

        double fees  = Math.min(amount * 0.01, 5000);
        double total = amount + fees;

        if (WalletData.balances[fromIndex] < total) 
            return "Erreur : Solde insuffisant";

        WalletData.balances[fromIndex] -= total;
        WalletData.balances[toIndex]   += amount;

        saveTransaction("TRANSFERT", fromPhone, toPhone, null, amount);
        return "Transfert effectué - Frais : " + fees;
    }

    public String pay(String phone, String merchantCode, double amount) {
        int index         = WalletUtils.findWalletByPhone(phone);
        int merchantIndex = findMerchant(merchantCode);

        if (index == -1)                            
            return "Erreur : Wallet introuvable";
        if (merchantIndex == -1)                    
            return "Erreur : Marchand introuvable";
        if (!WalletValidator.isValidAmount(amount)) 
            return "Erreur : Montant invalide";
        if (WalletData.balances[index] < amount)    
            return "Erreur : Solde insuffisant";

        WalletData.balances[index]                 -= amount;
        WalletData.merchantBalances[merchantIndex] += amount;

        saveTransaction("PAIEMENT", phone, null, merchantCode, amount);
        return "Paiement effectué";
    }

    public String[][] getHistory(String phone) {
        int count = 0;
        for (int i = 0; i < WalletData.txCount; i++) {
            if (phone.equals(WalletData.txFrom[i]) || phone.equals(WalletData.txTo[i])) count++;
        }

        String[][] history = new String[count][5];
        int j = 0;
        for (int i = 0; i < WalletData.txCount; i++) {
            if (phone.equals(WalletData.txFrom[i]) || phone.equals(WalletData.txTo[i])) {
                history[j][0] = WalletData.txType[i];
                history[j][1] = WalletData.txFrom[i];
                history[j][2] = WalletData.txTo[i];
                history[j][3] = WalletData.txMerchant[i];
                history[j][4] = String.valueOf(WalletData.txAmount[i]);
                j++;
            }
        }
        return history;
    }

    private int findMerchant(String code) {
        for (int i = 0; i < WalletData.merchantCount; i++) {
            if (WalletData.merchantCodes[i].equals(code)) return i;
        }
        return -1;
    }

    private void saveTransaction(String type, String from, String to, String merchant, double amount) {
        int i = WalletData.txCount;
        WalletData.txType[i]     = type;
        WalletData.txFrom[i]     = from;
        WalletData.txTo[i]       = to;
        WalletData.txMerchant[i] = merchant;
        WalletData.txAmount[i]   = amount;
        WalletData.txCount++;
    }

    private String hash(String value) {
        return String.valueOf(value.hashCode());
    }
}