package game_logic;

import java.util.*;

import view.GameBoard;
import view.GameFrame;
import view.Specialframe;


/**
 * Ez az osztály felelsõs a játékmenet elvárt futásáért.Elkezdi/befejezi az adott játszmát,
 * betölt egy pályát illetve kezeli a körökre osztást is
 */
public class Game {

	public static boolean button=false;
	private static Game instance = null;
	//public static MenuView view;
	volatile private Settler activeSettler;

	/**
	 * Statikus boolean ami igaz ha a játék éppen fut. Hamis ha befejezõdött
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
    
  /*  public Game(MenuView v, Map m) {
    	map = m;
    	running = true;
    	view = v;
    }*/
    
    /**
     * Paraméterként átadott pályát tölt be
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
    	//System.out.println("Wingame called");
    	running=false;
		GameBoard.Wingame();
    }

    /**
     * A játékosok vesztettek, a játéknakvége,
     * és a running-ot false-re állítja
     */
    public void Losegame() {
    	running = false;
    	//System.out.println("Losegame called");
		GameBoard.Losegame();
    }

   /**
    * Elindítja a körökre osztott játékmenetet,
    * lépteti a léptethetõ objektumokat. Ha minden telepes halott
    * akkor befejezi a játékot
    */
    public void StartGame() {
    	//System.out.println("A jatek elkezdodott, jo szorakozast!");
    	int number = 1;
    	while(running)
    	{
    		System.out.println("\nA " + number + ". kor elkezdodott");
    		for(int i= 0; i<steppableList.size(); i++)
    		{
    			if(getIsTherAnySettler()==false)
    			{
    				Losegame();
    				return;
    			}
    			if(steppableList.get(i) instanceof Settler)
    			{
    				setSettler((Settler) steppableList.get(i));
    				//activeSettler = (Settler)steppableList.get(i);
    				GameBoard.selectAction();
    				/*while(button==false)
    				{
    					
    				}*/
    				//szólni kell a view-nak, hogy most settler van
    				// -> pl. itt most várakoztatunk GUI eseményre (dialogbox?)
    			}
    			else //ha nem settler akkkor mehet automatikusan
    				steppableList.get(i).Step();
    			button=false;
    		}
    		number++;
    	}
    }
    
    public void ExplodeAction()
    {
    	
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
    public boolean getIsTherAnySettler()
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
    
    public static Map getMap()
    {
    	return map;
    }
    
    public Settler getActiveSettler()
    {
    	return activeSettler;
    }
    
    public void setSettler(Settler s)
    {
    	this.activeSettler = s;
    }
    
    public void DrillAction()
    {
    	//getActiveSettler().setValasz(2);
    	System.out.println(getActiveSettler().Getname());
    	getActiveSettler().Drill();
    }
    
    public void CraftTeleportAction(String name1, String name2)
    {
    	getActiveSettler().CraftTeleports(name1, name2);
    }
    
    public void CraftRobotAction()
    {
    	getActiveSettler().CraftRobot();
    }
    
    public void HideAction()
    {
    	getActiveSettler().Hide();
    }
    
    public void MineAction()
    {
    	getActiveSettler().Mine();
    }
    
    public void MoveAction(Field f)
    {
    	getActiveSettler().Move(f);
    }
    
    public void PutAction(Material m)
    {
    	getActiveSettler().PutMaterial(m);
    }
    
    public void ActivateAction(Teleport t)
    {
    	getActiveSettler().ActivateTeleport(t);
    }
    
    public void StoreAction(Material m)
    {
    	getActiveSettler().Store(m);
    }
    public void StoreInBaseAction(Material m)
    {
    	getActiveSettler().storeOnBaseMaterial(m);
    }
    
    public void BuildAction()
    {
    	getActiveSettler().Build();
    }


	public int getradiation(){return this.map.getSugarzas();}


}