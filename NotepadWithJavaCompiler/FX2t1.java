/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package fx2t1;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javafx.stage.Stage;

/**
 *
 * @author abdelrahman
 */
public class FX2t1 extends Application {
    BorderPane pane;
    MenuBar bar;
    Menu file;
    MenuItem itemnew;
    MenuItem itemopen;
    MenuItem itemsave;
    MenuItem itemexit;
    MenuItem undo;
    MenuItem cut; 
    Menu edit;
    MenuItem copy;
    MenuItem Paste;
    MenuItem delete;
    MenuItem selectall;
    Menu help;
    MenuItem about;
    Menu compile;
    MenuItem OpenCompile;
    MenuItem compileC;
    
    TextArea area;
    @Override
    public void init() throws Exception 
    {
        bar=new MenuBar();
        /*---------------------------file------------------------------------*/
        file=new Menu("File");
        itemnew =new MenuItem("New File");
        itemopen =new MenuItem("Open File");
        itemsave =new MenuItem("Save File");
        itemexit =new MenuItem("Exit File");
        file.getItems().addAll(itemnew,itemopen,itemsave,itemexit);
        bar.getMenus().addAll(file);
        SeparatorMenuItem sep = new SeparatorMenuItem();
        file.getItems().add(3, sep); 
        /*---------------------------end file------------------------------------*/
        /*---------------------------Edit----------------------------------------*/
        edit=new Menu("Edit");
        undo =new MenuItem("Undo");
        cut =new MenuItem("Cut");
        copy =new MenuItem("Copy");
        Paste =new MenuItem("Paste");
        delete =new MenuItem("Delete");
        selectall =new MenuItem("Select All");
        edit.getItems().addAll(undo,cut,copy,Paste,delete,selectall);
        bar.getMenus().addAll(edit);
        SeparatorMenuItem sep1 = new SeparatorMenuItem();
        SeparatorMenuItem sep2 = new SeparatorMenuItem();
        edit.getItems().add(1, sep1);
        edit.getItems().add(6, sep2);
        /*---------------------------end Edit-------------------------------------*/
        /*-----------------------------help---------------------------------------*/
        help=new Menu("Help");
        about =new MenuItem("About notbad");
        help.getItems().addAll(about);
        bar.getMenus().addAll(help);
        /*-----------------------------end help-----------------------------------*/
        /*---------------------------Text Area------------------------------------*/
        area=new TextArea();
        area.setPrefColumnCount(100);
        area.setPrefRowCount(100);
        /*---------------------------end Text Area--------------------------------*/
        /*----------------------------compile------------------------------------------*/
        compile=new Menu("Compile");
        OpenCompile=new MenuItem("Open&Compile&Run");
        compileC=new MenuItem("Open&Compile&Run");
        compile.getItems().addAll(OpenCompile,compileC);
        bar.getMenus().add(2,compile);
        /*--------------------------------end compile----------------------------------*/
        /*---------------------------Edit Functonalty--------------------------------*/
        undo.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                area.undo();
            }
        });
        cut.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                area.cut();
            }
        });
        copy.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                area.copy();
            }
        });
        Paste.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                area.paste();
            }
        });
        delete.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                area.deleteText(area.getSelection());
            }
        });
        selectall.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                area.selectAll();
            }
        });
        /*---------------------------end Edit Functonalty--------------------------------*/      
        pane=new BorderPane();
        pane.setTop(bar);
        pane.setCenter(area);       
    }
    @Override
    public void start(Stage primaryStage) {       
        /*---------------------------save Functonalty--------------------------------*/
        //Creating a File chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
        itemsave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    //Opening a dialog box
                    File file =fileChooser.showSaveDialog(primaryStage);
                    if (file != null)
                    {
                        saveTextToFile(area.getText(), file);
                    }
                
            }
        });
        /*---------------------------end save Functonalty--------------------------------*/
        /*-----------------------------open Functonalty----------------------------------------*/      
        FileChooser fileChooser1 = new FileChooser();
        fileChooser1.setTitle("Open");
        fileChooser1.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
        //Adding action on the menu item
        itemopen.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) 
            {
                FileInputStream fis;
                //Opening a dialog box
                File file = fileChooser1.showOpenDialog(primaryStage);
                try 
                {
                    if (file != null) 
                    {
                    fis = new FileInputStream(file);
                    byte[] c = new byte[fis.available()];
                    fis.read(c);
                    fis.close();
                    String str=new String(c);
                    area.setText(str);
                    }
                } catch (IOException ex) {
                Logger.getLogger(FX2t1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        /*-----------------------------end open Functonalty----------------------------------------*/
        /*----------------------------------compile-------------------------------------------------------*/
        FileChooser fileChooser2 = new FileChooser();
        fileChooser2.setTitle("Compile");
        fileChooser2.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
        //Adding action on the menu item
        OpenCompile.setOnAction(e -> {
            FileInputStream fis;
            //Opening a dialog box
            File file1 = fileChooser2.showOpenDialog(primaryStage);
            try {
                if (file1 != null) {
                    fis = new FileInputStream(file1);
                    byte[] c = new byte[fis.available()];
                    char[] c1 = new char[(int) file1.length()];
                    fis.read(c);
                    fis.close();
                    FileReader filereader = new FileReader(file1);
                    filereader.read(c1);
                    filereader.close();
                    String str=new String(c1);
                    area.setText(str);
                    Process process2=null;
                    Process process1=null;
                    BufferedReader inStream=null;
                    //process1=Runtime.getRuntime().exec("javac "+file.getAbsolutePath());
                    process1 = Runtime.getRuntime().exec("javac " + file1.getAbsolutePath());
                    while(process1.isAlive());
                    String filename = new String(file1.getName());
                    String path = new String(file1.getAbsolutePath());
                    path=path.replaceAll(filename,"");
                    System.out.println(file1.getAbsolutePath());
                    //path.(path.length()-1);
                    path=path.substring(0,path.length()-1);
                    System.out.println(path);
                    filename=filename.replaceAll(".java", "");
                    System.out.println("java -classpath "+path+" "+filename);
                    process2 =Runtime.getRuntime().exec("java -classpath "+path+" "+filename);
                    inStream = new BufferedReader(new  InputStreamReader(process2.getInputStream()));
                    String str1=inStream.readLine();
                    System.out.println(str1);
                    //Creating a dialog
                    Dialog<String> dialog = new Dialog<String>();
                    //Setting the title
                    dialog.setTitle("Compile output");
                    ButtonType ok_button = new ButtonType("Ok", ButtonData.OK_DONE);
                    //Setting the content of the dialog
                    dialog.setContentText(str1);
                    //Adding buttons to the dialog pane
                    dialog.getDialogPane().getButtonTypes().add(ok_button);
                    //Setting the label
                    //Text txt = new Text("ok");
                    Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
                    // txt.setFont(font);
                    dialog.show();
                }
            }catch (IOException ex) {
                Logger.getLogger(FX2t1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
        /*----------------------------------end compile-------------------------------------------------------*/
        
        itemexit.setOnAction(e-> {
            Platform.exit();
        });         
        /*---------------------------help Functonalty-----------------------------------*/
        //Creating a dialog
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Dialog");
        ButtonType ok_button = new ButtonType("Ok", ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText("Write what matters");
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(ok_button);
        //Setting the label
        Text txt = new Text("Click the button to show the dialog");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        txt.setFont(font);
        about.setOnAction(e -> dialog.show());
        /*---------------------------end help Functonalty-----------------------------------*/       
        Scene scene = new Scene(pane, 300, 250);
        primaryStage.setTitle("Note pad Abdo");
        primaryStage.setScene(scene);
        primaryStage.show();
        List<String> items=new ArrayList<>();
        items.forEach(System.out::println);
        
    }
    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FX2t1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
}

