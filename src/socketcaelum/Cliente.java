/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketcaelum;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintStream;
import java.net.*;

/**
 *
 * @author acer
 */
public class Cliente {
    
    public static void main(String[] args) throws IOException {
        
        new Cliente("127.0.0.1", 12345).execulta();
        
        
    }
        private String host;
        private int porta;
        
        public Cliente(String host, int porta){
         this.host=host;
         this.porta=porta;
        }
        
        public void execulta() throws IOException{
        Socket cliente = new Socket(this.host, this.porta);
            System.out.println("O Cliente se conectou ao servidor");
        
        Recebedor r = new Recebedor(cliente.getInputStream());
        
        new Thread(r).start();
        
        Scanner Teclado = new Scanner(System.in);
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        
        while (Teclado.hasNextLine()){
            
            saida.println(Teclado.nextLine());
             
        }
        
        saida.close();
        Teclado.close();
        cliente.close();      
    }  
}

