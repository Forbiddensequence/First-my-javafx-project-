package sample.DES;
import java.util.ArrayList;


public class SDES {

    public static int Data=35;
    public static int Key=642;
    public static int[] EandP={4,1,2,3,2,3,4,1};
    public static int[] P8 ={6,3,7,4,8,5,10,9};
    public static int[] P10 ={3,5,2,7,4,10,1,9,8,6};
    public static int[] P={2,4,3,1};
    public static int[] S1={1,0,3,2,
                            3,2,1,0,
                            0,2,1,3,
                            3,1,3,1};
    public static int[] S2={1,1,2,3,
                            2,0,1,3,
                            3,0,1,0,
                            2,1,0,3};
    public static int[] IP={2,6,3,1,4,8,5,7};
    public static int[] IPinv={4,1,3,5,7,2,8,6};
    public static String[] resultDocument;
    public static ArrayList<String> list;


    public static void SDES(){
        int[] K;
        int promkey=Key;
        int word=Data;
        list=new ArrayList<String>();

        addStringinList("Данные для шифрования  "+word+" - "+Feistel.extentiontoByte(Feistel.toBinar(word)));
        addStringinList("Ключ из которого извлекаем раундовые "+promkey+" - "+AlCorithme.extentiontoTenBits(Feistel.toBinar(promkey)));

        K= AlCorithme.selectKeysfromKey(promkey);

        addStringinList("1 раундовый ключ "+K[0]+" - "+Feistel.extentiontoByte(Feistel.toBinar(K[0])));

        addStringinList("2 раундовый ключ "+K[1]+" - "+Feistel.extentiontoByte(Feistel.toBinar(K[1])));

        int res= AlCorithme.sdes(word,K);

        SDES.resultDocument=new String[list.size()];

        for(int i=0;i<list.size();i++){
            SDES.resultDocument[i]=list.get(i);
        }

        /*
        int res2= AlCorithme.sdesinv(res,K);

        addStringinList("Результат алгоритма S-DES "+res+ " - "+Feistel.extentiontoByte(Feistel.toBinar(res)));

        addStringinList("Результат расшифровки алгоритма S-DES "+res2+ " - "+Feistel.extentiontoByte(Feistel.toBinar(res2)));
         */

    }

    public static void addStringinList(String input){
        list.add(input);
    }

/*
    public static void addarrayinDec(int[] arr){
        String res=new String();
        for(int i=0;i<arr.length;i++){
            res=res.concat(String.valueOf(arr[i]).concat(" "));
        }
        list.add(res);
    };

    public static void addarrayinBin(int[] arr){
        String res=new String();
        for(int i=0;i<arr.length;i++){
            res=res.concat(Feistel.toBinar(arr[i]).concat(" "));
        }
        list.add(res);
    };


 */

}


