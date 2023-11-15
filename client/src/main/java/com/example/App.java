package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
public class App 
{
    public static void main( String[] args )
    {
        try { 
            System.out.println( "Il client è partito" );
            Socket s = new Socket("localhost", 3000);

            Scanner scan = new Scanner(System.in);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            System.out.println( "Connessione effettuata" );
            int risposta = 0;
            do {
                System.out.println( "Inserisci 1, 2 o 3: " );
                String str = scan.nextLine();
                out.writeBytes(str + "\n");
                String confronto = in.readLine();
                risposta = Integer.parseInt(confronto);
                if(risposta == 1){
                    System.out.println("Indovina lettera");
                    out.writeBytes(str + "\n");
                }
                if(risposta == 2){
                    System.out.println( "Indovina parola");
                }
                if(risposta == 3){
                    System.out.println( "Parola indovinata");
                }
            } while (risposta != 3);
            scan.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}