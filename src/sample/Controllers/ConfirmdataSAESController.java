package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.AES.RoundClass;
import sample.AES.SAES;
import sample.Main;
import sample.Verifier;

import java.io.*;
import java.math.BigInteger;

public class ConfirmdataSAESController {

    @FXML
    private Button confirmall;

    @FXML
    private Button confirmresult;

    @FXML
    private Button confirmISN;

    @FXML
    private TextArea ISNtext;

    @FXML
    private TextArea Constnttext;

    @FXML
    private Button confirmconst;

    @FXML
    private Button confirminvconst;

    @FXML
    private TextArea InvConstnttext;

    @FXML
    private Button confirmkey;

    @FXML
    private TextArea keytext;

    @FXML
    private Button confirmdata;

    @FXML
    private TextArea datatext;

    @FXML
    private Button confirmSN;

    @FXML
    private TextArea SNtext;

    @FXML
    private Button confirmback;

    public boolean isDataready=false;
    public boolean isKeyready=false;
    public boolean isSNready=false;
    public boolean isISNready=false;
    public boolean isConstantready=false;
    public boolean isInverseConstantready=false;



    @FXML
    void initialize() {

        Main.CurrentButtonScene=getButt();
        initfunc();
        displayAll();

        confirmdata.setOnAction(event-> {
            System.out.println("Фега работает кнопка подтверждения ввода исходных данных ");
            DataTrasfer(datatext, SAES.data,isDataready);
            SAES.data=SAES.RotateMas(SAES.data);
            isDataready=true;
        });

        confirmkey.setOnAction(event->{
            System.out.println("Фега работает кнопка подтверждения ввода данных о ключе");
            DataTrasfer(keytext, SAES.key,isKeyready);
            SAES.key=SAES.RotateMas(SAES.key);
            isKeyready=true;
        });

        confirmconst.setOnAction(event->{
            System.out.println("Фега работает кнопка подтверждения ввода данных о матрице констант");
            DataTrasfer(Constnttext, SAES.C,isConstantready);
            isConstantready=true;
        });

        confirminvconst.setOnAction(event->{
            System.out.println("Фега работает кнопка подтверждения ввода данных о матрице обратных констант");
            DataTrasfer(InvConstnttext,SAES.Cinv,isInverseConstantready);
            isInverseConstantready=true;
        });

        confirmSN.setOnAction(event->{
            System.out.println("Фега работает кнопка подтверждения ввода данных о таблице прямой перестановки");
            DataTrasfer(SNtext,SAES.SN,isSNready);
            isSNready=true;
        });

        confirmISN.setOnAction(event->{
            System.out.println("Фега работает кнопка подтверждения ввода данных о таблице обратной перестановки");
            DataTrasfer(ISNtext,SAES.ISN,isISNready);
            isISNready=true;
        });

        confirmresult.setOnAction(event->{
            if(Verifier.isButtonHopeActive) {
                if (isConstantready && isSNready && isDataready && isKeyready) {
                    confirmresult.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/FXMLS/resultSAES.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setTitle("Я открылся!!!");
                    stage.setScene(new Scene(root, 926, 659));
                    stage.setResizable(false);
                    stage.show();
                }
            }
            else {
                confirmback.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/FXMLS/startwindow.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Я открылся!!!");
                stage.setScene(new Scene(root, 926, 659));
                stage.setResizable(false);
                stage.show();
            }
        });

        confirmall.setOnAction(event->{
            confirmdata.fire();
            confirmkey.fire();
            confirmconst.fire();
            confirminvconst.fire();
            confirmSN.fire();
            confirmISN.fire();
        });

        confirmback.setOnAction(event->{
            if(Verifier.isButtonHopeActive) {
                confirmback.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/FXMLS/startwindow.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Я открылся!!!");
                stage.setScene(new Scene(root, 926, 659));
                stage.setResizable(false);
                stage.show();
            }
            else {
                confirmback.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/FXMLS/startwindow.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Я открылся!!!");
                stage.setScene(new Scene(root, 926, 659));
                stage.setResizable(false);
                stage.show();
            }
        });

    }

    public String[] toAPPview(int[] dataarray){
        String[] arrayfromDATA=new String[dataarray.length];
        for(int i=0;i<dataarray.length;i++){
            arrayfromDATA[i]=String.valueOf(dataarray[i]);
        }
        return arrayfromDATA;
    }

    public void FromAPPtoWindow(TextArea field,String[] text){
        for(int i=0;i<3;i+=2){
            field.appendText(FromDecimaltoHex(text[0+i])+" "+FromDecimaltoHex(text[1+i]));
            if(i!=2){
                field.appendText( "\n");
            }
        }
    }

    public void FromAPPtoWindowTable(TextArea field,String[] text){
        for(int i=0;i<16;i+=4){

            field.appendText(FromDecimaltoHex(text[0+i])+" "+FromDecimaltoHex(text[1+i])+" "+FromDecimaltoHex(text[2+i])+" "+FromDecimaltoHex(text[3+i]));
            if(i!=12){
                field.appendText( "\n");
            }
        }
    }

    public void FromAPPtoWindowKey(TextArea field,String[] text){
        String[] prom=new String[4];
        for(int i=4;i<8;i++){
            prom[i-4]=text[i];
        }
        prom=SAES.RotateMas(prom);
        for(int i=0;i<4;i+=2){
            field.appendText(FromDecimaltoHex(prom[0+i])+" "+FromDecimaltoHex(prom[1+i]));
            if(i!=2){
                field.appendText( "\n");
            }
        }
    }

    public void FromAPPtoWindowData(TextArea field,String[] text){
        String[] prom=new String[4];
        for(int i=0;i<4;i++){
            prom[i]=text[i];
        }
        prom=SAES.RotateMas(prom);
        for(int i=0;i<4;i+=2){
            field.appendText(FromDecimaltoHex(prom[0+i])+" "+FromDecimaltoHex(prom[1+i]));
            if(i!=2){
                field.appendText( "\n");
            }
        }
    }

    public void displayData(int[] dataarray, TextArea field){
        FromAPPtoWindow(field,toAPPview(dataarray));
    }

    public void displayData(int[] dataarray, TextArea field,String mode){
        if(mode.equals("Key")){
            FromAPPtoWindowKey(field,toAPPview(dataarray));
            return;
        }
        if(mode.equals("Table")){
            FromAPPtoWindowTable(field,toAPPview(dataarray));
            return;
        }
        if(mode.equals("Data")){
            FromAPPtoWindowData(field,toAPPview(dataarray));
            return;
        }
    }


    public void displayAll(){
        displayData(SAES.data,datatext,"Data");
        displayData(SAES.key,keytext,"Data");
        displayData(SAES.C,Constnttext);
        displayData(SAES.Cinv,InvConstnttext);
        displayData(SAES.SN,SNtext,"Table");
        displayData(SAES.ISN,ISNtext,"Table");
    }

    public String FromHextoDecimal(String number){
        String result =new String(Integer.toString(new BigInteger(number,16).intValue()));
        return result;
    }

    public String FromDecimaltoHex(String number){
        String result =Integer.toHexString(new BigInteger(number).intValue());
        return result;
    }


    public void initfunc(){
        isDataready=false;
        isSNready=false;
        isISNready=false;
        isDataready=false;
        isDataready=false;
    }


    public void DataTrasfer(TextArea text,int[] datafield,boolean isready) {
        String prepdata=text.getText().trim();
        prepdata=StringProcessing(prepdata);
        String[] outputdata =prepdata.split(" ");
        for (int i=0;i<datafield.length;i++){
            datafield[i]= RoundClass.FromHextoDecimal(outputdata[i]);
            //outputdata[i]=Integer.toString(new BigInteger(outputdata[i],16).intValue());

        }
        for(int i=0;i<datafield.length;i++){
            System.out.print(datafield[i]+"  ");
        }
        System.out.print("\n");

        isready=true;

    }

    public String StringProcessing (String prepdata){
        prepdata=prepdata.replace("\n"," ");
        prepdata=prepdata.replace("  "," ");
        if(String.valueOf(prepdata.charAt(prepdata.length()-1)).equals(String.valueOf(" "))){
            prepdata=prepdata.substring(0,prepdata.length()-1);
        }
        if(String.valueOf(prepdata.charAt(0)).equals(String.valueOf(" "))){
            prepdata=prepdata.substring(1,prepdata.length());
        }
        return prepdata;
    }

    public Button getButt(){
        return confirmback;
    }
}
