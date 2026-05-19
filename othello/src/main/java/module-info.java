module iut.gon.othello {
    requires javafx.controls;
    requires javafx.fxml;
	requires org.junit.jupiter.api;

    opens iut.gon.othello to javafx.fxml;
    exports iut.gon.othello;
}
