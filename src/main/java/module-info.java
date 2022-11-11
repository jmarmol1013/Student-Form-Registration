module com.example.juanmarmolejo_comp228lab4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.juanmarmolejo_comp228lab4 to javafx.fxml;
    exports com.example.juanmarmolejo_comp228lab4;
}