package cn.ztion.sakura;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.javadoc.PsiDocParamRef;
import com.intellij.psi.javadoc.PsiDocTag;
import com.intellij.psi.javadoc.PsiDocToken;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappingAction extends AnAction {
    private static final Logger log = LoggerFactory.getLogger(MappingAction.class);

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        PsiElement element = e.getData(CommonDataKeys.PSI_ELEMENT);
        if (element instanceof PsiMethod method) {
            PsiModifierList modifierList = PsiTreeUtil.getChildOfType(method, PsiModifierList.class);
            if (modifierList != null) {
                for (PsiElement child : modifierList.getChildren()) {
                    if (child instanceof PsiAnnotation anno) {
                        this.resolveAnno(method, anno);
                    }
                }
            }
        }
    }

    public void resolveAnno(PsiMethod method, PsiAnnotation anno) {
        PsiParameterList parameterList = method.getParameterList();

        for (PsiElement child : method.getDocComment().getChildren()) {
            if(child instanceof PsiDocToken token){
                System.out.println("文档注释:"+token.getText());
            }
            if(child instanceof PsiDocTag tag){
                for (PsiElement tagChild : tag.getChildren()) {
                    if(tagChild instanceof PsiDocParamRef param){
                        System.out.println("标签:"+param.getText());
                    }
                    if(tagChild instanceof PsiDocToken param){
                        System.out.println("注释:"+param.getText());
                    }
                }
            }
        }

        for (PsiParameter parameter : parameterList.getParameters()) {
            String name = parameter.getName();
            System.out.println("name:" + name);
        }
        System.out.println(anno.getText());
    }
}
