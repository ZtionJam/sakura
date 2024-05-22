package cn.ztion.sakura;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.util.PsiTreeUtil;
import groovy.util.logging.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class MappingAction extends AnAction {
    private static final Logger log = LoggerFactory.getLogger(MappingAction.class);

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        PsiElement element = e.getData(CommonDataKeys.PSI_ELEMENT);
        if (element != null) {
            PsiModifierList modifierList = PsiTreeUtil.getChildOfType(element, PsiModifierList.class);
            if(modifierList!=null){
                PsiAnnotation annotation = PsiTreeUtil.getChildOfType(modifierList, PsiAnnotation.class);
                System.out.println(123);
                System.out.println(annotation.getText());
            }

        }

    }
}
