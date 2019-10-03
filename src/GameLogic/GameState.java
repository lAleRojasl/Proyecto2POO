package GameLogic;

public class GameState {
    public int currentState = 1;

    //1st State - Player 1 places their ships
    //2nd State - Player 2 places their ships
    //3rd State - Players take turns shooting at each other
    //4th State - When a player's ships are all down, that player wins.
    public GameState(){
        while(currentState != 4 ){
            switch (currentState){
                case 1:
                    System.out.println("Starting game on state 1 - Player 1 placing ships...");
                    break;
                case 2:
                    System.out.println("Continuing game on state 2 - Player 2 placing ships...");
                    break;
                case 3:
                    System.out.println("Cotinuing game on state 3 - Players take turns shooting...");
                    break;
                default:
                    break;
            }
        }
        System.out.println("Game over - A players ships are down...");
    }

}
