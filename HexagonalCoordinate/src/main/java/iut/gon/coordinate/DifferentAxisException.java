package iut.gon.coordinate;

public class DifferentAxisException extends Exception{

    private static final long serialVersionUID = -777890243411809221L;

    public DifferentAxisException(String mess) {
        super(mess);
    }

    public DifferentAxisException() {
        super("Erreur : Les deux coordonnees ne sont pas sur le meme axe");
    }

    
}