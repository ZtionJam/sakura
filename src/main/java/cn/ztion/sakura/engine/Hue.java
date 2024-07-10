package cn.ztion.sakura.engine;

import java.awt.*;

/**
 * 颜色工具
 *
 * @author ZtionJam
 * @date 2024/7/8
 */
public class Hue extends Color {

    public static Hue BLACK = new Hue(0, 0, 0);

    public static Hue WHITE = new Hue(255, 255, 255);
    public static Hue PINK_END = new Hue(255, 105, 180);
    public static Hue PINK_START = new Hue(255, 192, 203);

    public Hue(int r, int g, int b) {
        super(r, g, b);
    }

    public Hue(int r, int g, int b, int a) {
        super(r, g, b, a);
    }

    /**
     * 当前一半透明度的颜色
     */
    public Hue halfA() {
        return new Hue(this.getRed(), this.getGreen(), this.getBlue(), this.getAlpha() / 2);
    }

    public Hue halfA(int times) {
        int a = this.getAlpha();
        for (int i = 0; i < times; i++) {
            a = a / 2;
        }
        return new Hue(this.getRed(), this.getGreen(), this.getBlue(), a);
    }


}