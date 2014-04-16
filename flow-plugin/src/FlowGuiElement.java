import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.php.lang.psi.elements.Function;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import com.jetbrains.php.lang.psi.elements.impl.MethodImpl;
import com.jetbrains.php.lang.psi.elements.impl.MethodReferenceImpl;

public class FlowGuiElement
{
    public String name;
    public String content;
    public String className;

    public FlowGuiElement(PsiReference reference)
    {
        MethodReferenceImpl element = (MethodReferenceImpl)reference.getElement();

        PsiFile containingFile = element.getContainingFile();

        MethodImpl closestFunction = (MethodImpl)PsiTreeUtil.getParentOfType(element, Function.class);

        if (closestFunction != null) {
            name = closestFunction.getName();

            PhpClass containingClass = closestFunction.getContainingClass();
            className = containingClass != null ? containingClass.getFQN() : "N/A";
            content = closestFunction.getText();
        }
        else {
            Document document = PsiDocumentManager.getInstance(containingFile.getProject()).getDocument(containingFile);
            int lineNumber = document != null ? document.getLineNumber(element.getTextOffset()) + 1 : 0;
            name = String.valueOf(lineNumber);
            className = containingFile.getName();
            content = containingFile.getText();
        }
    }

    public String toString()
    {
        return className + "\t/\t " + name;
    }
}
