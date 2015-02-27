package rules;

import java.util.HashSet;
import java.util.Set;

import main.ChessColor;
import main.PieceRappresentation;
import model.Configuration;
import model.Position;

/**
 * implementa il pezzo del pedone.
 * Il pedone ha movimenti differenti in base al colore.
 * Il nero può solo scendere mentre il bianco può solo salire.
 * entrambi avanzano solo se la posizione in fronte è vuota, mentre
 * possono mangiare solo nelle posizioni adiacenti frontali sulle diagonali.
 * 
 * È l'unico pezzo che può essere promosso, se arriva nel lato opposto della scacchiera
 * 
 */
public class Pawn extends AbstractPiece implements UpgradablePiece {

	public Pawn(Position position, ChessColor color)
	{
		super(position,color);
	}
	
	@Override
	public PieceRappresentation getRapresentation() {
		return color == ChessColor.WHITE ? PieceRappresentation.PAWN_WHITE : PieceRappresentation.PAWN_BLACK;
	}
	
	@Override
	public Piece move(Position p) {
		return new Pawn(p, this.color);
	}

	@Override
	public Set<Position> allPseudoLegalMove(Configuration conf) {
		Set<Position> moves = new HashSet<>();
		if(color == ChessColor.WHITE)
		{
			if(isInBoundAndNotFriend(conf, position.up()) && !isMyEnemy(conf.at(position.up()))) {
				moves.add(position.up());
				if(position.getRow() == 6  && isInBoundAndNotFriend(conf, position.up().up()) && !isMyEnemy(conf.at(position.up().up()) ) )
					moves.add(position.up().up());
			}
			if(isMyEnemy(conf.at(position.up().left())) )
				moves.add(position.up().left());
			if(isMyEnemy(conf.at(position.up().right())) )
				moves.add(position.up().right());
		}
		else
		{
			if(isInBoundAndNotFriend(conf, position.down())  && !isMyEnemy(conf.at( position.down() )) ) {
				moves.add(position.down());
				if(position.getRow() == 1  && isInBoundAndNotFriend(conf, position.down().down()) && !isMyEnemy(conf.at( position.down().down() )) )
					moves.add(position.down().down());
			}
			if(isMyEnemy(conf.at(position.down().left())) )
				moves.add(position.down().left());
			if(isMyEnemy(conf.at(position.down().right())) )
				moves.add(position.down().right());
		}
		return moves;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Pawn && ((Pawn) other).getColor() == color 
				&& ((Pawn) other).getPosition().equals(position); 
	}

	@Override
	public boolean isInUpgradePosition() {
		if( color == ChessColor.WHITE )
			return position.getRow() == 0;
		else
			return position.getRow() == 7;
	}
}
