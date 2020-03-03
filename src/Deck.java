import java.util.ArrayList;
import java.util.List;

public class Deck
{
    List<Card> cardDeck;

    public Deck()
    {
        this.cardDeck = new ArrayList<>();

        for(int value = 1; value <= 13; value++)
        {
            for(Suit suit: Suit.values())
            {
                Card c = new Card(value, suit);
                cardDeck.add(c);
            }
        }
    }
}
