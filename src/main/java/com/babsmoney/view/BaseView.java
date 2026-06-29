package com.babsmoney.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class BaseView {

    protected final Scanner sc;

    public BaseView(Scanner sc) {
        this.sc = sc;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String prompt(String message) {
        System.out.print(message);
        try {
            return sc.nextLine().trim();
        } catch (Exception e) {
            showMessage("Entrée invalide");
            return "";
        }
    }

    public double promptDouble(String message) {
        System.out.print(message);
        try {
            double value = sc.nextDouble();
            sc.nextLine();
            return value;
        } catch (InputMismatchException e) {
            sc.nextLine();
            showMessage("Nombre invalide");
            return -1;
        }
    }

    public abstract int showMenu();
}