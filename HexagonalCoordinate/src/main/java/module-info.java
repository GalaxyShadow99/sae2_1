module iut.gon.HexagonalCoordinate {
	requires org.junit.jupiter.api;

    opens iut.gon.coordinate to javafx.fxml;
    exports iut.gon.coordinate;
}
