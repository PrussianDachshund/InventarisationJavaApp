import baza_danych.Connect;

import static baza_danych.Connect.*;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Connect conn = new Connect();
        conn.db_connect();
        print(select());
        conn.db_disconnect();
    }
}