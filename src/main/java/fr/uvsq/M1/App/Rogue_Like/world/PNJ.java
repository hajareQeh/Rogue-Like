package fr.uvsq.M1.App.Rogue_Like.world;


/**
 * type PNJ representant les creatures pacifiques et agressives.
 */
public class PNJ extends Creature{

	private static final long serialVersionUID = 1L;

	/**
	 * Classe de la creature.
	 */
	private Enum_PNJ type;

	/**
	 * Volonte du PNJ a vouloir de l'money contre la clef
	 * (specifique pour le Villageois).
	 */
	private int cadeau;


	/**
	 * Constructeur de PNJ.
	 * @param monde dans lequel se trouve le PNJ.
	 * @param type_pnj classe du PNJ.
	 */
	public PNJ(World monde, Enum_PNJ type_pnj){
		super(monde, type_pnj.getCaractere(), type_pnj.getColor(), (int)(Math.random() * 10)+1);
		this.type = type_pnj;
		if(type_pnj == Enum_PNJ.FAIRY){
			this.setLife(1);
			int life_cadeau = (int)(Math.random() * 7) + 10;
			this.cadeau = life_cadeau;

		}else if(type_pnj == Enum_PNJ.FAMILY){
			this.setLife(1);
			int money_cadeau = (int)(Math.random() * 7) + 1;
			this.cadeau = money_cadeau;
		}
		else 
			this.cadeau = 0;
	}


	/**
	 * Accesseur de la classe de PNJ.
	 * @return differents types de PNJ.
	 */
	public Enum_PNJ gettype() {
		return this.type;
	}

	/**
	 * Accesseur pour savoir combien veut le villageois en echange de la clef.
	 * @return entier representant la somme d'money qu'il veut.
	 */
	public int getcadeau() {
		return this.cadeau;
	}

	/**
	 * Methode qui permet au joueur d'attaquer le PNJ.
	 * @param joueur qui attaque
	 * @return false si le PNJ est mort et true sinon.
	 */
	public String Attaque(PJ joueur) {
		if(this.getLife() - joueur.getWeapon().getDegats() <= 0) {
			this.life = 0;
			return "you killed the monster good!!!";
		}else {
			this.life -= joueur.getWeapon().getDegats();
			return "the monster lost " + joueur.getWeapon().getDegats() + (char)3 + " good work keep going";
		}
	}
	/**
	 * Methode qui permt au PNJ de frapper le joueur.
	 * @param joueur a frapper.
	 * @return false si le joueur est mort et true sinon.
	 */

	public boolean approximated(PJ player) {
		int a = player.x - this.x;
		int b = player.y - this.y;
		if((a == 1 || a == -1 || a == 0 ) & ( b == -1 || b == 1 || b == 0 )) {
			return true;
		}else
			return false;
	}

	public String approximatedAttack(PJ joueur) {
		if(this.approximated(joueur) && this.type != Enum_PNJ.FAIRY && this.type != Enum_PNJ.FAMILY) {
			if(joueur.getWeapon().getDegats() < this.type.getDegats()) {

				return joueur.beingAttacked(this);
			}else
				return this.Attaque(joueur);
		}
		return "";
	}
}

