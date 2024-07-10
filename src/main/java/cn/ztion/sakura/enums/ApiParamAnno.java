package cn.ztion.sakura.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * 请求参数注解
 *
 * @author ZtionJam
 * @date 2024/7/10
 */
@Getter
public enum ApiParamAnno {
    REQUEST_HEADER("org.springframework.web.bind.annotation.RequestHeader", "value"),
    REQUEST_PARAM("org.springframework.web.bind.annotation.RequestParam", "value"),
    PATH_VARIABLE("org.springframework.web.bind.annotation.PathVariable", "value"),
    REQUEST_BODY("org.springframework.web.bind.annotation.RequestBody", "value"),
    ;

    private final String name;
    private final String fieldName;

    ApiParamAnno(String name, String fieldName) {
        this.name = name;
        this.fieldName = fieldName;
    }

    public static ApiParamAnno getAnno(String name) {

        return Arrays.stream(ApiParamAnno.values()).filter(a -> a.name.equals(name)).findFirst().orElse(null);
    }
}