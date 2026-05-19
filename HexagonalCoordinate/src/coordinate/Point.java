package coordinate;

/**
 * Représente une coordonnée 2D immuable (équivalente à une Coordinate Doubled).
 */
public record Point(int x, int y) {

	// get set et constructeur auto généré à la compilation par Java
   @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}