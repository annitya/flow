import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.php.lang.psi.elements.Function;
import com.jetbrains.php.lang.psi.elements.impl.MethodImpl;

public class FlowGuiElement
{
    public String name;
    public String containingFileName;
    public String content;

    public FlowGuiElement(PsiReference reference)
    {
        PsiElement element = reference.getElement();

        PsiFile containingFile = element.getContainingFile();
        containingFileName = containingFile.getName();

        MethodImpl closestFunction = (MethodImpl)PsiTreeUtil.getParentOfType(element, Function.class);

        if (closestFunction != null) {
            name = closestFunction.getName();
            content = closestFunction.getText();
        }
        else {
            name = "Global";
            content = containingFile.getText();
        }
    }

    public String toString()
    {
        return "Name: " + name + "\n" + "File: " + containingFileName + "\n" + content;
    }
}
