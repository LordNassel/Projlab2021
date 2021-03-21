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
	
	//Na ennek szerintem kb így kell kinéznie. -> a neveben nem vagyok holtbiztos de az refaktoralhato
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
		//myinput.close();
	}
	
	public void Move(Field a)
	{
		//Bocsi itt minden teljesen fölösleges. most kaptad meg paraméterként hova kell menned. Nem kell kikeresned. Értem
		//mit akartál, de ez nem jó elképzelés. Neked a mozgásra két függvény kell. Először ki kell listáznod a lehetséges irányokat
		//A field.getneighbors fgv-vel, utána pedig amikor a játékos vagy az AI eldöntötte hova akar menni, akkor meghívod ezt
		//Paraméterként azzal az aszteroidával amit választott a játékos. 
		System.out.println("Move()");
		//Asteroid neighbor = a.FindNeighbor();
		///Vector<Field> neighbors = a.FindNeighbor();
		//neighbors.firstElement().AcceptPlayer(this);
		//neighbor.AcceptPlayer(this);
		this.currentField.RemovePlayer(this);
		a.AcceptPlayer(this);
		
	}	
	
	public void Drill()
	{
		System.out.println("Drill()");
		//Tok jogos ez igy nem jo. Modositom az osztalyaimat -> artur
		if(this.currentField.GetDrilled()==true)
			System.out.println("Sikeres furas az aszteroida kisebb lett");
		else
			System.out.println("Sikertelen furas, a kereg mar 0");
		
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
	
	/*Osztï¿½lydiagram alapjï¿½n kap egy aszteroida paramï¿½tert, de ez szerintem felesleges
	 *ha ï¿½gy kezeljï¿½k, hogy az aktuï¿½lis aszteroidï¿½n amin van elbï¿½jik
	 */
	public void Hide()
	{
		System.out.println("Hide");
		// Bocs ezt kikommenteztem, ez az aszteroida dolga, hogy eldontse, hogy a movable elbujhat. 
		//edit. lattam hogy irtal erre, sajnos ez nem egy technikalitás, csak akkor tudlak befogadni, ha ures az aszteroida
		//this.isHidden = true;
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
	//Ez a hidden setterje sajnos kell.
	public void SetIsHidden()
	{
		System.out.println("SetIsHidden()");
		this.isHidden=!this.isHidden;
	}
}
