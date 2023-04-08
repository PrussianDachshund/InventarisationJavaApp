import baza_danych.Connect;

import static baza_danych.Connect.*;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        db_connect();
        db_disconnect();
    }
}