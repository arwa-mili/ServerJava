package com.example.clientserver;


import java.io.*;
import java.net.*;

import java.io.IOException;

/** Server Multi_client
 *
 */

public class Client extends Thread{
    static final int port = 8080;


    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost",port);
        System.out.println("SOCKET =" + socket);
        BufferedReader plec = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pred =  new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);
        String str = "Hello";
        while(true){
            for (int i =1;i<11;i++){
                System.out.println(plec.readLine());
                pred.println(str+"  "+i);

            }
            System.out.println(plec.readLine()+ "Ok Great");

        }
    }
}