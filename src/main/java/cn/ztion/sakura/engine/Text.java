package cn.ztion.sakura.engine;

import java.awt.*;

/**
 * 字
 *
 * @author ZtionJam
 * @date 2024/7/9
 */
public class Text {

    private Color color;
    private String text;
    private Font font;
    private int size;

    public Text txt(String txt) {
        this.text = txt;
        return this;
    }

    public Text size(int size) {
        this.font = new Fnt(font.getName(), Font.PLAIN, size);
        return this;
    }

    public Text color(Color color) {
        this.color = color;
        return this;
    }

    public Text color(Font font) {
        this.font = font;
        return this;
    }


    public Text(String text, Color color, Font font) {
        this.text = text;
        this.color = color;
        this.font = font;
    }
}