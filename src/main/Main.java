package main;

import database.Banco;

public class Main {

    public static void main(String[] args) {
        // Iniciando a tela principal
        //new database.Banco();
        Banco exemplo = new Banco();
        
        exemplo.connect();
        
    }
    
}
