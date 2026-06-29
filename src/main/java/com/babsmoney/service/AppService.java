package com.babsmoney.service;

import java.util.Scanner;

import com.babsmoney.controller.ClientController;
import com.babsmoney.controller.WalletController;
import com.babsmoney.view.AppView;
import com.babsmoney.view.ClientView;
import com.babsmoney.view.WalletView;

public class AppService {

    private final AppView view;
    private final WalletController walletController;
    private final ClientController clientController;

    public AppService() {
        view = new AppView(new Scanner(System.in));
        Scanner sc = view.getScanner();
        MerchantService merchantService = new MerchantService();
        merchantService.initMerchants(); // doit être ici
        walletController = new WalletController(new WalletService(), new WalletView(sc));
        clientController = new ClientController(new ClientService(), new ClientView(sc));
    }

    public void start() {
        int choice;
        do {
            choice = view.showMenu();
            switch (choice) {
                case 1 ->
                    walletController.startApp();
                case 2 ->
                    clientController.startApp();
                case 0 ->
                    view.showMessage("Au revoir !");
                default ->
                    view.showMessage("Choix invalide");
            }
        } while (choice != 0);
    }
}
