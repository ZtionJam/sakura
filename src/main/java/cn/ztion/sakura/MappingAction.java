package cn.ztion.sakura;

import cn.ztion.sakura.domain.Mapping;
import cn.ztion.sakura.engine.PicStarter;
import cn.ztion.sakura.util.MsgUtil;
import cn.ztion.sakura.util.PathUtil;
import cn.ztion.sakura.util.PsiUtil;
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
        PsiUtil.getMappingAnno(e.getData(CommonDataKeys.PSI_ELEMENT)).ifPresent(anno -> {
            PsiMethod method = (PsiMethod) e.getData(CommonDataKeys.PSI_ELEMENT);
            Mapping mapping = new Mapping();
            //获取类的注释
            PsiClass parent = PsiTreeUtil.getParentOfType(anno.getAnno(), PsiClass.class);
            if (!PsiUtil.hasController(parent)) {
                MsgUtil.showMsg("The class not a Controller");
                return;
            }
            final StringBuilder path = new StringBuilder();
            //获取类上的Path
            PsiUtil.getMappingAnno(parent).ifPresent(ca -> {
                path.append(PathUtil.formatPath(PsiUtil.getAnnoFieldVal(ca.getAnno(), anno.getData().getFieldName())));
            });
            //方法上的path
            String methodPath = PathUtil.formatPath(PsiUtil.getAnnoFieldVal(anno.getAnno(), anno.getData().getFieldName()));
            path.append(methodPath);
            //类上的注释
            String classComment = PsiUtil.getClassComment(parent);
            //方法上的注释
            String methodComment = PsiUtil.getMethodComment(e.getData(CommonDataKeys.PSI_ELEMENT));

            //方法参数
            PsiUtil.resolveMethodParam(e.getData(CommonDataKeys.PSI_ELEMENT), mapping);

            mapping.setAddress(path.toString())
                    .setMethod(anno.getData().getMethod())
                    .setAuthor("ZtionJam")
                    .setVersion("1.0")
                    .setDate("2024-07-10")
                    .setName(classComment + "-" + methodComment);
            try {
                PicStarter.gen(mapping, e.getProject());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public void resolveAnno(PsiMethod method, PsiAnnotation anno) {
        PsiParameterList parameterList = method.getParameterList();

        for (PsiElement child : method.getDocComment().getChildren()) {
            if (child instanceof PsiDocToken token) {
                System.out.println("文档注释:" + token.getText());
            }
            if (child instanceof PsiDocTag tag) {
                for (PsiElement tagChild : tag.getChildren()) {
                    if (tagChild instanceof PsiDocParamRef param) {
                        System.out.println("标签:" + param.getText());
                    }
                    if (tagChild instanceof PsiDocToken param) {
                        System.out.println("注释:" + param.getText());
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
