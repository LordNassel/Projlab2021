package game_logic;

import java.util.Vector;


abstract class Field implements Steppable{
//Vector storage for convenience, an array would work too since <=300
protected Vector<Field> Neighbors = new Vector<Field>();
protected Vector<Movable> MovableList = new Vector<Movable>();

//Explode. Kills all things on map then removes itself from the map.
protected void Explode() {
	System.out.println("Field.Explode Called");
	for(int i=0; i<MovableList.size(); i++)
		MovableList.get(i).Die();
	Neighbors =null;
	//delete me from the map pls!!!
	}

//Sunstorm on a generic asteroid type, the asteroid remains undamaged, all movables die.
public void SunStorm() {
	System.out.println("Field.SunStorm Called");
		for(int i=0; i<MovableList.size(); i++)
			MovableList.get(i).Die();
}

//Simple getter function for all neighbors, used mostly for player and AI navigation 
public Vector<Field> FindNeighbor(){System.out.println("Field.FindNeighbor Called"); return this.Neighbors;}


//Simple neighbor setter functon
public void SetNeighbor(Field WhichField) {System.out.println("Field.SetnNeighbor Called"); Neighbors.add(WhichField);}

//To be implemented
protected void BroadCastRadiation() {System.out.println("Field.BroadCastRadiation Called");};

public void AcceptPlayer (Movable M) {
	System.out.println("Field.AcceptPlayer Called");
	M.SetCurrentField(this);
	MovableList.add(M);
	M.Sethidden(false);
	}
public void RemovePlayer(Movable M) {
	System.out.println("RemovePlayer Called");
	M.SetCurrentField(null);
	MovableList.remove(M);
}

}