package game_logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import view.AsteroidView;

/**
 * T�rolja azokat az objektumokat (Movable), amelyekel�fordulhatnak az aszteroida�vben
 * tov�bb� ismeri a szomsz�dos mez�ket. Tov�bbi felel�ss�gereag�lni az aszteroida�vbenl�trej�v� k�l�nb�z� esem�nyekre
 */
public abstract class Field{
	
/**
 * T�rolja a szomsz�dos mez�ket
 */
protected Vector<Field> Neighbors = new Vector<Field>();
/**
 * Az �ppen a mez�n l�v� mozg�objektumok
 */
protected List<Movable> MovableList;
/**
 * A movable neve, azonositas segitendo
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
	 * Megh�vja a rajta l�v� movablek Explode f�ggv�ny�
	 */
	public void Explode() {
	for(int i=MovableList.size()-1; i>=0; i--) // Iter�ci� k�zben t�r�l�nk elemeket ez�rt ink�bb �gy
		MovableList.get(i).HitByExplosion();
	for(int i = Neighbors.size()-1 ; i>=0; i--)
	{
		Neighbors.get(i).RemoveNeighbor(this);
	}
	Neighbors = null;
	}

	/**
	 * A napvihar el�ri �s ��tk�zik�az adott mez�vel.�rtes�t mindenkit aki az adott mez�n van a HitBySunStorm()megh�v�s�val
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
	 * Az adott mez� szomsz�dj�tkeresi meg
	 */
	public Vector<Field> FindNeighbor(){
		return this.Neighbors;
	}


	/**
	 * A param�ter�l kapott mez�tbe�ll�tja szomsz�dj�ul,vagyis hozz�adja  a neighbors list�hoz
	 * @param WhichField
	 */
	public void SetNeighbor(Field WhichField) {
		Neighbors.add(WhichField);
	}

	/**
	 * A parameterul kapott field torli a szomszed listabol
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
	
	public List<Movable> getMovableList()
	{
		return MovableList;
	}

}

