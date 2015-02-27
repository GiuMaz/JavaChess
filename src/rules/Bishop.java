package rules;

import java.util.HashSet;
import java.util.Set;

import main.ChessColor;
import main.PieceRappresentation;
import model.Configuration;
import model.Position;

/**
 * implementa il pezzo dell'alfiere.
 * l'alfiere può muoversi solo sulle quattro diagonali
 * 
 */
public final class Bishop extends AbstractRayAttacckerPiece {

	public Bishop(Position position, ChessColor color)
	{
		super(position,color);
	}
	
	@Override
	public PieceRappresentation getRapresentation() {
		return color == ChessColor.WHITE ? PieceRappresentation.BISHOP_WHITE : PieceRappresentation.BISHOP_BLACK;
	}
	
	
	@Override
	public Piece move(Position p) {
		return new Bishop(p,color);
	}

	@Override
	public Set<Position> allPseudoLegalMove(Configuration conf) {
		Set<Position> moves = new HashSet<>();
		moves.addAll(getRayUpRight(conf));
		moves.addAll(getRayUpLeft(conf));
		moves.addAll(getRayDownLeft(conf));
		moves.addAll(getRayDownRight(conf));
		return moves;
	}

	@Override
	public Set<Position> getRayAttack(Configuration conf,Position pos) {
		if( getRayUpRight(conf).contains(pos) )
			return getRayUpRight(conf);
		if( getRayUpLeft(conf).contains(pos) )
			return getRayUpLeft(conf);
		if( getRayDownLeft(conf).contains(pos) )
			return getRayDownLeft(conf);
		if( getRayDownRight(conf).contains(pos) )
			return getRayDownRight(conf);
		return new HashSet<>();
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Bishop && ((Bishop) other).getColor() == color 
				&& ((Bishop) other).getPosition().equals(position); 
	}
	
}
