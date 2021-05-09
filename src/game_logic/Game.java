package game_logic;

import view.GameBoard;
import java.util.ArrayList;

/**
 * Ez az oszt�ly felel�s a j�t�kmenet elv�rt fut�s��rt.
 * Elkezdi/befejezi az adott j�tszm�t, bet�lt egy p�ly�t, illetve kezeli a k�r�kre oszt�st is.
 */
public class Game {

	/**
	 *  .
	 */
	public static boolean button=false;

	/**
	 *  .
	 */
	private static Game instance = null;

	/**
	 * Az akt�v telepest.
	 */
	volatile private Settler activeSettler;

	/**
	 * Statikus boolean, ami igaz ha a j�t�k �ppen fut. Hamis, ha befejez�d�tt.
	 */
	private static boolean running;

	/**
	 * Az aktu�lis p�lya, amin j�tszunk
	 */
	private static Map map;

	/**
	 * Steppable, vagyis a mozg�sra k�pes objektumok list�ja
	 */
	private static ArrayList<Steppable> steppableList = new ArrayList<Steppable>();

    /**
     * Default constructor
     */
    public Game() {
    	map = new Map();
    	running = true;
    }

    /**
     * Constructor, param�terk�nt �tadott p�ly�t t�lt be
     */
    public Game(Map generatedmap) {
    	map = generatedmap;
    	running = true;
    }

    /**
     * Ha a j�t�kosok teljes�tett�k az el��rtakat,akkor megnyert�k a j�t�kot,
     * �s a running-ot false-ra �ll�tja
     */
    public static void Wingame() {
    	running=false;
		GameBoard.Wingame();
    }

    /**
     * A j�t�kosok vesztettek, a j�t�knak v�ge,
     * �s a running-ot false-re �ll�tja
     */
    public void Losegame() {
    	running = false;   	
		GameBoard.Losegame();
    }

   /**
    * Elind�tja a k�r�kre osztott j�t�kmenetet, l�pteti a l�ptethet� objektumokat.
	* Ha minden telepes halott, akkor befejezi a j�t�kot
    */
    public void StartGame() {
    	int number = 1;
    	while(running)
    	{
    		System.out.println("\nA " + number + ". kor elkezdodott");
    		for(int i= 0; i<steppableList.size(); i++)
    		{
    			if(getIsThereAnySettler()==false)
    			{
    				Losegame();
    				return;
    			}
    			if(steppableList.get(i) instanceof Settler)
    			{
    				setSettler((Settler) steppableList.get(i));
    				GameBoard.selectAction();
    			}
    			else 
    				steppableList.get(i).Step();
    			button=false;
    		}
    		number++;
    	}
    }

    /**
     * Egy �j mozg� objektumotadunk hozz� a j�t�kt�rhez
     */
    public void AddMovable(Movable Playable, Asteroid a) {
    	Playable.SetCurrentField(a);
    	a.AcceptPlayer(Playable);
    	AddSteppable(Playable);
    }
    
    /**
     * L�ptethet� objektumok list�j�ba �jat ad hozz�
     */
    public static void AddSteppable(Steppable s)
    {
    	steppableList.add(s);
    }
    
    /**
     * L�ptethet� objektumok list�j�b�l t�r�l egy param�ter�l kapottat
     */
    public static void RemoveSteppable(Steppable s)
    {
    	steppableList.remove(s);
    }
    
    /**
     * Seg�df�ggv�ny annak az ellen�rz�s�re, hogy van-e j�t�kban m�g Telepes
     */
    public boolean getIsThereAnySettler()
	{
    	Asteroid a = new Asteroid("bela");
    	Settler s = new Settler("Palyer", a);
		int number = 0;
		for(int i = 0; i<steppableList.size(); i++)
		{
			if(steppableList.get(i).getClass().equals( s.getClass()))
				number++;
		}
		if(number==0)
			return false;
		return true;
	}
    
    public static Game getInstance()
    {
    	if(instance == null)
    		instance = new Game();
    	return instance;
    }

	/**
	 * Visszaadja az aktu�lis p�ly�t.
	 */
    public static Map getMap()
    {
    	return map;
    }

	/**
	 * Visszaadja az akt�v telepest.
	 */
    public Settler getActiveSettler()
    {
    	return activeSettler;
    }

	/**
	 * Be�ll�tja az akt�v telepest.
	 */
    public void setSettler(Settler s)
    {
    	this.activeSettler = s;
    }

	/**
	 * Megh�vja az akt�v telepes f�r�s f�ggv�ny�t.
	 */
    public void DrillAction()
    {
    	System.out.println(getActiveSettler().Getname());
    	getActiveSettler().Drill();
    }

	/**
	 * Megh�vja az akt�v telepes teleportp�rt craftol� f�ggv�ny�t.
	 */
    public void CraftTeleportAction(String name1, String name2)
    {
    	getActiveSettler().CraftTeleports(name1, name2);
    }

	/**
	 * Megh�vja az akt�v telepes robotot craftol� f�ggv�ny�t.
	 */
    public void CraftRobotAction()
    {
    	getActiveSettler().CraftRobot();
    }

	/**
	 * Megh�vja az akt�v telepes elb�j�s f�ggv�ny�t.
	 */
    public void HideAction()
    {
    	getActiveSettler().Hide();
    }

	/**
	 * Megh�vja az akt�v telepes b�ny�sz�s f�ggv�ny�t.
	 */
    public void MineAction()
    {
    	getActiveSettler().Mine();
    }

	/**
	 * Megh�vja az akt�v telepes mozg�s f�ggv�ny�t.
	 */
    public void MoveAction(Field f)
    {
    	getActiveSettler().Move(f);
    }

	/**
	 * Megh�vja az akt�v telepes f�ggv�ny�t, mely elhelyez egy egys�gnyi nyersanyagot az aszteroid�ba.
	 */
    public void PutAction(Material m)
    {
    	getActiveSettler().PutMaterial(m);
    }

	/**
	 * Megh�vja az akt�v telepes teleportot aktiv�l� f�ggv�ny�t.
	 */
    public void ActivateAction(Teleport t)
    {
    	getActiveSettler().ActivateTeleport(t);
    }

	/**
	 * Megh�vja az akt�v telepes f�ggv�ny�t, mely elt�rolja a kib�ny�szott nyersanyagot a b�zison.
	 */
    public void StoreInBaseAction(Material m)
    {
    	getActiveSettler().storeOnBaseMaterial(m);
    }

    /**
	 * Megh�vja az akt�v telepes b�zist �p�t� f�ggv�ny�t.
	 */
    public void BuildAction()
    {
    	getActiveSettler().Build();
    }

	/**
	 * Megh�vja az akt�v telepes f�ggv�ny�t, mely elt�rolja a kib�ny�szott nyersanyagot az inventory-ban.
	 */
	public void StoreAction(Material m)
	{
		getActiveSettler().Store(m);
	}

	/**
	 *
	 */
	public void ExplodeAction()	{	}

	/**
	 *  Visszaadja az aszteroida�vben uralkodo sugarzas nagysagat.
	 */
	public int getradiation(){return this.map.getSugarzas();}
}