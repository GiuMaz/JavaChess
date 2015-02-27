package model;

import view.View;


/**
 * Implementazione dell'Interfaccia Model.
 * 
 * È una classe mutabile costruita su una configurazione immutabile.
 * Cambiare configuarizone nel modello comporta l'assegnamento di una nuova
 * configurazione, non modificare la configuarizone corrente.
 * 
 *  Per la descrizione dei singoli metodi si rimanda all'interfaccia*
 */
public class ChessModel implements Model {

	private Configuration configuration;
	private View view;
	
	boolean isSelected = false;
	private Position selectedPosition;
	
	private boolean isGameOver = false;
	
	public ChessModel(Configuration configuration)
	{
		this.configuration = configuration;
	}
	
	@Override
	public Configuration getConfiguration() {
		return configuration;
	}

	@Override
	public void setConfiguration(Configuration configuration) {
		if ( this.configuration != configuration ) {
			this.configuration = configuration;
			if( view != null )
				view.onConfigurationChange();
		}
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}
	
	@Override
	public boolean isSelected() {
		return isSelected;
	}

	@Override
	public void setSelection(Position pos) {
		isSelected = true;
		selectedPosition = pos;
		if( view != null )
			view.onSelectionChange();
	}

	@Override
	public void deSelect() {
		isSelected = false;
		if( view != null )
			view.onSelectionChange();
	}

	@Override
	public Position getSelection() {
		if( isSelected == true && selectedPosition != null )
			return selectedPosition;
		else
			throw new RuntimeException("Nulla è stato selezionato");
	}

	@Override
	public boolean isGameOver() {
		return isGameOver;
	}

	@Override
	public void setGameOver(boolean gameOver) {
		isGameOver = gameOver;	
	}
}
