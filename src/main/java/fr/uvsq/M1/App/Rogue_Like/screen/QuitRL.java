package fr.uvsq.M1.App.Rogue_Like.screen;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class QuitRL implements Screen {
	/**
	 * to quit the terminal
	 */
	QuitRL(){
		System.exit(0);
	}
	public void displayOutput(AsciiPanel terminal) {

	}

	public Screen respondToUserInput(KeyEvent key) {
		return null;
	}

}
