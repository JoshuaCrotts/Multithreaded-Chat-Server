import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 */
public class ClientFrame extends JFrame {

    /**
     *
     */
    private final ClientPane CLIENT_PANE;

    /**
     *
     */
    private final Client CLIENT;

    public ClientFrame() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.CLIENT_PANE = new ClientPane(this);
        this.CLIENT = new Client(this);
        this.addWindowCloseListener();
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        super.setTitle("Multithreaded Chat Server");
        super.setVisible(true);
        super.setMinimumSize(new Dimension(640, 480));
        super.setLocationRelativeTo(null);
        super.add(this.CLIENT_PANE);
        super.pack();
    }

    /**
     *
     */
    public void addWindowCloseListener() {
        super.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                int confirm = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to close the application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    CLIENT.sendCloseRequest();
                    System.exit(0);
                }
            }
        });
    }

    public ClientPane getClientPane() {
        return this.CLIENT_PANE;
    }
}
