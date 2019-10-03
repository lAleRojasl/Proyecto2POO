package Netcode;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

/**
 * A server for a multi-player BattleVoid (Battleship) game.
 */
public class Server {

    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(58901)) {
            System.out.println("BattleVoid Server is Running...");
            var pool = Executors.newFixedThreadPool(200);
            while (true) {
                Game game = new Game();
                pool.execute(game.new Player(listener.accept(), "Player 1"));
                pool.execute(game.new Player(listener.accept(), "Player 2"));
            }
        }
    }
}

