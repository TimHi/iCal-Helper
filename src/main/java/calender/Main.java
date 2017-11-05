package calender;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {


        TextFlow textFlow = new TextFlow();
        Font font = new Font("Tahoma", 20);

        Text text1 = new Text("Drag & Drop CSV File here");
        text1.setFill(Color.BLACK);
        text1.setFont(font);

        textFlow.getChildren().addAll(text1);

        Group group = new Group(textFlow);
        Scene scene = new Scene(group, 650, 150, Color.WHITE);
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

        stage.setScene(scene);
        stage.show();
    }
}