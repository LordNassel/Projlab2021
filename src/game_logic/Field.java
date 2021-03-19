package game_logic;

import java.util.Vector;
import game_logic.Movable;


abstract class Field {
//Vector storage for convenience, an array would work too since <=300
protected Vector<Field> Neighbors = new Vector<Field>();
protected Vector<Movable> MovableList = new Vector<Movable>();

//Explode. Kills all things on map then removes itself from the map.
protected void Explode() {
	for(int i=0; i<MovableList.Size(); i++)
		MovableList.Movable.Die();
	Neighbors =null;
	//delete me from the map pls!!!
	}

//Sunstorm on a generic asteroid type, the asteroid remains undamaged, all movables die.
public void SunStorm() {
		for(int i=0; i<MovableList.Size(); i++)
			MovableList.Movable.Die();
}

//Simple getter function for all neighbors, used mostly for player and AI navigation 
public Vector<Field> Getneighbors(){return this.Neighbors;}
//Simple neighbor setter functon
public Field SetNeighbor(Field WhichField) {Neighbors.add(WhichField);}

//To be implemented
protected void BroadCastRadiation() {};


}