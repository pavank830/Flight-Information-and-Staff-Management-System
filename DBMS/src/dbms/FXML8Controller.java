
package dbms;
import static dbms.FXML7Controller.password;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.stage.Stage;

public class FXML8Controller implements Initializable {

    @FXML
    private JFXPasswordField newpass;
    @FXML
    private JFXPasswordField confirm;
    @FXML
    private Label lab;
    @FXML
    private Label lab1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lab.setVisible(false);
        lab1.setVisible(false);
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
    private void nextclick(ActionEvent e) throws IOException {
        change(e,"FXML4.fxml");
    }

    @FXML
    private void save(ActionEvent event) {
        if(confirm.getText().matches(newpass.getText())){
            lab.setVisible(false);
            lab1.setVisible(true);
            password=newpass.getText();
        }
        else
        {
            lab1.setVisible(false);
            lab.setVisible(true);
            
        }
    }

    
}
