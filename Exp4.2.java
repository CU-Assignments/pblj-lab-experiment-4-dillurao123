import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

class Card {
    String value; 
    String suit;   

    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return value.equals(card.value) && suit.equals(card.suit);
    }

    @Override
    public int hashCode() {
        return value.hashCode() + suit.hashCode();
    }
}

public class CardCollectionSystem {
    private HashSet<Card> cardSet;
    private HashMap<String, List<Card>> suitMap;

    public CardCollectionSystem() {
        cardSet = new HashSet<>();
        suitMap = new HashMap<>();
    }

    // Add a card to the collection
    public void addCard(String value, String suit) {
        Card card = new Card(value, suit);
        if (!cardSet.contains(card)) {
            cardSet.add(card);
            suitMap.computeIfAbsent(suit, k -> new ArrayList<>()).add(card);
            System.out.println("Card added: " + card);
        } else {
            System.out.println("Error: Card \"" + card + "\" already exists.");
        }
    }

    
    public void findCardsBySuit(String suit) {
        List<Card> cards = suitMap.get(suit);
        if (cards == null || cards.isEmpty()) {
            System.out.println("No cards found for " + suit + ".");
        } else {
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }

    
    public void displayAllCards() {
        if (cardSet.isEmpty()) {
            System.out.println("No cards found.");
        } else {
            for (Card card : cardSet) {
                System.out.println(card);
            }
        }
    }

     public void removeCard(String value, String suit) {
        Card card = new Card(value, suit);
        if (cardSet.contains(card)) {
            cardSet.remove(card);
            suitMap.get(suit).remove(card);
            System.out.println("Card removed: " + card);
        } else {
            System.out.println("Error: Card \"" + card + "\" not found.");
        }
    }

    public static void main(String[] args) {
        CardCollectionSystem system = new CardCollectionSystem();

         System.out.println("Test Case 1: Display All Cards");
        system.displayAllCards();  
         System.out.println("\nTest Case 2: Adding Cards");
        system.addCard("Ace", "Spades");    
        system.addCard("King", "Hearts");   
        system.addCard("10", "Diamonds");  
        system.addCard("5", "Clubs");     

         System.out.println("\nTest Case 3: Finding Cards by Suit");
        system.findCardsBySuit("Hearts");  

         System.out.println("\nTest Case 4: Searching Suit with No Cards");
        system.findCardsBySuit("Diamonds");  

         System.out.println("\nTest Case 5: Display All Cards");
        system.displayAllCards();  
         System.out.println("\nTest Case 6: Preventing Duplicate Cards");
        system.addCard("King", "Hearts");  
         System.out.println("\nTest Case 7: Removing a Card");
        system.removeCard("10", "Diamonds");  
    }
}
