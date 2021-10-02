package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.AES.RoundClass;
import sample.DES.SDES;

import java.io.IOException;
import java.math.BigInteger;

public class ConfirmdataSDESController {

    @FXML
    private Button confirmall;

    @FXML
    private Button confirmS1;

    @FXML
    private TextArea S1text;

    @FXML
    private Button confirmIP;

    @FXML
    private Button confirmP;

    @FXML
    private TextField IPtext;

    @FXML
    private Button confirmS2;

    @FXML
    private TextArea S2text;

    @FXML
    private Button confirmresult;

    @FXML
    private Button confirmdata;

    @FXML
    private TextField datatext;

    @FXML
    private Button confirmkey;

    @FXML
    private TextField keytext;

    @FXML
    private Button confirmP8;

    @FXML
    private TextField P8text;

    @FXML
    private Button confirmP10;

    @FXML
    private TextField P10text;

    @FXML
    private Button confirmEP;

    @FXML
    private TextField EPtext;

    @FXML
    private Button confirmIPnv;

    @FXML
    private TextField IPinvtext;

    @FXML
    private TextField Ptext;

    @FXML
    private Button confirmback;

    public boolean isDataready=false;
    public boolean isKeyready=false;
    public boolean isIPready=false;
    public boolean isIPinvready=false;
    public boolean isEPready=false;
    public boolean isP8ready=false;
    public boolean isP10ready=false;
    public boolean isPready=false;
    public boolean isS1ready=false;
    public boolean isS2ready=false;


    @FXML
    void initialize() {


        initfunc();
        displayAll();

        
        confirmdata.setOnAction(event-> {
            System.out.println("Фега работает кнопка подтверждения ввода исходных данных ");
            SDES.Data=DataTrasfer(datatext, SDES.Data,isDataready);
            isDataready=true;
            System.out.println(SDES.Data);
        });

        confirmkey.setOnAction(event->{
            System.out.println("Фега работает кнопка подтверждения ввода данных о ключе");
            SDES.Key=DataTrasfer(keytext, SDES.Key,isKeyready);
            isKeyready=true;
        });

        confirmS1.setOnAction(event->{
            System.out.println("Фега работает кнопка подтверждения ввода данных о матрице S!");
            DataTrasfer(S1text, SDES.S1,isS1ready);
            isS1ready=true;
        });

        confirmS2.setOnAction(event->{
            System.out.println("Фега работает кнопка подтверждения ввода данных о матрице S!");
            DataTrasfer(S2text, SDES.S2,isS2ready);
            isS2ready=true;
        });

        confirmIP.setOnAction(event->{
            System.out.println("Фега работает кнопка подтверждения ввода данных о IP перестановке");
            DataTrasfer(IPtext,SDES.IP,isIPready);
            isIPready=true;
        });

        confirmIPnv.setOnAction(event->{
            System.out.println("Фега работает кнопка подтверждения ввода данных о ~IP перестановке");
            DataTrasfer(IPinvtext,SDES.IPinv,isIPinvready);
            isIPinvready=true;
        });

        confirmEP.setOnAction(event->{
            System.out.println("Фега работает кнопка подтверждения ввода данных о E/P сжатии");
            DataTrasfer(EPtext,SDES.EandP,isEPready);
            isEPready=true;
        });

        confirmP.setOnAction(event->{
            System.out.println("Фега работает кнопка подтверждения ввода данных о P сжатии");
            DataTrasfer(Ptext,SDES.P,isPready);
            isPready=true;
        });

        confirmP8.setOnAction(event->{
            System.out.println("Фега работает кнопка подтверждения ввода данных о сжатии P8");
            DataTrasfer(P8text,SDES.P8,isP8ready);
            isP8ready=true;
        });

        confirmP10.setOnAction(event->{
            System.out.println("Фега работает кнопка подтверждения ввода данных о сжатии P10");
            DataTrasfer(P10text,SDES.P10,isP10ready);
            isP10ready=true;
        });

        confirmresult.setOnAction(event->{

            if (isPready&&isIPready&&isS1ready&&isS2ready&&isDataready&&isKeyready&&isP8ready&&isP10ready&&isEPready) {
                confirmresult.getScene().getWindow().hide();
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/FXMLS/resultSDES.fxml"));
                try{
                    loader.load();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
                Parent root=loader.getRoot();
                Stage stage=new Stage();
                stage.setTitle("Я открылся!!!");
                stage.setScene(new Scene(root,926,659));
                stage.setResizable(false);
                stage.show();
            }
        });


        confirmall.setOnAction(event->{
            /*isDataready=true;
            isKeyready=true;
            isS1ready=true;
            isS2ready=true;
            isEPready=true;
            isP8ready=true;
            isP10ready=true;
            isPready=true;
            isIPready=true;
            isIPinvready=true;*/
            confirmdata.fire();
            confirmkey.fire();
            confirmS1.fire();
            confirmS2.fire();
            confirmEP.fire();
            confirmP8.fire();
            confirmP10.fire();
            confirmP.fire();
            confirmIP.fire();
            confirmIPnv.fire();
        });

        confirmback.setOnAction(event->{
            confirmback.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/FXMLS/startwindow.fxml"));
            try{
                loader.load();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setTitle("Я открылся!!!");
            stage.setScene(new Scene(root,926,659));
            stage.setResizable(false);
            stage.show();
        });


    }




    public String[] toAPPview(int[] dataarray){
        String[] arrayfromDATA=new String[dataarray.length];
        for(int i=0;i<dataarray.length;i++){
            arrayfromDATA[i]=String.valueOf(dataarray[i]);
        }
        return arrayfromDATA;
    }

    public String toAPPview(int data){
        return String.valueOf(data);
    }

    public void FromAPPtoWindow(TextField field,String[] text){
        for(int i=0;i<text.length;i++){
            field.appendText(text[i]);
            if(i!=(text.length-1)){
                field.appendText(" ");
            }
        }
    }

    public void FromAPPtoWindow(TextField field,String text){
        field.appendText(text);
    }

    public void FromAPPtoWindowTable(TextArea field,String[] text){
        for(int i=0;i<16;i+=4){

            field.appendText(text[0+i]+" "+text[1+i]+" "+text[2+i]+" "+text[3+i]);
            if(i!=12){
                field.appendText( "\n");
            }
        }
    }

    public void displayData(int[] dataarray, TextArea field){
        FromAPPtoWindowTable(field,toAPPview(dataarray));
    }

    public void displayData(int[] dataarray, TextField field){
        FromAPPtoWindow(field,toAPPview(dataarray));
    }

    public void displayData(int dataarray, TextField field){
        FromAPPtoWindow(field,toAPPview(dataarray));
    }

    public void displayAll(){
        displayData(SDES.Data,datatext);
        displayData(SDES.Key,keytext);
        displayData(SDES.S1,S1text);
        displayData(SDES.S2,S2text);
        displayData(SDES.IP,IPtext);
        displayData(SDES.IPinv,IPinvtext);
        displayData(SDES.P8,P8text);
        displayData(SDES.P10,P10text);
        displayData(SDES.EandP,EPtext);
        displayData(SDES.P,Ptext);

    }

    public void initfunc(){
        isDataready=false;
        isKeyready=false;
        isIPready=false;
        isIPinvready=false;
        isEPready=false;
        isP8ready=false;
        isP10ready=false;
        isPready=false;
        isS1ready=false;
        isS2ready=false;
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

    public void DataTrasfer(TextField text,int[] datafield,boolean isready) {
        String prepdata=text.getText().trim();
        prepdata=StringProcessing(prepdata);
        String[] outputdata =prepdata.split(" ");
        for (int i=0;i<datafield.length;i++){
            datafield[i]=Integer.parseInt(outputdata[i]);
            //outputdata[i]=Integer.toString(new BigInteger(outputdata[i],16).intValue());
        }

        for(int i=0;i<datafield.length;i++){
            System.out.print(datafield[i]+"  ");
        }

        System.out.print("\n");

        isready=true;

    }

    public int DataTrasfer(TextField text,int datafield,boolean isready) {
        String prepdata=text.getText().trim();
        datafield=Integer.parseInt(prepdata);
        System.out.println(datafield);
        isready=true;
        return datafield;

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


}
