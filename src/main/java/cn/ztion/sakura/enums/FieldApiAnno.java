package cn.ztion.sakura.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * 字段
 *
 * @author ZtionJam
 * @date 2024/7/10
 */
@Getter
public enum FieldApiAnno {

    API_MODEL_PROPERTIES("io.swagger.annotations.ApiModelProperty", "value"),
    ;

    private final String name;
    private final String fieldName;

    FieldApiAnno(String name, String fieldName) {
        this.name = name;
        this.fieldName = fieldName;
    }

    public static FieldApiAnno getAnno(String name) {

        return Arrays.stream(FieldApiAnno.values()).filter(a -> a.name.equals(name)).findFirst().orElse(null);
    }
}