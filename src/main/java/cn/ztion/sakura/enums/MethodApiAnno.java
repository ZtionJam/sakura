package cn.ztion.sakura.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * TODO
 *
 * @author ZtionJam
 * @date 2024/7/10
 */
@Getter
public enum MethodApiAnno {
    API("io.swagger.annotations.ApiOperation", "value"),
    ;

    private final String name;
    private final String fieldName;

    MethodApiAnno(String name, String fieldName) {
        this.name = name;
        this.fieldName = fieldName;
    }

    public static MethodApiAnno getAnno(String name) {

        return Arrays.stream(MethodApiAnno.values()).filter(a -> a.name.equals(name)).findFirst().orElse(null);
    }
}