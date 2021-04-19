//She's an alieeeen alieeeeeen on the dancefloor
//Az ufo osztaly, egy AI iranyitott karater fuggvenyei, es osztalya
package game_logic;

import java.util.Random;
import java.util.Vector;

public class Alien extends Movable  {
    //Ennek az ertelme limitalt, mert semmit nem tud kezdeni a nyersanyaggal. Mindegy vegulis elteszi a jatek logikaja szerint
    private Vector<Material> items = new Vector<Material>();
    //o jegyzi meg hogy amin all-e az ures-e
    private boolean thisasteroidempty = false;
    //Ez a default konstruktor. egyelore a field-el megegyezo
    //F param, bolygon kell peldanyositani a movableket
    public Alien(String name, Field f) {
        super(f);
		movablesName = name;
    }

    public Alien(Field f) {
        super(f);
    }
    public void Step(){
        //Mivel nem igazán tud semmit semmirol, illetve nem nagyon tud semmit csinalni minden lepesben banyaszik,
        // ha nem tud, elmegy  egy random szomszedos aszteroidara;
        //Ha teleportra tevedne semmi gond, mert az isMinable ugyanugy false lesz
    	System.out.println("Alien vagyok és most lépek a körben");
    	
        //if( ((Asteroid)currentField).getThickness() == 0 && ((Asteroid)currentField).isEmpty() == false) {
    	if(thisasteroidempty == false) {
        	System.out.println("	Bányásztam aleinként");
        	Mine();

        	//Material minedMaterial=currentField.GetMined();
        }
        //Ha nem tud banyaszni akkor mozog... Egy korben csak az egyiket csinalhatja szoval jo ez igy
        else {
        	System.out.println("	Mozogtam aleinként");
            Move_AI();
        }



    }

    //Ezek privat fgv-ek hogy a step ne legyen egy kupleraj
   public void Mine(){

        Material minedMaterial = ((Asteroid)currentField).GetMined();
        if(minedMaterial!= null) {
            items.add(minedMaterial);
            //Ja, tudom, de osszesen 3x hivodhat meg ez a fgv maximum
            //Mine(); //Maradjunk annál, hogy õ is egyszerre csak egyet tudjon kiszedni
        }
        else{
            thisasteroidempty = true;
            return;
        }

    }
    //A mozgasert felelos belso fuggveny
    private void Move_AI(){
        Vector<Field> neighbor = currentField.FindNeighbor();
        Random Dirgen = new Random();
        //elmegy egy random bolygora
        super.Move(neighbor.get(Dirgen.nextInt(neighbor.size()-1)));
    }
    
    public void HitBySunStorm()
    {
    	return;
    }
}
