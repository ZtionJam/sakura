package cn.ztion.sakura.domain;

import cn.ztion.sakura.enums.ApiParamAnno;
import com.intellij.psi.PsiAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 注解和信息
 *
 * @author ZtionJam
 * @date 2024/7/10
 */
@AllArgsConstructor
@Data
public class AnnoParam {

    private PsiAnnotation anno;
    private ApiParamAnno data;
}