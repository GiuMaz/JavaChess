package model;

/**
 * Implementazione dell'Interfaccia Position
 * implementata in maniera diretta con due valori interi
 *
 *  Per la descrizione dei singoli metodi si rimanda all'interfaccia
 *
 */
public final class ChessPosition implements Position {

	private final int row;
	private final int col;

	public ChessPosition(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	@Override
	public int getRow() {
		return row;
	}

	@Override
	public int getColumn() {
		return col;
	}

	@Override
	public ChessPosition up() {
		return new ChessPosition(row-1,col);
	}

	@Override
	public ChessPosition down() {
		return new ChessPosition(row+1,col);
	}

	@Override
	public ChessPosition left() {
		return new ChessPosition(row,col-1);
	}

	@Override
	public ChessPosition right() {
		return new ChessPosition(row,col+1);
	}

	@Override
	public boolean isInBound() {
		return col >= 0 && col <=7 && row >=0 && row <=7;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof ChessPosition 
				&& ((ChessPosition) other).getColumn() == col 
				&& ((ChessPosition) other).getRow() == row;
	}
	
	@Override
	public int hashCode() {
		return row | (col << 4) ;
	}

}
