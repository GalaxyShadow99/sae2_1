module iut.gon.othello {
    requires javafx.controls;
    requires javafx.fxml;
	requires HexagonalCoordinate;

    opens iut.gon.othello to javafx.fxml;
    exports iut.gon.othello;
}
