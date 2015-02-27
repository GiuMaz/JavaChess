package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import view.ChessView;

/**
 * 
 * @author Bonfante Pietro e Mazzi Giulio
 *
 */
public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new ChessView();
				frame.setTitle("JavaChess");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});

	}

}
