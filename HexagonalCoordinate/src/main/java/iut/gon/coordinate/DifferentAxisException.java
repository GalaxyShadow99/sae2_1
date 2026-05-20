package iut.gon.coordinate;

public class DifferentAxisException extends Exception{

    private static final long serialVersionUID = -777890243411809221L;

    /**
     * Crée une exception avec un message personnalisé.
     * @param mess message d'erreur (String)
     */
    public DifferentAxisException(String mess) {
        super(mess);
    }

    /**
     * Crée une exception avec le message par défaut.
     */
    public DifferentAxisException() {
        super("Erreur : Les deux coordonnees ne sont pas sur le meme axe");
    }

    
}