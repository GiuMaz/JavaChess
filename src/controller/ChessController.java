package controller;

import rules.Rules;
import rules.Upgrader;
import model.Configuration;
import model.Model;
import model.Position;
import view.View;

/**
 * Implementazione dell'interfaccia Controller.
 * usa il package rules per gestire gli aspetti legati al regolamento
 * mentre gestisce direttamente gli aspetti legati alle scelte dell'utente
 * 
 * Per la descrizione dei singoli metodi si rimanda all'interfaccia
 *
 */
public class ChessController implements Controller {
	
	private final View view;
	private final Model model;
	
	public ChessController( View view ){
		this.view = view;
		this.model = this.view.getModel();
		view.setController(this);
	}
	
	@Override
	public void onClick(Position pos) {
		if( !model.isGameOver() ){
			Configuration conf = model.getConfiguration();
			if (model.isSelected())
			{
				// se clicco su di un altro amico volgio cambiare la selezione
				if( conf.isFriend(pos) )
					model.setSelection(pos);
				else {
					if( (new Rules(conf)).isLegalMove(model.getSelection(), pos) ) 
					{
						model.setConfiguration(conf.move(model.getSelection(), pos));
						model.setConfiguration(new Upgrader(model.getConfiguration(), view).pieceUpgrade());
						
						if( (new Rules(model.getConfiguration())).isCheckmate() )
						{
							System.out.println("SCACCO MATTO");
							model.setGameOver(true);
							view.onGameOver();
						}
						else
							model.setConfiguration(model.getConfiguration().changeTurn());
					}
					model.deSelect();
				}
			}	
			else if(conf.isFriend(pos))
				model.setSelection(pos);
		}
	}

	@Override
	public void onNewGame() {
		model.deSelect();
		model.setConfiguration(new Rules(model.getConfiguration()).newGame());
		model.setGameOver(false);
	}

}
