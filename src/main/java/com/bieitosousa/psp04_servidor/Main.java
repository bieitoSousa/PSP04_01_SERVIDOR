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
public class Main {
    public static void main(String[] args) {
        try {
            int port = 2000;
            // CREA SOCKECT
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("[Server :" + port + " ]Escucho el puerto " + port);
            while (true) {
                // INICIA CONNECTION // (accept)Bloquea el bucle hasta que llege una conexion
                Socket clientSocket = serverSocket.accept();
                System.out.println("[Server :" + port + " ]Cliente conectado");
                // INICIA THREAD --> ejecuta (RUNNABLE).run()
                new ServidorThread(clientSocket).start();
                System.out.println("[Server :" + port + " ]creado hilo para cliente");
            }
        } catch (Exception e) {
            System.out.println("ERROR : [Start]" + e.getMessage());
        }
    }



}
