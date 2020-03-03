import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameTest
{
    public static void main(String[] args)
    {
        List<Player> players = new ArrayList<>();
        Deck deck = new Deck();
        Game game = new Game(players, deck);

        while(true)
        {
            System.out.println("1] Add/Remove player(s) to the game.");
            System.out.println("2] Shuffle the deck.");
            System.out.println("3] Print all the cards present in the deck.");
            System.out.println("4] Start the game.");
            System.out.println("5] Print the card each player is holding.");
            System.out.println("6] Find the winner of the game.");
            System.out.println("7] Finish the game by returning all cards back to the deck.");

            System.out.println("Input an option: ");
            Scanner in = new Scanner(System.in);
            int option = in.nextInt();
            int opt;

            switch (option)
            {
                case 1:
                    game.addOrRemovePlayers();
                    break;
                case 2:
                    game.shuffleCards();
                    break;
                case 3:
                    game.printCards();
                    break;
                case 4:
                    if(game.started)
                    {
                        System.out.println("Game already started. Press 6 for the winner or 7 to end game.");
                    }
                    else
                    {
                        game.startGame();
                    }
                    break;
                case 5:
                    if(!game.started)
                    {
                        System.out.println("Game not started. Cannot print cards. Press 4 to start game.");
                    }
                    else
                    {
                        game.printPlayerCards();
                    }

                    break;
                case 6:
                    if(!game.started)
                    {
                        System.out.println("Game not started. Cannot declare winner. Press 4 to start game.");
                    }
                    else
                    {
                        game.declareWinner();
                    }
                    break;
                case 7:
                    if(!game.started)
                    {
                        System.out.println("Game not started. Press 4 to start game.");
                    }
                    else
                    {
                        game.endGame();
                        System.out.println("Press 0 to start another game or 1 to exit.");
                        opt = in.nextInt();

                        if(opt == 1)
                        {
                            System.exit(1);
                        }
                    }
                    break;
                default:
                    System.out.println("Please press a valid option.");
                    break;
            }

        }
    }
}
