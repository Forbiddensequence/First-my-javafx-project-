package sample;
import Aladdin.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
//import Aladdin.Hasp;
//import Aladdin.HaspStatus;


public class Verifier {

    public static long feature = Hasp.HASP_DEFAULT_FID;
    public static String vendorCode =
                    "AzIceaqfA1hX5wS+M8cGnYh5ceevUnOZIzJBbXFD6dgf3tBkb9cvUF/Tkd/iKu2fsg9wAysYKw7RMAsV" +
                    "vIp4KcXle/v1RaXrLVnNBJ2H2DmrbUMOZbQUFXe698qmJsqNpLXRA367xpZ54i8kC5DTXwDhfxWTOZrB" +
                    "rh5sRKHcoVLumztIQjgWh37AzmSd1bLOfUGI0xjAL9zJWO3fRaeB0NS2KlmoKaVT5Y04zZEc06waU2r6" +
                    "AU2Dc4uipJqJmObqKM+tfNKAS0rZr5IudRiC7pUwnmtaHRe5fgSI8M7yvypvm+13Wm4Gwd4VnYiZvSxf" +
                    "8ImN3ZOG9wEzfyMIlH2+rKPUVHI+igsqla0Wd9m7ZUR9vFotj1uYV0OzG7hX0+huN2E/IdgLDjbiapj1" +
                    "e2fKHrMmGFaIvI6xzzJIQJF9GiRZ7+0jNFLKSyzX/K3JAyFrIPObfwM+y+zAgE1sWcZ1YnuBhICyRHBh" +
                    "aJDKIZL8MywrEfB2yF+R3k9wFG1oN48gSLyfrfEKuB/qgNp+BeTruWUk0AwRE9XVMUuRbjpxa4YA67SK" +
                    "unFEgFGgUfHBeHJTivvUl0u4Dki1UKAT973P+nXy2O0u239If/kRpNUVhMg8kpk7s8i6Arp7l/705/bL" +
                    "Cx4kN5hHHSXIqkiG9tHdeNV8VYo5+72hgaCx3/uVoVLmtvxbOIvo120uTJbuLVTvT8KtsOlb3DxwUrwL" +
                    "zaEMoAQAFk6Q9bNipHxfkRQER4kR7IYTMzSoW5mxh3H9O8Ge5BqVeYMEW36q9wnOYfxOLNw6yQMf8f9s" +
                    "JN4KhZty02xm707S7VEfJJ1KNq7b5pP/3RjE0IKtB2gE6vAPRvRLzEohu0m7q1aUp8wAvSiqjZy7FLaT" +
                    "tLEApXYvLvz6PEJdj4TegCZugj7c8bIOEqLXmloZ6EgVnjQ7/ttys7VFITB3mazzFiyQuKf4J6+b/a/Y";

    public static String[] abbs;
    public static Hasp hasp = new Hasp(feature);
    public static String keyID = "673047327";
    public static boolean isSessionopened=false;
    public static boolean isButtonHopeActive=false;
    public static Button startWindowButton=null;
    public static String scope =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
                    "<haspscope/>";

    public static void BeginCheck() throws Exception{

        //System.out.println("Проверка начата");
        while(true){
            if(KeyInsert()){
                if(!isSessionopened){
                    login();
                    int status = hasp.getLastError();
                    if (HaspStatus.HASP_STATUS_OK != status)
                    {
                        isSessionopened=false;
                    }
                    else{
                        isSessionopened=true;
                    }
                }

            }
            else{
                if(isSessionopened){
                    logout();
                    int status = hasp.getLastError();
                    if (HaspStatus.HASP_STATUS_OK != status)
                    {
                        isSessionopened=true;
                    }
                    else{
                        isSessionopened=false;
                    }
                }
            }

            if(!isSessionopened){
                //System.out.println("Сессия закрыта");
                isButtonHopeActive=false;
            }
            else{
                //System.out.println("Сессия открыта");
                String test= getKeyID();
                //System.out.println(test);
                if(!test.equals(keyID)){
                    isButtonHopeActive=false;
                }
                else{
                    isButtonHopeActive=true;
                }
            }
            /*if(!isButtonHopeActive&&!Main.CurrentButtonScene.equals(startWindowButton)){
                Main.CurrentButtonScene.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/FXMLS/confirmdataSDES.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Я открылся!!!");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            }

             */
        }
        //System.out.println("ПОлучается что все плохо");
    }

    public static void login(){
        hasp.login(vendorCode);
        //System.out.println("Сессия создана");
    }

    public static void logout(){
        hasp.logout();
        //System.out.println("Сессия убита");
    }

    public static boolean KeyInsert(){
        String info;
        info =getSessioninfo();
        if(info==null){
            return false;
        }
        else {
            //System.out.println(info);
            return true;
        }
    }

    public static void  reopenAPP()throws Exception{
        Platform.exit();
        System.exit(0);
        //sample.Main.main(abbs);
    }

    public static String getSessioninfo(){
        String scope =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
                        "<haspscope/>";

        String format =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
                        "<haspformat root=\"hasp_info\">" +
                        "    <hasp>" +
                        "        <attribute name=\"id\" />" +
                        "        <attribute name=\"type\" />" +
                        "        <feature>" +
                        "            <attribute name=\"id\" />" +
                        "        </feature>" +
                        "    </hasp>" +
                        "</haspformat>";

        String info;
        info = hasp.getInfo(scope, format, vendorCode);
        return info;
    }

    public static String getKeyID(){
        String info=getSessioninfo();
        String begin=new String("hasp id=");
        String end=new String(" type=");
        if(info==null){
            return new String("Неполучилось");
        }
        int firstindex=info.indexOf(begin);
        int lestindex=info.indexOf(end);
        String test=info.substring(firstindex+begin.length()+1,lestindex-1);
        return test;
    }

    public static String encrypt(String input){
        while(input.length()<16){
            String addit=new String(" ");
            input=addit.concat(input);
        }
        //System.out.println(input);
        byte[] byteArray = input.getBytes();
        hasp.encrypt(byteArray);
        return new String(byteArray);
    }

    public static String decrypt(String input){
        while(input.length()<16){
            String addit=new String(" ");
            input=addit.concat(input);
        }
        byte[] byteArray = input.getBytes();
        hasp.decrypt(byteArray);
        return new String(byteArray);
    }
}
