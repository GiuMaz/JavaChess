package Test;

import java.util.HashSet;

import main.ChessColor;
import model.ChessConfiguration;
import model.ChessPosition;
import model.Configuration;
import model.Position;

import org.junit.Assert;
import org.junit.Test;

import rules.Bishop;
import rules.King;
import rules.Knight;
import rules.Pawn;
import rules.Piece;
import rules.Queen;
import rules.Rook;

public class PieceTest {
	
	private HashSet<Piece> getTestConfiguration()
	{
		HashSet<Piece> set = new HashSet<>();
		set.add(new Pawn(new ChessPosition(6, 3), ChessColor.WHITE));
		set.add(new Rook(new ChessPosition(1, 3), ChessColor.WHITE));
		set.add(new King(new ChessPosition(7, 4), ChessColor.WHITE));
		set.add(new Queen(new ChessPosition(4, 7), ChessColor.WHITE));
		set.add(new Knight(new ChessPosition(4, 6), ChessColor.BLACK));
		set.add(new King(new ChessPosition(0, 4), ChessColor.BLACK));
		set.add(new Bishop(new ChessPosition(5, 2), ChessColor.BLACK));
		return set;
	}

	@Test
	public void PseudoLegalTest() {
		Configuration conf = new ChessConfiguration( getTestConfiguration());
		// test bianchi
		Assert.assertTrue(conf.at(new ChessPosition(4, 7)).isPseudoLegalMove(conf, new ChessPosition(4, 6)));
		Assert.assertFalse(conf.at(new ChessPosition(4, 7)).isPseudoLegalMove(conf, new ChessPosition(4, 5)));
		Assert.assertTrue(conf.at(new ChessPosition(4, 7)).isPseudoLegalMove(conf, new ChessPosition(0, 3)));
		
		Assert.assertTrue(conf.at(new ChessPosition(1, 3)).isPseudoLegalMove(conf, new ChessPosition(1, 0)));
		Assert.assertFalse(conf.at(new ChessPosition(1, 3)).isPseudoLegalMove(conf, new ChessPosition(6, 3)));
		
		Assert.assertTrue(conf.at(new ChessPosition(6, 3)).isPseudoLegalMove(conf, new ChessPosition(4, 3)));
		Assert.assertFalse(conf.at(new ChessPosition(6, 3)).isPseudoLegalMove(conf, new ChessPosition(7, 3)));
		
		// test neri
		conf = conf.changeTurn();
		
		Assert.assertTrue(conf.at(new ChessPosition(0, 4)).isPseudoLegalMove(conf, new ChessPosition(1, 3)));
		Assert.assertTrue(conf.at(new ChessPosition(0, 4)).isPseudoLegalMove(conf, new ChessPosition(0, 3)));
		
		Assert.assertTrue(conf.at(new ChessPosition(4, 6)).isPseudoLegalMove(conf, new ChessPosition(2, 7)));
		Assert.assertFalse(conf.at(new ChessPosition(4, 6)).isPseudoLegalMove(conf, new ChessPosition(2, 6)));
		
	}
	
	@Test
	public void LegalTest() {
		Configuration conf = new ChessConfiguration( getTestConfiguration());
		// test bianchi
		Assert.assertTrue(conf.at(new ChessPosition(4, 7)).isLegalMove(conf, new ChessPosition(4, 6)));
		Assert.assertFalse(conf.at(new ChessPosition(4, 7)).isLegalMove(conf, new ChessPosition(4, 5)));
		Assert.assertTrue(conf.at(new ChessPosition(4, 7)).isLegalMove(conf, new ChessPosition(0, 3)));
		
		Assert.assertTrue(conf.at(new ChessPosition(1, 3)).isLegalMove(conf, new ChessPosition(1, 0)));
		Assert.assertFalse(conf.at(new ChessPosition(1, 3)).isLegalMove(conf, new ChessPosition(6, 3)));
		
		Assert.assertFalse(conf.at(new ChessPosition(6, 3)).isLegalMove(conf, new ChessPosition(4, 3)));
		Assert.assertFalse(conf.at(new ChessPosition(6, 3)).isLegalMove(conf, new ChessPosition(7, 3)));
		
		// test neri
		conf = conf.changeTurn();
		
		Assert.assertTrue(conf.at(new ChessPosition(0, 4)).isLegalMove(conf, new ChessPosition(1, 3)));
		Assert.assertFalse(conf.at(new ChessPosition(0, 4)).isLegalMove(conf, new ChessPosition(0, 3)));
		
		Assert.assertTrue(conf.at(new ChessPosition(4, 6)).isLegalMove(conf, new ChessPosition(2, 7)));
		Assert.assertFalse(conf.at(new ChessPosition(4, 6)).isLegalMove(conf, new ChessPosition(2, 6)));
		
	}

	@Test
	public void positionTest()
	{
		Position pos = new ChessPosition(0, 0);
		Piece pawn = new Pawn(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece rook = new Rook(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece knigth = new Knight(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece bishop = new Bishop(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece queen = new Queen(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece king = new King(new ChessPosition(0, 0), ChessColor.BLACK);
		Assert.assertEquals(pawn.getPosition(), pos);
		Assert.assertEquals(rook.getPosition(), pos);
		Assert.assertEquals(knigth.getPosition(), pos);
		Assert.assertEquals(bishop.getPosition(), pos);
		Assert.assertEquals(queen.getPosition(), pos);
		Assert.assertEquals(king.getPosition(), pos);
	}
	
	@Test
	public void enemyFriendTest()
	{
		Piece pawn = new Pawn(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece rook = new Rook(new ChessPosition(1, 1), ChessColor.WHITE);
		Piece knigth = new Knight(new ChessPosition(0, 0), ChessColor.BLACK);
		
		Assert.assertTrue(pawn.isMyEnemy(rook));
		Assert.assertFalse(pawn.isMyFriend(rook));
		
		Assert.assertFalse(pawn.isMyEnemy(knigth));
		Assert.assertTrue(pawn.isMyFriend(knigth));
		
	}
	
	@Test
	public void colorTest()
	{
		Piece pawn = new Pawn(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece rook = new Rook(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece knigth = new Knight(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece bishop = new Bishop(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece queen = new Queen(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece king = new King(new ChessPosition(0, 0), ChessColor.BLACK);
		Assert.assertTrue(pawn.getColor() ==  ChessColor.BLACK);
		Assert.assertTrue(rook.getColor() ==  ChessColor.BLACK);
		Assert.assertTrue(knigth.getColor() ==  ChessColor.BLACK);
		Assert.assertTrue(bishop.getColor() ==  ChessColor.BLACK);
		Assert.assertTrue(queen.getColor() ==  ChessColor.BLACK);
		Assert.assertTrue(king.getColor() ==  ChessColor.BLACK);
	}
	
	public void moveTest()
	{
		Piece pawn = new Pawn(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece rook = new Rook(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece knigth = new Knight(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece bishop = new Bishop(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece queen = new Queen(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece king = new King(new ChessPosition(0, 0), ChessColor.BLACK);
		Position pos = new ChessPosition(1, 1);
		Assert.assertEquals(pawn.move(pos),new Pawn(pos, ChessColor.BLACK));
		Assert.assertEquals(rook.move(pos),new Rook(pos, ChessColor.BLACK));
		Assert.assertEquals(knigth.move(pos),new Knight(pos, ChessColor.BLACK));
		Assert.assertEquals(bishop.move(pos),new Bishop(pos, ChessColor.BLACK));
		Assert.assertEquals(queen.move(pos),new Queen(pos, ChessColor.BLACK));
		Assert.assertEquals(king.move(pos),new King(pos, ChessColor.BLACK));
	}
	
	@Test
	public void kingTest()
	{
		Piece pawn = new Pawn(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece rook = new Rook(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece knigth = new Knight(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece bishop = new Bishop(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece queen = new Queen(new ChessPosition(0, 0), ChessColor.BLACK);
		Piece king = new King(new ChessPosition(0, 0), ChessColor.BLACK);
		Assert.assertFalse(pawn.isKing());
		Assert.assertFalse(rook.isKing());
		Assert.assertFalse(knigth.isKing());
		Assert.assertFalse(bishop.isKing());
		Assert.assertFalse(queen.isKing());
		Assert.assertTrue(king.isKing());
	}
	
	
}
