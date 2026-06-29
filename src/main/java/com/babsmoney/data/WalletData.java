package com.babsmoney.data;

public class WalletData {

    public static final int MAX = 100;

    // ===== WALLETS =====
    public static String[] owners   = new String[MAX];
    public static String[] phones   = new String[MAX];
    public static String[] codes    = new String[MAX];
    public static double[] balances = new double[MAX];
    public static int walletCount   = 0;

    // ===== MARCHANDS =====
    public static String[] merchantNames    = new String[MAX];
    public static String[] merchantCodes    = new String[MAX];
    public static double[] merchantBalances = new double[MAX];
    public static int merchantCount         = 0;

    // ===== TRANSACTIONS =====
    public static String[] txType     = new String[MAX];
    public static String[] txFrom     = new String[MAX];
    public static String[] txTo       = new String[MAX];
    public static String[] txMerchant = new String[MAX];
    public static double[] txAmount   = new double[MAX];
    public static int txCount         = 0;
}