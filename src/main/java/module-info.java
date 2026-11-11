module com.mycompany.pengaduandesaa {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.pengaduandesaa to javafx.fxml;
    exports com.mycompany.pengaduandesaa;
}
