package fr.uvsq.M1.App.Rogue_Like.screen;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

import asciiPanel.AsciiPanel;
import fr.uvsq.M1.App.Rogue_Like.AppMain;
import fr.uvsq.M1.App.Rogue_Like.save.EntreeSortie;



public class Dialoge implements Screen {
	private String url;
	private String url1=null;
	private PlayScreen s;
	private String msg="";
	
	/**
	 * constricture called by StartScreen
	 */
	public Dialoge() {
		PlayScreen a=outDialoge();
		AppMain app = new AppMain(a);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
	}
	/**
	 * save the player parte and go to StartScreen
	 * @param o : instance de la class PlayScreen
	 */
	
	public Dialoge(PlayScreen o) {
		inDialoge(o);
		new StartScreen();
	}
	
	/**
	 * save the player screen in the derectory he chose
	 * @param o : instance de la class PlayScreen
	 */
	public void inDialoge(PlayScreen o) {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			url=selectedFile.getAbsolutePath();
		}

		try {
			EntreeSortie es =new EntreeSortie();
			if(url != null) {
				es.ouvrir("ECRIRE", url);
				es.ecrire(o);
				es.fermer();
				msg="Sauvegarde reussie";
			}else {
				msg="Enregistrement annule";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * pull out the player screen in the derectory he chose
	 * @return the saved PlayScren
	 */
	public PlayScreen outDialoge() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			url=selectedFile.getAbsolutePath();
			url1=selectedFile.getAbsolutePath();
		}
		try {
			EntreeSortie es =new EntreeSortie();
			if( url1 != null) {
				es.ouvrir("Lire", url);
				s=es.lire();
				es.fermer();
				return s;	

			}else {
				msg="Overture annulee !";
			}
		} catch (Exception e) {
			msg="votre fichier est incompatible !";
		}
		return s;

	}
	//@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter(msg, 10, AsciiPanel.brightBlue);
		terminal.writeCenter("-- press [Q] to QUITE --", 24);
		terminal.writeCenter("-- press [A] to go back to the menu principal --", 25);

	}
	//@Override
	public Screen respondToUserInput(KeyEvent key) {
		if(key.getKeyCode() == KeyEvent.VK_A) return new StartScreen();
		else if(key.getKeyCode() == KeyEvent.VK_Q) return new QuitRL();
		else 
			return this;
	}
}