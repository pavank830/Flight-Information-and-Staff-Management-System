
package dbms;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class FXMLDocument1Controller implements Initializable {
    
    @FXML
    private Label label;
    private AnchorPane rootpane;
   
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
       private void Customer(ActionEvent e) throws IOException
    {
        
      change(e,"FXML.fxml");
      
    }
       @FXML
          private void Staffbt(ActionEvent e) throws IOException
    {
      change(e,"FXML6.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    
    
    
}
