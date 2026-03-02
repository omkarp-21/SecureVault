package com.securevault;

import com.securevault.system.SystemInitializer;

public class Main {

    public static void main(String[] args) {
        System.out.println("Secure Vault");

        SystemInitializer systemInitializer = new SystemInitializer();
        systemInitializer.initialize();
    }
}