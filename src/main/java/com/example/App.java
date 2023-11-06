package com.example;

import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args )
    {
        try {
            System.out.println("> Avvio del server....");
            ServerSocket connectSocket = new ServerSocket(4500);

            while(true){
                Socket dataSocket = connectSocket.accept();
                System.out.println("> Si e' connesso un nuovo client con Socket " + dataSocket.getRemoteSocketAddress());

                ServerThread serverThread = new ServerThread(dataSocket);

                serverThread.start();
            }
        } catch (Exception e) {
            e.getMessage();
            System.out.println("> Errore durante l'istanza del server!");
        }

    }
}
