/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsystem;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author cstuser
 */
public class StudentSystem extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Load");
        Label label=new Label("Enter student file path");
        TextField textField = new TextField();
        ListView<Student> listView = new ListView<Student>();
                
        Button btnAscending = new Button();
        btnAscending.setText("Sort Ascending");

        Button btnDescending = new Button();
        btnDescending.setText("Sort Descending");
        
        ArrayList<Student> share_students = new ArrayList<Student>();

        btn.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                final StudentLoader stloader = new StudentLoader();
//                final String pathStr = "E:\\SessionII\\JavaII\\Lab2\\StudentSystem\\student.dat";
                final String pathStr = textField.getText();
                                
                try {
                    final ArrayList<Student> students = stloader.load(Paths.get(pathStr));                    
                    ObservableList<Student> items =FXCollections.observableArrayList (students);
                    listView.setItems(items);
                    
                    for (Student item : students){
                            share_students.add(item);
                    }
 
 //                   StudentLoader.testLoad(pathStr);                    
/*                    final ArrayList<Student> students = stloader.load(Paths.get(pathStr));
                    for(final Student st : students) {
                        System.out.println(st.getId() + ", " + st.getName());
                    }*/
                } catch (IOException ex) {
                    Logger.getLogger(StudentSystem.class.getName()).log(Level.SEVERE, null, ex);
                    Alert fileNotFound = new Alert(Alert.AlertType.WARNING,"Please enter correct path");
                    fileNotFound.showAndWait();
                }
            }
        });
        
        btnAscending.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                if(share_students.isEmpty()) {
                    Alert NotLoad = new Alert(Alert.AlertType.WARNING,"Please load the student datafile");
                    NotLoad.showAndWait();                    
                }
                else {
                    selectionSort(share_students, true);
                    ObservableList<Student> items =FXCollections.observableArrayList (share_students);
                    listView.setItems(items);
                }
            }
        });
        
        btnDescending.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                if(share_students.isEmpty()) {
                    Alert NotLoad = new Alert(Alert.AlertType.WARNING,"Please load the student datafile");
                    NotLoad.showAndWait();                    
                }
                else
                {
                    selectionSort(share_students, false);
                    ObservableList<Student> items =FXCollections.observableArrayList (share_students);
                    listView.setItems(items);
                }
            }
        });
                
        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(textField, 0, 1);
        GridPane.setConstraints(btn, 0, 2);
        GridPane.setConstraints(btnAscending, 0, 3);
        GridPane.setConstraints(btnDescending, 0, 4);
        GridPane.setConstraints(listView, 1, 0, 1, 8);
        
        
        GridPane root = new GridPane();
        root.getChildren().add(btn);
        root.getChildren().add(textField);
        root.getChildren().add(listView);
        root.getChildren().add(label);
        root.getChildren().add(btnAscending);
        root.getChildren().add(btnDescending);
        
        Scene scene = new Scene(root, 400, 250);
        
        primaryStage.setTitle("Student System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void swap(ArrayList<Student> input, int iLeft, int iRight) {
        Collections.swap(input, iLeft, iRight);
    }
    
    public static void selectionSort(ArrayList<Student> input, boolean flag) {
        for (int i=0; i< input.size(); ++i) {
            
            int indexMinSoFar = i;
            
            for(int j=i+1; j<input.size(); ++j) {
                Student a = input.get(indexMinSoFar);
                Student b = input.get(j);

                if(flag)
                {
                    if(a.getName().compareTo(b.getName())>0) {
                        indexMinSoFar = j;
                    }
                }
                else
                {
                    if(a.getName().compareTo(b.getName())<0) {
                        indexMinSoFar = j;
                    }                    
                }
            }
            swap(input, i, indexMinSoFar);
        }
    }
}
