package cn.ztion.sakura.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * SupportControllerAnno
 *
 * @author ZtionJam
 * @date 2024/7/10
 */
@Getter
public enum ControllerAnno {

    REST_CONTROLLER("org.springframework.web.bind.annotation.RestController"),

    CONTROLLER("org.springframework.stereotype.Controller");

    private final String name;

    ControllerAnno(String name) {
        this.name = name;
    }

    public static boolean isSupport(String name) {
        return Arrays.stream(ControllerAnno.values()).anyMatch(a -> a.name.equals(name));
    }
}