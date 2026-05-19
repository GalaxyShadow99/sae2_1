package coordinate;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class CoordinateCube extends Coordinate {
    private int q;
    private int r;
    private int s;
    
    public CoordinateCube(int q, int r, int s) {
        if (q + r + s != 0) {
            throw new IllegalArgumentException("La somme de q, r et s doit être égale à 0");
        }
        this.q = q;
        this.r = r;
        this.s = s;
    }

    public int getQ() { return q; }
    public int getR() { return r; }
    public int getS() { return s; }

    @Override
    public Coordinate NO(Mode mode) {
        return new CoordinateCube(this.q, this.r - 1, this.s + 1);
    }

    @Override
    public Coordinate NE(Mode mode) {
        return new CoordinateCube(this.q + 1, this.r - 1, this.s);
    }

    @Override
    public Coordinate E(Mode mode) {
        if (mode == Mode.FLAT) throw new InvalidParameterException("E n'accepte pas le mode FLAT");
        return new CoordinateCube(this.q + 1, this.r, this.s - 1);
    }

    @Override
    public Coordinate O(Mode mode) {
        if (mode == Mode.FLAT) throw new InvalidParameterException("O n'accepte pas le mode FLAT");
        return new CoordinateCube(this.q - 1, this.r, this.s + 1);
    }

    @Override
    public Coordinate N(Mode mode) {
        if (mode == Mode.POINTY) throw new InvalidParameterException("N n'accepte pas le mode POINTY");
        return new CoordinateCube(this.q, this.r - 1, this.s + 1); 
    }

    @Override
    public Coordinate S(Mode mode) {
        if (mode == Mode.POINTY) throw new InvalidParameterException("S n'accepte pas le mode POINTY");
        return new CoordinateCube(this.q, this.r + 1, this.s - 1); 
    }

    @Override
    public Coordinate SO(Mode mode) {
        return new CoordinateCube(this.q - 1, this.r + 1, this.s);
    }

    @Override
    public Coordinate SE(Mode mode) {
        return new CoordinateCube(this.q, this.r + 1, this.s - 1);
    }

    @Override
    public List<Coordinate> between(Mode mode, Coordinate to) throws DifferentAxisException {
        if (!(to instanceof CoordinateCube)) throw new InvalidParameterException();
        CoordinateCube target = (CoordinateCube) to;
        
        if ((this.q != target.q) && (this.r != target.r) && (this.s != target.s)) {
            throw new DifferentAxisException("Not on the same axis");
        }

        List<Coordinate> resultat = new ArrayList<>();
        int pasQ = Integer.compare(target.q, this.q);
        int pasR = Integer.compare(target.r, this.r);
        int pasS = Integer.compare(target.s, this.s);

        int qActuel = this.q + pasQ;
        int rActuel = this.r + pasR;
        int sActuel = this.s + pasS;

        while (qActuel != target.q || rActuel != target.r || sActuel != target.s) {
            resultat.add(new CoordinateCube(qActuel, rActuel, sActuel));
            qActuel += pasQ;
            rActuel += pasR;
            sActuel += pasS;
        }
        return resultat;
    }

    @Override
    public Point to2DCoordinate() {
        int y = this.r + 5;
        int x = 2 * this.q + this.r + 9;
        
        return new Point(x, y);
    }
}