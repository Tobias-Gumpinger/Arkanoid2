package at.ac.fhcampuswien;

/**
 * responsible for gameplay
 * contains all game objects, resolves interactions between them
 * */
public class GameEngine {
    /*called every game tick*/
    public void updateTick(){}
    /*called if paddle is supposed to receive input*/
    public void updateTick(int impulse){}

    /*reset the game, called to start a new game*/
    public void reset(){}
    /*start to a specific level*/
    public void reset(int level){
        //prelim
        reset();
    }
}
