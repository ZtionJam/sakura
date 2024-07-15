package cn.ztion.sakura.util;

import cn.ztion.sakura.domain.Anno;
import cn.ztion.sakura.domain.AnnoParam;
import cn.ztion.sakura.domain.Mapping;
import cn.ztion.sakura.domain.Param;
import cn.ztion.sakura.enums.*;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;

import java.util.*;

/**
 * psi
 *
 * @author ZtionJam
 * @date 2024/7/9
 */
public class PsiUtil {


    public static void resolveResult(PsiMethod method, Mapping mapping) {
        PsiType returnType = method.getReturnType();
        if (returnType instanceof PsiClassType classType) {
            PsiClass psiClass = classType.resolve();
            if (psiClass == null) {
                return;
            }
            Map<String, List<Param>> par = new HashMap<>();
            PsiType[] parameters = ((PsiClassType) returnType).getParameters();
            for (int i = 0; i < parameters.length; i++) {
                try {
                    PsiType parameter = parameters[i];
                    PsiClass resolve = ((PsiClassType) parameter).resolve();
                    PsiTypeParameter[] typeParameters = psiClass.getTypeParameters();
                    if (resolve != null) {
                        if (LangUtil.isPrim(resolve.getName())) {
                            continue;
                        }
                        List<Param> classField = getClassField(resolve);
                        par.put(typeParameters[i].getName(), classField);
                    }
                } catch (Exception ignored) {
                }
            }
            List<Param> classField = getClassField(psiClass);
            classField.forEach(p -> par.keySet().forEach(k -> {
                if (k.equals(p.getType())) {
                    p.setChild(par.get(k));
                }
            }));
            mapping.setResultParam(classField);
        }

    }

    public static List<Param> getClassField(PsiClass psiClass) {
        List<Param> params = new ArrayList<>();
        PsiField[] fields = psiClass.getAllFields();
        for (PsiField field : fields) {
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }
            Param p = new Param()
                    .setType(field.getTypeElement().getText())
                    .setName(field.getName());
            for (PsiAnnotation annotation : field.getAnnotations()) {
                FieldApiAnno anno = FieldApiAnno.getAnno(annotation.getQualifiedName());
                if (anno != null) {
                    p.setRemark(getAnnoFieldVal(annotation, anno.getFieldName()));
                    p.setRequired(getAnnoFieldVal(annotation, "required"));
                }
            }
            params.add(p);
        }

        return params;
    }

    public static void resolveMethodParam(PsiElement element, Mapping mapping) {

        if (element instanceof PsiMethod method) {
            for (PsiParameter parameter : method.getParameterList().getParameters()) {
                AnnoParam paramMod = getParamMod(parameter);
                if (paramMod == null) {
                    paramMod = new AnnoParam(null, ApiParamAnno.REQUEST_PARAM);
                }

                switch (paramMod.getData()) {
                    //请求
                    case REQUEST_BODY:
                        String typeName = parameter.getTypeElement().getText();
                        if (LangUtil.isPrim(typeName) || LangUtil.isMap(typeName) || LangUtil.isList(typeName)) {
                            mapping.getBodyParam().add(getSingleParam(paramMod.getAnno(), parameter, paramMod.getData()));
                            break;
                        }
                        PsiType parameterType = parameter.getType();
                        PsiClass psiClass = ((PsiClassType) parameterType).resolve();
                        if (psiClass != null) {
                            mapping.getBodyParam().addAll(getClassField(psiClass));
                        }
                        break;
                    //路径
                    case PATH_VARIABLE:
                        mapping.getPathParam().add(getSingleParam(paramMod.getAnno(), parameter, paramMod.getData()));
                        break;
                    //请求头
                    case REQUEST_HEADER:
                        mapping.getHeaderParam().add(getSingleParam(paramMod.getAnno(), parameter, paramMod.getData()));
                        break;
                    //GET
                    case REQUEST_PARAM:
                        mapping.getQueryParam().add(getSingleParam(paramMod.getAnno(), parameter, paramMod.getData()));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static Param getSingleParam(PsiAnnotation annotation, PsiParameter parameter, ApiParamAnno anno) {
        Param p = new Param()
                .setRequired("false")
                .setName(parameter.getName())
                .setType(parameter.getTypeElement().getText());
        if (annotation != null) {
            p.setName(getAnnoFieldVal(annotation, anno.getFieldName()));
            p.setRequired(getAnnoFieldVal(annotation, "required"));
        }
        return p;
    }

    /**
     * 获取参数上的参数注解
     */
    public static AnnoParam getParamMod(PsiParameter parameter) {
        for (PsiAnnotation annotation : parameter.getModifierList().getAnnotations()) {
            ApiParamAnno anno = ApiParamAnno.getAnno(annotation.getQualifiedName());
            if (anno != null) {
                return new AnnoParam(annotation, anno);
            }
        }
        return null;
    }

    /**
     * TODO
     * 获取方法上的注释 注解注释>文档注释>方法名
     */
    public static String getMethodComment(PsiElement element) {
        String comment = "";
        PsiModifierList modifierList = PsiTreeUtil.getChildOfType(element, PsiModifierList.class);
        if (modifierList != null) {
            for (PsiElement child : modifierList.getChildren()) {
                if (child instanceof PsiAnnotation anno) {
                    MethodApiAnno api = MethodApiAnno.getAnno(anno.getQualifiedName());
                    if (api != null) {
                        comment = getAnnoFieldVal(anno, api.getFieldName());
                    }
                }
            }
        }

        return comment;
    }

    /**
     * TODO
     * 获取类上的注释 注解注释>文档注释>项目名字
     */
    public static String getClassComment(PsiClass psiClass) {
        String comment = "";
        PsiModifierList modifierList = PsiTreeUtil.getChildOfType(psiClass, PsiModifierList.class);
        if (modifierList != null) {
            for (PsiElement child : modifierList.getChildren()) {
                if (child instanceof PsiAnnotation anno) {
                    ClassApiAnno api = ClassApiAnno.getAnno(anno.getQualifiedName());
                    if (api != null) {
                        comment = getAnnoFieldVal(anno, api.getFieldName());
                    }
                }
            }
        }
        if (comment.isEmpty()) {
            comment = psiClass.getProject().getName();
        }
        return comment;
    }

    /**
     * 获取注解的值
     */
    public static String getAnnoFieldVal(PsiAnnotation anno, String fieldName) {
        PsiAnnotationMemberValue field = anno.findDeclaredAttributeValue(fieldName);
        if (field == null) {
            return "";
        }
        String text = field.getText();
        return text.replace("\"", "");
    }

    /**
     * 判断类是否包含Controller注解
     */
    public static boolean hasController(PsiClass psiClass) {
        return Arrays.stream(ControllerAnno.values()).anyMatch(sca -> psiClass.getAnnotation(sca.getName()) != null);
    }

    /**
     * 获取映射注解
     */
    public static Optional<Anno> getMappingAnno(PsiElement element) {
//        if (element instanceof PsiMethod method) {
        PsiModifierList modifierList = PsiTreeUtil.getChildOfType(element, PsiModifierList.class);
        if (modifierList != null) {
            for (PsiElement child : modifierList.getChildren()) {
                if (child instanceof PsiAnnotation anno && MappingAnno.isSupport(anno.getQualifiedName())) {
                    return Optional.of(new Anno(anno, MappingAnno.getAnno(anno.getQualifiedName())));
                }
            }
        }
//        }
        return Optional.empty();
    }

}