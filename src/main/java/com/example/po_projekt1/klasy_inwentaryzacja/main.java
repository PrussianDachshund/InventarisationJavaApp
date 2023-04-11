package com.example.po_projekt1.klasy_inwentaryzacja;

import com.example.po_projekt1.baza_danych.Connect;


public class main {
    public static void main(java.lang.String[] args) {
        System.out.println("Hello world!");
        Connect conn = new Connect();
        //conn.db_connect();
       // print(select());
        conn.db_disconnect();
    }
}