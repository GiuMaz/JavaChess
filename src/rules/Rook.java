package rules;

import java.util.HashSet;
import java.util.Set;

import main.ChessColor;
import main.PieceRappresentation;
import model.Configuration;
import model.Position;

/**
 * implementa il pezzo della torre.
 * La torre si può muovere in verticale ed in orizzontale a piacere.
 * 
 */
public final class Rook extends AbstractRayAttacckerPiece {

	public Rook(Position position, ChessColor color)
	{
		super(position,color);
	}
	@Override
	public PieceRappresentation getRapresentation() {
		return color == ChessColor.WHITE ? PieceRappresentation.ROOK_WHITE : PieceRappresentation.ROOK_BLACK;
	}
	@Override
	public Piece move(Position p) {
		return new Rook(p, color);
	}
	
	@Override
	public Set<Position> allPseudoLegalMove(Configuration conf) {
		Set<Position> moves = new HashSet<>();
		moves.addAll(getRayUp(conf));
		moves.addAll(getRayLeft(conf));
		moves.addAll(getRayDown(conf));
		moves.addAll(getRayRight(conf));
		return moves;
	}

	@Override
	public Set<Position> getRayAttack(Configuration conf,Position pos) {
		if( getRayUp(conf).contains(pos) )
			return getRayUp(conf);
		if( getRayDown(conf).contains(pos) )
			return getRayDown(conf);
		if( getRayLeft(conf).contains(pos) )
			return getRayLeft(conf);
		if( getRayRight(conf).contains(pos) )
			return getRayRight(conf);
		return new HashSet<>();
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Rook && ((Rook) other).getColor() == color 
				&& ((Rook) other).getPosition().equals(position); 
	}
}
