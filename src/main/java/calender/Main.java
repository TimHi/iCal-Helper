package calender;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class Main extends Application {

    private static final TextArea textArea = new TextArea();
    //add textArea to your scene somewhere in the start method
    public static void println(String s){
        Platform.runLater(new Runnable() {//in case you call from other thread
            @Override
            public void run() {
                textArea.setText(textArea.getText()+s+"\n");
            }
        });
    }

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
        stage.setTitle("iCal Helper");
        iCal i = new iCal();

        ListView listView = new ListView<String>();
        VBox vbox = new VBox(listView, textArea);

        Scene scene = new Scene(vbox, 800, 500);
        stage.setScene(scene);
        stage.show();
        Main.println("Ready");
        scene.setOnDragOver((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                textArea.clear();
                Main.println("Drop File to start");
                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
        });

        scene.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                success = true;
                String filePath = null;
                for (File file:db.getFiles()) {
                    Main.println("File Received");
                    filePath = file.getAbsolutePath();
                    i.setFileName(filePath);
                    i.setFile(file);
                    i.readCSV();

                }
            }
            event.setDropCompleted(success);
            event.consume();
            fillList(listView, i);
        });
    }


}