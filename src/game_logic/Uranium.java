package game_logic;

// Uranium, simple material
public class Uranium extends Material {
	
	private int counter;
	
	public Uranium() {
		super();
		counter = 0;
	}
	//IncreaseUraniumCnt-hoz kell
	public void IncreaseCounter() {
		counter++;
	}
	
	@Override
	public void GetExposed(boolean Sunside, Asteroid who) {
		if(Sunside) {
			counter++;
		}
		
		if(counter == 3) {
			System.out.println("Radioactive uranium exploded");
			who.Explode();
		}
	}

}
