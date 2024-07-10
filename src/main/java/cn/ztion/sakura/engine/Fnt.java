package cn.ztion.sakura.engine;

import java.awt.*;

/**
 * 字体
 *
 * @author ZtionJam
 * @date 2024/7/8
 */
public class Fnt extends Font {

    public static Fnt ARIAL = new Fnt("Arial", Font.PLAIN, 150);
    public static Fnt DENG_XIAN = new Fnt("等线", Font.PLAIN, 150);

    public Fnt(String name, int style, int size) {
        super(name, style, size);
    }

    /**
     * 大小
     */
    public Fnt size(int size) {
        return new Fnt(this.name, this.style, size);
    }

    /**
     * 加粗
     */
    public Fnt bold() {
        return new Fnt(super.name, super.BOLD, super.size);
    }
}