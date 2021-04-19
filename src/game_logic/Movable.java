package game_logic;

import java.util.*;

//Minden ami mozoghat
public abstract class Movable implements Steppable {

	//El van-e rejtozve
	protected boolean isHidden;
	protected String movablesName;

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
		if(!isHidden)
		{
		this.currentField.RemovePlayer(this);
		a.AcceptPlayer(this);
		}
		else
			System.out.println("Sikertelen: Elobb buj elo a muvelet elvegzesehez");
		
	}
	//fur aszteroidat
	public void Drill()
	{
		if(!isHidden)
		{
			if(this.currentField.GetDrilled()==true)
				System.out.println("Sikeres furas az aszteroida kereg kisebb lett\n");
			else
				System.out.println("Sikertelen furas, a kereg mar 0\n");
		}
		else
			System.out.println("Sikertelen: Elobb buj elo a muvelet elvegzesehez");
		
	}
	//Elpusztul az obejektu,
	public void Die()
	{
		currentField.RemovePlayer(this);
		Game.RemoveSteppable(this);
		if(this.getClass() == Settler.class)
			System.out.println("A(z) " + movablesName + " jatekos meghalt, beke poraira");
		
	}
	//felrobban az obajektum
	public void HitByExplosion()
	{
		Die();
		
	}
	//napvihar eri az objektumot
	public void HitBySunStorm()
	{
		if (!isHidden) {
			Die();
		}
	}
	// a step overridja
	public abstract void Step();

	
	//Elrejtozik
	public void Hide()
	{
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
		isHidden=!isHidden;
	}
}
