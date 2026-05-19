module iut.gon.othello {
    requires javafx.controls;
    requires javafx.fxml;

    opens iut.gon.othello to javafx.fxml;
    exports iut.gon.othello;
}
