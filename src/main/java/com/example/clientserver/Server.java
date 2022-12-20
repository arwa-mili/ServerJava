package com.example.clientserver;


import java.io.*;
import java.net.*;

import java.io.IOException;

/** Server Multi_client
 *
 */

public class Server extends Thread{
    int port = 8080;
    int numClient ;
    @Override
    public void run(){
        try{
            ServerSocket s =new ServerSocket(port);
            while(true){
                Socket soc = s.accept();
                ++numClient;
                new Client(soc, numClient).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    class Client extends Thread{
        private Socket socket;
        private int numclient;

        public Client(Socket socket,int nbC){
            this.socket=socket;
            this.numclient= numClient;
        }
        @Override
        public void run(){
            try{
                BufferedReader plec = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pred =  new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);
                // here we get the client IP connected with the port
                String IP = socket.getRemoteSocketAddress().toString();
                System.out.println("Connection du client: "+numclient+ " avec l'IP" + IP);
                pred.println("Bienvenue, Vous etes le client numero" + numclient);
                while(true){
                    String request;
                    while((request=plec.readLine()) != null){
                        System.out.println("le client " + numclient+ "a dit: "+request);
                        String response = "------> Message Recieved number :";
                        pred.println(response);
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Server().start();
        System.out.println("Server start...");

    }
}