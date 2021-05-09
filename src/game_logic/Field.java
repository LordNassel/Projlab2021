package game_logic;

import view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * T�rolja azokat az objektumokat (Movable), amelyek el�fordulhatnak az aszteroida�vben.
 * Tov�bb� ismeri a szomsz�dos mez�ket. Tov�bbi felel�ss�ge reag�lni az aszteroida�vben l�trej�v� k�l�nb�z� esem�nyekre.
 */
public abstract class Field{

	/**
	 * A mez� n�zete.
	 */
	protected View fieldView;
	
	/**
	 * T�rolja a szomsz�dos mez�ket.
	 */
	protected Vector<Field> Neighbors = new Vector<Field>();

	/**
	 * Az �ppen a mez�n l�v� movable objektumok.
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
	 * Megh�vja a rajta l�v� movable-ok HitByExplosion() f�ggv�ny�t.
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
	 * A napvihar el�ri �s ��tk�zik� az adott mez�vel. �rtes�t mindenkit, aki az adott mez�n van a HitBySunStorm() megh�v�s�val.
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
	 * Visszaadja a szomsz�dos mez�ket.
	 */
	public Vector<Field> FindNeighbor(){
		return this.Neighbors;
	}


	/**
	 * A param�ter�l kapott mez�t be�ll�tja szomsz�dj�ul, vagyis hozz�adja a neighbors list�hoz.
	 * @param WhichField
	 */
	public void SetNeighbor(Field WhichField) {
		Neighbors.add(WhichField);
	}

	/**
	 * A parameterul kapott mez�t torli a szomszed listabol.
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
	 * Visszaadja az �ppen a mez�n l�v� movable objektumok list�j�t.
	 */
	public List<Movable> getMovableList()
	{
		return MovableList;
	}

	/**
	 * Absztrakt f�ggv�ny n�zet l�trehoz�s�hoz.
	 */
	public abstract void createFieldView();

	/**
	 * Visszaadja a mez� n�zet�t.
	 */
	public View getFieldView()
	{
		return fieldView;
	}

}

