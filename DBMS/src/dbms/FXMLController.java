package dbms;

import java.awt.HeadlessException;
import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

public class FXMLController   implements Initializable {
    Connection x;
    Statement s;
  //jdbc:sqlserver://<hostname>:<port>;instanceName=<instance_name>;databaseName=<database_name>
 //String url="jdbc:sqlserver://ROBUST-PC:1433;instanceName=ROBUST-PC\\SQLEXPRESS;databaseName=test;user=Pavan;password=pavank830;";
 String url="jdbc:sqlserver://localhost:1433;instanceName=ROBUST-PC\\SQLEXPRESS;databaseName=Flight_Management;user=Pavan;password=pavank830;";
    ArrayList<String> my;
    @FXML
    private TextField from,to;
    @FXML
    private AnchorPane AboutUspane,Homepane,ContactUspane,FAQpane;
   @FXML
    public   DatePicker dp;
static int jcb;
    static String c,c1;
 static String dept_airport, arri_airport;
    @FXML
    private Label label,label1,label13;
    @FXML
    private Button AboutUs;
    @FXML
    private Button ContactUs;
    @FXML
    private Button Home;
    @FXML
    private Button FAQ;
    @FXML
    private Button Submit;
    @FXML
    private RadioButton single;
    @FXML
    private RadioButton round;
    @FXML
    private Label label131;
    @FXML
    private DatePicker dp1;
    @FXML
    private Label arrival;
    @FXML
    private Button Back;
    public FXMLController()
    {
        my = new ArrayList<>();
        try
        {
       
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(url);
       
        String sql="select Airport_city from Airport";
        
        s=x.createStatement();
        ResultSet rx=s.executeQuery(sql);
        while(rx.next())
        {
           
            my.add( rx.getString(1));  
        }
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            System.out.print(e);

        }
        
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
   AboutUspane.setVisible(false);
   
     ContactUspane.setVisible(false);
     FAQpane.setVisible(false);
     label.setVisible(false);
     label1.setVisible(false);
     label13.setVisible(false);
     label131.setVisible(false);
          TextFields.bindAutoCompletion(from, my);
          TextFields.bindAutoCompletion(to, my);
           ToggleGroup group=new ToggleGroup();
        single.setSelected(true);
        
        single.setToggleGroup(group);
        
        round.setToggleGroup(group);
       dp1.setVisible(false); 
       arrival.setVisible(false);
    }   
    @FXML
    private void Roundradio(ActionEvent e) throws IOException
    {
       dp1.setVisible(true);
       arrival.setVisible(true);
    }
    @FXML
    private void Singleradio(ActionEvent e) throws IOException
    {
       dp1.setVisible(false);
       arrival.setVisible(false);
    }
    @FXML
    private void Backclick(ActionEvent e) throws Exception
    {
         Node node=(Node) e.getSource();
                     
  Stage stage=(Stage) node.getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument1.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
   @FXML
    private void Submit(ActionEvent e) throws Exception
    {
        int i=0,j=0,p=1,p1=1;
         c = dp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          String array1[]= c.split("-");
       int date=Integer.parseInt(array1[2]);
       int month=Integer.parseInt(array1[1]);
       int year=Integer.parseInt(array1[0]);
         if( date < 16||month<10||year<2018 ||c.matches(""))
         {
            label13.setVisible(true);
            System.out.print(date+" "+month+" "+year);
           
            p=0;
         }
         else
         {
              label13.setVisible(false); 
         }
         RadioButton ck=(RadioButton)round.getToggleGroup().getSelectedToggle();
         j=0;
         p1=1;
       
        
         if((ck.getText()).equalsIgnoreCase("Round-Way"))
        { p1=0;
             c1 = dp1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
             jcb=1;
       
          
                 String array11[]= c1.split("-");
       int date1=Integer.parseInt(array11[2]);
       int month1=Integer.parseInt(array11[1]);
       int year1=Integer.parseInt(array11[0]);
         if( date1 < 16||month1<10||year1<2018||c1.matches("") )
         {
            label131.setVisible(true);
            p1=0;
         }
         else
         {
              label131.setVisible(false); 
              p1=1;
         }
        }
        
        
            String departure_airport=from.getText();
            dept_airport=departure_airport;    
          String arrival_airport=to.getText();
           arri_airport=arrival_airport;
        
          
           if(!(dept_airport.equalsIgnoreCase(arri_airport)))
           {
   
      
        int k=0,l=0;
         
        if(departure_airport == " "||!(departure_airport.matches("[a-zA-Z]+"))&&i==0)
        {
            j=1;
           label.setVisible(true);
          
        }
       else
        {
            if(departure_airport.matches("[a-zA-Z]+"))
            {
            for(String c:my)
            {
                
                if(departure_airport.equalsIgnoreCase(c))
                {
                    i=1;
                    k=1;
                     label.setVisible(false);
                     break;
                }
            }
           
            if(i==0&&j==0)
            {
                i=1;
               label.setVisible(true); 
            
            }
            }
        }
        
        i=0;
        j=0;
         if(arrival_airport == " "||!(arrival_airport.matches("[a-zA-Z]+"))&&i==0)
        {
            j=1;
           label1.setVisible(true);
           //continue;
        }
       else
        {
        
             if(arrival_airport.matches("[a-zA-Z]+"))
            {
            for(String c:my)
            {
                
                if(arrival_airport.equalsIgnoreCase(c))
                {
                    i=1;
                   l=1;
                     label1.setVisible(false);
                    
                    
        break;
                    
                }
            }
           
            if(i==0&&j==0)
            {
                i=1;
               label1.setVisible(true); 
              
            }
            }
        }
         if(l==1&&k==1)
         {
             if(p==1&&p1==1)
             {
          Node node=(Node) e.getSource();
                     
  Stage stage=(Stage) node.getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource("FXML3.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
         }
         }
           }
            else
           {
           
                   label1.setVisible(true); 
                       label.setVisible(true); 
              
           }
    }
 
    @FXML
     private void Onhome(ActionEvent e) 
    {
    AboutUspane.setVisible(false);
     Homepane.setVisible(true);
     ContactUspane.setVisible(false);
     FAQpane.setVisible(false);
    }
    @FXML
      private void Onaboutus(ActionEvent e) 
    {
     AboutUspane.setVisible(true);
     Homepane.setVisible(false);
     ContactUspane.setVisible(false);
     FAQpane.setVisible(false);
    }
    @FXML
    public void Oncontactus(ActionEvent e) 
    { 
      
     AboutUspane.setVisible(false);
     Homepane.setVisible(false);
     ContactUspane.setVisible(true);
     FAQpane.setVisible(false);
    }
    @FXML
       public  void Onfaq(ActionEvent e) 
    {
     AboutUspane.setVisible(false);
     Homepane.setVisible(false);
     ContactUspane.setVisible(false);
     FAQpane.setVisible(true);
    }
}