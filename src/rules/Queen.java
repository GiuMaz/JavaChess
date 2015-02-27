package rules;

import java.util.HashSet;
import java.util.Set;

import main.ChessColor;
import main.PieceRappresentation;
import model.Configuration;
import model.Position;

/**
 * implementa il pezzo della regina.
 * la regina può fare le mosse dell'alfiere e della torre
 * 
 */
public final class Queen extends AbstractRayAttacckerPiece {

	public Queen(Position position, ChessColor color)
	{
		super(position,color);
	}

	@Override
	public PieceRappresentation getRapresentation() {
		return color == ChessColor.WHITE ? PieceRappresentation.QUEEN_WHITE : PieceRappresentation.QUEEN_BLACK;
	}

	@Override
	public Piece move(Position p) {
		return new Queen(p, color);
	}

	
	@Override
	public Set<Position> allPseudoLegalMove(Configuration conf) {
		Set<Position> moves = new HashSet<>();
		moves.addAll(getRayUp(conf));
		moves.addAll(getRayLeft(conf));
		moves.addAll(getRayDown(conf));
		moves.addAll(getRayRight(conf));
		moves.addAll(getRayUpRight(conf));
		moves.addAll(getRayUpLeft(conf));
		moves.addAll(getRayDownLeft(conf));
		moves.addAll(getRayDownRight(conf));
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
		return other instanceof Queen && ((Queen) other).getColor() == color 
				&& ((Queen) other).getPosition().equals(position); 
	}

}
