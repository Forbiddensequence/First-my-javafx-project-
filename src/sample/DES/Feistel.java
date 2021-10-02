package sample.DES;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

class Feistel{

    public static int feistel(int R0int, int Kint) {

        String R0=new String();
        String K=new String();
        String P1=new String();
        String P2=new String();
        String P3=new String();
        String P4=new String();
        String P5=new String();
        String P6=new String();
        R0 = Integer.toBinaryString(R0int);
        K = Integer.toBinaryString(Kint);
        R0 = extentiontoFourBits(R0);
        K=extentiontoByte(K);

        P1=EandP(R0);

        P2=xor(P1,K);

        String R=new String(P2.substring(4,8));
        String L=new String(P2.substring(0,4));

        P3=S1(L);
        P4=S2(R);
        P5=P3.concat(P4);
        P6=P(P5);

        int  result= new BigInteger(P6,2).intValue();
        SDES.addStringinList("<-----------------------------> ");
        SDES.addStringinList("Половина для пребразования функцией Фейстеля "+R0int);
        SDES.addStringinList("Ключ для функции Фейстеля  "+Kint);
        SDES.addStringinList("Двоичное число для шифрования "+R0);
        SDES.addStringinList("Двоичный ключ для шифрования "+K);
        SDES.addStringinList("Результат расширения и перестановки "+P1);
        SDES.addStringinList("Результат взаимного исключения с Ключом "+P2);
        SDES.addStringinList("Левая половина прошлого шага "+L);
        SDES.addStringinList("Правая половина прошлого шага "+R);
        SDES.addStringinList("Левая половина после преобразования "+P3);
        SDES.addStringinList("Правая половина после преобразования "+P4);
        SDES.addStringinList("Результат конкатенации двух половин "+P5);
        SDES.addStringinList("Результат конечной перестановки "+P6);
        SDES.addStringinList("<-----------------------------> ");
        return result;
    }

    public static String EandP(String R0){
        int[] E;
        E=SDES.EandP;
        String ER=new String();
        for (int i=0;i<8;i++){
            ER=ER.concat(String.valueOf(R0.charAt(E[i]-1)));
        }
        ER=extentiontoByte(ER);
        return ER;
    }

    public static String S1(String L1){
        int[] E;
        E=SDES.S1;
        String result=new String();
        int[] D={
                0,3,1,2
        };
        String DOP=new String();
        for(int i=0;i<4;i++){
            DOP=DOP.concat(String.valueOf(L1.charAt(D[i])));
        }

        int  L1int= new BigInteger(DOP,2).intValue();
        result=Integer.toBinaryString(E[L1int]);
        result= extentiontoTwoBits(result);
        return result;
    }

    public static String S2(String R1){

        int[] E;

        E=SDES.S2;
        String result=new String();

        int[] D={
                0,3,1,2
        };

        String DOP=new String();
        for(int i=0;i<4;i++){
            DOP=DOP.concat(String.valueOf(R1.charAt(D[i])));
        }
        int  R1int= new BigInteger(DOP,2).intValue();
        result=Integer.toBinaryString(E[R1int]);
        result= extentiontoTwoBits(result);
        return result;
    }

    public static String P(String str){
        int[] E;
        E=SDES.P;
        String result=new String();
        for(int i=0;i<4;i++){
            result=result.concat(String.valueOf(str.charAt(E[i]-1)));
        }

        return result;
    }

    public static String xor(String x,String y){
        int  X= new BigInteger(x,2).intValue();
        int  Y= new BigInteger(y,2).intValue();
        int result=X^Y;
        return extentiontoByte(Integer.toBinaryString(result));
    }

    public static int xor(int x,int y){
        return x^y;
    }

    public static int  swapString(int str){
        int a=str/16;
        int b=str%16;
        int result=b*16+a;
        return result;
    }

    public static String  swapString(String str){
        String a=str.substring(0,4);
        String b=str.substring(4,8);
        String result =b.concat(a);
        return result;
    }

    public static String extentiontoByte(String result){

        while(result.length()<8){
            result="0".concat(result);
        }
        return result;
    }

    public static String extentiontoFourBits(String result){

        while(result.length()<4){
            result="0".concat(result);
        }
        return result;
    }

    public static String extentiontoTwoBits(String result){

        while(result.length()<2){
            result="0".concat(result);
        }
        return result;
    }

    public static String extentiontoTenBits(String result){

        while(result.length()<10){
            result="0".concat(result);
        }
        return result;
    }

    public static String extentiontFiveBit(String result){

        while(result.length()<5){
            result="0".concat(result);
        }
        return result;
    }

    public static int[] transfertoarray(String input){
        String[] sr= input.split(" ");
        int[] E=new int[sr.length];
        for(int i=0;i<sr.length;i++){
            E[i]= new BigInteger(sr[i]).intValue();
        }
        return E;
    }

    public static String toBinar(int number){
        return Integer.toBinaryString(number);
    }

}
