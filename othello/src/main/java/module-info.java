module iut.gon.othello {
    requires javafx.controls;
    requires javafx.fxml;
	requires HexagonalCoordinate;
	requires org.junit.jupiter.api;

    opens iut.gon.othello to javafx.fxml;
    exports iut.gon.othello;
}
