import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;

public class FlowPlugin implements ToolWindowFactory
{
    protected FlowModel model;

    private JPanel contentPane;
    private JTextPane graphics;

    public void createToolWindowContent(Project project, ToolWindow toolWindow)
    {
        model = new FlowModel(project);
        model.getEditor().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void beforeDocumentChange(DocumentEvent event) {}
            @Override
            public void documentChanged(DocumentEvent event) {
                model.updateData();
                setContentText();
            }
        });

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(contentPane, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    protected void setContentText()
    {
        String displayedText = "";

        for (FlowGuiElement element : model.getElements()) {
            displayedText += element.toString() + "\n";
        }

        graphics.setText(displayedText);
    }
}