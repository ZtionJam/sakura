package cn.ztion.sakura.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个接口
 *
 * @author ZtionJam
 * @date 2024/7/9
 */
public class Mapping {
    /**
     * 接口名字
     */
    private String name = "";
    /**
     * 地址
     */
    private String address = "";
    /**
     * 版本
     */
    private String version = "";
    /**
     * 作者
     */
    private String author = "";
    /**
     * 日期
     */
    private String date = "";
    /**
     * 接口类型
     */
    private String method = "";
    /**
     * 请求头参数
     */
    private List<Param> headerParam = new ArrayList<>();
    /**
     * 查询参数
     */
    private List<Param> queryParam = new ArrayList<>();
    /**
     * 路径参数
     */
    private List<Param> pathParam = new ArrayList<>();
    /**
     * 请求体参数
     */
    private List<Param> bodyParam = new ArrayList<>();
    /**
     * 返回参数
     */
    private List<Param> resultParam = new ArrayList<>();

    public List<Param> getResultParam() {
        return resultParam;
    }

    public Mapping setResultParam(List<Param> resultParam) {
        this.resultParam = resultParam;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Mapping setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Mapping setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Mapping setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public Mapping setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Mapping setDate(String date) {
        this.date = date;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public Mapping setMethod(String method) {
        if (method.length() > 4) {
            method = method.substring(0, 4);
        }
        this.method = method;
        return this;
    }

    public List<Param> getHeaderParam() {
        return headerParam;
    }

    public Mapping setHeaderParam(List<Param> headerParam) {
        this.headerParam = headerParam;
        return this;
    }

    public List<Param> getQueryParam() {
        return queryParam;
    }

    public Mapping setQueryParam(List<Param> queryParam) {
        this.queryParam = queryParam;
        return this;
    }

    public List<Param> getPathParam() {
        return pathParam;
    }

    public Mapping setPathParam(List<Param> pathParam) {
        this.pathParam = pathParam;
        return this;
    }

    public List<Param> getBodyParam() {
        return bodyParam;
    }

    public Mapping setBodyParam(List<Param> bodyParam) {
        this.bodyParam = bodyParam;
        return this;
    }
}