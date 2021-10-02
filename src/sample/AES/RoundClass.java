package sample.AES;

import java.io.IOException;
import java.math.BigInteger;

public class RoundClass{

    public static int[] SubNibbles(int[] data)  {

        int[] SN = SAES.SN;
        int[] result=new int[data.length];
        for(int i=0;i<data.length;i++){
            result[i]=SN[data[i]];
        }
        return result;
    }

    public static int[] InvSubNibbles(int[] data) {

        int[] SN = SAES.ISN;
        int[] result=new int[4];
        for(int i=0;i<4;i++){
            result[i]=SN[data[i]];
        }
        return result;
    }

    public static int[] ShiftRows(int[] data){
        int[] result=new int[4];
        for(int i=0;i<4;i++){
            result[i]=data[i];
        }
        result[2]=data[3];
        result[3]=data[2];

        return result;
    }

    public static int[] InvShiftRows(int[] data){
        return ShiftRows(data);
    }


    public static int[] MixColumns(int[] input){
        int[] C;
        C= SAES.C;
        String Poly=new String();
        Poly= SAES.polinom;
        int[] result=new int[4];
        result[0]=new BigInteger(mod(xor(polymultipl(toBinar(C[0]),toBinar(input[0])),polymultipl(toBinar(C[1]),toBinar(input[2]))),Poly),2).intValue();
        result[1]=new BigInteger(mod(xor(polymultipl(toBinar(C[0]),toBinar(input[1])),polymultipl(toBinar(C[1]),toBinar(input[3]))),Poly),2).intValue();
        result[2]=new BigInteger(mod(xor(polymultipl(toBinar(C[2]),toBinar(input[0])),polymultipl(toBinar(C[3]),toBinar(input[2]))),Poly),2).intValue();
        result[3]=new BigInteger(mod(xor(polymultipl(toBinar(C[2]),toBinar(input[1])),polymultipl(toBinar(C[3]),toBinar(input[3]))),Poly),2).intValue();
        return result;
    }

    public static int[] InvMixColumns(int[] input){
        int[] C;

        C= SAES.Cinv;
        String Poly=new String();
        Poly= SAES.polinom;
        int[] result=new int[4];
        result[0]=new BigInteger(mod(xor(polymultipl(toBinar(C[0]),toBinar(input[0])),polymultipl(toBinar(C[1]),toBinar(input[2]))),Poly),2).intValue();
        result[1]=new BigInteger(mod(xor(polymultipl(toBinar(C[0]),toBinar(input[1])),polymultipl(toBinar(C[1]),toBinar(input[3]))),Poly),2).intValue();
        result[2]=new BigInteger(mod(xor(polymultipl(toBinar(C[2]),toBinar(input[0])),polymultipl(toBinar(C[3]),toBinar(input[2]))),Poly),2).intValue();
        result[3]=new BigInteger(mod(xor(polymultipl(toBinar(C[2]),toBinar(input[1])),polymultipl(toBinar(C[3]),toBinar(input[3]))),Poly),2).intValue();
        return result;
    }

    /**
     * Реализация функции примешивания раундового ключа - сложение по модулю два
     * @param data массив входных данных в целых числах 4 шт по полубайту каждый
     * @param key массив ключа 2 числа по байту
     * @return результат добавления в данные ключа
     */
    public static int[] AddRoundKey(int[] data, int[] key){
        int[] result=new int[4];
        for(int i=0;i<result.length;i++){
            result[i]=key[i];
        }
        for(int i=0;i<result.length;i++){
            result[i]=xor(data[i],result[i]);
        }
        return result;
    }

    public static String mod(String devisible,String devisor){
        String result=new String(devisible);
        while(result.length()>4) {
            String dopl=new String(devisor);
            while(dopl.length()<result.length()){
                dopl=dopl.concat("0");
            }
            result=xor(result,dopl);

        }
        return result;
    }

    public static String polymultipl(String x,String y){
    String result=new String("0");
    String dopl=new String(y);
    for(int i=0;i<x.length();i++){
        if(String.valueOf(x.charAt(x.length()-i-1)).equals("1")){
            result=xor(result,dopl);
        }
        dopl=dopl.concat("0");
    }
    return result;
}

    public static String xor(String x,String y){
        int  X= new BigInteger(x,2).intValue();
        int  Y= new BigInteger(y,2).intValue();
        int result=X^Y;
        return Integer.toBinaryString(result);
    }

    public static int xor(int x,int y){
        return x^y;
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

    public static String extentiontoFiveBits(String result){

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

    public static void showarrayinDec(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
            if(i==(arr.length/2-1)){
                System.out.print("\n");
            }
        }
        System.out.println(" ");
    }

    public static void showarrayinBin(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(extentiontoFourBits(toBinar(arr[i]))+" ");
            if(i==(arr.length/2-1)){
                System.out.print("\n");
            }
        }
        System.out.println(" ");
    }

    public static void showarrayinHex(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(toHex(arr[i])+" ");
            if(i==(arr.length/2-1)){
                System.out.print("\n");
            }
        }
        System.out.println(" ");
    }

    public static String toBinar(int number){
        return Integer.toBinaryString(number);
    }

    public static String toHex(int number){
        return Integer.toHexString(number);
    }

    public static int FromHextoDecimal(String number){
        int result = new BigInteger(number,16).intValue();
        return result;
    }

    public static String FromHextoDecimalS(String number){
        String result =new String(Integer.toString(new BigInteger(number,16).intValue()));
        return result;
    }

    public static String FromHextoBinary(String number){
        int result = new BigInteger(number,16).intValue();
        String rt=new String(Integer.toBinaryString(result));
        rt=extentiontoFourBits(rt);
        return rt;
    }

    public static int powerOfTwo(int number){
        int result =1;
        for (int i=0;i<number;i++){
            result*=2;
        }
        return result;
    }
}
