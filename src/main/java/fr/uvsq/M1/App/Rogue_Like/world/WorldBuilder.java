package fr.uvsq.M1.App.Rogue_Like.world;

import java.io.Serializable;

public class WorldBuilder implements Serializable{
	/**
     * to solve some compilation problem.
     */
	private static final long serialVersionUID = 1L;
	private int width;
    private int height;
    private Tile[][] tiles;

    public WorldBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    private WorldBuilder construitMur(int x, int y) {
        int i = 0;
        if(Math.random() < 0.5){
            while((i < 3) && (x < width)){
                tiles[x][y] = Tile.WALL;
                i ++;
                x ++;
            }
        }
        else {
            while((i < 3) && (y < height)){
                tiles[x][y] = Tile.WALL;
                i ++;
                y ++;
            }
        }
        return this;
    }

    
    private WorldBuilder randomizeTiles() {
    	int x_random, y_random;
        //on met du sol partout
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                    tiles[x][y] = Tile.FLOOR;
            }
        }
        // on met des murs a des endroits aleatoires
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(Math.random() >= 0.99){
                    this.construitMur(x,y);
                }
            }
        }

        // on met 5 dollars sur la map
        for(int money = 0 ; money < 5 ; money ++) {
            x_random = (int)(Math.random() * width);
            y_random = (int)(Math.random() * height);
            while(tiles[x_random][y_random] != Tile.FLOOR){
                x_random = (int)(Math.random() * width);
                y_random = (int)(Math.random() * height);
            }
            tiles[x_random][y_random] = Tile.MONEY;
        }
        //met les arbre
        for(int a = 0 ; a < 4 ; a ++) {
            x_random = (int)(Math.random() * width);
            y_random = (int)(Math.random() * height);
            while(tiles[x_random][y_random] != Tile.FLOOR){
                x_random = (int)(Math.random() * width);
                y_random = (int)(Math.random() * height);
            }
            tiles[x_random][y_random] = Tile.TREE;
        }
        // on met le clé
        x_random = (int)(Math.random() * width);
        y_random = (int)(Math.random() * height);
        while(tiles[x_random][y_random] != Tile.FLOOR){
            x_random = (int)(Math.random() * width);
            y_random = (int)(Math.random() * height);
        }
        tiles[x_random][y_random] = Tile.KEY;

        // on met une porte
        x_random = (int)(Math.random() * width);
        y_random = (int)(Math.random() * height);
        while(tiles[x_random][y_random] != Tile.FLOOR){
            x_random = (int)(Math.random() * width);
            y_random = (int)(Math.random() * height);
        }
        tiles[x_random][y_random] = Tile.DOOR;
        //add magic 
        x_random = (int)(Math.random() * width);
        y_random = (int)(Math.random() * height);
        while(tiles[x_random][y_random] != Tile.FLOOR){
            x_random = (int)(Math.random() * width);
            y_random = (int)(Math.random() * height);
        }
        int m = (int)(Math.random() * Magic.NB_MAGIC.ordinal() + 1); // ordinal recupere le nombre d'enum
        Magic spell = Magic.values()[m];
        if(spell == Magic.MEGA_POWER)
        		tiles[x_random][y_random] = Tile.MEGA_POWER;
        else if(spell == Magic.TRAVEL)
        	 	tiles[x_random][y_random] = Tile.TRAVEL;
        
        // on met 2 arme
        int type_arme;
        Weapon nb_armes = Weapon.NB_WEAPON;
        Weapon arme_choisi;
        for(int weap = 0 ; weap < 2 ; weap ++) {
        x_random = (int)(Math.random() * width);
        y_random = (int)(Math.random() * height);
        
        while(tiles[x_random][y_random] != Tile.FLOOR) {
            x_random = (int)(Math.random() * width);
            y_random = (int)(Math.random() * height);
        }
        
        do{
            type_arme = (int)(Math.random() * nb_armes.ordinal() + 1); // ordinal recupere le nombre d'enum
            arme_choisi = Weapon.values()[type_arme];
        }while((arme_choisi == Weapon.NO_WEAPON) || (arme_choisi == Weapon.NB_WEAPON));
        
        if(arme_choisi == Weapon.GUN) 
        	tiles[x_random][y_random] = Tile.GUN;
        else if(arme_choisi == Weapon.POISON)
        	tiles[x_random][y_random] = Tile.POISON;
        else if(arme_choisi == Weapon.SWORD) 
        	tiles[x_random][y_random] = Tile.SWORD;
        }
        // on met 2 vies sur la map
        for(int vie = 0 ; vie < 2 ; vie ++) {
            x_random = (int)(Math.random() * width);
            y_random = (int)(Math.random() * height);
            while(tiles[x_random][y_random] != Tile.FLOOR){
                x_random = (int)(Math.random() * width);
                y_random = (int)(Math.random() * height);
            }
            tiles[x_random][y_random] = Tile.LIFE;
        }
        return this;
    }

    public WorldBuilder makeCaves() {
    return randomizeTiles();
    }

    public World build() {
        return new World(tiles);
    }
}
