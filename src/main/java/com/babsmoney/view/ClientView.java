package com.babsmoney.view;

import java.util.Scanner;

import com.babsmoney.data.WalletData;

public class ClientView extends BaseView {

    public ClientView(Scanner sc) {
        super(sc);
    }

    @Override
    public int showMenu() {
        System.out.println("\n===== ESPACE CLIENT =====");
        System.out.println("1 - Faire un Transfert");
        System.out.print("2 - Faire un Paiement (");
        System.out.println("3 - Historique");
        System.out.println("0 - Quitter");
        System.out.print("Choix : ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }

    public void showTransaction(String type, String from, String to, String merchant, double amount) {
        System.out.println("----------------------------");
        System.out.println("Type : " + type);
        System.out.println("De : " + from);
        if (to != null) {
            System.out.println("Vers : " + to);
        }
        if (merchant != null) {
            System.out.println("Marchand: " + merchant);
        }
        System.out.println("Montant : " + amount);
    }

    public void showMerchants() {
        System.out.println("\n===== MARCHANDS =====");
        for (int i = 0; i < WalletData.merchantCount; i++) {
            System.out.println("-" + WalletData.merchantNames[i] + "(code :" + WalletData.merchantCodes[i] + ")");
        }
    }
}
