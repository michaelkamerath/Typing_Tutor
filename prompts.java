import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Michael on 10/29/2014.
 */
public class prompts extends JPanel {
    private JTextArea displayPrompts;
    private String[] typingPrompts;
    private int atPrompt = 0;
    private int lengthOfPrompt;

    public void setAtPrompt(int atPrompt) {
        this.atPrompt = atPrompt;
    }

    public int getAtPrompt() {
        return atPrompt;
    }

    public String[] getTypingPrompts() {
        return typingPrompts;
    }

    public void displayNewPrompt() {
        displayPrompts.setText(getTypingPrompts()[getAtPrompt()]);
    }

    public void setLengthOfPrompt() {
        this.lengthOfPrompt = getTypingPrompts()[getAtPrompt()].length();
    }

    public int getLengthOfPrompt() {
        return lengthOfPrompt;
    }

    public char promptPosition(int position) {
        return getTypingPrompts()[getAtPrompt()].charAt(position);
    }

    prompts() {
        typingPrompts = new String[]{
                "A typed word is counted as any five keystrokes.",
                "Do not stop to correct your errors in these first tests but check them out.",
                "The beautiful scenic country of New Zealand is situated in the South Pacific to the east of Australia.",
                "The ferry crosses Cook Strait and cruises beautiful Queen Charlotte Sounds between Wellington, NZ's Capital City, and Picton.",
                "NZ's East Coast has many stretches of white sand and rolling surf which attract NZ and overseas surfers. They are popular NZ family holiday places.",
                "New Zealand is a land of contrasts, which attract thousands of overseas tourists every year to climb, ski or snowboard our mountains, swim, fish or cruise on our lakes and rivers.",
                "Between The Southern Alps and the West Coast is a fantastic scenic drive taking the Haast Pass road. Here is our great rain forest. Most overseas and local tours include this route in their itinerary.",
                "New Zealand is a very sport oriented country and sometimes hosts world events. Sports include tennis, golf, yachting, canoeing, cruising, cricket, soccer, rugby, basketball, netball, swimming, surf lifesaving, athletics, and riding",
                "Watching events where they take place is fine but many can only watch at home as the event is screened on our TVs. New Zealand is proud too of our sporting participants who have entered and gained medals in many sporting events including Olympic Games.",
                "Masters' Games are very popular in New Zealand as in many other countries and NZ swimmers were really proud in the year 2002 to host the FINA World Masters Swimming Champs at Christchurch in the South Island, at which I gained 10th place medals for 100 m and 200 m backstroke.",
                "Some challenging events which draw overseas sports people include the annual Coast to Coast involving running, cycling and kayaking from the West Coast, through mountain passes to the East Coast, and the Iron Man including running, cycling, swimming. I am proud one of my sons twice took part in the Ironman"
        };

        setLayout(null);

        displayPrompts = new JTextArea();
        displayPrompts.setSize(900, 250);
        displayPrompts.setLocation(0, 50);
        displayPrompts.setFont(new Font("Serif", Font.PLAIN, 16));
        displayPrompts.setLineWrap(true);
        displayPrompts.setWrapStyleWord(true);
        displayPrompts.setFocusable(false);
        displayPrompts.setEditable(false);
        displayPrompts.setText(getTypingPrompts()[getAtPrompt()]);

        add(displayPrompts);

        setVisible(true);
    }
}
