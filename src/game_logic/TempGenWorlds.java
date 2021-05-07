package game_logic;

import java.io.IOException;
import java.util.*;

import view.*;

public class TempGenWorlds {

    //Ez most itt azert ilyen bajos, mert a game feladatat vegzem el. A game majd file-bol olvassa be ezeket.
// Azonban a process jellege nem valtozik elore megtervezett vilagokkal dolgozunk majd
    public Game Generateworlds(int which) throws IOException{
        Iron i = new Iron();
        Coal c = new Coal();
        Ice ice = new Ice();
        Uranium u = new Uranium();
       if(which ==1){

                System.out.println("Az inner worlds konstruktora hivodott meg. " +
                        "Ez azon resze a vilagnak, ahol minden rendben megy, es a nap messze van. Nagyon kellemes kirandulohelyszin, ellenben itt nyerni nem lehet. " +
                        "Jo kikapcsolodast");
                Vector<Asteroid> temp = new Vector<Asteroid>();
                //Minden uj aszteroida
                Asteroid Auchen = new Asteroid("Auchenshuggle", "Uranium", true, 0);
                //tring name, Material M, boolean isSunside
                Asteroid FormFoss = new Asteroid("FormFoss", "Ice");
                Asteroid Timeston = new Asteroid("Timeston", "Uranium");
                Asteroid Hewe = new Asteroid("Hewe", "Uranium");
                Asteroid Beckistale = new Asteroid("Beckistale", "Iron"); //rand
                Asteroid Boroughton = new Asteroid("Boroughton", "Ice");
                Asteroid MyreFall = new Asteroid("MyreFall", "Iron"); //rand
                Asteroid LongDale = new Asteroid("LongDale", "Iron");
                Asteroid Sotrun = new Asteroid("Sotrun", "Uranium");
                Asteroid Benqua = new Asteroid("Benqua", "Iron"); //rand
               
                temp.add(Auchen);
                temp.add(FormFoss);
                temp.add(Timeston);
                temp.add(Hewe);
                temp.add(Beckistale);
                temp.add(Boroughton);
                temp.add(MyreFall);
                temp.add(LongDale);
                temp.add(Sotrun);
                temp.add(Benqua);
                
                int x = 5;
                int y = 5;
                Random rand = new Random();

                //Szomszedok
                Auchen.SetNeighbor(FormFoss);
                Auchen.SetNeighbor(Timeston);
                Auchen.SetNeighbor(MyreFall);

                FormFoss.SetNeighbor(Auchen);

                Timeston.SetNeighbor(Auchen);
                Timeston.SetNeighbor(Hewe);
                Timeston.SetNeighbor(Beckistale);

                Beckistale.SetNeighbor(Timeston);
                Beckistale.SetNeighbor(Hewe);
                Beckistale.SetNeighbor(Boroughton);

                MyreFall.SetNeighbor(Beckistale);
                MyreFall.SetNeighbor(Benqua);

                LongDale.SetNeighbor(Benqua);
                LongDale.SetNeighbor(Sotrun);

                Hewe.SetNeighbor(Beckistale);
                Hewe.SetNeighbor(Timeston);

                Benqua.SetNeighbor(LongDale);

                Sotrun.SetNeighbor(LongDale);

                Boroughton.SetNeighbor(Beckistale);

                Settler S = new Settler("Player1", Auchen);
                Robot robi = new Robot("Robi", Auchen);
				
                Settler S2 = new Settler("Player2", Auchen);
                Vector<Material> list = new Vector<Material>();

                Goal_Asteroid goal = new Goal_Asteroid("Goal_Asteroid", "", list);
                Alien a = new Alien("Alien", Auchen);
                goal.SetNeighbor(Auchen);
                Auchen.SetNeighbor(goal);
                
                Vector<Field> fieldlist = new Vector<Field>();
                fieldlist.add(Auchen);
                fieldlist.add(FormFoss);
                fieldlist.add(Timeston);
                fieldlist.add(Hewe);
                fieldlist.add(Beckistale);
                fieldlist.add(Boroughton);
                fieldlist.add(MyreFall);
                fieldlist.add(LongDale);
                fieldlist.add(Sotrun);
                fieldlist.add(Benqua);
                fieldlist.add(goal);
                

                Teleport t = new Teleport("Bab is");
                Teleport t2= new Teleport("hus");
                t.setPair(t2);
                t2.setPair(t);
                S.addTelportToInventory(t);
                S.addTelportToInventory(t2);


                Map map = new Map(true, fieldlist);
                Game game = new Game(map);

              
                game.AddMovable(S, Auchen);
                game.AddMovable(S2, Auchen);
                game.AddMovable(robi, Auchen);
                game.AddMovable(a, Auchen);
                game.AddSteppable(map);
                S.Move(goal);
                return game;
        }
      else if (which == 2){
           System.out.println("A lonely worlds konstruktora hivodott meg. " +
                   "Ez azon resze a vilagnak, ahol relatív könny? meghalni");
           Vector<Field> fieldlist = new Vector<Field>();
           Asteroid A1 = new Asteroid("BigScaryAsteroid", "Uranium", true, 0);
           A1.getMats().clear();
           Uranium uran = new Uranium();
           A1.getMats().add(uran);
      //    Asteroid A2 = new Asteroid("UranDepo", "Uranium", true, 0);
       //   Asteroid A3 = new Asteroid("AhShitHereweBlowAgain", "Uranium", true, 0);

      //    A1.SetNeighbor(A2);
        //  A2.SetNeighbor(A1);

          //A1.SetNeighbor(A3);
          /*A3.SetNeighbor(A1);

          A3.SetNeighbor(A2);
          A2.SetNeighbor(A3);*/

          fieldlist.add(A1);
         /* fieldlist.add(A2);
          fieldlist.add(A3);*/

          Settler S = new Settler("Player1", A1);
          Robot r = new Robot(A1);


          Map map = new Map(true, fieldlist);


          Game returngame = new Game(map);

          returngame.AddMovable(S, A1);
          returngame.AddMovable(r, A1);

          returngame.AddSteppable(map);
          return returngame;
       }
       else if (which == 3){
           System.out.println("Ez azon pálya ahol veszteni nehéz");
           Asteroid A1 = new Asteroid("IronIronSoFarAway", "Iron", false, 0);
           Vector<Material> oneiron = new Vector<Material>();
           Iron Maiden = new Iron();
           oneiron.add(Maiden);
           Goal_Asteroid G1 = new Goal_Asteroid("WinnerWinner", "Iron", oneiron);

           Vector<Field> temp = new Vector<Field>();
           temp.add(A1);
           temp.add(G1);

           A1.SetNeighbor(G1);
           G1.SetNeighbor(A1);

           Settler S1 = new Settler("Bruszwillisz", A1);
           Map map = new Map(true, temp);
           Game returngame = new Game(map);
           returngame.AddMovable(S1, A1);
           returngame.AddSteppable(map);
           return returngame;
       }

       //tesztvilag#4
       else if (which == 4){
           System.out.println("Ez egy nyersanyaggal tele rész, itt eleg konnyu kraftolni");

           //Aszteroidak
           Asteroid A1 = new Asteroid("DummyAsteroid1", "Iron", false, 0);
           Asteroid A2 = new Asteroid("DummyAsteroid2", "Iron", false, 0);
           Asteroid A3 = new Asteroid("DummyAsteroid3", "Iron", false, 0);
           Asteroid A4 = new Asteroid("DummyAsteroid4", "Iron", false, 0);

           //Szomszédság
           A1.SetNeighbor(A3);
           A3.SetNeighbor(A1);

           A2.SetNeighbor(A4);
           A4.SetNeighbor(A2);

           A4.SetNeighbor(A3);
           A3.SetNeighbor(A4);

           Vector<Field> temp = new Vector<Field>();

           temp.add(A1);
           temp.add(A2);
           temp.add(A3);
           temp.add(A4);

           //Movable
           Settler S2 = new Settler("TeleportMan", A3);
           Settler S1 = new Settler("Robotman",A1);
           Robot R1 = new Robot(A2);

           //Material hozzáadás
           Iron iron = new Iron();
           Ice ice2 = new Ice();
           Uranium uranium = new Uranium();
           Coal coal = new Coal();



           S1.Store(coal);
           S1.Store(iron);
           S1.Store(uranium);

           S2.Store(iron);
           S2.Store(iron);
           S2.Store(ice2);
           S2.Store(ice2);
           S2.Store(uranium);


           Map map = new Map(true, temp);
           Game returngame = new Game(map);
           returngame.AddMovable(S1, A1);
           returngame.AddSteppable(map);
           return returngame;


       }

       return null;



    }
}
