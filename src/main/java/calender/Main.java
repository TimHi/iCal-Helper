package calender;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class Main extends Application {

    public void fillList(ListView listView, iCal i){
        ObservableList<String> items = FXCollections.observableArrayList ();
        List<String> tItems = i.getEventString();
        for(int o = 0; o < tItems.size(); o++){
            items.add(tItems.get(o));
        }
        listView.setItems(items);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        FXMLLoader loader = new FXMLLoader();
 //       loader.setLocation(getClass().getResource("/MainGUI.fxml"));
//        Parent root = loader.load();
        stage.setTitle("iCal Helper");
        iCal i = new iCal();




        ListView listView = new ListView<String>();
        VBox vbox = new VBox(listView);

        Scene scene = new Scene(vbox, 783, 434);
        stage.setScene(scene);
        stage.show();


        scene.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        // Dropping over surface
        scene.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    for (File file:db.getFiles()) {
                        filePath = file.getAbsolutePath();
                        System.out.println(filePath);
                        i.setFile(file);
                        i.readCSV();

                    }
                }
                event.setDropCompleted(success);
                event.consume();
                fillList(listView, i);
            }
        });

        //stage.setScene(scene);
        //stage.show();

    }

}