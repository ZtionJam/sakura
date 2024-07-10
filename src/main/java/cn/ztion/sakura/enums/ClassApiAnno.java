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
public enum ClassApiAnno {
    API("io.swagger.annotations.Api", "tags"),
    TAG("io.swagger.annotations", "name"),
    ;

    private final String name;
    private final String fieldName;

    ClassApiAnno(String name, String fieldName) {
        this.name = name;
        this.fieldName = fieldName;
    }

    public static ClassApiAnno getAnno(String name) {

        return Arrays.stream(ClassApiAnno.values()).filter(a -> a.name.equals(name)).findFirst().orElse(null);
    }
}