/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifrado;

import java.math.BigInteger;
import java.util.*;
/**
 *
 * @author Marcelo Esperguel
 */
public class RSA {
    
    private BigInteger p; // Primo1
    private BigInteger q; // Primo2
    private BigInteger k; // K cualquier
    private BigInteger dKey; // Private Key
    private BigInteger nPublic; // Public Key 1
    private BigInteger ePublic; // Public Key 2
    private BigInteger phiN;
    private int bitLength = 5;
    public RSA() {
    }

    public RSA(BigInteger p, BigInteger q, BigInteger k, BigInteger dKey, BigInteger n, BigInteger e) {
        this.p = p;
        this.q = q;
        this.k = k;
        this.dKey = dKey;
        this.nPublic = n;
        this.ePublic = e;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }
    
    public String generatePublicKey(BigInteger p, BigInteger q){
        
        setP(p);
        setQ(q);
        
        nPublic=p.multiply(q);
  
        //  System.out.println(nPublic.toString());
        phiN=findPhiN(p, q);

        System.out.println("phi(N)= "+phiN.toString());
        ePublic=findE(phiN);
        
        dKey= ePublic.modInverse(phiN);
        System.out.println("Private Key (d)= "+dKey);
        return "Public Key (e,N) = ("+ePublic.toString()+","+nPublic.toString()+")";
       
    }
    
    
    public String generatePublicKey(){
        
        generaPrimos();
        return generatePublicKey(p, q);
        
        
    }
    
    public void generaPrimos(){
        
        this.p=new BigInteger(bitLength, 10, new Random());
        do{
            this.q=new BigInteger(bitLength, 10, new Random());       
        }
        while(p.compareTo(q)==0);
        
        System.out.println("Primos (P,Q)= ("+p+","+q+")");
    }
    
    public BigInteger findPhiN(BigInteger p, BigInteger q){
      
        return (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
    }
    
    public BigInteger findE(BigInteger phiN){
        
        // e coprimo y menor de phiN 
        BigInteger e;
        do{
            e= new BigInteger(bitLength, 10, new Random());
        }while(e.compareTo(phiN)!=-1);
        
        return e;
    }
    
    public BigInteger[] encryptMessage(String msg, BigInteger e,BigInteger n){
        
        /*
        String textoCesar ="";
        String str="";
        for(int i=0; i< msg.length(); i++){
            textoCesar=textoCesar+cesar(msg.charAt(i));
        }
        
        BigInteger c = new BigInteger(textoCesar);
        System.out.println(c.toString());
        */
        
        byte byteArray[] = msg.getBytes();
        byte aux[] = new byte[1];
        
        BigInteger finalArray[]=new BigInteger[byteArray.length];
        
        for(int i =0; i<byteArray.length;i++){
            aux[0]=byteArray[i];
            finalArray[i]= new BigInteger(aux);
        }
        
        for (int i = 0; i < finalArray.length; i++) {
            finalArray[i]=finalArray[i].modPow(e, n);
        }
        
        //System.out.println("Cifrado: "+aTexto(finalArray));
        return finalArray;
    }
    
    public String cesar (char letter){ // cesar 10
        String finalString = "";
        String r="";
        int c= (int) letter;
        
        c=c-87;
        
        if(c<10){
            if(c==-55){
                r="99";
            }            
        }else{
            r=c+"";
        }
        return r;
    }
    
    
    public String decrifrador( BigInteger[] c, BigInteger d, BigInteger n){
        
        BigInteger[] cDes = new BigInteger[c.length];
        String r;
        for (int i = 0; i < cDes.length; i++) {
            cDes[i]=c[i].modPow(d, n);
        }
        
        char charArray[]= new char[cDes.length];
        
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) cDes[i].intValue();
        }

        
        r=new String(charArray);
        
        return r;
    }
    
    public String aTexto(BigInteger a[]){
        String s="";
        
        for (int i = 0; i < a.length; i++) {
            
            s=s+a[i].toString();
            if(i!=a.length-1){
                s=s+" ";
            }
        }
        
        return s;
    }
    

}
