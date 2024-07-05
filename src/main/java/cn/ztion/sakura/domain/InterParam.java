package cn.ztion.sakura.domain;

import lombok.Data;

/**
 * 参数
 *
 * @author ZtionJam
 * @date 2024/5/24
 */
@Data
public class InterParam {
    /**
     * 描述
     */
    private String des;
    /**
     * 参数名字
     */
    private String name;
    /**
     * 参数类型
     */
    private String type;
    /**
     * 是否必填
     */
    private Boolean must;
}