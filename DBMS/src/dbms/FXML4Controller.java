package dbms;


import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;


public class FXML4Controller implements Initializable {

    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    Connection x;
    Statement s;
    PreparedStatement preparedStatement=null;
    String url="jdbc:sqlserver://localhost:1433;instanceName=ROBUST-PC\\SQLEXPRESS;databaseName=Flight_Management;user=Pavan;password=pavank830;";
  
    ArrayList<String> my,my1,my2;
    @FXML
    private TextField nametext;
    @FXML
    private TextField phnotext;
    @FXML
    private TextField unittext;
    @FXML
    private TextField airporttext;
    @FXML
    private TextField flighttext;
    @FXML
    private Label airport_city;
    @FXML
    private Label Flight_num;
    @FXML
    private Label label2;
    @FXML
    private Label label21;
    @FXML
    private Label label211;
    private Label label3;
    @FXML
    private AnchorPane pane3;
   
  String unit, name, phno, gender,address;
  int malerad=0,femalerad=0;
   int count=0;
        
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private Label label22;
    @FXML
    private Label label221;
    @FXML
    private AnchorPane pane4;
    @FXML
    private Label label34;
    @FXML
    private AnchorPane pane45;
    @FXML
    private Label label56;
    @FXML
    private Label label8;
    @FXML
    private Label label9;
    @FXML
    private TextField staffidtext;
    @FXML
    private Label label10;
   
    @FXML
    private AnchorPane nextpane;
    @FXML
    private TextField addresstext;
    @FXML
    private Label labeladdress;
    @FXML
    private Label labellimit;

    public FXML4Controller()
    {
       
        my = new ArrayList<>();
        try
        {
       
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(url);
       
        String sql="select unit_name from unit";
        
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
       
            my1 = new ArrayList<>();
       
        try
        {
       
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(url);
       
        String sql="select Airport_city from Airport";
        
        s=x.createStatement();
        ResultSet rx=s.executeQuery(sql);
        while(rx.next())
        {
            my1.add( rx.getString(1));
        }
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            System.out.print(e);

        }
        
       
         my2 = new ArrayList<>();
        try
        {
       
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(url);
       
        String sql="select leg_inst_num from leg_instance";
        
        s=x.createStatement();
        ResultSet rx=s.executeQuery(sql);
        while(rx.next())
        {
            my2.add( rx.getString(1));
        }
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            System.out.print(e);

        }

    }
        

     

          
    @Override
    public void initialize(URL ur, ResourceBundle rb) {
        pane1.setVisible(false);
        pane2.setVisible(false);
        TextFields.bindAutoCompletion(unittext, my);
          TextFields.bindAutoCompletion(airporttext, my1);
            TextFields.bindAutoCompletion(flighttext, my2);
            flighttext.setVisible(false);
        Flight_num.setVisible(false);
         airporttext.setVisible(false);
        airport_city.setVisible(false);
        label2.setVisible(false);
        label21.setVisible(false);
         label211.setVisible(false);
         label221.setVisible(false);
         label22.setVisible(false);
         labeladdress.setVisible(false);
         pane45.setVisible(false);
         ToggleGroup group=new ToggleGroup();
        male.setSelected(true);
        labellimit.setVisible(false);
        male.setToggleGroup(group);
        
        female.setToggleGroup(group);
          malerad=1;
        femalerad=0;
        
         pane4.setVisible(false);
         label34.setVisible(false);
       
            
        
      
    }    

    @FXML
    private void assign(ActionEvent event) throws SQLException {
    
        int p=0,y=0,t=0,y1=0;
         name=nametext.getText();
         phno=phnotext.getText();
        unit=unittext.getText();
        address=addresstext.getText();
        
        if(name==" "||!(name.matches("[a-zA-Z]+")))
        {
            label21.setVisible(true);
        }
         else
       {
            label21.setVisible(false);
           p=1;
       }
         if(phno==" "||!(phno.matches("[0-9]+")))
        {
            label211.setVisible(true);
        }
          else
       {
            label211.setVisible(false);
           y=1;
       }
         if(address.matches(""))
        {
            labeladdress.setVisible(true);
        }
          else
       {
            labeladdress.setVisible(false);
           y1=1;
       }
        
         if(malerad==1)
        {
            gender="M";
        }
        else if(femalerad==1)
        {
            gender="F";
        }
         if((unittext.getText()).equalsIgnoreCase("Ground-Crew")||(unittext.getText()).equalsIgnoreCase("Security"))
        {
             flighttext.setVisible(false);
        Flight_num.setVisible(false);
         airporttext.setVisible(true);
        airport_city.setVisible(true);
         label2.setVisible(false);
   t=1;
        }
         else if((unittext.getText()).equalsIgnoreCase("Flight-Crew"))
        {
             airporttext.setVisible(false);
        airport_city.setVisible(false);
         flighttext.setVisible(true);
        Flight_num.setVisible(true);
         label2.setVisible(false);
         t=1;
        }
         else
         {
             label2.setVisible(true);
             t=0;
         }    
         
         if(p==1&&y==1&&t==1&&y1==1)
         {
             pane1.setVisible(false);
        pane2.setVisible(true);
         }
         
         
    }

    @FXML
    private void addstaff(ActionEvent event) {
     
        pane1.setVisible(true);
            pane2.setVisible(false);
         pane4.setVisible(false);
         pane45.setVisible(false);
         label34.setVisible(true);
         labellimit.setVisible(false);
         nametext.clear();
        phnotext.clear();
    unittext.clear();
    addresstext.clear();
     airporttext.clear();
   flighttext.clear();
      try
        {
       
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(url);
         String sql="select staff_id from Staff order by staff_id DESC";
           preparedStatement = x.prepareStatement(sql);
       
        ResultSet rx=preparedStatement.executeQuery();
        while(rx.next())
        {
        String hco=rx.getString("staff_id");
        count=Integer.parseInt(hco);
        count=count+1;
        String co=Integer.toString(count);
      
        label3=new Label();
        label3.setVisible(true);
        label3.setStyle("-fx-alignment:Center;-fx-font-size:20px;-fx-font-weight:Bold;");
        label3.minWidth(57);
        label3.minHeight(25);
        label3.setText(co);
        pane3.getChildren().add(label3);
          break;
        }
        }
          catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            System.out.print(e);

        }
    }

    @FXML
    private void store(ActionEvent event) {
          pane3.getChildren().removeAll(label3);
         if((unittext.getText()).equalsIgnoreCase("Ground-Crew")||(unittext.getText()).equalsIgnoreCase("Security"))
        {
             String airport=airporttext.getText();
                  int i=0,j=0;
         
        if(airport == " "||!(airport.matches("[a-zA-Z]+"))&&i==0)
        {
            j=1;
           label22.setVisible(true);
        }
       else
        {
            if(airport.matches("[a-zA-Z]+"))
            {
            for(String ce:my1)
            {
                if(airport.equalsIgnoreCase(ce))
                {
                    i=1;
                   
                     label22.setVisible(false);
                     break;
                }
            }
           
            if(i==0&&j==0)
            {
                i=1;
               label22.setVisible(true);
            }
            }
        }
       
         try
        {
       
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(url);
           String qu = "{call Ground(?,?,?,?,?,?,?)}"; 
            CallableStatement statement = x.prepareCall(qu);  
            statement.setInt(1, count);
            statement.setString(2, name);
            statement.setString(3, phno);
            statement.setString(4, gender);
             statement.setString(5, address);
            statement.setString(6, unit);
            statement.setString(7, airport);
            statement.execute();
        }
         catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            System.out.print(e);

        }
         pane2.setVisible(false);
         pane4.setVisible(true);
         label34.setVisible(true);
   
        }
         else if((unittext.getText()).equalsIgnoreCase("Flight-Crew"))
        {
             String flight=flighttext.getText();
          int i=0,j=0;
         
        if(flight == " "||!(flight.matches("[0-9]+"))&&i==0)
        {
            j=1;
           label221.setVisible(true);
          
        }
       else
        {
            if(flight.matches("[0-9]+"))
            {
            for(String ce:my2)
            {
                
                if(flight.equalsIgnoreCase(ce))
                {
                    i=1;
                   
                     label221.setVisible(false);
                     break;
                }
            }
           
            if(i==0&&j==0)
            {
                i=1;
               label221.setVisible(true); 
             
            }
            }
        }
        
      
         try
        {
       
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(url);
           String qu = "{call FlightCrew(?,?,?,?,?,?,?)}"; 
            CallableStatement statement = x.prepareCall(qu);  
            statement.setInt(1, count);
            statement.setString(2, name);
            statement.setString(3, phno);
            statement.setString(4, gender);
               statement.setString(5, address);
            statement.setString(6, unit);
            statement.setString(7, flight);
            statement.execute();
        }
         catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            System.out.print(e);
            
        }
         int hl=0;
         
         try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(url);
             String sql="select name from staff where staff_id=?";
 
        preparedStatement = x.prepareStatement(sql);
        System.out.print(count);
        preparedStatement.setInt(1,count);
       ResultSet rs= preparedStatement.executeQuery();
            
       while(rs.next())
       {
           hl=1;
           break;
       }
         }
           catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            System.out.print(e);
            
        }
         if(hl==0)
         {
              
       pane2.setVisible(false);
         labellimit.setVisible(true);
         }
         else
         {
                pane2.setVisible(false);
              pane4.setVisible(true);
         label34.setVisible(true);
         }
        
       }
    }
    @FXML
    private void Backclick1(ActionEvent e) throws Exception
    {
          pane2.setVisible(false);
        pane1.setVisible(true);
        labellimit.setVisible(false);
    }
     @FXML
    private void Backclick2(ActionEvent e) throws Exception
    {
          pane1.setVisible(false);
    }
 @FXML
    private void Backclick3(ActionEvent e) throws Exception
    {
          pane45.setVisible(false);
    }
    @FXML
    private void maleradio(ActionEvent event) {
        malerad=1;
        femalerad=0;
    }

    @FXML
    private void femaleradio(ActionEvent event) {
          malerad=0;
        femalerad=1;
    }

    @FXML
    private void staffdeleted(ActionEvent event) throws SQLException {
         String stafftext=staffidtext.getText();
         label56.setVisible(false);
        label8.setVisible(false);
        label9.setVisible(false);
       
         if(stafftext==" "||!(stafftext.matches("[0-9]+")))
        {
            label9.setVisible(true);
            
        }
         else
       { 
           int id=Integer.parseInt(stafftext);
           label9.setVisible(false);
            int lp=0;
         try
        {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(url);
           String qu = "select staff_id from staff"; 
               int hl=0;
           ResultSet rx = s.executeQuery(qu);
     while(rx.next())
        {
           hl =rx.getInt("staff_id");   
           if(hl==id)
          {
              lp=1;
              break;
          }
           else
           {
               lp=0;
           }
        }
        }
         catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            System.out.print(e);

        }
         if(lp==0)
         {
            label8.setVisible(true);   
         }
         else
         {
             label8.setVisible(false);   
         try
        {
   
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(url);
           String qu = "{call delstaff(?)}"; 
            CallableStatement statement = x.prepareCall(qu);  
            statement.setInt(1, id);
            statement.execute();
            label56.setVisible(true);
        }
         catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            System.out.print(e);

        }
         
         }
       }
         
    }

    @FXML
    private void stadel(ActionEvent event) {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane4.setVisible(false);
        int u=0;
        pane45.setVisible(true);
        label10.setVisible(true);
        staffidtext.setVisible(true);
        label56.setVisible(false);
        label8.setVisible(false);
        label9.setVisible(false);
        staffidtext.clear();
           labellimit.setVisible(false);
       
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
          change(e,"FXMLDocument1.fxml");
    }

    @FXML
    private void securitynext(ActionEvent e) throws Exception
    {
        change(e,"FXML5.fxml");
        
    }
    
    
}
