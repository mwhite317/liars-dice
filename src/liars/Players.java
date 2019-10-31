package liars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class Players implements Iterable<Player> {
    private List<Player> players;

    public Players(Player[] players) {
        this.players = Arrays.asList(players);
    }

    public void removeDicelessPlayers() {
        List<Player> playersWithDice = new ArrayList<>();
        for (Player player : players) {
            if (player.getDice().length != 0) {
                playersWithDice.add(player);
            }
        }
        players = playersWithDice;
    }

    public int numberOfPlayers() {
        return players.size();
    }

    public Player getPlayerForTurn(int turnNumber) {
        return players.get(turnNumber % numberOfPlayers());
    }

    public int getTurnNumberForPlayer(Player thePlayer) {
        return players.indexOf(thePlayer);
    }

    public Player getPreviousPlayer(Player thisPlayer) {
        /*getPlayers().get((players.getPlayers().size() - 1) - (turnNumber % 2));*/
        if (players.indexOf(thisPlayer) == 0) {
            return players.get(players.size() - 1);
        }
        return players.get(players.indexOf(thisPlayer) - 1);
    }


    public Iterator<Player> iterator() {
        return players.iterator();
    }
}

