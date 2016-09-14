/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifrado;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Marcelo Esperguel
 */
public class Cifrado {
    static Scanner entrada = new Scanner (System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        RSA cifrado = new RSA();
        String msje = "";
        cifrado.setP(new BigInteger("17"));
        cifrado.setQ(new BigInteger("19"));
        
        System.out.println("========GENERANDO CLAVE PÚBLICA==========");
        
        System.out.println(cifrado.generatePublicKey());

        /////////////////////////////////////////////////////////
        
        System.out.println("=======PROCESO DE ENCRIPTACIÓN=========");
        System.out.println("Ingrese mensaje a encriptar: ");
        msje = entrada.nextLine();
        
        System.out.println("Ingrese e:");
        BigInteger e = new BigInteger(entrada.nextInt()+"");
        System.out.println("Ingrese N:");
        BigInteger n = new BigInteger(entrada.nextInt()+"");

        BigInteger c[] =cifrado.encryptMessage(msje, e, n);
        String mensajeCifrado=cifrado.aTexto(c);
        System.out.println("Cifrado: "+ mensajeCifrado);
        
        System.out.println("========PROCESO DESENCRIPTACIÓN==========");
        System.out.println("Ingrese clave privada d: ");
        BigInteger d = new BigInteger(entrada.nextInt()+"");
        System.out.println("Ingrese clave publica N: ");
        n = new BigInteger(entrada.nextInt()+"");
        
        System.out.println("Texto decifrado: ");
        System.out.println(cifrado.decrifrador(c,d, n));
        
    }
    
}
