package game_logic;

import java.util.Scanner;
import java.util.Vector;

public abstract class Movable implements Steppable {
	
	private boolean isTurn;
	
	private boolean isHidden;
	
	private int radiation;
	
	protected Field currentField;
	
	public Movable(Field onField)
	{
		currentField = onField;
		radiation = 1;
		isTurn = false;
		isHidden = false;
	}
	
	//
	public void FindDirections() {
		Vector<Field> currentlist = new Vector<Field>();
		currentlist = this.currentField.FindNeighbor();
		System.out.println("Válaszd ki melyik szomszédos bolygóra akarsz utazni:");
		for(int i = 0; i<currentlist.size(); i++) {
			System.out.println(i + ". " + currentlist.get(i).Getname());
		}
	
		Scanner myinput =new Scanner(System.in);
		int n=0;
		n= myinput.nextInt();
		this.Move(currentlist.get(n));
	}
	
	public void Move(Field a)
	{
		System.out.println("Move()");
		this.currentField.RemovePlayer(this);
		a.AcceptPlayer(this);
		
	}	
	
	public void Drill()
	{
		System.out.println("Drill()");
		if(this.currentField.GetDrilled()==true)
			System.out.println("Sikeres furas az aszteroida kereg kisebb lett\n");
		else
			System.out.println("Sikertelen furas, a kereg mar 0\n");
		
	}
	
	public void Die()
	{
		System.out.println("Die()");
		currentField.RemovePlayer(this);
		
	}
	
	public void HitByExplosion()
	{
		System.out.println("HitByExplosion");
		Die();
		
	}
	
	public void HitBySunStorm()
	{
		System.out.println("HitBySunStorm");
		if (!isHidden) {
			Die();
		}
	}
	
	public void Step()
	{
		
	}
	
	 
	public void Hide()
	{
		System.out.println("Hide");
		((Asteroid)currentField).GetHidden(this);
	}
	
	public void SetTurn()
	{
		
	}
	
	public void SetRadiation()
	{
		System.out.println("SetRadiation");
		
		
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
