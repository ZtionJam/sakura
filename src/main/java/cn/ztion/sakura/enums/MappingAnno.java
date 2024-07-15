package cn.ztion.sakura.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * support annotation
 *
 * @author ZtionJam
 * @date 2024/7/10
 */
@Getter
public enum MappingAnno {
    REQUEST("org.springframework.web.bind.annotation.RequestMapping", "ALL", "value"),

    GET("org.springframework.web.bind.annotation.GetMapping", "GET", "value"),

    POST("org.springframework.web.bind.annotation.PostMapping", "POST", "value"),

    PUT("org.springframework.web.bind.annotation.PutMapping", "PUT", "value"),

    DELETE("org.springframework.web.bind.annotation.DeleteMapping", "DELETE", "value"),
    ;

    private final String name;
    private final String method;
    private final String fieldName;

    MappingAnno(String name, String method, String fieldName) {
        this.name = name;
        this.method = method;
        this.fieldName = fieldName;
    }

    public static boolean isSupport(String name) {
        return Arrays.stream(MappingAnno.values()).anyMatch(a -> a.name.equals(name));
    }

    public static MappingAnno getAnno(String name) {
        return Arrays.stream(MappingAnno.values()).filter(a -> a.name.equals(name)).findFirst().orElse(null);
    }
}