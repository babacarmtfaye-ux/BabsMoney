package com.babsmoney.view;

import java.util.Scanner;

public class AppView extends BaseView {

    public AppView(Scanner sc) {
        super(sc);
    }

    @Override
    public int showMenu() {
        System.out.println("\n===== BABS MONEY =====");
        System.out.println("1 - Espace Distributeur");
        System.out.println("2 - Espace Client");
        System.out.println("0 - Quitter");
        System.out.print("Choix : ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }

    public Scanner getScanner() {
        return sc;
    }
}