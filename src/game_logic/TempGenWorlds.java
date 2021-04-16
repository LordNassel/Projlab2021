package game_logic;

import java.util.Vector;

public class TempGenWorlds {
    //Ez most itt azert ilyen bajos, mert a game feladatat vegzem el. A game majd file-bol olvassa be ezeket.
// Azonban a process jellege nem valtozik elore megtervezett vilagokkal dolgozunk majd
    public Vector<Field> Generateworlds(int which){
        Iron i = new Iron();
        Coal c = new Coal();
        Ice ice = new Ice();
        Uranium u = new Uranium();
       if(which ==1){

                System.out.println("Az inner worlds konstruktora hivodott meg. " +
                        "Ez azon resze a vilagnak, ahol minden rendben megy, es a nap messze van. Nagyon kellemes kirandulohelyszin, ellenben itt nyerni nem lehet. " +
                        "Jo kikapcsolodast");
                Vector<Field> temp = new Vector<Field>();
                //Minden uj aszteroida
                Asteroid Auchen = new Asteroid("Auchenshuggle", i, false, 0);
                Asteroid FormFoss = new Asteroid("FormFoss", c);
                Asteroid Timeston = new Asteroid("Timeston", u);
                Asteroid Hewe = new Asteroid("Hewe", u);
                Asteroid Beckistale = new Asteroid("Beckistale");
                Asteroid Boroughton = new Asteroid("Boroughton", ice);
                Asteroid MyreFall = new Asteroid("MyreFall");
                Asteroid LongDale = new Asteroid("LongDale", i);
                Asteroid Sotrun = new Asteroid("Sotrun", u);
                Asteroid Benqua = new Asteroid("Benqua");
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

                Settler S = new Settler(Auchen);
                Settler S2 = new Settler(Auchen);
                Robot r = new Robot(Auchen);
                Alien a = new Alien(Auchen);

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
                
                Game game = new Game();
                /*game.setSteppable(r);
                game.setSteppable(a);
                game.setSteppable(S);
                game.setSteppable(S2);*/
                game.AddMovable(r, Auchen);
                game.AddMovable(a, Auchen);
                game.AddMovable(S, Auchen);
                game.AddMovable(S2, Auchen);

                game.StartGame();

                return temp;
        }
        if(which ==2){
            System.out.println("Az inner worlds 200 evvel ezelotti konstruktora hivodott meg. " +
                    "Ez azon resze a vilagnak, ahol minden rendben megy, es a nap messze van. Ez azonban a felujitasok korszaka, eppen minden aszteroida ki van furva ");
            //igen igen, ez erosen sorminta, de csak tesztelesre van igy
            Vector<Field> temp = new Vector<Field>();
            //Minden uj aszteroida
            Asteroid Auchen = new Asteroid("Auchenshuggle", i, false, 0);
            Asteroid FormFoss = new Asteroid("FormFoss", c,false, 0);
            Asteroid Timeston = new Asteroid("Timeston", u,false, 0);
            Asteroid Hewe = new Asteroid("Hewe", u,false, 0);
            Asteroid Beckistale = new Asteroid("Beckistale", i ,false, 0);
            Asteroid Boroughton = new Asteroid("Boroughton", i);
            Asteroid MyreFall = new Asteroid("MyreFall", i);
            Asteroid LongDale = new Asteroid("LongDale", i);
            Asteroid Sotrun = new Asteroid("Sotrun", u);
            Asteroid Benqua = new Asteroid("Benqua");
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

            // Settler S = new Settler(Auchen);

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

            return temp;
        }

       return null;
    }
}
