
package dbms;


import java.awt.HeadlessException;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Robust
 */
public class FXML3Controller extends FXMLController  implements Initializable   {
   Connection x;
   Statement s;  
 String ur="jdbc:sqlserver://localhost:1433;instanceName=ROBUST-PC\\SQLEXPRESS;databaseName=Flight_Management;user=Pavan;password=pavank830;";
  
 @FXML
    private VBox root;
  PreparedStatement preparedStatement = null;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Label label1;
    @FXML
    private Label label11;
    @FXML
    private Label label12;
    @FXML
    private Label label13;
    @FXML
    private Label label14;
    @FXML
    private Label label141;
    @FXML
    private AnchorPane anchor1;
    @FXML
    private ScrollPane scroll1;
    @FXML
    private VBox root1;
    @FXML
    private Label ski;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       System.out.print("p"+c+dept_airport+arri_airport);
       label1.setVisible(false);
       label14.setVisible(false);
       label13.setVisible(false);
       label11.setText(arri_airport);
       label12.setText(dept_airport);
       label141.setText("-->"); 
       anchor1.setVisible(false);
       scroll1.setVisible(false);
       root1.setVisible(false);
       try
        {
            
            
       int dept_code=0,arri_code=0;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(ur);
        String sql1="select Airport_id from Airport where Airport_city=?";
      
        preparedStatement = x.prepareStatement(sql1);
	preparedStatement.setString(1,dept_airport);
        ResultSet rx;//=s.executeQuery(sql);
     rx = preparedStatement.executeQuery();
     while(rx.next())
        {
     dept_code=rx.getInt("Airport_id");     
        }
    

        preparedStatement = x.prepareStatement(sql1);
	preparedStatement.setString(1,arri_airport);
     
     rx = preparedStatement.executeQuery();
   
              while(rx.next())
        {
     arri_code=rx.getInt("Airport_id");   
        }
    
 
     
       String sql2="select leg_number from leg where departure_airport_code=? and arrival_airport_code=?";
       
        preparedStatement = x.prepareStatement(sql2);
	preparedStatement.setInt(1,dept_code);
	preparedStatement.setInt(2,arri_code);
          rx = preparedStatement.executeQuery();
   int legnum=0;
              while(rx.next())
        {
     legnum=rx.getInt("leg_number");   
        }
              System.out.print(legnum);
        
        String sql3="select departure_time,arrival_time,leg_inst_num,Flightnum,fare from Leg_instance where leg_instance_date=? and leg_number=?;";
        
        preparedStatement = x.prepareStatement(sql3);
	preparedStatement.setString(1,c);
        preparedStatement.setInt(2,legnum);
      
     rx = preparedStatement.executeQuery();
        while(rx.next())
        {
            String d1_time=rx.getString(1);
            String d_time;
            d_time= d1_time.substring(0, 8);
            String a1_time=rx.getString(2);
            String a_time;
            a_time= a1_time.substring(0, 8);
           int  leginstnum=rx.getInt("leg_inst_num");
            int flinum=rx.getInt("Flightnum");
            String fr=rx.getString("fare");
            String far;
            far= fr.substring(0, 7);
             String sql4="select Airplaneid,food,Baggage_limit from Flight where Flight_num=?";
      
        preparedStatement = x.prepareStatement(sql4);
	preparedStatement.setInt(1,flinum);
     System.out.print("jkl");
        ResultSet rx1;//=s.executeQuery(sql);
     rx1 = preparedStatement.executeQuery();
     
     
      
        
            Label dtime =new Label();
            Label atime=new Label();
             Label food=new Label();
              Label Baggage=new Label();
              Label fare=new Label();
              Label aseats=new Label();
       
       AnchorPane pane = new AnchorPane();
        pane.setLayoutX(14.0);
                    pane.setLayoutY(137.0);
                    pane.setMinHeight(50.0);
                   pane.setMinWidth(480.0);
                    pane.setStyle("-fx-background-color: white;-fx-border-color:red;");
                  
                 
                    dtime.setText(d_time);
                    dtime.setMinHeight(47);
                    dtime.setMinWidth(39);
                    dtime.setTranslateX(10);
                     dtime.setTextFill(Color.web("#0076a3"));
                    atime.setMinHeight(47);
                    atime.setMinWidth(39);
                    atime.setStyle("-fx-alignment:Center;-fx-font-size:14px;-fx-font-weight:Bold Italic;");
                       atime.setTranslateX(86);
                    atime.setText(a_time);
                           food.setMinHeight(47);
                    food.setMinWidth(39);
                    food.setStyle("-fx-alignment:Center;-fx-font-size:14px;-fx-font-weight:Bold Italic;");
                       food.setTranslateX(282);
                        Baggage.setMinHeight(47);
                    Baggage.setMinWidth(39);
                    Baggage.setStyle("-fx-alignment:Center;-fx-font-size:14px;-fx-font-weight:Bold Italic;");
                       Baggage.setTranslateX(186);
                        fare.setMinHeight(47);
                    fare.setMinWidth(59);
                    fare.setStyle("-fx-alignment:Center;-fx-font-size:14px;-fx-font-weight:Bold Italic;");
                       fare.setTranslateX(344);
                       fare.setText(far);
                        aseats.setMinHeight(47);
                    aseats.setMinWidth(59);
                    aseats.setStyle("-fx-alignment:Center;-fx-font-size:14px;-fx-font-weight:Bold Italic;");
                       aseats.setTranslateX(414);
                    
              while(rx1.next())
                     {
                          String foode=rx1.getString("food"); 
                            food.setText(foode);
                             String Baggag=rx1.getString("Baggage_limit"); 
                            Baggage.setText(Baggag);
                            System.out.print(Baggag);
                            int airplid=rx1.getInt("Airplaneid");
     System.out.print(airplid);
            String sql5="select TotalNumberOfSeats from Airplane where Airplane_id=?";
      
        preparedStatement = x.prepareStatement(sql5);
	preparedStatement.setInt(1,airplid);
     
        ResultSet rx2;//=s.executeQuery(sql);
     rx2 = preparedStatement.executeQuery();
     while(rx2.next())
     {
     int toatlseats=rx2.getInt("TotalNumberOfSeats");
     
     String sql6="select count(*) as t from Reserved_seats where leginstnum=?";
      
        preparedStatement = x.prepareStatement(sql6);
	preparedStatement.setInt(1,leginstnum);
     
        ResultSet rx3;//=s.executeQuery(sql);
     rx3 = preparedStatement.executeQuery();
                     while(rx3.next())
                        {
                          int seats=rx3.getInt("t");
                     int availseats=toatlseats-seats;
                     if(availseats<=0)
                     {
                       String ase="N/A";
                       aseats.setText(ase);
                     } 
                     else
                     {String ase=Integer.toString(availseats);
                       aseats.setText(ase);
                         
                     }
                    }
        }
    }             
                        pane.getChildren().addAll(dtime,atime,food,Baggage,fare,aseats);
       root.getChildren().add(pane);
          
        }
        }
        
    catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            System.out.print(e);

        }
        
        ScrollPane scroll = new ScrollPane();
scroll.setContent(root);
    
   
    
    ///////////////////////
       // System.out.print("p"+c+dept_airport+arri_airport);
       if(jcb==1)
       {    
            label1.setVisible(true);
       label14.setVisible(true);
       label13.setVisible(true);
       label1.setText(arri_airport);
       label13.setText(dept_airport);
       label14.setText("-->");
       anchor1.setVisible(true);
       scroll1.setVisible(true);
       root1.setVisible(true);
       ski.setVisible(false);
        try
        {
            
            
       int dept_code=0,arri_code=0;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        x=DriverManager.getConnection(ur);
        String sql1="select Airport_id from Airport where Airport_city=?";
      
        preparedStatement = x.prepareStatement(sql1);
	preparedStatement.setString(1,arri_airport);
        ResultSet rx;//=s.executeQuery(sql);
     rx = preparedStatement.executeQuery();
     while(rx.next())
        {
     dept_code=rx.getInt("Airport_id");     
        }
    

        preparedStatement = x.prepareStatement(sql1);
	preparedStatement.setString(1,dept_airport);
        
     rx = preparedStatement.executeQuery();
   
              while(rx.next())
        {
     arri_code=rx.getInt("Airport_id");   
        }
    
 
     
       String sql2="select leg_number from leg where departure_airport_code=? and arrival_airport_code=?";
       
        preparedStatement = x.prepareStatement(sql2);
	preparedStatement.setInt(1,dept_code);
	preparedStatement.setInt(2,arri_code);
          rx = preparedStatement.executeQuery();
   int legnum=0;
              while(rx.next())
        {
     legnum=rx.getInt("leg_number");   
        }
              System.out.print(legnum);
        
        String sql3="select departure_time,arrival_time,leg_inst_num,Flightnum,fare from Leg_instance where leg_instance_date=? and leg_number=?;";
        
        preparedStatement = x.prepareStatement(sql3);
	preparedStatement.setString(1,c1);
        preparedStatement.setInt(2,legnum);
      
     rx = preparedStatement.executeQuery();
        while(rx.next())
        {
            String d1_time=rx.getString(1);
            String d_time;
            d_time= d1_time.substring(0, 8);
            String a1_time=rx.getString(2);
            String a_time;
            a_time= a1_time.substring(0, 8);
           int  leginstnum=rx.getInt("leg_inst_num");
            int flinum=rx.getInt("Flightnum");
            String fr=rx.getString("fare");
            String far;
            far= fr.substring(0, 7);
             String sql4="select Airplaneid,food,Baggage_limit from Flight where Flight_num=?";
      
        preparedStatement = x.prepareStatement(sql4);
	preparedStatement.setInt(1,flinum);
     System.out.print("jkl");
        ResultSet rx1;//=s.executeQuery(sql);
     rx1 = preparedStatement.executeQuery();
     
     
      
        
            Label dtime =new Label();
            Label atime=new Label();
             Label food=new Label();
              Label Baggage=new Label();
              Label fare=new Label();
              Label aseats=new Label();
       
       AnchorPane pane = new AnchorPane();
        pane.setLayoutX(14.0);
                    pane.setLayoutY(137.0);
                    pane.setMinHeight(50.0);
                   pane.setMinWidth(480.0);
                    pane.setStyle("-fx-background-color: white;-fx-border-color:red;");
                  
                 
                    dtime.setText(d_time);
                    dtime.setMinHeight(47);
                    dtime.setMinWidth(39);
                    dtime.setTranslateX(10);
                     dtime.setTextFill(Color.web("#0076a3"));
                    atime.setMinHeight(47);
                    atime.setMinWidth(39);
                    atime.setStyle("-fx-alignment:Center;-fx-font-size:14px;-fx-font-weight:Bold Italic;");
                       atime.setTranslateX(86);
                    atime.setText(a_time);
                           food.setMinHeight(47);
                    food.setMinWidth(39);
                    food.setStyle("-fx-alignment:Center;-fx-font-size:14px;-fx-font-weight:Bold Italic;");
                       food.setTranslateX(282);
                        Baggage.setMinHeight(47);
                    Baggage.setMinWidth(39);
                    Baggage.setStyle("-fx-alignment:Center;-fx-font-size:14px;-fx-font-weight:Bold Italic;");
                       Baggage.setTranslateX(186);
                        fare.setMinHeight(47);
                    fare.setMinWidth(59);
                    fare.setStyle("-fx-alignment:Center;-fx-font-size:14px;-fx-font-weight:Bold Italic;");
                       fare.setTranslateX(344);
                       fare.setText(far);
                        aseats.setMinHeight(47);
                    aseats.setMinWidth(59);
                    aseats.setStyle("-fx-alignment:Center;-fx-font-size:14px;-fx-font-weight:Bold Italic;");
                       aseats.setTranslateX(414);
                   
                       while(rx1.next())
                     {
                          String foode=rx1.getString("food"); 
                            food.setText(foode);
                             String Baggag=rx1.getString("Baggage_limit"); 
                            Baggage.setText(Baggag);
                            System.out.print(Baggag);
                            int airplid=rx1.getInt("Airplaneid");
     System.out.print(airplid);
            String sql5="select TotalNumberOfSeats from Airplane where Airplane_id=?";
      
        preparedStatement = x.prepareStatement(sql5);
	preparedStatement.setInt(1,airplid);
     
        ResultSet rx2;//=s.executeQuery(sql);
     rx2 = preparedStatement.executeQuery();
     while(rx2.next())
     {
     int toatlseats=rx2.getInt("TotalNumberOfSeats");
     
     String sql6="select count(*) as t from Reserved_seats where leginstnum=?";
      
        preparedStatement = x.prepareStatement(sql6);
	preparedStatement.setInt(1,leginstnum);
     
        ResultSet rx3;//=s.executeQuery(sql);
     rx3 = preparedStatement.executeQuery();
                     while(rx3.next())
                        {
                          int seats=rx3.getInt("t");
                     int availseats=toatlseats-seats;
                     if(availseats<=0)
                     {
                       String ase="N/A";
                       aseats.setText(ase);
                     } 
                     else
                     {String ase=Integer.toString(availseats);
                       aseats.setText(ase);
                         
                     }
                    }
        }
    }             
                        pane.getChildren().addAll(dtime,atime,food,Baggage,fare,aseats);
       root1.getChildren().add(pane);
            //System.out.print(d);
        }
        }
        
    catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            System.out.print(e);

        }
       
        ScrollPane scroll1 = new ScrollPane();
scroll1.setContent(root1);
    
}
    }
      @FXML
    private void Backonclick(ActionEvent e) throws Exception
    {
        jcb=0;
        c="";
        c1="";
        dept_airport="";
                arri_airport="";
         Node node=(Node) e.getSource();
                     
  Stage stage=(Stage) node.getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
}
















/*  public FXML3Controller()
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
            String d=rx.getString(1);
            System.out.print(d);
            /* ObservableList row = FXCollections.observableArrayList();

                //for (int i = 1; i <= rx.getMetaData().getColumnCount(); i++)
                //{
                    row.add(rx.getString(1));
                    System.out.println(row);;
          
           
                //} 
            data.add( row);
          
           
            my.add(d);
        
        }
       
        //tet.getItems().add(my);
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            System.out.print(e);

        }
        
    }  */
