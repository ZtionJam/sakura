package cn.ztion.sakura.domain;

import lombok.Data;

import java.util.List;

/**
 * 实体
 *
 * @author ZtionJam
 * @date 2024/5/24
 */
@Data
public class Endpoint {
    /**
     * 名字
     */
    private String name;
    /**
     * 地址
     */
    private String Url;
    /**
     * 请求方式
     */
    private String method;
    /**
     * url参数
     */
    private List<InterParam> queryParam;
    /**
     * body参数
     */
    private List<InterParam> bodyParam;


}