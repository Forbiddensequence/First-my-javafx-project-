package sample.AES;


public class SAES {


    public static int[] SN={0x9,0xe,0x5,0x1,
                            0x8,0xb,0xd,0xa,
                            0x6,0x7,0xf,0x3,
                            0xc,0x4,0x0,0x2};
    public static int[] ISN={0xe,0x3,0xf,0xb,
                            0xd,0x2,0x8,0x9,
                            0x4,0x0,0x7,0x5,
                            0xc,0x6,0x1,0xa};
    public static int[] C={3,2,
                           2,3};
    public static int[] Cinv={3,2,
                              2,3};
    public static String polinom="10011";
    public static int[] data={0x7,0xe,
                              0x3,0xb};
    public static int[] key={0x3,0xe,
                             0xf,0xa};
    public static int[] result=new int[4];
    public static String[] resultDocument;



    public static void SAES(){
    result= ALG.saes(RotateMas(data),RotateMas(key));
    System.out.println("Вывод результатов дешифрования при зашифрованных данных и ключе");
    RoundClass.showarrayinHex(RotateMas(data));
    RoundClass.showarrayinHex(result);
    RoundClass.showarrayinHex(RotateMas(key));
    RoundClass.showarrayinHex(ALG.saesinv(result,RotateMas(key)));

    }

    public static int[] RotateMas(int[] array){
        int[] result=new int[array.length];
        int square=0;
        for(int i=0;i*i<=array.length;i++){
            square=i;
        }
        int[][] postresult=new int[square][square];
        int[][] preresult=new int[square][square];
        for(int i=0;i<square;i++){
            for(int j=0;j<square;j++){
                preresult[i][j]=array[i*square+j];
            }
        }
        for(int i=0;i<square;i++){
            for(int j=0;j<square;j++){
                postresult[j][i]=preresult[i][j];
            }
        }
        for(int i=0;i<square;i++){
            for(int j=0;j<square;j++){
                result[i*square+j]=postresult[i][j];
            }
        }
        return result;
    }

    public static String[] RotateMas(String[] array){
        String[] result=new String[array.length];
        int square=0;
        for(int i=0;i*i<=array.length;i++){
            square=i;
        }
        String[][] postresult=new String[square][square];
        String[][] preresult=new String[square][square];
        for(int i=0;i<square;i++){
            for(int j=0;j<square;j++){
                preresult[i][j]=array[i*square+j];
            }
        }
        for(int i=0;i<square;i++){
            for(int j=0;j<square;j++){
                postresult[j][i]=preresult[i][j];
            }
        }
        for(int i=0;i<square;i++){
            for(int j=0;j<square;j++){
                result[i*square+j]=postresult[i][j];
            }
        }
        return result;
    }

}


