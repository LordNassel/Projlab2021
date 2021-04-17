package game_logic;

import java.util.Vector;


abstract class Field implements Steppable{
//Vector storage for convenience, an array would work too since <=300
protected Vector<Field> Neighbors = new Vector<Field>();
protected Vector<Movable> MovableList = new Vector<Movable>();
protected boolean isMinable = false;
//Kell egy nev most ahhoz, hogy a paranccsoros navigacio letezhessen
private String name;


	public Field(String Name) {
	this.name = Name;
}

	//Explode. Kills all things on map then removes itself from the map.
	protected void Explode() {
	System.out.println("Field.Explode Called");
	for(int i=0; i<MovableList.size(); i++)
		MovableList.get(i).HitByExplosion();
	Neighbors =null;
	}

	//Sunstorm on a generic asteroid type, the asteroid remains undamaged, all movables die.
	public void ReachedBySunStorm() {
	System.out.println("Field.ReachedBySunStorm Called");
		for(int i=0; i<MovableList.size(); i++)
			MovableList.get(i).HitBySunStorm();
	}

	//Simple getter function for all neighbors, used mostly for player and AI navigation
	public Vector<Field> FindNeighbor(){/*System.out.println("Field.FindNeighbor Called")*/ return this.Neighbors;}


	//Simple neighbor setter functon
	public void SetNeighbor(Field WhichField) {/*System.out.println("Field.SetnNeighbor Called"*/ Neighbors.add(WhichField);}

	//To be implemented
	protected void BroadCastRadiation(double rad) {
		System.out.println("Field.BroadCastRadiation Called");
		for(int i=0; i<MovableList.size(); i++)
			MovableList.get(i).radiation = rad;
	}

	public void AcceptPlayer (Movable M) {
	//System.out.println("Field.AcceptPlayer Called");
	M.SetCurrentField(this);
	MovableList.add(M);
	}

	public void RemovePlayer(Movable M) {
	System.out.println("RemovePlayer Called");
	M.SetCurrentField(null);
	MovableList.remove(M);
	}

	public String Getname() {return this.name;}
	//Ez sajnos kell az aliennek
	public boolean getMinable(){ return this.isMinable;}
	public abstract boolean GetDrilled();

	//Ez ket tipusellenorzest elkerulendo
	public Material GetMined(){return null;}


}

