package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {      

            ArrayList<String> parole = new ArrayList<>();
            parole.add("Pizza");
            parole.add("Sushi");
            parole.add("Panino");

            Random random = new Random();  
            int randomitem = random.nextInt(parole.size());
            String cibo = parole.get(randomitem);
            System.out.println("Server in avvio!");

            ServerSocket server = new ServerSocket(3000);
            Socket s = server.accept();

            System.out.println("Un client si è collegato");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
        
            int risposta = 0;
            do {
               
                String stringaRicevuta = in.readLine();
                int numeroRicevuto = Integer.parseInt(stringaRicevuta);

                System.out.println("Scelta effettuata: " + numeroRicevuto);

                switch (numeroRicevuto) {
                    case 1:
                        System.out.println("Inserisci una lettera");
                        stringaRicevuta = in.readLine();
                        if(stringaRicevuta.contains(cibo)){
                            out.writeBytes("1\n"); 
                        }
                        risposta = 1;
                        break;
                    case 2:
                        out.writeBytes("2\n");
                        risposta = 2;
                        break;
                    case 3:
                        out.writeBytes("3\n");
                        risposta = 3;
                    default:
                        break;
                }
            } while (risposta != 3);

            s.close();
            server.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        }
    }
}