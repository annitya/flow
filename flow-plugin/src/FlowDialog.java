import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlowDialog extends JDialog
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextPane graphics;

    public FlowDialog(String text)
    {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        graphics.setText(text);
    }

    private void onOK()
    {
        dispose();
    }

    public static void main(String text) {
        FlowDialog dialog = new FlowDialog(text);
        dialog.pack();
        dialog.setVisible(true);
        //System.exit(0);
    }
}
