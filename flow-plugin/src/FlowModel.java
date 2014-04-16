import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.util.Query;
import com.jetbrains.php.lang.psi.elements.Function;
import com.jetbrains.php.lang.psi.elements.impl.MethodImpl;

import java.util.ArrayList;
import java.util.Collection;

public class FlowModel
{
    protected Project project;
    protected Editor editor;
    protected ArrayList<FlowGuiElement> elements;
    protected MethodImpl currentFunction;
    protected boolean dirty = false;

    public FlowModel(Project project)
    {
        this.project = project;
        elements = new ArrayList<FlowGuiElement>();
        editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
    }

    public void updateData()
    {
        elements.clear();

        MethodImpl focusedFunction = getCurrentFunction();
        if (focusedFunction == null) {
            return;
        }

        dirty = currentFunction != focusedFunction;
        if (dirty) {
            currentFunction = focusedFunction;
        }
        else {
            return;
        }

        Query<PsiReference> result = ReferencesSearch.search(currentFunction);
        Collection<PsiReference> references = result.findAll();

        for (PsiReference reference : references) {
            elements.add(new FlowGuiElement(reference));
        }
    }

    protected MethodImpl getCurrentFunction()
    {
        if (editor == null) {
            return null;
        }

        PsiElement cursorElement = PsiUtilBase.getElementAtCaret(editor);

        return (MethodImpl)PsiTreeUtil.getParentOfType(cursorElement, Function.class);
    }

    public String getCurrentFunctionName()
    {
        return currentFunction.getName();
    }

    public Collection<FlowGuiElement> getElements()
    {
        return elements;
    }

    public Editor getEditor()
    {
        return editor;
    }

    public boolean isDirty() { return dirty; }
}
