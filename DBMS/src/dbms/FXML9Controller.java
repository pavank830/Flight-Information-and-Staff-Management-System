
package dbms;

import static dbms.FXML6Controller.plko;
import static dbms.FXML6Controller.staffid;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class FXML9Controller implements Initializable {
 Connection x;
    Statement s;
    PreparedStatement preparedStatement=null;
    String url="jdbc:sqlserver://localhost:1433;instanceName=ROBUST-PC\\SQLEXPRESS;databaseName=Flight_Management;user=Pavan;password=pavank830;";
  
    @FXML
    private Button Find;
    @FXML
    private TextField numberfield;
    @FXML
    private TextArea address;
    @FXML
    private Text unitno;
    @FXML
    private Text name;
    @FXML
    private Text id;
    @FXML
    private Text gender;
    @FXML
    private Text unitname;
    @FXML
    private Button save1;
    @FXML
    private Button save2;
    int idl=0;
    @FXML
    private Text airport;
    @FXML
    private Text airport1;
    @FXML
    private AnchorPane pane3;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox root;

    @Override
    public void initialize(URL ul, ResourceBundle rb) {
     System.out.println(plko);
     save1.setVisible(false);
     save2.setVisible(false);
       Find.setVisible(false);
       airport.setVisible(false);
        airport1.setVisible(false);
        pane3.setVisible(false);
          if(plko==3)
        {
            Find.setVisible(true);
        }
         
           try
        {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(url);
       String sql="select * from staff where staff_id=?";
           preparedStatement = x.prepareStatement(sql);
       preparedStatement.setString(1,staffid);
        ResultSet rx=preparedStatement.executeQuery();
        while(rx.next())
        {
           unitno.setText("Unitno:"+rx.getString("unitno"));  
           name.setText(rx.getString("name"));  
           id.setText("id:"+rx.getString("staff_id"));
          idl= rx.getInt("staff_id");
           String sql1="select unit_name from unit where Unit_no=?";
           preparedStatement = x.prepareStatement(sql1);
        
       preparedStatement.setInt(1,rx.getInt("unitno"));
        ResultSet rx1=preparedStatement.executeQuery();
        while(rx1.next())
        {
           unitname.setText(rx1.getString("unit_name"));  
         
        } 
        String gen=rx.getString("unitno");
           if(gen.matches("M"))
           gender.setText("MALE");
           else if(gen.matches("F"))
           gender.setText("FEMALE");
           numberfield.setText(rx.getString("phno"));
            address.setText(rx.getString("address"));
               if(rx.getInt("unitno")!=1)
               {
                   int airportid=0;
                 airport.setVisible(true);  
                  airport1.setVisible(true);  
                        String sql2="select airportid from Ground_crew where staffid=?";
                 preparedStatement = x.prepareStatement(sql2);

                   preparedStatement.setInt(1,idl);
                  ResultSet rx2=preparedStatement.executeQuery();
                 while(rx2.next())
                {
                    airportid=rx2.getInt("airportid");
                   
                }
                String sql3="select Airport_name,Airport_city from Airport where Airport_id=?";
                 preparedStatement = x.prepareStatement(sql3);

                   preparedStatement.setInt(1,airportid);
                  ResultSet rx3=preparedStatement.executeQuery();
                 while(rx3.next())
                {
                    airport.setText("AirportName:"+rx3.getString("Airport_name")); 
                   airport1.setText("AirportCity:"+rx3.getString("Airport_city")); 
                }
                
               }
               else
               {
                   pane3.setVisible(true);
                 try
        {
         String sql4="select leginstid from Flight_crew where staffid=?";
           preparedStatement = x.prepareStatement(sql4);
       preparedStatement.setInt(1,idl);
        ResultSet rx4=preparedStatement.executeQuery();
        while(rx4.next())
        {
            int leginstid=rx4.getInt("leginstid");
           String sql5="select leg_instance_date from leg_instance where leg_inst_num=?";
           preparedStatement = x.prepareStatement(sql5);
       preparedStatement.setInt(1,leginstid);
        ResultSet rx5=preparedStatement.executeQuery(); 
        while(rx5.next())
        {
        String flightno=Integer.toString(leginstid);
         String date=rx5.getString("leg_instance_date");
             Label flight=new Label();
              Label dat=new Label();
       
       AnchorPane pane = new AnchorPane();
        pane.setLayoutX(14.0);
                    pane.setLayoutY(137.0);
                    pane.setMinHeight(55.0);
                   pane.setMinWidth(197.0);
                    pane.setStyle("-fx-background-color: white;-fx-font-size:18px;-fx-border-color:red;");
                    flight.setText(flightno);
                    flight.setMinHeight(32);
                    flight.setMinWidth(67);
                    flight.setTranslateX(10);
                     
                     flight.setTextFill(Color.web("#0076a3"));
                    dat.setMinHeight(32);
                    dat.setMinWidth(67);
                    dat.setStyle("-fx-alignment:Center;-fx-font-size:18px;-fx-font-weight:Bold Italic;");
                       dat.setTranslateX(74);
                    
                       dat.setText(date);
                         
        pane.getChildren().addAll(flight,dat);
            root.getChildren().add(pane); 
          
        } 
        }
        }
          catch(HeadlessException | SQLException e)
        {
            System.out.print(e);
        }
         ScrollPane scroll = new ScrollPane();
scroll.setContent(root);
                   
               }
        }
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            System.out.print(e);
        }
        numberfield.setEditable(false);
          address.setEditable(false);
          
 
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
    private void Backclick(ActionEvent e) throws Exception
    {
          change(e,"FXML6.fxml");
    }
   @FXML
    private void Nextclick(ActionEvent e) throws Exception
    {
          change(e,"FXML7.fxml");
    }
    @FXML
    private void find(ActionEvent e) throws Exception
    {
          change(e,"FXML5.fxml");
    }
     @FXML
    private void onclick(ActionEvent e) throws Exception
    {
          numberfield.setEditable(true);
        save1.setVisible(true);
    }

    @FXML
    private void onclick1(ActionEvent event) {
       address.setEditable(true);
       save2.setVisible(true);
    }

    @FXML
    private void onclick3(ActionEvent event) {
        numberfield.setEditable(false);
        save1.setVisible(false);
        
        System.out.print(numberfield.getText());
        
           try
        {
     
       String sql="update staff set phno=? where staff_id=?";
           preparedStatement = x.prepareStatement(sql);
           preparedStatement.setString(1,numberfield.getText());
       preparedStatement.setInt(2,idl);
       ResultSet rs=preparedStatement.executeQuery();
       
        }
        catch(HeadlessException | SQLException e)
        {
           // System.out.print(e);
        }
    }

    @FXML
    private void onclick4(ActionEvent event) {
          address.setEditable(false);
          save2.setVisible(false);
             try
        {
     
       String sql="update staff set address=? where staff_id=?";
           preparedStatement = x.prepareStatement(sql);
           preparedStatement.setString(1,address.getText());
       preparedStatement.setInt(2,idl);
       ResultSet rs=preparedStatement.executeQuery();
       
        }
        catch(HeadlessException | SQLException e)
        {
           // System.out.print(e);
        }
    }
}
