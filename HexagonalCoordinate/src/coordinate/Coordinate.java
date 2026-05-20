package coordinate;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public abstract class Coordinate {

    public abstract Point to2DCoordinate();
    
    
    public Coordinate toDir(Mode mode, Direction direction) {
        if (direction.equals(Direction.N)) return N(mode);
        if (direction.equals(Direction.NE)) return NE(mode);
        if (direction.equals(Direction.NO)) return NO(mode);
        if (direction.equals(Direction.E)) return E(mode);
        if (direction.equals(Direction.O)) return O(mode);
        if (direction.equals(Direction.S)) return S(mode);
        if (direction.equals(Direction.SE)) return SE(mode);
        if (direction.equals(Direction.SO)) return SO(mode);
        return null;
    }

    public List<Coordinate> getNeighbors(Mode mode) {
        List<Coordinate> neighbors = new ArrayList<>();
        
        for (Direction dir : Direction.values()) {
            // 1. En mode POINTY N et S existent pas 
            if (mode == Mode.POINTY && (dir == Direction.N || dir == Direction.S)) {
                continue;
            }
            
            // 2. En mode FLAT E et O existent pas 
            if (mode == Mode.FLAT && (dir == Direction.E || dir == Direction.O)) {
                continue;
            }

            try {
                Coordinate neighbor = this.toDir(mode, dir);
                if (neighbor != null) {
                    neighbors.add(neighbor);
                }
            } catch (InvalidParameterException e) {
                throw new InvalidParameterException("Erreur critique : La direction " + dir + " a planté pour le mode " + mode);
            }
        }
        
        return neighbors;
    }

    public abstract List<Coordinate> between(Mode mode, Coordinate to) throws DifferentAxisException;

    public abstract Coordinate NO(Mode mode);
    public abstract Coordinate NE(Mode mode);
    public abstract Coordinate E(Mode mode);
    public abstract Coordinate O(Mode mode);
    public abstract Coordinate N(Mode mode);
    public abstract Coordinate S(Mode mode);
    public abstract Coordinate SO(Mode mode);
    public abstract Coordinate SE(Mode mode);
    
    // pour tests
    @Override
    public abstract boolean equals(Object obj);
    @Override
    public abstract int hashCode();
    
}