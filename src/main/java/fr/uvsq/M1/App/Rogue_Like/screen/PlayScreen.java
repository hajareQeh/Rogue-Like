package fr.uvsq.M1.App.Rogue_Like.screen;

import java.awt.event.KeyEvent;
import java.io.Serializable;

import asciiPanel.AsciiPanel;
import fr.uvsq.M1.App.Rogue_Like.world.*;
import java.util.ArrayList;

/**
 * the game environment.
 */
public class PlayScreen implements Screen,Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Liste de PNJ (FAMILY or FAIRY or ZAMBIE or WITCH).
	 */
	private ArrayList <PNJ> listePNJ;
	private int level;
	private int screenWidth;
	private int screenHeight;
	public String msg;
	private PJ player;
	/**
	 * Map the world Rogue Like.
	 */
	private World world;

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public PJ getPlayer() {
		return player;
	}

	public void setPlayer(PJ player) {
		this.player = player;
	}

	public ArrayList<PNJ> getListePNJ() {
		return listePNJ;
	}

	public void setListePNJ(ArrayList<PNJ> listePNJ) {
		this.listePNJ = listePNJ;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Constructor of PlayScreen which generate the map, PJ and PNJ.
	 * @param Weapon of player.
	 * @param  of player.
	 */
	public PlayScreen(int level, Weapon arme, Magic magic, int money){
		screenWidth = 80;
		screenHeight = 21;
		this.level = level;

		createWorld();
		player=new PJ(world, arme, magic, money);
		createPNJ(world);

	}

	private boolean confusion(int x, int y, int rang){
		if((this.player.x == x) && (this.player.y == y)) return false;
		else{
			for(int i = 0; i < rang; i ++){
				if((this.listePNJ.get(i).x == x) && (this.listePNJ.get(i).y == y)) return false;
			}
		}
		return true;
	}

	private void spawnPNJ(){
		for(int i = 0; i < this.listePNJ.size(); i ++){
			int mx = 0;
			int my = 0;
			int deplacement;
			do {
				deplacement = (int)(Math.random() * 4);
				if(deplacement == 0) { mx = 0; my = 1;}
				else if(deplacement == 1) {
					mx = 0; my = -1; 
				}else if(deplacement == 2) {
					mx = 1; my = 0; 
				}else if(deplacement == 3) {
					mx = -1; my = 0; 
				}
			}
			while ((!world.tile(this.listePNJ.get(i).x + mx, this.listePNJ.get(i).y + my).isGround())
					||(!confusion(this.listePNJ.get(i).x + mx, this.listePNJ.get(i).y + my,i)));

			this.listePNJ.get(i).x += mx;
			this.listePNJ.get(i).y += my;

			if(this.listePNJ.get(i).approximated(player)) {

				if(this.listePNJ.get(i).gettype()==Enum_PNJ.FAIRY) {

					int k=this.listePNJ.get(i).getcadeau();
					msg = "here i will give you " + k + " " + (char)3 + " to help you good luck";
					player.addLife(k);
					this.listePNJ.get(i).setLife(0);

				}else if(this.listePNJ.get(i).gettype() == Enum_PNJ.FAMILY) {

					int k = this.listePNJ.get(i).getcadeau();
					msg = "here i will give you " + k + " $ to help you take care son";
					player.setmoney(k);
					this.listePNJ.get(i).setLife(0);

				}else
					msg = this.listePNJ.get(i).approximatedAttack(player);
			}
		}
	}

	// enlever difficulte
	private void createPNJ(World world){

		this.listePNJ = new ArrayList<PNJ>();
		int nb_pnj = 15 * level;
		Enum_PNJ new_pnj; 
		int type_pnj;
		for(int i = 0;i < nb_pnj;i ++) {
			type_pnj = (int)(Math.random() * Enum_PNJ.NB_ENUM_PNJ.ordinal()); // ordinal recupere le nombre d'enum
			if(type_pnj == 0) type_pnj = 1;
			new_pnj = Enum_PNJ.values()[type_pnj];
			this.listePNJ.add(new PNJ(world, new_pnj));
		}

		this.listePNJ.add(new PNJ(world, Enum_PNJ.FAIRY));
		spawnPNJ();

	}


	/**
	 * Private Method generate the world.
	 */
	private void createWorld(){
		world = new WorldBuilder(90, 32)
				.makeCaves()
				.build();
	}

	/**
	 * Methode de point de vue de la camera sur l'axe de la longueur.
	 * @return position de la camera en longueur.
	 */
	public int getScrollX() { return Math.max(0, Math.min(player.x - screenWidth / 2, world.getWidth() - screenWidth)); }

	/**
	 * Methode de point de vue de la camera sur l'axe de la largeur.
	 * @return position de la camera en largeur.
	 */
	public int getScrollY() { return Math.max(0, Math.min(player.y - screenHeight / 2, world.getHeight() - screenHeight)); }

	/**
	 * Methode of possible interaction with player.
	 * @param terminal represente the game screen.
	 */
	//@Override
	public void displayOutput(AsciiPanel terminal) {

		int left = getScrollX();
		int top = getScrollY(); 

		displayTilesCreatures(terminal, left, top);

		terminal.write(player.getGlyph(), player.x - left, player.y - top, player.getColor());
		terminal.write(player.getLife() + "/100 " + (char)3 , 0, 21);
		terminal.write("Weapon : " + player.getWeapon().getDescription(), 0, 22);
		terminal.write("Magic  : " + player.getMagic().getNom(), 0, 23);
		terminal.write(this.player.getmoney() + " $ " , 70, 21);

		if(player.getClef() == true) terminal.write((char)213, 70, 22, AsciiPanel.brightYellow);

		terminal.writeCenter("Level " + this.level, 21, AsciiPanel.white);

		if(this.msg != null) terminal.writeCenter(this.msg, 22, AsciiPanel.brightGreen);
		this.msg = null;
	}

	/**
	 * Affiche Tiles. 
	 * @param terminal where Tiles are affiched;
	 * @param left length of screen.
	 * @param top height of screen.
	 */
	private void displayTilesCreatures(AsciiPanel terminal, int left, int top) {
		PNJ pnj;
		for (int x = 0; x < screenWidth; x ++) {
			for (int y = 0; y < screenHeight; y ++) {
				int wx = x + left;
				int wy = y + top;

				terminal.write(world.glyph(wx, wy), x, y, world.color(wx, wy));
				for(int ii = 0 ; ii < this.listePNJ.size() ; ii ++){
					pnj = this.listePNJ.get(ii);
					if(((x + left) == pnj.x) && ((y + top) == pnj.y)) { //mettre x+left et y+top
						terminal.write(pnj.gettype().getCaractere(), x, y, pnj.gettype().getColor());
					}
				}
			}
		}
	}

	private boolean testChangerlevel() {
		if(player.getClef()) {
			if((world.tile(player.x + 1, player.y) == Tile.DOOR)
					|| (world.tile(player.x, player.y + 1) == Tile.DOOR)
					|| (world.tile(player.x, player.y - 1) == Tile.DOOR)
					|| (world.tile(player.x - 1, player.y) == Tile.DOOR)){
				player.laisserClef();
				msg = "you win!!!! yaaay now let's go to the next level ";
				return true;
			}else {
				msg = "you have the key heary up and go to the door ";
				return false;
			}

		}else {
			msg = "search for the key!!! ";
			return false;
		}
	}

	/**
	 * Methode qui permet au player d'interagir avec l'environement.
	 * @param key touche que player tape sur le clavier.
	 * @return nouvel ecran a afficher apres l'interaction avec l'environement.
	 */

	//@Override
	public Screen respondToUserInput(KeyEvent key) {
		boolean action = false;
		switch (key.getKeyCode()) {
		case KeyEvent.VK_LEFT: player.moveBy(-1, 0); action = true; break;
		case KeyEvent.VK_RIGHT: player.moveBy( 1, 0); action = true; break;
		case KeyEvent.VK_UP: player.moveBy( 0,-1); action = true; break;
		case KeyEvent.VK_DOWN: player.moveBy( 0, 1); action = true; break;
		case KeyEvent.VK_R: msg=player.ramasserObjet(world); action = true; break;
		case KeyEvent.VK_Z: for(int i = 0;i < listePNJ.size(); i++) {
										if(listePNJ.get(i).approximated(player)) 
												msg = listePNJ.get(i).approximatedAttack(player);
							}action=true;break;
		
		case KeyEvent.VK_O:
			if(testChangerlevel()) return new PlayScreen(level + 1, player.getWeapon(), player.getMagic(), player.getmoney()); break;
		case KeyEvent.VK_P: msg=player.useMagic(world); break;
		case KeyEvent.VK_S: return new Dialoge(this);
		case KeyEvent.VK_Q: return new QuitRL();

		}
		for(int i = 0;i< listePNJ.size(); i ++) {
			if(listePNJ.get(i).getLife() <= 0)
				listePNJ.remove(i);
		}
		if(action == true) this.spawnPNJ();
		if(player.getLife() <= 0) return new LoseScreen();
		if(level == 10) return new WinScreen();

		return this;
	}
}