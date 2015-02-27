package rules;

import java.util.HashSet;
import java.util.Set;

import main.ChessColor;
import model.Configuration;
import model.Position;

/**
 * implementa i metodi comuni a tutti i pezzi che hanno attacchi a raggio
 * (Queen, Rook, Bishop)
 *
 */
public abstract class AbstractRayAttacckerPiece extends AbstractPiece {

	protected AbstractRayAttacckerPiece(Position position, ChessColor color) {
		super(position, color);
	}
	
	abstract public Set<Position> getRayAttack(Configuration conf,Position pos);
	
	protected Set<Position> getRayUp(Configuration conf)
	{
		HashSet<Position> moves = new HashSet<>();
		getRayUpAux(conf, moves, position.up());
		return moves;
	}
	
	private void getRayUpAux(Configuration conf,HashSet<Position> moves, Position pos)
	{
		if(conf.isEmpty(pos))
		{
			moves.add(pos);
			getRayUpAux(conf, moves, pos.up());
		}
		else
		{
			if(isMyEnemy(conf.at(pos)))
				moves.add(pos);
		}
	}
	
	protected Set<Position> getRayDown(Configuration conf)
	{
		HashSet<Position> moves = new HashSet<>();
		getRayDownAux(conf, moves, position.down());
		return moves;
	}
	
	private void getRayDownAux(Configuration conf,HashSet<Position> moves, Position pos)
	{
		if(conf.isEmpty(pos))
		{
			moves.add(pos);
			getRayDownAux(conf, moves, pos.down());
		}
		else
		{
			if(isMyEnemy(conf.at(pos)))
				moves.add(pos);
		}
	}
	
	protected Set<Position> getRayLeft(Configuration conf)
	{
		HashSet<Position> moves = new HashSet<>();
		getRayLeftAux(conf, moves, position.left());
		return moves;
	}
	
	private void getRayLeftAux(Configuration conf,HashSet<Position> moves, Position pos)
	{
		if(conf.isEmpty(pos))
		{
			moves.add(pos);
			getRayLeftAux(conf, moves, pos.left());
		}
		else
		{
			if(isMyEnemy(conf.at(pos)))
				moves.add(pos);
		}
	}
	
	
	protected Set<Position> getRayRight(Configuration conf)
	{
		HashSet<Position> moves = new HashSet<>();
		getRayRightAux(conf, moves, position.right());
		return moves;
	}
	
	private void getRayRightAux(Configuration conf,HashSet<Position> moves, Position pos)
	{
		if(conf.isEmpty(pos))
		{
			moves.add(pos);
			getRayRightAux(conf, moves, pos.right());
		}
		else
		{
			if(isMyEnemy(conf.at(pos)))
				moves.add(pos);
		}
	}
	
	protected Set<Position> getRayUpRight(Configuration conf)
	{
		HashSet<Position> moves = new HashSet<>();
		getRayUpRightAux(conf, moves, position.up().right());
		return moves;
	}
	
	private void getRayUpRightAux(Configuration conf,HashSet<Position> moves, Position pos)
	{
		if(conf.isEmpty(pos))
		{
			moves.add(pos);
			getRayUpRightAux(conf, moves, pos.up().right());
		}
		else
		{
			if(isMyEnemy(conf.at(pos)))
				moves.add(pos);
		}
	}
	
	protected Set<Position> getRayUpLeft(Configuration conf)
	{
		HashSet<Position> moves = new HashSet<>();
		getRayUpLeftAux(conf, moves, position.up().left());
		return moves;
	}
	
	private void getRayUpLeftAux(Configuration conf,HashSet<Position> moves, Position pos)
	{
		if(conf.isEmpty(pos))
		{
			moves.add(pos);
			getRayUpLeftAux(conf, moves, pos.up().left());
		}
		else
		{
			if(isMyEnemy(conf.at(pos)))
				moves.add(pos);
		}
	}
	
	protected Set<Position> getRayDownLeft(Configuration conf)
	{
		HashSet<Position> moves = new HashSet<>();
		getRayDownLeftAux(conf, moves, position.down().left());
		return moves;
	}
	
	private void getRayDownLeftAux(Configuration conf,HashSet<Position> moves, Position pos)
	{
		if(conf.isEmpty(pos))
		{
			moves.add(pos);
			getRayDownLeftAux(conf, moves, pos.down().left());
		}
		else
		{
			if(isMyEnemy(conf.at(pos)))
				moves.add(pos);
		}
	}
	
	protected Set<Position> getRayDownRight(Configuration conf)
	{
		HashSet<Position> moves = new HashSet<>();
		getRayDownRightAux(conf, moves, position.down().right());
		return moves;
	}
	
	private void getRayDownRightAux(Configuration conf,HashSet<Position> moves, Position pos)
	{
		if(conf.isEmpty(pos))
		{
			moves.add(pos);
			getRayDownRightAux(conf, moves, pos.down().right());
		}
		else
		{
			if(isMyEnemy(conf.at(pos)))
				moves.add(pos);
		}
	}
	
	
}
