package com.babsmoney.controller;

import com.babsmoney.service.WalletService;
import com.babsmoney.view.WalletView;

public class WalletController {

    private final WalletService service;
    private final WalletView view;

    public WalletController(WalletService service, WalletView view) {
        this.service = service;
        this.view    = view;
    }

    public void startApp() {
        int choice;
        do {
            choice = view.showMenu();
            switch (choice) {
                case 1 -> createWallet();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 0 -> view.showMessage("Bye Bye");
                default -> view.showMessage("Choix invalide");
            }
        } while (choice != 0);
    }

    private void createWallet() {
        String owner   = view.prompt("Titulaire : ");
        String phone   = view.prompt("Téléphone : ");
        String code    = view.prompt("Code : ");
        double balance = view.promptDouble("Solde initial : ");

        String result = service.createWallet(owner, phone, code, balance);
        view.showMessage(result);
    }

    private void deposit() {
        String phone  = view.prompt("Téléphone : ");
        String code   = view.prompt("Code : ");
        double amount = view.promptDouble("Montant dépôt : ");

        String result = service.deposit(phone, code, amount);
        view.showMessage(result);
    }

    private void withdraw() {
        String phone  = view.prompt("Téléphone : ");
        String code   = view.prompt("Code : ");
        double amount = view.promptDouble("Montant retrait : ");

        String result = service.withdraw(phone, code, amount);
        view.showMessage(result);
    }
}