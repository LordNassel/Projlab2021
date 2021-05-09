package game_logic;

import view.GameBoard;
import java.util.ArrayList;

/**
 * Ez az osztály felelõs a játékmenet elvárt futásáért.
 * Elkezdi/befejezi az adott játszmát, betölt egy pályát, illetve kezeli a körökre osztást is.
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
	 * Az aktív telepest.
	 */
	volatile private Settler activeSettler;

	/**
	 * Statikus boolean, ami igaz ha a játék éppen fut. Hamis, ha befejezõdött.
	 */
	private static boolean running;

	/**
	 * Az aktuális pálya, amin játszunk
	 */
	private static Map map;

	/**
	 * Steppable, vagyis a mozgásra képes objektumok listája
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
     * Constructor, paraméterként átadott pályát tölt be
     */
    public Game(Map generatedmap) {
    	map = generatedmap;
    	running = true;
    }

    /**
     * Ha a játékosok teljesítették az elõírtakat,akkor megnyerték a játékot,
     * és a running-ot false-ra állítja
     */
    public static void Wingame() {
    	running=false;
		GameBoard.Wingame();
    }

    /**
     * A játékosok vesztettek, a játéknak vége,
     * és a running-ot false-re állítja
     */
    public void Losegame() {
    	running = false;   	
		GameBoard.Losegame();
    }

   /**
    * Elindítja a körökre osztott játékmenetet, lépteti a léptethetõ objektumokat.
	* Ha minden telepes halott, akkor befejezi a játékot
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
     * Egy új mozgó objektumotadunk hozzá a játéktérhez
     */
    public void AddMovable(Movable Playable, Asteroid a) {
    	Playable.SetCurrentField(a);
    	a.AcceptPlayer(Playable);
    	AddSteppable(Playable);
    }
    
    /**
     * Léptethetõ objektumok listájába újat ad hozzá
     */
    public static void AddSteppable(Steppable s)
    {
    	steppableList.add(s);
    }
    
    /**
     * Léptethetõ objektumok listájából töröl egy paraméterül kapottat
     */
    public static void RemoveSteppable(Steppable s)
    {
    	steppableList.remove(s);
    }
    
    /**
     * Segédfüggvény annak az ellenõrzésére, hogy van-e játékban még Telepes
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
	 * Visszaadja az aktuális pályát.
	 */
    public static Map getMap()
    {
    	return map;
    }

	/**
	 * Visszaadja az aktív telepest.
	 */
    public Settler getActiveSettler()
    {
    	return activeSettler;
    }

	/**
	 * Beállítja az aktív telepest.
	 */
    public void setSettler(Settler s)
    {
    	this.activeSettler = s;
    }

	/**
	 * Meghívja az aktív telepes fúrás függvényét.
	 */
    public void DrillAction()
    {
    	System.out.println(getActiveSettler().Getname());
    	getActiveSettler().Drill();
    }

	/**
	 * Meghívja az aktív telepes teleportpárt craftoló függvényét.
	 */
    public void CraftTeleportAction(String name1, String name2)
    {
    	getActiveSettler().CraftTeleports(name1, name2);
    }

	/**
	 * Meghívja az aktív telepes robotot craftoló függvényét.
	 */
    public void CraftRobotAction()
    {
    	getActiveSettler().CraftRobot();
    }

	/**
	 * Meghívja az aktív telepes elbújás függvényét.
	 */
    public void HideAction()
    {
    	getActiveSettler().Hide();
    }

	/**
	 * Meghívja az aktív telepes bányászás függvényét.
	 */
    public void MineAction()
    {
    	getActiveSettler().Mine();
    }

	/**
	 * Meghívja az aktív telepes mozgás függvényét.
	 */
    public void MoveAction(Field f)
    {
    	getActiveSettler().Move(f);
    }

	/**
	 * Meghívja az aktív telepes függvényét, mely elhelyez egy egységnyi nyersanyagot az aszteroidába.
	 */
    public void PutAction(Material m)
    {
    	getActiveSettler().PutMaterial(m);
    }

	/**
	 * Meghívja az aktív telepes teleportot aktiváló függvényét.
	 */
    public void ActivateAction(Teleport t)
    {
    	getActiveSettler().ActivateTeleport(t);
    }

	/**
	 * Meghívja az aktív telepes függvényét, mely eltárolja a kibányászott nyersanyagot a bázison.
	 */
    public void StoreInBaseAction(Material m)
    {
    	getActiveSettler().storeOnBaseMaterial(m);
    }

    /**
	 * Meghívja az aktív telepes bázist építõ függvényét.
	 */
    public void BuildAction()
    {
    	getActiveSettler().Build();
    }

	/**
	 * Meghívja az aktív telepes függvényét, mely eltárolja a kibányászott nyersanyagot az inventory-ban.
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
	 *  Visszaadja az aszteroidaövben uralkodo sugarzas nagysagat.
	 */
	public int getradiation(){return this.map.getSugarzas();}
}