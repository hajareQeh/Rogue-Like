package fr.uvsq.M1.App.Rogue_Like.world;

import asciiPanel.AsciiPanel;
/**
 * PJ Class (player)
 */
public class PJ extends Creature{

	private static final long serialVersionUID = 1L;

	/**
	 * player's weapon
	 */
	private Weapon arme;

	/**
	 * Player's magic
	 */
	private Magic magic;

	/**
	 * money possessed by the player.
	 */
	private int money;

	private boolean key;

	/**
	 * Constructor of the Player.
	 * @param world the universe where play the player.
	 * @param arme du joueur.
	 * @param money player money 
	 */
	public PJ(World world, Weapon arme, Magic magic, int money) {
		super(world, (char)1, AsciiPanel.brightWhite, 100);
		this.arme = arme;
		this.magic = magic;
		this.money = money;
		this.key = false;
	}

	/**
	 * @return weapon.
	 */
	public Weapon getWeapon() {
		return this.arme;
	}

	/**
	 * @return magic.
	 */
	public Magic getMagic() {
		return this.magic;
	}

	/**
	 * @return money posseded for the player.
	 */
	public int getmoney() {
		return this.money;
	}
	public void setmoney(int i) {
		this.money += i;
	}

	public boolean getClef() {
		return this.key;
	}

	public void laisserClef() { 
		this.key = false;
	}

	public String useMagic(World world) {
		
		if(this.magic == Magic.TRAVEL) {
			world.addAtEmptyLocation(this);
			return "booooom you have traveled!!!!!";
		}
		else if(this.magic == Magic.MEGA_POWER) {
			this.setLife(100);
			return "MEGA_POWER 100/100 "+(char)3;
		}
		return "you have noo magic";
	}

	/**
	 * interacte with the world
	 * @param world
	 * @return msg telling what the player did found
	 */
	public String ramasserObjet(World world){
		
		Tile element = world.tile(this.x, this.y);
		
		if(element == Tile.KEY) {
			world.affectationTile(this.x, this.y, Tile.FLOOR);
			this.key = true;
		}
		else if(element == Tile.TRAVEL || element == Tile.MEGA_POWER){
			if(world.tile(this.x, this.y) == Tile.TRAVEL){
				this.magic = Magic.TRAVEL;
				world.affectationTile(this.x, this.y, Tile.FLOOR);
			}
			else if (world.tile(this.x, this.y) == Tile.MEGA_POWER){
				this.magic = Magic.MEGA_POWER;
				world.affectationTile(this.x, this.y, Tile.FLOOR);
			}

		}
		else if(element == Tile.MONEY){
			world.affectationTile(this.x, this.y, Tile.FLOOR);
			this.money++;
		}
		else if(element == Tile.LIFE){
			if(this.life + 10 <= 100) {
				world.affectationTile(this.x, this.y, Tile.FLOOR);
				this.life += 10;
			}else 
				return "you have reached the max Life";
		}
		else if((element == Tile.KEY&&(this.key == false))){
			world.affectationTile(this.x, this.y, Tile.FLOOR);
			this.key = true;
		}
		else if((element == Tile.GUN)||(element == Tile.SWORD)||(element == Tile.POISON)){
			Weapon weap = this.arme;
			// si il y a une batte par terre on la ramasse
			if(world.tile(this.x, this.y) == Tile.GUN){
				this.arme = Weapon.GUN;
				world.affectationTile(this.x, this.y, Tile.FLOOR);
			}
			// si il y a un SWORD par terre on la ramasse
			else if (world.tile(this.x, this.y) == Tile.SWORD){
				this.arme = Weapon.SWORD;
				world.affectationTile(this.x, this.y, Tile.FLOOR);
			}
			// si il y a un POISON par terre on la ramasse
			else if (world.tile(this.x, this.y) == Tile.POISON){
				this.arme = Weapon.POISON;
				world.affectationTile(this.x, this.y, Tile.FLOOR);
			}
			// si l'arme anciennement possedee etait une batte on la pose par terre
			if(weap == Weapon.GUN){
				world.affectationTile(this.x, this.y, Tile.GUN);
			}
			// si l'arme anciennement possedee etait un SWORD on la pose par terre
			else if(weap == Weapon.SWORD){
				world.affectationTile(this.x, this.y, Tile.SWORD);
			}
			// si l'arme anciennement possedee etait une POISON on la pose par terre
			else if(weap == Weapon.POISON){
				world.affectationTile(this.x, this.y, Tile.POISON);
			}
			if(weap != Weapon.NO_WEAPON)
				return "you found " + element + " let go of " + weap;

		}
		if(element != Tile.FLOOR)
			return "you found :" + element.name();
		return "go faster";
	}

	public String beingAttacked(PNJ pnj) {
		if(this.life - pnj.gettype().getDegats() <= 0) {
			this.life = 0;
			return "you lost the game";
		}else{
			this.life -= pnj.gettype().getDegats();
			return "you are beeing attacked by " + pnj.gettype() + " you lost " + pnj.gettype().getDegats() + (char)3;
		}

	}

	public void setWeapon(Weapon arme2) {
		this.arme=arme2;		
	}



}