/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.psp04_servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bieito
 */
// EXTENDEMOS DE THREAD PARA CREAR HILOS
class ServidorThread extends Thread {

//    private ServerSocket serverSocket;
    static int cont;
    static  String name  = "cliente";
    String nameCli;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    int indexUP = 100;
    int indexDown = 0;
    int n = (int) (Math.random() * indexUP + indexDown);

    public ServidorThread(Socket sCliente) {
        this.clientSocket = sCliente;
        this.nameCli=name+"{"+(cont++)+"}";
    }

    public void run() {
        try {
            // CREO STREAM [LECTURA/ESCRITURA]
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Inicio el programa del servidor
            intProgram();
        } catch (Exception e) {
            System.out.println("ERROR : [run]" + e.getMessage());

        }
    }

    public void intProgram() {
        int inputLine;
        int cont = 0;
        boolean op= true;
        String cabecera = "CONECT :: [Server - "+nameCli+"]";
        try {
            do {
                if ((inputLine = Integer.parseInt(in.readLine())) >= 0) {
                    System.out.println(cabecera+" OPERATION :: [ buscando el numero {" + n + "}]");
                    System.out.println(" COMUNICATION :: [Server : READ]" + inputLine);
                    if (inputLine >= 0) {
                        if (inputLine == n) {
                            out.println( cabecera+ " COMUNICATION :: [Server : WRITE] " +"si , correcto el numero es ["+n+"]");
                            System.out.println("[Server] escribe si");
                            op = false;
                        } else {
                            String o = cabecera+ " COMUNICATION :: [Server : WRITE] " +" no ";
                            if (inputLine < n) {
                                out.println(o + "mayor");
                                System.out.println("[Server - "+nameCli+"] escribe mayor");

                            } else if (inputLine > n) {
                                out.println(o + "menor");
                                System.out.println("[Server - "+nameCli+"] escribe menor");
                            } else {
                                out.println("no");
                            }
                        }
                    } else {
                        System.out.println("[Server - "+nameCli+"] -----");
                    }
//                    out.println(inputLine);
                    System.out.println("se reiniciaconversacion " + cont++);
                }
            } while (op);
        } catch (IOException | NumberFormatException e) {
            System.out.println("ERROR : [inprogram]" + e.getMessage());
        }
    }

}
