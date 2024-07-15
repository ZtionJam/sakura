package cn.ztion.sakura.domain;

import cn.ztion.sakura.enums.MappingAnno;
import com.intellij.psi.PsiAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * info
 *
 * @author ZtionJam
 * @date 2024/7/10
 */
@AllArgsConstructor
@Data
public class Anno {

    private PsiAnnotation anno;
    private MappingAnno data;
}