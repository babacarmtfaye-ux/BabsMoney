package com.babsmoney.controller;

import com.babsmoney.service.ClientService;
import com.babsmoney.view.ClientView;

public class ClientController {

    private final ClientService service;
    private final ClientView view;
    private String currentPhone;

    public ClientController(ClientService service, ClientView view) {
        this.service = service;
        this.view    = view;
    }

    public void startApp() {
        String phone = view.prompt("Téléphone : ");
        String code  = view.prompt("Code : ");

        String auth = service.authenticate(phone, code);
        view.showMessage(auth);
        if (auth.startsWith("Erreur")) return;

        currentPhone = phone;

        int choice;
        do {
            choice = view.showMenu();
            switch (choice) {
                case 1 -> transfer();
                case 2 -> pay();
                case 3 -> showHistory();
                case 0 -> view.showMessage("Au revoir !");
                default -> view.showMessage("Choix invalide");
            }
        } while (choice != 0);
    }

    private void transfer() {
        String toPhone = view.prompt("Téléphone destinataire : ");
        double amount  = view.promptDouble("Montant : ");

        String result = service.transfer(currentPhone, toPhone, amount);
        view.showMessage(result);
    }

    private void pay() {
        view.showMerchants();
        String merchantCode = view.prompt("Code marchand : ");
        double amount       = view.promptDouble("Montant : ");

        String result = service.pay(currentPhone, merchantCode, amount);
        view.showMessage(result);
    }

    private void showHistory() {
        String[][] history = service.getHistory(currentPhone);
        if (history.length == 0) { view.showMessage("Aucune transaction"); return; }

        for (String[] tx : history) {
            view.showTransaction(tx[0], tx[1], tx[2], tx[3], Double.parseDouble(tx[4]));
        }
    }
}