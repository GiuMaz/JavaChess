package Test;

import model.ChessPosition;
import model.Position;

import org.junit.Assert;
import org.junit.Test;

public class PositionTest {

	@Test
	public void coordinateTest()
	{
		Position pos = new ChessPosition(1,2);
		Assert.assertTrue(pos.getRow() == 1);
		Assert.assertTrue(pos.getColumn() == 2);
	}
	
	@Test
	public void moveTest()
	{
		Position pos = new ChessPosition(4,4);
		Assert.assertEquals(pos, pos.up().down());
		Assert.assertEquals(pos, pos.left().right());
	}
	
	@Test
	public void BoundTest()
	{
		Assert.assertTrue( (new ChessPosition(1,1)).isInBound() );
		Assert.assertTrue( (new ChessPosition(0,0)).isInBound() );
		Assert.assertTrue( (new ChessPosition(7,7)).isInBound() );
		Assert.assertFalse( (new ChessPosition(0,-1)).isInBound() );
		Assert.assertFalse( (new ChessPosition(8,1)).isInBound() );
	}
	
	@Test
	public void HashCodeTest()
	{
		Position pos1 = new ChessPosition(4,4);
		Position pos2 = new ChessPosition(3,3);
		Assert.assertEquals(pos1, pos2.down().right());
		Assert.assertTrue(pos1.hashCode() == pos2.down().right().hashCode());
	}
	
	
}
