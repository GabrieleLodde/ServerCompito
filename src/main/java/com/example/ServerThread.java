package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    
    Socket clientSocket;

    public ServerThread(Socket s){
        this.clientSocket = s;
    }

    @Override
    public void run(){
        try {
            BufferedReader inDalClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outVersoIlClient = new DataOutputStream(clientSocket.getOutputStream());

            String stringaRicevuta = "";
            boolean esci = false;
            ArrayList <String> listaNote = new ArrayList <String> ();

            while(!esci){
                stringaRicevuta = inDalClient.readLine();
                if(stringaRicevuta.equals("@")){
                    outVersoIlClient.writeBytes(rendiArrayString(listaNote) + "\n");
                }
                else if(stringaRicevuta.equals("-1")){
                    esci = true;
                    System.out.println("> Il client con socket " + clientSocket.getRemoteSocketAddress() + " si e' disconnesso");
                }
                else if(!stringaRicevuta.equals("")){
                    listaNote.add(stringaRicevuta);
                }
            }
            clientSocket.close();

        } catch (IOException e) {
            e.getMessage();
            System.out.println("> Errore nella creazione degli stream comunicazione");
        }
        
    }

    public String rendiArrayString(ArrayList<String> array){
        String arrayString = "";
        for(int i = 0; i < array.size(); i ++){
            arrayString = arrayString + "> " + array.get(i) + ";";
        }
        return arrayString;
    }

}