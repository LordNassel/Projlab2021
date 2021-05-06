//She's an alieeeen alieeeeeen on the dancefloor
//Az ufo osztaly, egy AI iranyitott karater fuggvenyei, es osztalya
package game_logic;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import view.AlienView;
import view.SettlerView;

/**
 *  Az aszteroida�vben b�kl�sz� f�ld�nk�v�liek oszt�lya, amit egy mesters�ges intelligencia vez�rel.
 */
public class Alien extends Movable  {
    /**
     *  Ennek az ertelme limitalt, mert semmit nem tud kezdeni a nyersanyaggal. Mindegy vegulis elteszi a jatek logikaja szerint.
     */
    private Vector<Material> items = new Vector<Material>();

    /**
     *  mivel az aszteroida nem k�zli az aliennel hogy � �res-e az alien elt�rolja ezt. Ez az AI d�nt�shoz�s�t teszi lehet�v�.
     */
    private boolean thisasteroidempty = false;

    /**
     *  Ez a default konstruktor. egyelore a field-el megegyezo.
     *  F param, bolygon kell peldanyositani a movableket.
     */
    public Alien(String name, Field f) {
        super(f);
		movablesName = name;
		createMovableView();
    }


    /**
     *  Az AI l�pteti egy m�sik bolyg�ra vagy pedig b�ny�szik a jelenlegin.
     */
    public void Step(){
    	System.out.println("Alien step");
    	if(thisasteroidempty == false) {
        	Mine();
        }
        else {
            Move_AI();
        }
    }

    /**
     *   Egy egys�g nyersanyagot b�ny�szik.
     */
   public void Mine(){

        Material minedMaterial = ((Asteroid)currentField).GetMined();
        if(minedMaterial!= null) {
            items.add(minedMaterial);
            //Ja, tudom, de osszesen 3x hivodhat meg ez a fgv maximum
            //Mine(); //Maradjunk ann�l, hogy � is egyszerre csak egyet tudjon kiszedni
        }
        else{
            thisasteroidempty = true;
            return;
        }

    }

    /**
     *   A random mozgasert felelos belso fuggveny.
     */
    private void Move_AI(){
        Vector<Field> neighbor = currentField.FindNeighbor();
        Random Dirgen = new Random();
        //elmegy egy random bolygora
        super.Move(neighbor.get(Dirgen.nextInt(neighbor.size())));
    }
    
    public void HitBySunStorm()
    {
    	return;
    }
    
	@Override
	public void createMovableView()
	{
		int x = this.currentField.getFieldView().getPosx();
		int y = this.currentField.getFieldView().getPosy();
		try {
			this.movableView = new AlienView(x+110,y+10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
