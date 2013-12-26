import com.intellij.openapi.ui.Messages;

import javax.swing.*;

public class FlowGui
{
    protected FlowModel model;
    private String displayedText;

    public FlowGui(FlowModel newModel)
    {
        model = newModel;
    }

    public void render()
    {
        for (FlowGuiElement element : model.getElements()) {
            displayedText += element.toString() + "\n";
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FlowDialog.main(displayedText);
            }
        });
                //Messages.showMessageDialog(model.getProject(), messageText, "Flow", Messages.getInformationIcon());
    }
}