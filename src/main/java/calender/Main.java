package calender;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    ListView<String> list = new ListView<String>();
    ObservableList<String> data = FXCollections.observableArrayList(
            "chocolate", "salmon", "gold", "coral", "darkorchid",
            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
            "blueviolet", "brown");
    @Override
    public void start(Stage stage) throws Exception {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainGUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 783, 434);
            stage.setTitle("iCal Helper");


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
                            iCal i = new iCal(file);
                            i.readCSV();
                        }
                    }
                    event.setDropCompleted(success);
                    event.consume();
                }
            });
            ObservableList<String> names = FXCollections.observableArrayList(
                    "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
            ListView<String> tableView = new ListView<String>(names);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}