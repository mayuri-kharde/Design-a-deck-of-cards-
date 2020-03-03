import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game
{
    List<Player> players;
    Deck deck;
    Boolean started;

    Game(List<Player> players, Deck deck)
    {
        this.players = players;
        this.deck = deck;
        this.started = false;
    }

    public void addOrRemovePlayers()
    {
        int opt, size;
        Scanner in = new Scanner(System.in);

        if(this.started)
        {
            System.out.println("Game in progress. Cannot add/remove players. Press 5 to view cards for each player or 6 for the winner or 7 to end game.");
        }
        else
        {
            if(this.players.size() == 0)
            {
                System.out.println("No players yet. Add players. Press 1 to continue.");
                opt = in.nextInt();

                if(opt == 1)
                {
                    System.out.println("How many players do you want to add?");
                    opt = in.nextInt();

                    for(int i = 1; i <= opt; i++)
                    {
                        this.players.add(new Player(i));
                    }
                    System.out.println(opt+" players added to the game.");
                }
            }
            else
            {
                System.out.println("Total players in the game: "+ this.players.size());
                System.out.println("Print 1 to add or 2 to remove players");
                opt = in.nextInt();
                if(opt == 1)
                {
                    System.out.println("How many players do you want to add?");
                    opt = in.nextInt();

                    for(int i = 1; i <= opt; i++)
                    {
                        size = this.players.size();
                        this.players.add(new Player(size + opt));
                        size++;
                    }
                    System.out.println(opt+" players are added.");
                }
                else
                {
                    System.out.println("How many players do you want to remove?");
                    opt = in.nextInt();

                    if(opt >= this.players.size())
                    {
                        System.out.println("Cannot remove "+opt+" players. Only "+this.players.size()+" are present.");
                    }
                    else
                    {
                        for(int i = 1; i <= opt; i++)
                        {
                            this.players.remove(this.players.size() - 1);
                        }

                        System.out.println(opt+" players removed from the game.");
                    }
                }
            }
        }
    }

    public void shuffleCards()
    {
        if(this.started)
        {
            System.out.println("Game in progress. Cannot shuffle cards. Press 5 to view cards for each player or 6 for the winner or 7 to end game.");
        }
        else
        {
            Collections.shuffle(this.deck.cardDeck);
            System.out.println("Deck shuffled.");
        }
    }

    public void printCards()
    {
        for(int i = 0; i < this.deck.cardDeck.size(); i++)
        {
            System.out.println(this.deck.cardDeck.get(i).faceValue + " of " + this.deck.cardDeck.get(i).suite);
        }
    }

    public void startGame()
    {
        if(this.players.size() == 0)
        {
            System.out.println("No players yet. Add players to start game.");
            System.out.println("How many players do you want to add?");
            Scanner in = new Scanner(System.in);
            int opt = in.nextInt();

            if(opt >= 1)
            {
                for(int i = 1; i <= opt; i++)
                {
                    this.players.add(new Player(i));
                }
                System.out.println(opt+" players added to the game.");
                this.started = true;

                for(int i = 0; i < this.players.size(); i++)
                {
                    Player p = this.players.get(i);
                    Card c = this.deck.cardDeck.get(this.deck.cardDeck.size() - 1);

                    p.card = c;
                    deck.cardDeck.remove(this.deck.cardDeck.size() - 1);
                }

                System.out.println("Game started.");
            }
            else
            {
                System.out.println("No players added.");
            }

        }
        else
        {
            System.out.println("Game started.");
            this.started = true;
            for(int i = 0; i < this.players.size(); i++)
            {
                Player p = this.players.get(i);
                Card c = this.deck.cardDeck.get(this.deck.cardDeck.size() - 1);

                p.card = c;
                deck.cardDeck.remove(this.deck.cardDeck.size() - 1);
            }
        }
    }

    public void printPlayerCards()
    {
        for(int i = 0; i < this.players.size(); i++)
        {
            Player p = this.players.get(i);
            Card c = p.card;
            int id = p.id;

            System.out.println("Player "+id+" has "+c.faceValue+" of "+c.suite);
        }
    }

    public void declareWinner()
    {
        Player winner = this.players.get(0);
        Suit winnerSuit = winner.card.suite;
        int winnerValue = winner.card.faceValue;

        for(int i = 1; i < this.players.size(); i++)
        {
            Player p = this.players.get(i);
            Suit pSuit = p.card.suite;
            int pValue = p.card.faceValue;

            if(pValue > winnerValue)
            {
                winner = p;
            }
            else if(pValue == winnerValue)
            {
                if(winnerSuit.ordinal() > pSuit.ordinal())
                {
                    winner = p;
                }
            }
        }

        System.out.println("Winner is "+winner.id);
    }

    public void endGame()
    {
        System.out.println("Game ended.");
        this.started = false;
        for(int i = 0; i < this.players.size(); i++)
        {
            Player p = this.players.get(i);
            Card c = p.card;

            deck.cardDeck.add(c);
        }

        Collections.shuffle(deck.cardDeck);
    }
}
