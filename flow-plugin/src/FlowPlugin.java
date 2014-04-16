import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class FlowPlugin implements ToolWindowFactory
{
    protected FlowModel model;

    private JPanel contentPane;
    private JTextPane graphics;
    private ToolWindow toolWindow;

    public void createToolWindowContent(Project project, ToolWindow window)
    {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(contentPane, "", false);
        window.getContentManager().addContent(content);
        toolWindow = window;

        model = new FlowModel(project);
        bindEvents();
    }

    protected void bindEvents()
    {
        model.getEditor().getCaretModel().addCaretListener(new CaretListener()
        {
            public void caretPositionChanged(CaretEvent e)
            {
                model.updateData();

                if (model.isDirty()) {
                    setContentText();
                }
            }
        });

        graphics.addAncestorListener(new AncestorListener() {
            // Toolbar has been restored, time to update the content.
            public void ancestorAdded(AncestorEvent event)
            {
                setContentText();
                graphics.invalidate();
            }

            public void ancestorRemoved(AncestorEvent event) {}
            public void ancestorMoved(AncestorEvent event) {}
        });
    }

    protected void setContentText()
    {
        // Do nothing if the tool-window is invisible.
        if (!toolWindow.isVisible()) {
            return;
        }

        String displayedText = "";

        for (FlowGuiElement element : model.getElements()) {
            displayedText += element.toString() + "\t" + model.getCurrentFunctionName() + "\n";
        }

        graphics.setText(displayedText);
    }
}