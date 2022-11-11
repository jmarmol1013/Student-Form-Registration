package com.example.juanmarmolejo_comp228lab4;

import javafx.scene.control.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TestLab extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage){
        // Create a pane and set its properties
        new InputStage();
    }

    public static void main(String[] args) {
        launch();
    }
}

// Stage for the user inputs
class InputStage extends Stage{
    // Private instance
    private Label lblName,lblAdress,lblProvince,lblPostalCode,lblPhoneNumber,lblMail,lblCourses;
    private TextField txtName, txtAdress,txtProvince,txtPostalCode,txtPhoneNumber,txtMail;
    private CheckBox cboxStudent, cboxVolunteer;
    private ToggleGroup RadioGroup;
    private RadioButton rbComputer,rbBusiness;
    private String[] courses = {};
    private ChoiceBox<String> languages;
    private ListView<String> listCourses;
    private Button btnDisplay;

    // Create stage to display
        InputStage(){
            GridPane pane = new GridPane();
            pane.setAlignment(Pos.CENTER);
            pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
            pane.setHgap(5.5);
            pane.setVgap(5.5);

            // Place nodes in the pane
            // Create Label
            lblName = new Label("Name:");
            lblAdress = new Label("Adress:");
            lblProvince = new Label("Province:");
            lblPostalCode = new Label("Postal Code:");
            lblPhoneNumber = new Label("Phone Number:");
            lblMail = new Label("Mail:");
            lblCourses = new Label("Courses:");

            // Create Text Field
            txtName = new TextField();
            txtName.setPromptText("John Doe");
            txtAdress = new TextField();
            txtAdress.setPromptText("100 Developer Road");
            txtProvince = new TextField();
            txtProvince.setPromptText("ON");
            txtPostalCode = new TextField();
            txtPostalCode.setPromptText("M3T 2Y7");
            txtPhoneNumber = new TextField();
            txtPhoneNumber.setPromptText("4162223434");
            txtMail = new TextField();
            txtMail.setPromptText("jdoe@java.ca");

            //Create CheckBox
            cboxStudent = new CheckBox("Student Council");
            cboxVolunteer = new CheckBox("Volunteer Work");

            //Create Radio Buttton
            RadioGroup = new ToggleGroup();

            rbComputer = new RadioButton("Computer Science");
            rbComputer.setToggleGroup(RadioGroup);

            rbBusiness = new RadioButton("Business");
            rbBusiness.setToggleGroup(RadioGroup);
          //  rbBusiness.setLineSpacing(300);
            // Create display button
            btnDisplay = new Button("Display");

            // Display objects
            //Text inputs
            pane.add(lblName, 0, 0);
            pane.add(txtName, 2, 0);
            pane.add(lblAdress, 0, 1);
            pane.add(txtAdress, 2, 1);
            pane.add(lblProvince, 0, 2);
            pane.add(txtProvince, 2, 2);
            pane.add(lblPostalCode, 0, 3);
            pane.add(txtPostalCode, 2, 3);
            pane.add(lblPhoneNumber, 0, 4);
            pane.add(txtPhoneNumber, 2, 4);
            pane.add(lblMail, 0, 5);
            pane.add(txtMail, 2, 5);

            //Check box
            pane.add(cboxStudent, 4, 1);
            pane.add(cboxVolunteer, 4, 4);

            //Radio button
            pane.add(rbComputer, 13, 0);
            pane.add(rbBusiness, 13, 1);

            //Create Choice Box
            languages = new ChoiceBox<>();
            languages.setStyle("-fx-pref-width:200px");

            rbBusiness.setOnAction(e -> {
                pane.getChildren().remove(languages);
                pane.getChildren().remove(lblCourses);
                courses = new String[]{"Finances", "Business", "Marketing"};
                languages.setItems(FXCollections.observableArrayList(courses));
                pane.add(lblCourses,13,2);
                pane.add(languages, 13, 3);
                listCourses.getItems().clear();

            });
            rbComputer.setOnAction(e -> {
                pane.getChildren().remove(languages);
                pane.getChildren().remove(lblCourses);
                courses = new String[]{"Java", "Python", "C#"};
                languages.setItems(FXCollections.observableArrayList(courses));
                pane.add(lblCourses,13,2);
                pane.add(languages, 13, 3);
                listCourses.getItems().clear();
            });

            // Create list View
            listCourses = new ListView<>();
            listCourses.setStyle("-fx-pref-height:100px; -fx-pref-width:200px;");
            pane.add(listCourses,13,4);
            languages.setOnAction(e->{
                if (languages.getValue() != null){
                    if(listCourses.getItems().size() > 0){
                        boolean twiceCourse = false;
                        for (int i = 0 ; i < listCourses.getItems().size(); i++) {
                            if (languages.getValue().equals(listCourses.getItems().get(i))) {
                                twiceCourse = true;
                                System.out.println(listCourses.getItems().get(i));
                            }
                        }
                        if(!twiceCourse){
                            listCourses.getItems().add(languages.getValue());
                        }
                    }else{
                        listCourses.getItems().add(languages.getValue());
                    }
                }
            });

            //Button
            pane.add(btnDisplay, 4, 7);
            GridPane.setHalignment(btnDisplay, HPos.CENTER);

            // Create a scene and place it in the stage
            Scene scene = new Scene(pane);
            this.setTitle("Lab 4"); // Set the stage title
            this.setScene(scene); // Place the scene in the stage
            this.show(); // Display the stage

            // Button to display results or error
           btnDisplay.setOnAction(e -> {
               if (txtName.getText().equals("") || txtAdress.getText().equals("") || txtProvince.getText().equals("") || txtPostalCode.getText().equals("") || txtPhoneNumber.getText().equals("") || txtMail.getText().equals("") || listCourses.getItems().size() == 0) {
                   new ErrorDisplay("missFields");
               } else if (!txtProvince.getText().matches("[A-Z][A-Z]") || !txtPostalCode.getText().matches("[A-Z][0-9][A-Z]\\s[0-9][A-Z][0-9]") || !txtPhoneNumber.getText().matches("[0-9]{10}") || !txtMail.getText().matches("[a-z0-9]+@[a-z]+\\.[a-z]{2,3}")) {
                    new ErrorDisplay("incorrectInput");
                   // Display results
               } else {
                   String name = txtName.getText();
                   String address = txtAdress.getText();
                   String province = txtProvince.getText();
                   String postalCode = txtPostalCode.getText();
                   String phoneNumber = txtPhoneNumber.getText();
                   String mail = txtMail.getText();
                   String otherExperiences = "";
                   String major = "";
                   StringBuilder courses = new StringBuilder();
                   if (cboxStudent.isSelected()) otherExperiences += cboxStudent.getText() + '\n';
                   if (cboxVolunteer.isSelected()) otherExperiences += cboxVolunteer.getText() + '\n';

                   if (rbComputer.isSelected()) {
                       major = rbComputer.getText();
                   } else if (rbBusiness.isSelected()) {
                       major = rbBusiness.getText();
                   }

                   for (int i = 0; i < listCourses.getItems().size(); i++) {
                       courses.append(listCourses.getItems().get(i)).append('\n');
                   }
                   new ResultStage(name,address,province,postalCode,phoneNumber,mail,major,courses,otherExperiences);

               }
           });
        }
}

// Stage to display results
class ResultStage extends Stage{

    private TextArea txtArea;
    private Button btnReturn;

    // Stage to display results
    ResultStage(String name, String address, String province, String postalCode, String phoneNumber, String mail, String major, StringBuilder courses, String otherExperiences ) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        txtArea = new TextArea();
        pane.add(txtArea, 2, 2);
        txtArea.appendText(
                name + ", " + address + ", " + province + ", " + postalCode + ", " + phoneNumber + ", " + mail + '\n' +
                        "Degree: " + major + '\n' +
                        "Courses: " + '\n' + courses +
                        "Other experiences: " + '\n' +
                        otherExperiences
        );

        btnReturn = new Button("Return");
        pane.add(btnReturn, 2, 4);
        GridPane.setHalignment(btnReturn, HPos.CENTER);
        btnReturn.setOnAction(e-> this.close());

        Scene scene = new Scene(pane);
        this.setTitle("Results"); // Set the stage title
        this.setScene(scene); // Place the scene in the stage
        this.show(); // Display the stage
    }
}

// Stage for a user input error in validation
class ErrorDisplay extends Stage{
    private TextArea txtArea;
    private Button btnReturn;

    // Create stage to display
    ErrorDisplay(String er){
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        txtArea = new TextArea();
        txtArea.setStyle("-fx-text-fill: red");
        pane.add(txtArea, 2, 2);

        if(er.equals("missFields")) {
            txtArea.appendText("All fields are required, missing information" + '\n' + "Try again" + '\n');
        } else if (er.equals("incorrectInput")) {
            txtArea.appendText("Follow the pattern in the fields" + '\n' + "Try again" + '\n');
        }

        btnReturn = new Button("Return");
        pane.add(btnReturn, 2,4);
        GridPane.setHalignment(btnReturn, HPos.CENTER);
        btnReturn.setOnAction(e-> this.close());

        Scene scene = new Scene(pane);
        this.setTitle("Error in the data"); // Set the stage title
        this.setScene(scene); // Place the scene in the stage
        this.show(); // Display the stage

    }
}
