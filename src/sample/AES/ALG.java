package sample.AES;

import java.util.ArrayList;

public class ALG {

public static ArrayList<String> list;

public static int[] saes(int[] Word, int[] Key) {
    list=new ArrayList<String>();
    int[] result =new int[4];
    int[][] DK=selextKeysfromKey(Key);
    int[] key1=new int[4];
    int[] key2=new int[4];
    int[] key3=new int[4];
    for(int i=0;i<4;i++){
        key1[i]=DK[0][i];
        key2[i]=DK[1][i];
        key3[i]=DK[2][i];
    }
    int[] prres1=new int[4];
    int[] prres2=new int[4];
    int[] prres3=new int[4];
    int[] prres4=new int[4];
    int[] prres5=new int[4];
    int[] prres6=new int[4];
    int[] prres7=new int[4];
    int[] prres8=new int[4];
    addStringinList("Вывод массива перед началом работы");
    addarrayinHex(Word);

    addStringinList("Вывод ключа 1");
    addarrayinHex(key1);

    prres1=RoundClass.AddRoundKey(Word,key1);
    addStringinList("Вывод массива после добавления первого ключа");
    addarrayinHex(prres1);

    prres2=RoundClass.SubNibbles(prres1);
    addStringinList("Вывод массива после 1 перестановки");
    addarrayinHex(prres2);
    prres3=RoundClass.ShiftRows(prres2);
    addStringinList("Вывод массива после 1 смещения строк");
    addarrayinHex(prres3);
    prres4=RoundClass.MixColumns(prres3);
    addStringinList("Вывод массива после перемешивания");
    addarrayinHex(prres4);
    prres5=RoundClass.AddRoundKey(prres4,key2);

    addStringinList("Вывод ключа 2");
    addarrayinHex(key2);

    addStringinList("Вывод массива после добавления второго ключа");
    addarrayinHex(prres5);
    prres6=RoundClass.SubNibbles(prres5);
    addStringinList("Вывод массива после 2 перестановки");
    addarrayinHex(prres6);
    prres7=RoundClass.ShiftRows(prres6);
    addStringinList("Вывод массива после 2 смещения");
    addarrayinHex(prres7);

    addStringinList("Вывод ключа 3");
    addarrayinHex(key3);

    prres8=RoundClass.AddRoundKey(prres7,key3);
    addStringinList("Вывод массива после добавления третьего ключа");
    addarrayinHex(prres8);
    result=prres8;
    addStringinList("Вывод массива после конца работы");
    addarrayinHex(result);
    SAES.resultDocument=new String[list.size()];
    for(int i=0;i<list.size();i++){
        SAES.resultDocument[i]=list.get(i);
    }
    /*System.out.println("Длина документа для вывода в строках "+SAES.resultDocument.length);
    System.out.println("java version: "+System.getProperty("java.version"));
    System.out.println("javafx.version: " + System.getProperty("javafx.version"));
    System.out.println("javafx.runtime.version: " + System.getProperties().get("javafx.runtime.version"));*/
    return result;
    }

public static int[] saesinv(int[] Word, int[] Key){
    int[] result =new int[4];
    int[][] DK=selextKeysfromKey(Key);
    int[] key1=new int[4];
    int[] key2=new int[4];
    int[] key3=new int[4];
    for(int i=0;i<4;i++){
        key1[i]=DK[0][i];
        key2[i]=DK[1][i];
        key3[i]=DK[2][i];
    }
    int[] prres1=new int[4];
    int[] prres2=new int[4];
    int[] prres3=new int[4];
    int[] prres4=new int[4];
    int[] prres5=new int[4];
    int[] prres6=new int[4];
    int[] prres7=new int[4];
    int[] prres8=new int[4];

    prres1=RoundClass.AddRoundKey(Word,key3);

    prres2=RoundClass.ShiftRows(prres1);

    prres3=RoundClass.InvSubNibbles(prres2);

    prres4=RoundClass.AddRoundKey(prres3,key2);

    prres5=RoundClass.MixColumns(prres4);

    prres6=RoundClass.ShiftRows(prres5);

    prres7=RoundClass.InvSubNibbles(prres6);

    prres8=RoundClass.AddRoundKey(prres7,key1);

    result=prres8;

    return result;
    }

public static int[][] selextKeysfromKey(int[] Key){
    int[][] result=new int[3][4];

    int[] M = SAES.SN;

    for(int i=0;i<Key.length;i++){
        result[0][i]=Key[i];
    }

    for(int i=1;i<=2;i++){
        result[i][0]=RoundClass.xor(M[result[i-1][3]],result[i-1][0]);
        result[i][2]=RoundClass.xor(RoundClass.xor(M[result[i-1][1]],result[i-1][2]),RoundClass.powerOfTwo(i-1));
        result[i][1]=RoundClass.xor(result[i][0],result[i-1][1]);
        result[i][3]=RoundClass.xor(result[i][2],result[i-1][3]);
    }

    return result;
    }


public static void addStringinList(String input){
    list.add(input);
}

public static void addarrayinHex(int[] arr){
    String res=new String();
    for(int i=0;i<arr.length;i++){
        res=res.concat(RoundClass.toHex(arr[i]).concat(" "));
        if(i==(arr.length/2-1)){
            res=res.concat("\n");
        }
    }
    list.add(res);

};

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

public static String Shift(String input, int number){
    String result=input;
    String dop=result.substring(1,input.length());
    while((number<0)||(number>=input.length())){
        if(number<0){
            number+=input.length();
        }
        if(number>=input.length()){
            number-=input.length();
        }
    }
    for(int i=0;i<number;i++){
        result=dop.concat(String.valueOf(result.charAt(0)));
        dop=result.substring(1,input.length());
    }
    return result;
    }
}
