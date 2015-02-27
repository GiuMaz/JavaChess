package Test;

import model.ChessConfiguration;
import model.ChessModel;
import model.ChessPosition;
import model.Configuration;
import model.Model;

import org.junit.Assert;
import org.junit.Test;

import rules.Rules;

public class ModelTest {
	
	@Test
	public void getSetConfigurationTest()
	{
		Configuration conf = new ChessConfiguration();
		conf = (new Rules(conf)).newGame();
		Model model = new ChessModel(new ChessConfiguration());
		model.setConfiguration(conf);
		Assert.assertTrue(model.getConfiguration() == conf);
	}
	
	@Test
	public void gameOverTest()
	{
		Model model = new ChessModel(new ChessConfiguration());
		model.setGameOver(true);
		Assert.assertTrue(model.isGameOver());
		model.setGameOver(false);
		Assert.assertFalse(model.isGameOver());
	}
	
	@Test
	public void selectionTest()
	{
		Model model = new ChessModel(new ChessConfiguration());
		
		model.setSelection(new ChessPosition(1, 1));
		Assert.assertTrue(model.isSelected());
		Assert.assertEquals(model.getSelection(), new ChessPosition(1, 1));

		model.deSelect();
		Assert.assertFalse(model.isSelected());
	}

}
