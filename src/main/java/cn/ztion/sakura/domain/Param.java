package cn.ztion.sakura.domain;

import cn.ztion.sakura.engine.Pic;
import com.intellij.openapi.util.text.StringUtil;

import java.util.List;

/**
 * 一个参数
 *
 * @author ZtionJam
 * @date 2024/7/9
 */
public class Param {

    private String name = "";
    private String type = "";
    private String required = "false";
    private String remark = "";

    private List<Param> child;


    public String getName() {
        return name;
    }

    public Param setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Param setType(String type) {
        this.type = type;
        return this;
    }

    public String getRequired() {
        return required;
    }

    public Param setRequired(String required) {
        if (StringUtil.isEmpty(required)) {
            required = "false";
        }
        this.required = required;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Param setRemark(String remark) {
        if (Pic.getLength(remark) > 8) {
            remark = remark.substring(0, 8);
        }
        this.remark = remark;
        return this;
    }

    public List<Param> getChild() {
        return child;
    }

    public Param setChild(List<Param> child) {
        this.child = child;
        return this;
    }
}