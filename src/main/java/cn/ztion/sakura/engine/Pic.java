package cn.ztion.sakura.engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 三层图封装
 *
 * @author ZtionJam
 * @date 2024/7/8
 */

@SuppressWarnings("UnusedReturnValue")
public class Pic {
        public BufferedImage img;
    public Graphics2D g2;
    public final int curWidth;
    public int curHeight;

    private BufferedImage t;
    private BufferedImage m;
    private BufferedImage d;


    private boolean endFlag = false;
    private int radius = 0;

    /**
     * 构造方法，指定上中下，已中间块为扩展点
     *
     * @param t 上
     * @param m 中
     * @param d 下
     */
    public Pic(BufferedImage t, BufferedImage m, BufferedImage d) {
        this.t = t;
        this.m = m;
        this.d = d;

        BufferedImage bufferedImage = new BufferedImage(t.getWidth(), t.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(t, 0, 0, null);

        this.img = bufferedImage;
        this.g2 = g2d;
        this.curWidth = this.img.getWidth();
        this.curHeight = this.img.getHeight();
    }

    /**
     * 拼接N次中间块
     *
     * @param times 次数
     */
    public Pic expandHeight(int times) {
        for (int i = 0; i < times; i++) {
            expandHeight();
        }
        return this;
    }

    private void copyNew(int nowH) {
        BufferedImage newImage = new BufferedImage(curWidth, nowH, this.img.getType());
        Graphics2D ng2d = newImage.createGraphics();

        ng2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ng2d.setFont(this.g2.getFont());
        ng2d.setColor(this.g2.getColor());

        this.g2.dispose();
        this.g2 = ng2d;

        this.g2.drawImage(img, 0, 0, null);
        this.img = newImage;
        this.curHeight = nowH;
    }

    /**
     * 拼接一次中间块
     */
    private Pic expandHeight() {
        //新的
        int nowH = this.img.getHeight();
        this.copyNew(this.img.getHeight() + m.getHeight());

        //背景
        this.g2.drawImage(m, 0, nowH, null);

        return this;
    }

    /**
     * 拼接结束块
     */
    public Pic end() {
        int curH = this.img.getHeight();
        this.copyNew(this.img.getHeight() + d.getHeight());

        this.g2.drawImage(d, 0, curH, null);


        this.endFlag = true;


        this.cutRadius();
        return this;
    }

    /**
     * 保存为PNG
     *
     * @param path 路径
     */
    public Pic savePng(String path) throws Exception {
        if (!this.endFlag) {
            this.end();
        }
        File file = new File(path);
        ImageIO.write(this.img, "PNG", file);
        return this;
    }

    /**
     * 添加矩形
     */
    public Pic addRect(Color color, int x, int y, int w, int h, int radius) {
        this.fixHeight(h, y);
        if (color != null) {
            this.g2.setColor(color);
        }
        RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, w, h, radius, radius);
        this.g2.fill(rect);
        return this;
    }

    /**
     * 左对齐添加文字
     */
    public Pic addText(Color color, Font font, String text, int x, int y) {
        if (color != null) {
            this.g2.setColor(color);
        }
        int contentH = 100;
        if (font != null) {
            this.g2.setFont(font);
            contentH = font.getSize();
        }
        this.fixHeight(contentH, y);
        this.g2.drawString(text, x, y);
        return this;
    }

    /**
     * 右对齐添加文字
     */
    public Pic addTextRight(Color color, Font font, String text, int rightX, int y) {
        if (color != null) {
            this.g2.setColor(color);
        }
        int contentH = 100;
        if (font != null) {
            this.g2.setFont(font);
            contentH = font.getSize();
            rightX = this.curWidth - (getLength(text) * font.getSize()) - rightX;
        }
        this.fixHeight(contentH, y);
        this.g2.drawString(text, rightX, y);
        return this;
    }

    /**
     * 计算字符长度
     */
    public static int getLength(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length += 2;
            } else {
                length += 1;
            }
        }
        return length / 2;
    }

    /**
     * 根据新增的容器高度，添加中段
     */
    private Pic fixHeight(int contentHeight, int y) {
        int pos = contentHeight + y;
        if (pos > curHeight) {
            int over = pos - this.curHeight;
            int times = over / m.getHeight();
            if (over % m.getHeight() != 0) {
                times++;
            }
            this.expandHeight(times);
        }

        return this;
    }

    /**
     * 设置图片的圆角，会在保存或者调用end()时裁剪圆角
     *
     * @param radius 半径
     */
    public Pic radius(int radius) {
        this.radius = radius;
        return this;
    }

    /**
     * 裁剪圆角
     */
    private Pic cutRadius() {
        BufferedImage roundedImage = new BufferedImage(this.curWidth, this.curHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, this.curWidth, this.curHeight, radius, radius);
        g2d.setClip(roundedRectangle);
        g2d.drawImage(this.img, 0, 0, null);
        this.img = roundedImage;
        this.g2 = g2d;
        return this;
    }


}