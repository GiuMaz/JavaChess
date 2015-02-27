package Test;


import java.util.HashSet;

import main.ChessColor;
import model.ChessConfiguration;
import model.ChessPosition;
import model.Configuration;

import org.junit.Assert;
import org.junit.Test;

import rules.Bishop;
import rules.King;
import rules.Knight;
import rules.Pawn;
import rules.Piece;
import rules.Queen;
import rules.Rook;



public class ConfigurationTest {
	
	
	private HashSet<Piece> getTestConfiguration()
	{
		HashSet<Piece> set = new HashSet<>();
		set.add(new Pawn(new ChessPosition(0, 0), ChessColor.WHITE));
		set.add(new Rook(new ChessPosition(1, 2), ChessColor.WHITE));
		set.add(new Knight(new ChessPosition(2, 0), ChessColor.BLACK));
		set.add(new King(new ChessPosition(3, 5), ChessColor.BLACK));
		set.add(new Queen(new ChessPosition(4, 7), ChessColor.WHITE));
		set.add(new Bishop(new ChessPosition(5, 5), ChessColor.WHITE));
		return set;
	}
	
	@Test
	public void atTest()
	{
		Configuration conf = new ChessConfiguration( getTestConfiguration());
		Assert.assertEquals(conf.at(new ChessPosition(0, 0)), new Pawn(new ChessPosition(0, 0), ChessColor.WHITE));
		Assert.assertEquals(conf.at(new ChessPosition(2, 0)), new Knight(new ChessPosition(2, 0), ChessColor.BLACK));
		Assert.assertNotEquals(conf.at(new ChessPosition(4, 7)), new Bishop(new ChessPosition(5, 5), ChessColor.WHITE));
		
	}
	
	@Test
	public void isEmptyTest()
	{
		Configuration conf = new ChessConfiguration( getTestConfiguration());
		Assert.assertTrue(conf.isEmpty(new ChessPosition(0, 1)));
		Assert.assertTrue(conf.isEmpty(new ChessPosition(7, 7)));
		Assert.assertFalse(conf.isEmpty(new ChessPosition(0, 0)));
		
	}
	
	@Test
	public void FriendEnemyTest()
	{
		Configuration conf = new ChessConfiguration( getTestConfiguration(), ChessColor.WHITE );
		Assert.assertTrue(conf.isEnemy(new ChessPosition(2, 0)));
		Assert.assertTrue(conf.isFriend(new ChessPosition(0, 0)));
		
		conf = conf.changeTurn();
		
		Assert.assertTrue(conf.isFriend(new ChessPosition(2, 0)));
		Assert.assertTrue(conf.isEnemy(new ChessPosition(0, 0)));
		
	}
	
	@Test
	public void getPiecesTest()
	{
		Configuration conf = new ChessConfiguration( getTestConfiguration(), ChessColor.WHITE );
		Assert.assertEquals(conf.whitePieces(), conf.friendPieces() );
		Assert.assertEquals(conf.blackPieces(), conf.enemyPieces() );
		
		conf = conf.changeTurn();
		
		Assert.assertNotEquals(conf.whitePieces(), conf.friendPieces() );
		Assert.assertNotEquals(conf.blackPieces(), conf.enemyPieces() );
		
	}
	
	@Test
	public void addTest()
	{
		Configuration conf = new ChessConfiguration( getTestConfiguration(), ChessColor.WHITE );
		Assert.assertTrue( conf.isEmpty(new ChessPosition(2, 2)));
		conf  = conf.add(new King(new ChessPosition(2, 2),ChessColor.WHITE));
		Assert.assertFalse( conf.isEmpty(new ChessPosition(2, 2)));
	}
	
	@Test
	public void moveTest()
	{
		Configuration conf = new ChessConfiguration( getTestConfiguration(), ChessColor.WHITE );
		Assert.assertTrue( conf.isEmpty(new ChessPosition(2, 2)));
		Assert.assertFalse( conf.isEmpty(new ChessPosition(0, 0)));
		
		conf  = conf.move(new ChessPosition(0, 0),new ChessPosition(2, 2));
		Assert.assertFalse( conf.isEmpty(new ChessPosition(2, 2)));
		Assert.assertTrue( conf.isEmpty(new ChessPosition(0, 0)));
	}

}
