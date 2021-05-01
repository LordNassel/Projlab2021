package game_logic;

import java.util.*;

import view.SettlerView;


/**
 * A játékban előforduló, mozgóképes objektumokat reprezentálja egy mezőn. Tárolja az éppen aktuális helyét a pályán.
 */
public abstract class Movable implements Steppable {
	/**
	 * Egy segéd tagváltozó, ami megmondja,hogy az adott movableelbújt-e egy aszteroidában.
	 * Ha az értéke true, akkorigen, ha false akkor nem
	 */
	protected boolean isHidden;
	/**
	 * Movable neve. Azonositast segit
	 */
	protected String movablesName;

	/**
	 * A movable aktuális pozíciója ahol éppen van
	 */
	protected Field currentField;

	/**
	 * Konstruktor, a megadott fieldre teszi a movablet
	 * @param onField
	 */
	public Movable(Field onField)
	{
		currentField = onField;
		isHidden = false;
	}	

	/**
	 * Konstruktor
	 * @param a
	 */
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

	/**
	 * Fúrja az aszteroidát körülvevő kérget aminek hatására eggyel csökkena kéreg (thickness).
	 * Ha nincs már kérge az aszteroidánaknem történik semm
	 */
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

	/**
	 * Meghal, eltűnik az aszteroida övből (kiesika játékból) a RemovePlayer()fgv. hívással
	 */
	public void Die()
	{
		currentField.RemovePlayer(this);
		Game.RemoveSteppable(this);
			System.out.println("A(z) " + movablesName + " meghalt, beke poraira");
		
	}

	/**
	 * Movable-ként eléri a robbanása minek hatására meghívódik a Die() függvény.
	 */
	public void HitByExplosion()
	{
		Die();
		
	}
	
	/**
	 * Movable-ként eléri a napviharaminek hatására rögtönmeghal ha nincs elbújva a Die() meghívásával.
	 */
	public void HitBySunStorm()
	{
		if (!isHidden) {
			Die();
		}
	}

	/**
	 * Interfész Step()-je
	 * Valamilyen műveletet végez
	 */
	public abstract void Step();

	
	/**
	 * Elbújik az aszteroidában ha az üregesezzel beállítja az isHide-otture-ra. Ha nem üres, akkor nem tud elbújni
	 */
	public void Hide()
	{
		((Asteroid)currentField).GetHidden(this);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public Field GetCurrentField()
	{
		return this.currentField;
	}
	
	/**
	 * Setter
	 * @param field
	 */
	public void SetCurrentField(Field field)
	{
		this.currentField=field;
	}

	/**
	 * Setter
	 */
	public void SetIsHidden()
	{
		isHidden=!isHidden;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String Getname() {
		return this.movablesName;
	}
}
