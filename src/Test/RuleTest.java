package Test;

import java.util.HashSet;

import main.ChessColor;
import model.ChessConfiguration;
import model.ChessPosition;
import model.Configuration;

import org.junit.Assert;
import org.junit.Test;

import rules.King;
import rules.Piece;
import rules.Rook;
import rules.Rules;

public class RuleTest {
	
	@Test
	public void CheckTest()
	{
		HashSet<Piece> set = new HashSet<>();
		set.add(new King(new ChessPosition(0, 4), ChessColor.BLACK));
		set.add(new King(new ChessPosition(7, 4), ChessColor.WHITE));
		set.add(new Rook(new ChessPosition(0, 7), ChessColor.WHITE));
		Configuration conf = new ChessConfiguration( set );
		Rules r = new Rules(conf);
		Assert.assertFalse(r.isFriendKingInCheck());
		Assert.assertTrue(r.isEnemyKingInCheck());
		Assert.assertFalse(r.isCheckmate());
	}
	
	@Test
	public void CheckmateTest()
	{
		HashSet<Piece> set = new HashSet<>();
		set.add(new King(new ChessPosition(0, 4), ChessColor.BLACK));
		set.add(new King(new ChessPosition(7, 4), ChessColor.WHITE));
		set.add(new Rook(new ChessPosition(0, 7), ChessColor.WHITE));
		set.add(new Rook(new ChessPosition(1, 7), ChessColor.WHITE));
		Configuration conf = new ChessConfiguration( set );
		Rules r = new Rules(conf);
		Assert.assertFalse(r.isFriendKingInCheck());
		Assert.assertTrue(r.isEnemyKingInCheck());
		Assert.assertTrue(r.isCheckmate());
	}
	
	@Test
	public void DumbCheckmateTest()
	{
		Configuration conf = new ChessConfiguration();
		conf = new Rules(conf).newGame();
		conf = conf.move(new ChessPosition(6,5), new ChessPosition(5,5));
		conf = conf.move(new ChessPosition(1,4), new ChessPosition(3,4));
		conf = conf.move(new ChessPosition(6,6), new ChessPosition(4,6));
		conf = conf.move(new ChessPosition(0,3), new ChessPosition(4,7));
		conf = conf.changeTurn();
		Assert.assertTrue(new Rules(conf).isCheckmate());
	}
}
