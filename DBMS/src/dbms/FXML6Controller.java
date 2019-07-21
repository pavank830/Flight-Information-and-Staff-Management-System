
package dbms;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXML6Controller implements Initializable {
 Connection x;
    Statement s;
    String url="jdbc:sqlserver://localhost:1433;instanceName=ROBUST-PC\\SQLEXPRESS;databaseName=Flight_Management;user=Pavan;password=pavank830;";
    
   
   static int plko;
   static String staffid;
    @FXML
    private JFXTextField userTextField;
    @FXML
    private JFXPasswordField passsword;
   
    @FXML
    private Label label;

    @FXML
    private void change(ActionEvent e,String res) throws IOException  
    {
           Node node=(Node) e.getSource();
                     
  Stage stage=(Stage) node.getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource(res));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    
    }
    
      @FXML
    private void login(ActionEvent e) throws IOException  
    {
        
        String usernam=userTextField.getText();
        String pass=passsword.getText();
        System.out.print(pass);
         System.out.print(pass);
        if(usernam==" "||!(usernam.matches("[a-zA-Z]+"))||pass==" "||!(pass.matches("[0-9]+")))
        {
            label.setVisible(true);
             
         
        }
         else
       {
           System.out.print("sdfghjkl");
            label.setVisible(false);
           try
        {
            int p=1;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(url);
        String sql="select staff_id,name,unitno from staff";
        
        s=x.createStatement();
        ResultSet rx=s.executeQuery(sql);
        while(rx.next())
        {
            String pw=  rx.getString(1);
            staffid=pw;
           String nam=  rx.getString(2);
           plko=rx.getInt("unitno");
           if(nam.equalsIgnoreCase(usernam))
           {
               if(pw.equalsIgnoreCase(pass))
               {
                  
                   p=0;
                 
               change(e,"FXML9.fxml");
              
               }
               else
               {
                   
                   label.setVisible(true);
                   break;
               }
           }
        }
        if(p==1)
        {
                   label.setVisible(true);
        }
        }
        catch(HeadlessException | ClassNotFoundException | SQLException eh)
        {
            System.out.print(eh);
        }
       }
            
    
    }
      @FXML
    private void back(ActionEvent e) throws IOException  
    {
            change(e,"FXMLDocument1.fxml");
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       label.setVisible(false);
    }    
    
}
