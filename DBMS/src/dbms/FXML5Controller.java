package dbms;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;


public class FXML5Controller implements Initializable {
 Connection x;
    Statement s;
    String url="jdbc:sqlserver://localhost:1433;instanceName=ROBUST-PC\\SQLEXPRESS;databaseName=Flight_Management;user=Pavan;password=pavank830;";
    ArrayList<String> my;
    int lok=0;
    @FXML
    private TextField flighttextfield;
   
    @FXML
    private AnchorPane label2;
    @FXML
    private Label label;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox pane2;
   

   PreparedStatement preparedStatement =null;
    
    @Override
    public void initialize(URL ul, ResourceBundle rb) {
        my = new ArrayList<>();
        label2.setVisible(false);
        label.setVisible(false);
       pane2.setVisible(false);
       scroll.setVisible(false);
        try
        {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(url);
        String sql="select leg_inst_num from leg_instance";
        
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
         
         TextFields.bindAutoCompletion(flighttextfield, my);
    
    }   
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
    private void backclick(ActionEvent e) throws IOException  
    {
            change(e,"FXMLDocument1.fxml");
    
    }
    
      @FXML
    private void clear(ActionEvent e) throws IOException  
    {
            change(e,"FXML5.fxml");
    }

    @FXML
    private void submitclick(ActionEvent event)  {
        
        label2.setVisible(false);
       
       pane2.setVisible(false);
       scroll.setVisible(false);
       
         int k=0,l=0;
         int i=0,j=0;
         String leginst=flighttextfield.getText();
         System.out.print(leginst);
        if(leginst == " "||!(leginst.matches("[0-9]+"))&&i==0)
        {
            j=1;
           label.setVisible(true);
          
        }
       else
        {
            if(leginst.matches("[0-9]+"))
            {
            for(String c:my)
            {
                
                if(leginst.equalsIgnoreCase(c))
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
        if(k==1)
        {
            label2.setVisible(true);
            pane2.setVisible(true);
            scroll.setVisible(true);
            int leginstnumber=Integer.parseInt(leginst);
         try
        {
       
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(url);
         String sql="select customer_name,customer_phno from Reserved_seats where leginstnum=?";
           preparedStatement = x.prepareStatement(sql);
       preparedStatement.setInt(1,leginstnumber);
        ResultSet rx=preparedStatement.executeQuery();
        while(rx.next())
        {
        String custname=rx.getString("customer_name");
         String custphno=rx.getString("customer_phno");
             Label customernam=new Label();
              Label cusphno=new Label();
       
       AnchorPane pane = new AnchorPane();
        pane.setLayoutX(14.0);
                    pane.setLayoutY(137.0);
                    pane.setMinHeight(52.0);
                   pane.setMinWidth(478.0);
                    pane.setStyle("-fx-background-color: white;-fx-border-color:red;");
                  
             System.out.print(custname);
                    customernam.setText(custname);
                    customernam.setMinHeight(40);
                    customernam.setMinWidth(111);
                    customernam.setTranslateX(30);
                     customernam.setTranslateY(5);
                     customernam.setTextFill(Color.web("#0076a3"));
                    cusphno.setMinHeight(40);
                    cusphno.setMinWidth(111);
                    cusphno.setStyle("-fx-alignment:Center;-fx-font-size:14px;-fx-font-weight:Bold Italic;");
                       cusphno.setTranslateX(265);
                       cusphno.setTranslateY(5);
                       cusphno.setText(custphno);
                         
        pane.getChildren().addAll(customernam,cusphno);
            pane2.getChildren().add(pane); 
            lok=1;
          
        }
        }
          catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            System.out.print(e);
        }
         ScrollPane scroll = new ScrollPane();
scroll.setContent(pane2);
    }
  
    flighttextfield.clear();
}
}




             