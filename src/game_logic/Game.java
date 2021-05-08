package game_logic;

import java.util.*;

import view.GameBoard;
import view.GameFrame;
import view.Specialframe;


/**
 * Ez az oszt�ly felels�s a j�t�kmenet elv�rt fut�s��rt.Elkezdi/befejezi az adott j�tszm�t,
 * bet�lt egy p�ly�t illetve kezeli a k�r�kre oszt�st is
 */
public class Game {

	public static boolean button=false;
	private static Game instance = null;
	//public static MenuView view;
	volatile private Settler activeSettler;

	/**
	 * Statikus boolean ami igaz ha a j�t�k �ppen fut. Hamis ha befejez�d�tt
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
    
  /*  public Game(MenuView v, Map m) {
    	map = m;
    	running = true;
    	view = v;
    }*/
    
    /**
     * Param�terk�nt �tadott p�ly�t t�lt be
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
    	//System.out.println("Wingame called");
    	running=false;
		GameBoard.Wingame();
    }

    /**
     * A j�t�kosok vesztettek, a j�t�knakv�ge,
     * �s a running-ot false-re �ll�tja
     */
    public void Losegame() {
    	running = false;
    	//System.out.println("Losegame called");
		GameBoard.Losegame();
    }

   /**
    * Elind�tja a k�r�kre osztott j�t�kmenetet,
    * l�pteti a l�ptethet� objektumokat. Ha minden telepes halott
    * akkor befejezi a j�t�kot
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
    				//sz�lni kell a view-nak, hogy most settler van
    				// -> pl. itt most v�rakoztatunk GUI esem�nyre (dialogbox?)
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