package sample.DES;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

class AlCorithme {

public static int round(int Word,int k) {

        String word=new String();
        word=Integer.toBinaryString(Word);
        word=Feistel.extentiontoByte(word);
        String RS=new String(word.substring(4,8));
        String LS=new String(word.substring(0,4));
        String NK=new String(Integer.toBinaryString(k));
        NK=Feistel.extentiontoByte(NK);
        int  R= new BigInteger(RS,2).intValue();
        int  L= new BigInteger(LS,2).intValue();
        //int result=NL*16+NL;

        SDES.addStringinList("|-----------------------------| ");
        SDES.addStringinList("Входное слово раунда " + Word);
        SDES.addStringinList("Входное слово раунда " + word);
        SDES.addStringinList("Входной ключ "+ k);
        SDES.addStringinList("Левая половина слова " + LS);
        SDES.addStringinList("Правая половина слова "+ RS);
        //SDES.addStringinList("Левая половина " +L);
        //SDES.addStringinList("Правая половина " +R);
        SDES.addStringinList("Входной ключ для функции Фейстеля "+ NK);

        int Fei=Feistel.feistel(R,k);

        int NR=Feistel.xor(L,Fei);
        int NL=R;
        String NRS=new String(Feistel.extentiontoFourBits(Integer.toBinaryString(NR)));
        String NLS=new String(Feistel.extentiontoFourBits(Integer.toBinaryString(NL)));
        String Result=new String();
        Result=NLS.concat(NRS);
        int result =new BigInteger(Result,2).intValue();

        SDES.addStringinList("Резульльтат функции фейстеля " +Fei);
        SDES.addStringinList("Новая левая половина " +NLS);
        SDES.addStringinList("Новая правая половина " +NRS);
        SDES.addStringinList("Итоговое слово раунда " +result);
        SDES.addStringinList("|-----------------------------| ");

        return result;
        }

public static String IP(String str){
        int[] E;
        E=SDES.IP;
        String result=new String();
        for(int i=0;i<8;i++){
        result=result.concat(String.valueOf(str.charAt(E[i]-1)));
        }
        return result;
        }

public static String IPinv(String str){
        int[] E;
        E=SDES.IPinv;
        String result=new String();
        for(int i=0;i<8;i++){
        result=result.concat(String.valueOf(str.charAt(E[i]-1)));
        }
        return result;
        }

public static int sdes(int Word,int[] Key){
        SDES.addStringinList("Слово перед работой алгоритма " + Word);
        String wordS=new String();
        wordS=Integer.toBinaryString(Word);
        wordS=Feistel.extentiontoByte(wordS);
        SDES.addStringinList("Слово перед начальной перестановкой " + wordS);
        String wordIP=new String();
        wordIP=IP(wordS);
        SDES.addStringinList("Слово после начальной перестановки " + wordIP);
        //wordIP=Feistel.extentiontoByte(wordIP);
        int  PREV= new BigInteger(wordIP,2).intValue();
        int Round1;
        int Round2;
        Round1=round(PREV,Key[0]);
        //Round1=Feistel.swapString(Round1);
        SDES.addStringinList("Слово после первого раунда " + Round1);
        Round2=round(Round1,Key[1]);
        SDES.addStringinList("Слово после второго раунда " + Round2);
        Round2=Feistel.swapString(Round2);
        SDES.addStringinList("Слово после второго раунда " + Round2);
        String wordIPprinv=new String();
        wordIPprinv=Integer.toBinaryString(Round2);
        wordIPprinv=Feistel.extentiontoByte(wordIPprinv);
        SDES.addStringinList("Слово перед конечной перестановкой " + wordIPprinv);
        String wordIPinv=new String();
        wordIPinv=IPinv(wordIPprinv);
        SDES.addStringinList("Слово после конечной перестановки " + wordIPinv);
        int result=new BigInteger(wordIPinv,2).intValue();
        SDES.addStringinList("Слово после работы алгоритма " + result);
        return result;
        }

public static int sdesinv(int Word,int[] Key){
        SDES.addStringinList("Слово перед работой обратного алгоритма " + Word);
        String wordS=new String();
        wordS=Integer.toBinaryString(Word);
        wordS=Feistel.extentiontoByte(wordS);
        SDES.addStringinList("Слово перед начальной перестановкой " + wordS);

        String wordIP=new String();
        wordIP=IP(wordS);
        SDES.addStringinList("Слово после начальной перестановки " + wordIP);
        //wordIP=Feistel.extentiontoByte(wordIP);
        int  PREV= new BigInteger(wordIP,2).intValue();
        int Round1;
        int Round2;
        Round1=round(PREV,Key[1]);
        //Round1=Feistel.swapString(Round1);
        SDES.addStringinList("Слово после первого раунда " + Round1);
        Round2=round(Round1,Key[0]);
        Round2=Feistel.swapString(Round2);
        SDES.addStringinList("Слово после второго раунда " + Round2);
        String wordIPprinv=new String();
        wordIPprinv=Integer.toBinaryString(Round2);
        wordIPprinv=Feistel.extentiontoByte(wordIPprinv);
        SDES.addStringinList("Слово перед конечной перестановкой " + wordIPprinv);
        String wordIPinv=new String();
        wordIPinv=IPinv(wordIPprinv);
        SDES.addStringinList("Слово после конечной перестановки " + wordIPinv);
        int result=new BigInteger(wordIPinv,2).intValue();
        SDES.addStringinList("Слово после работы обратного алгоритма " + result);
        return result;
        }

public static int[] selectKeysfromKey(int Key){
    int result[]={0,0};
    int E[];
    E=SDES.P10;
    int D[];
    D=SDES.P8;
    String KeyS=Integer.toBinaryString(Key);
    KeyS= extentiontoTenBits(KeyS);

    String DOP1=new String();
    for(int i=0;i<10;i++){
        DOP1=DOP1.concat(String.valueOf(KeyS.charAt(E[i]-1)));
    }
    String L1=DOP1.substring(0,5);
    String R1=DOP1.substring(5,10);
    String L2=Shift(L1,1);
    String R2=Shift(R1,1);
    String word2=L2.concat(R2);
    String DOP2=new String();

    for (int i=0;i<8;i++){
        DOP2=DOP2.concat(String.valueOf(word2.charAt(D[i]-1)));
    }

    result[0]= new BigInteger(DOP2,2).intValue();

    String L3=Shift(L2,2);
    String R3=Shift(R2,2);
    String word3=L3.concat(R3);
    String DOP3=new String();

    for (int i=0;i<8;i++){
        DOP3=DOP3.concat(String.valueOf(word3.charAt(D[i]-1)));
    }
    result[1]= new BigInteger(DOP3,2).intValue();

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
