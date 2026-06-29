package com.babsmoney.view;

import java.util.Scanner;

public class WalletView extends BaseView {

    public WalletView(Scanner sc) {
        super(sc);
    }

    @Override
    public int showMenu() {
        System.out.println("\n===== ESPACE DISTRIBUTEUR =====");
        System.out.println("1 - Créer wallet");
        System.out.println("2 - Dépôt");
        System.out.println("3 - Retrait");
        System.out.println("0 - Quitter");
        System.out.print("Choix : ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
}