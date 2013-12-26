import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.util.Query;
import com.jetbrains.php.lang.psi.elements.Function;
import java.util.ArrayList;
import java.util.Collection;

public class FlowModel
{
    protected Project project;
    protected AnActionEvent currentEvent;
    protected ArrayList<FlowGuiElement> elements;

    public FlowModel(AnActionEvent e)
    {
        elements = new ArrayList<FlowGuiElement>();
        currentEvent = e;
        project = e.getData(PlatformDataKeys.PROJECT);

        PsiElement currentFunction = getCurrentFunction();

        if (currentFunction == null) {
            return;
        }

        Query<PsiReference> result = ReferencesSearch.search(currentFunction);
        Collection<PsiReference> references = result.findAll();

        for (PsiReference reference : references) {
            elements.add(new FlowGuiElement(reference));
        }
    }

    protected PsiElement getCurrentFunction()
    {
        Editor editor = currentEvent.getData(PlatformDataKeys.EDITOR);

        if (editor == null) {
            return null;
        }

        PsiElement cursorElement = PsiUtilBase.getElementAtCaret(editor);

        return PsiTreeUtil.getParentOfType(cursorElement, Function.class);
    }

    public Collection<FlowGuiElement> getElements()
    {
        return elements;
    }

    public Project getProject()
    {
        return project;
    }
}
