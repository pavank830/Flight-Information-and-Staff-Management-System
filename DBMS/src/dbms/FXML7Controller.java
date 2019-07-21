
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
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class FXML7Controller implements Initializable {

   static String password="pavan";
    @FXML
    private AnchorPane pane44;
    @FXML
    private Label label;

    
    @FXML
    private PasswordField passwordtext;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane44.setVisible(false);
        passwordtext.clear();
        label.setVisible(false);
      
       
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
    private void submit(ActionEvent event) {
        if(passwordtext.getText().matches(password)){
          pane44.setVisible(true); 
          label.setVisible(false);
        }
          else{
          label.setVisible(true);   
        }
    }

    @FXML
    private void nextclick(ActionEvent e) throws IOException {
        change(e,"FXML4.fxml");
    }

    @FXML
    private void changepass(ActionEvent e) throws IOException {
         change(e,"FXML8.fxml");
    }

      @FXML
    private void Backclick(ActionEvent e) throws Exception
    {
          change(e,"FXML6.fxml");
    }
    
}
