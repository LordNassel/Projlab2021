package game_logic;

import view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Tárolja azokat az objektumokat (Movable), amelyek elõfordulhatnak az aszteroidaövben.
 * Továbbá ismeri a szomszédos mezõket. További felelõssége reagálni az aszteroidaövben létrejövõ különbözõ eseményekre.
 */
public abstract class Field{

	/**
	 * A mezõ nézete.
	 */
	protected View fieldView;
	
	/**
	 * Tárolja a szomszédos mezõket.
	 */
	protected Vector<Field> Neighbors = new Vector<Field>();

	/**
	 * Az éppen a mezõn lévõ movable objektumok.
	 */
	protected List<Movable> MovableList;

	/**
	 * A field neve, azonositast segitendo.
	 */
	private String name;

	/**
	 * Konstruktor, megadott nevvel letrehoz egy uj fieldet
	 */
	public Field(String Name) {
		this.name = Name;
		MovableList = new ArrayList<Movable>();
	}
	
	/**
	 * A mezo felrobban.
	 * Meghívja a rajta lévõ movable-ok HitByExplosion() függvényét.
	 */
	public void Explode() {
		for(int i=MovableList.size()-1; i>=0; i--) 
			MovableList.get(i).HitByExplosion();

		for(int i = Neighbors.size()-1 ; i>=0; i--){
			Neighbors.get(i).RemoveNeighbor(this);
		}
		Neighbors = null;
		Game.getMap().FieldList.remove(this);
	}

	/**
	 * A napvihar eléri és „ütközik” az adott mezõvel. Értesít mindenkit, aki az adott mezõn van a HitBySunStorm() meghívásával.
	 */
	public void ReachedBySunStorm() {		
		if(this.getClass() == Asteroid.class)
		{
			List<Teleport> teleports = ((Asteroid)this).getTeleportsOnAsteroid();
			for(int x = 0; x< teleports.size(); x++)
				teleports.get(x).ReachedBySunStorm();
		}
		
		for(int i= MovableList.size()-1; i>=0; i--)
		{
			MovableList.get(i).HitBySunStorm(); //IndexOutOfBoundsExceptiont kapunk ha nem igy csinaljuk
		}

	}

	/**
	 * Visszaadja a szomszédos mezõket.
	 */
	public Vector<Field> FindNeighbor(){
		return this.Neighbors;
	}


	/**
	 * A paraméterül kapott mezõt beállítja szomszédjául, vagyis hozzáadja a neighbors listához.
	 * @param WhichField
	 */
	public void SetNeighbor(Field WhichField) {
		Neighbors.add(WhichField);
	}

	/**
	 * A parameterul kapott mezõt torli a szomszed listabol.
	 * @param field
	 */
	public void RemoveNeighbor(Field field)
	{
		Neighbors.remove(field);
	}

	/**
	 * Parameterul kapott movable a fieldre lep
	 * @param M
	 */
	public void AcceptPlayer (Movable M) {
		M.SetCurrentField(this);
		MovableList.add(M);
	}

	/**
	 * A parameterul kapott movable lelep a fieldrol
	 * @param M
	 */
	public void RemovePlayer(Movable M) {
		MovableList.remove(M);
	}

	/**
	 * Name-hez tartozo getter
	 * @return
	 */
	public String Getname() {return this.name;}

	/**
	 * Field-et furjak
	 * @return
	 */
	public abstract boolean GetDrilled();

	/**
	 * Visszaadja az éppen a mezõn lévõ movable objektumok listáját.
	 */
	public List<Movable> getMovableList()
	{
		return MovableList;
	}

	/**
	 * Absztrakt függvény nézet létrehozásához.
	 */
	public abstract void createFieldView();

	/**
	 * Visszaadja a mezõ nézetét.
	 */
	public View getFieldView()
	{
		return fieldView;
	}

}

