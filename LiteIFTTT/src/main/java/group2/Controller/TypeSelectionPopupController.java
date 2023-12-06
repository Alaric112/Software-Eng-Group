/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group2.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;


// THIS CLASS FOR NOW IS USELESS
/**
 * FXML Controller class
 *
 * 
 * TO DO
 * TO DO
 * TO DO
 * TO DO
 * TO DO
 * TO DO
 * TO DO
 * TO DO
 * TO DO
 * TO DO
 * TO DO
 * 
 * @author patap
 */
public class TypeSelectionPopupController implements Initializable {

    @FXML
    private ListView<?> baseTypeListView;

//    @FXML
//    private ListView<String> baseTypeListView;
//    
//    private ObservableList<String> types;
//    
//    private Map<String, Image> typeImageMap = new HashMap<>();
//    
//    private static String selectedType;
//    private int listViewImageHeight = 16;
//    private int listViewImageWidth = 16;
    
//    RuleCreator ruleCreator =  RuleCreator.getInstance();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        if(!ruleCreator.getLastSelectedType().isEmpty()){
//            if(ruleCreator.getLastSelectedType().equalsIgnoreCase("trigger")){
//                            
//                this.types = FXCollections.observableArrayList(ruleCreator.getAvailableTriggerTypes());
//              
//            } else if(ruleCreator.getLastSelectedType().equalsIgnoreCase("action")){
//                
//                this.types = FXCollections.observableArrayList(ruleCreator.getAvailableActionTypes());
//                           
//            }
//            baseTypeListView.setItems(this.types);
//        } 
//        
//        List<String> objtypes = new ArrayList<>();
//       
//        
//        addDoubleClickEventHandler();
//        
//        try {
//            initializeTypeImageMap();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        
    }    
    
    private void initializeTypeImageMap() throws IOException{
//        
//       InputStream stream = new FileInputStream("src/main/resources/group2/Image/fixes.png");
//       typeImageMap.put("Generic", new Image(stream));
//     
    }
    
    public void setTypes(List<String> types) {
//        this.types = FXCollections.observableArrayList(types);
//        baseTypeListView.setItems(this.types);
        
//        // Add The Image to the listView
//        baseTypeListView.setCellFactory(listView -> new ListCell<String>() {
//        @Override
//        protected void updateItem(String item, boolean empty) {
//            super.updateItem(item, empty);
//            if (empty || item == null) {
//                setText(null);
//                setGraphic(null);
//            } else {
//                
//                setText(item);
//                Image image = typeImageMap.get(item);
//                if (image != null) {
//                    ImageView imageView = new ImageView(image);
//                    imageView.setFitHeight(listViewImageHeight); // Imposta l'altezza desiderata
//                    imageView.setFitWidth(listViewImageWidth); // Imposta la larghezza desiderata
//                    setGraphic(imageView);
//                }
//            }
//        }
//    });
        
    }
        


    
}
