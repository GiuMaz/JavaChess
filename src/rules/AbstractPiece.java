package rules;

import java.util.HashSet;
import java.util.Set;

import main.ChessColor;
import main.PieceRappresentation;
import model.Configuration;
import model.Position;

/**
 * implementa gli aspetti comuni a tutti i pezzi
 * 
 * Per la descrizione dei singoli metodi si rimanda all'interfaccia
 * 
 */
public abstract class AbstractPiece implements Piece {

	protected final ChessColor color;
	protected final Position position;
	
	protected AbstractPiece(Position position, ChessColor color)
	{
		this.position = position;
		this.color = color;
	}
	
	public final ChessColor getColor()
	{
		return color;
	}
	
	public Position getPosition() {
		return position;
	}
	
	@Override
	abstract public PieceRappresentation getRapresentation();
	
	abstract public Piece move(Position p);
	
	@Override
	public boolean isPseudoLegalMove(Configuration conf, Position to) {
		return allPseudoLegalMove(conf).contains(to);
	}
	
	
	public boolean isLegalMove(Configuration conf, Position to) {
		if( conf.enemyPieces().contains(this))
			return isPseudoLegalMove(conf, to) && !(new Rules(conf.move(this.position, to))).isEnemyKingInCheck();
		else
			return isPseudoLegalMove(conf, to) 	&& !(new Rules(conf.move(this.position, to))).isFriendKingInCheck() ;
	}

	@Override
	public Set<Position> allLegalMove(Configuration conf) {
		Set<Position> legalmoves = new HashSet<>();
		for(Position newPos : allPseudoLegalMove(conf) )
			if( isLegalMove(conf,newPos) )
				legalmoves.add(newPos);
		
		return legalmoves;
	}
		
	public boolean isKing() {
		return false;
	}
	
	@Override
	public boolean isMyEnemy(Piece other) {
		return other != null && color != other.getColor();
	}
	
	@Override
	public boolean isMyFriend(Piece other) {
		return other != null && color == other.getColor();
	}
	
	public boolean isInBoundAndNotFriend(Configuration conf, Position pos)
	{
		if( pos.isInBound() && !isMyFriend(conf.at(pos)) )
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		return position.hashCode() ^ color.hashCode();
	}
}
