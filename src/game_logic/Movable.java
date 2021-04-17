package game_logic;

import java.util.*;

//Minden ami mozoghat
public abstract class Movable implements Steppable {

	//El van-e rejtozve
	private boolean isHidden;

	//konstruktor
	protected Field currentField;

	//
	public Movable(Field onField)
	{
		currentField = onField;
		isHidden = false;
	}
	

	//Befogadast ker egy parameterben kaptt mezore
	public void Move(Field a)
	{
		System.out.println("Move()");
		/*this.currentField.RemovePlayer(this);
		a.AcceptPlayer(this);*/
		
	}	
	//fur aszteroidat
	public void Drill()
	{
		System.out.println("Drill()");
		if(this.currentField.GetDrilled()==true)
			System.out.println("Sikeres furas az aszteroida kereg kisebb lett\n");
		else
			System.out.println("Sikertelen furas, a kereg mar 0\n");
		
	}
	//Elpusztul az obejektu,
	public void Die()
	{
		System.out.println("Die()");
		currentField.RemovePlayer(this);
		Game.RemoveSteppable(this);
		
	}
	//felrobban az obajektum
	public void HitByExplosion()
	{
		System.out.println("HitByExplosion");
		Die();
		
	}
	//napvihar eri az objektumot
	public void HitBySunStorm()
	{
		System.out.println("HitBySunStorm elert");
		if (!isHidden) {
			Die();
		}
	}
	// a step overridja
	public abstract void Step();

	
	//Elrejtozik
	public void Hide()
	{
		System.out.println("Hide");
		((Asteroid)currentField).GetHidden(this);
	}
	
	public Field GetCurrentField()
	{
		return this.currentField;
	}
	
	public void SetCurrentField(Field field)
	{
		this.currentField=field;
	}

	public void SetIsHidden()
	{
		System.out.println("SetIsHidden()");
		isHidden=!isHidden;
	}
}
