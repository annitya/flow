import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class FlowPlugin extends AnAction
{
    public FlowPlugin() {}

    public void actionPerformed(AnActionEvent event)
    {
        FlowModel model = new FlowModel(event);
        FlowGui gui = new FlowGui(model);
        gui.render();
    }
}