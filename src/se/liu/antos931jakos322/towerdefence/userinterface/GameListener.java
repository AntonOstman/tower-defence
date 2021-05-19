package se.liu.antos931jakos322.towerdefence.userinterface;

/**
 *
 * MapListener is used by graphics components to listen to game
 * Classes implementing this interface can therefore get knowledge when a change has occured that needs to be acted upon.
 * In conjunction with adding with calling it with gameChanged
 *
 */

public interface GameListener
{

    public void gameChanged();

}
