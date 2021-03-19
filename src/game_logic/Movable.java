package game_logic;

public abstract class Movable {
	
	private boolean isTurn;
	
	private boolean isHidden;
	
	private int radiation;
	
	protected Asteroid currentField;
	
	public Movable(Asteroid onField)
	{
		currentField = onField;
		radiation = 1;
		isTurn = false;
		isHidden = false;
	}
	
	public void Move(Asteroid a)
	{
		System.out.println("Move()");
		Asteroid neighbor = a.FindNeighbor();
		neighbor.AcceptPlayer(this);
		a.RemovePlayer(this);		
	}	
	
	public void Drill()
	{
		System.out.println("Drill()");
		currentField.GetDrilled();
		
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
		Die();
	}
	
	public void Step()
	{
		
	}
	
	/*Oszt�lydiagram alapj�n kap egy aszteroida param�tert, de ez szerintem felesleges
	 *ha �gy kezelj�k, hogy az aktu�lis aszteroid�n amin van elb�jik
	 */
	public void Hide()
	{
		System.out.println("Hide");
		this.Hide();
	}
	
	public void SetTurn()
	{
		
	}
	
	public void SetRadiation()
	{
		System.out.println("SetRadiation");
		
		
	}
	
}
