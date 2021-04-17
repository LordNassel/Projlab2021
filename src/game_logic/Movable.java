package game_logic;

import java.util.*;

//Minden ami mozoghat
public abstract class Movable implements Steppable {
	//A movable forduloja van e
	private boolean isTurn;
	//El van-e rejtozve
	private boolean isHidden;
	//A surgarzas merteke, ezt ellenorizheti a jatekos
	protected double radiation;

	//konstruktor
	protected Field currentField;

	//
	public Movable(Field onField)
	{
		currentField = onField;
		radiation = 1;
		isTurn = false;
		isHidden = false;
	}
	

	//Befogadast ker egy parameterben kaptt mezore
	public void Move(Field a)
	{
		System.out.println("Move()");
		this.currentField.RemovePlayer(this);
		a.AcceptPlayer(this);
		
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
		System.out.println("HitBySunStorm");
		if (!isHidden) {
			Die();
		}
	}
	// a step overridja
	public void Step()
	{
		
	}
	
	//Elrejtozik
	public void Hide()
	{
		System.out.println("Hide");
		((Asteroid)currentField).GetHidden(this);
	}
	//Beallitja a movable -nek a kort
	public void SetTurn()
	{
		
	}
	
	public void SetRadiation(double rad)
	{
		System.out.println("SetRadiation");
		radiation = rad;
		
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
		this.isHidden=!this.isHidden;
	}
}
