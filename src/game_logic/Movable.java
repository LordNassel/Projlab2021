package game_logic;

import view.View;

/**
 * A játékban előforduló, mozgóképes objektumokat reprezentálja egy mezőn. Tárolja az éppen aktuális helyét a pályán.
 */
public abstract class Movable implements Steppable {

	/**
	 * Movable nézete.
	 */
	protected View movableView;

	/**
	 * Egy segéd tagváltozó, ami megmondja, hogy az adott movable elbújt-e egy aszteroidában.
	 * Ha az értéke true, akkor igen, ha false, akkor nem.
	 */
	protected boolean isHidden = false;

	/**
	 * Movable neve. Azonositast segiti.
	 */
	protected String movablesName;

	/**
	 * A movable aktuális mezője.
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
	 * Ha nincs elbújva a movable, akkor átmegy a kapott fieldre.
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
	 * Fúrja az aszteroidát körülvevő kérget, aminek hatására eggyel csökken a kéreg (thickness).
	 * Ha nincs már kérge az aszteroidának, akkor nem történik semmi.
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
	 * Meghal a movable, eltűnik az aszteroida övből (kiesik a játékból) a RemovePlayer() függvény hívással.
	 */
	public void Die()
	{
		currentField.RemovePlayer(this);
		Game.RemoveSteppable(this);
		System.out.println("A(z) " + movablesName + " meghalt, beke poraira");
	}

	/**
	 * Movable-ként eléri a robbanás, minek hatására meghívódik a Die() függvény.
	 */
	public void HitByExplosion()
	{
		Die();
	}
	
	/**
	 * Movable-ként eléri a napvihar, aminek hatására rögtön meghal, ha nincs elbújva a Die() meghívásával.
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
	 * Elbújik az aszteroidában, ha az üreges.
	 * Ezzel beállítja az isHide-ot true-ra. Ha nem üres, akkor nem tud elbújni.
	 */
	public void Hide()
	{
		((Asteroid)currentField).GetHidden(this);
	}
	
	/**
	 * Visszadja a movable aktuális mezőjét.
	 */
	public Field GetCurrentField()
	{
		return this.currentField;
	}
	
	/**
	 * Beállítja a movable aktuális mezőjét.
	 * @param field
	 */
	public void SetCurrentField(Field field)
	{
		this.currentField=field;
	}

	/**
	 * Ha a movable el volt bújva, akkor előmászik.
	 * Ha nem volt elbújva, akkor elbújik.
	 */
	public void SetIsHidden()
	{
		isHidden=!isHidden;
	}

	/**
	 * Visszadja, hogy a movable el van-e bújva.
	 * @return boolean
	 */
	public boolean GetIsHidden()
	{
		return isHidden;
	}
	
	/**
	 * Visszadja movable nevét.
	 * @return String
	 */
	public String Getname() {
		return this.movablesName;
	}

	/**
	 * Absztrakt függvény nézet létrehozásához.
	 */
	public abstract void createMovableView();

	/**
	 * Visszaadja a movable nézetét.
	 */
	public View getView()
	{
		return movableView;
	}

}
