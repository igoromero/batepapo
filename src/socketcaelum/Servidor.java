/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketcaelum;

import java.io.IOException;
import java.io.PrintStream;
import static java.lang.System.in;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sun.awt.windows.ThemeReader;
/**
 *
 * @author acer
 */
public class Servidor {
    public static void main(String[] args) throws IOException {
        
        new Servidor(12345).executa();
    }    
        
    int porta;
    private List<PrintStream> clientes;
    
    
    
    public Servidor (int porta){
     this.porta=porta;
     this.clientes = new ArrayList<PrintStream>();                 
    }
    
    public void executa () throws IOException{
     ServerSocket servidor = new ServerSocket(this.porta);
     System.out.println("Porta 12345 aberta !");
        while (true) {            
         //aceitando clientes 
        Socket cliente = servidor.accept();
            System.out.println("Nova conexão "+
            cliente.getInetAddress().getHostAddress());            
       
        
        PrintStream ps = new PrintStream(cliente.getOutputStream());
        this.clientes.add(ps);
        
        TrataCliente tc = new TrataCliente(cliente.getInputStream(), this);
        new Thread(tc).start();
     }
    
    
    }
    
    public void distribuiMensagem(String msg){
        
        for(PrintStream cliente: this.clientes){
            cliente.println(msg);
        }
        
    }
    
    
}