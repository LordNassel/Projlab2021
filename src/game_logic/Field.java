package game_logic;

import java.util.Vector;
import game_logic.Movable;

abstract class Field {
//Vector storage for convenience, an array would work too since <=300
protected Vector<Field> Neighbors = new Vector<Field>();
protected Vector<Movable> MovableList = new Vector<Movable>();
protected void Explode() {Neighbors.remove(this);}
abstract void SunStorm();

}
