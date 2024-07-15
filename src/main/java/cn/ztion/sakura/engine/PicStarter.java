package cn.ztion.sakura.engine;

import cn.ztion.sakura.domain.Mapping;
import cn.ztion.sakura.domain.Param;
import cn.ztion.sakura.wapper.ImageDialog;
import com.intellij.openapi.project.Project;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * 使用PIC
 *
 * @author ZtionJam
 * @date 2024/7/10
 */
public class PicStarter {

    public static void gen(Mapping mapping, Project project) throws Exception {
        ClassLoader classLoader = PicStarter.class.getClassLoader();

        // 读取三张图片
        BufferedImage t = ImageIO.read(classLoader.getResourceAsStream("t.png"));
        BufferedImage m = ImageIO.read(classLoader.getResourceAsStream("m.png"));
        BufferedImage d = ImageIO.read(classLoader.getResourceAsStream("d.png"));

        int ptr = 0;
        //信息块
        Pic pic = new Pic(t, m, d)
                .addRect(Hue.WHITE.halfA(2), 1200, 100, 400, 400, 100)
                .addText(Hue.PINK_END, Fnt.ARIAL.size(125).bold(), mapping.getMethod(), 1220, 250);
        //作者
        pic.addText(Hue.PINK_START, Fnt.DENG_XIAN.size(40).bold(), "作者:", 1220, 340)
                .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(40).bold(), mapping.getAuthor(), 1330, 340);
        //版本
        pic.addText(Hue.PINK_START, Fnt.DENG_XIAN.size(40).bold(), "接口版本:", 1220, 390)
                .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(40).bold(), mapping.getVersion(), 1410, 390);
        //版本
        pic.addText(Hue.PINK_START, Fnt.DENG_XIAN.size(40).bold(), "日期:", 1220, 440)
                .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(40).bold(), mapping.getDate(), 1330, 440);
        //标题
        pic.addTextRight(Hue.BLACK, Fnt.DENG_XIAN.size(60).bold(), mapping.getName(), 600, 220)
                .addRect(Hue.PINK_END.halfA(), 900, 250, 200, 10, 0);
        //接口地址
        pic.addRect(Hue.WHITE.halfA(2), 100, 800, 200, 100, 30)
                .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "接口地址", 130, 860)
                .addRect(Hue.WHITE.halfA(2), 320, 800, 1280, 100, 30)
                .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35).bold(), mapping.getAddress(), 350, 860)
                .addRect(Hue.PINK_END.halfA(), 350, 870, Pic.getLength(mapping.getAddress()) * 35, 5, 0);
        ptr = 860;

        //Header参数
        int headerSize = mapping.getHeaderParam().size();
        if (headerSize > 0) {
            pic.addRect(Hue.WHITE.halfA(3), 100, ptr += 80, 1500, 160 + (headerSize * 50), 30)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "Header参数", 120, ptr += 50)
                    .addRect(Hue.PINK_END.halfA(), 120, ptr + 10, 120, 5, 0)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "参数名", 200, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "值类型", 480, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "必填", 880, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "描述", 1280, ptr + 70);
            ptr += 70;
            int line = ptr;

            for (Param param : mapping.getHeaderParam()) {
                line += 50;
                pic.addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35).bold(), param.getName(), 200, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getType(), 480, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getRequired(), 880, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getRemark(), 1280, line);
            }
            ptr += (headerSize * 50);
        }
        //Path参数
        int pathSize = mapping.getPathParam().size();
        if (pathSize > 0) {
            pic.addRect(Hue.WHITE.halfA(3), 100, ptr += 80, 1500, 160 + (pathSize * 50), 30)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "Path参数", 120, ptr += 50)
                    .addRect(Hue.PINK_END.halfA(), 120, ptr + 10, 120, 5, 0)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "参数名", 200, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "值类型", 480, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "必填", 880, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "描述", 1280, ptr + 70);
            ptr += 70;
            int line = ptr;

            for (Param param : mapping.getPathParam()) {
                line += 50;
                pic.addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35).bold(), param.getName(), 200, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getType(), 480, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getRequired(), 880, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getRemark(), 1280, line);
            }
            ptr += (pathSize * 50);
        }
        //Query参数
        int querySize = mapping.getQueryParam().size();
        if (querySize > 0) {
            pic.addRect(Hue.WHITE.halfA(3), 100, ptr += 80, 1500, 160 + (querySize * 50), 30)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "Query参数", 120, ptr += 50)
                    .addRect(Hue.PINK_END.halfA(), 120, ptr + 10, 120, 5, 0)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "参数名", 200, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "值类型", 480, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "必填", 880, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "描述", 1280, ptr + 70);
            ptr += 70;
            int line = ptr;

            for (Param param : mapping.getQueryParam()) {
                line += 50;
                pic.addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35).bold(), param.getName(), 200, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getType(), 480, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getRequired(), 880, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getRemark(), 1280, line);
            }
            ptr += (querySize * 50);
        }

        //body参数
        int bodySize = mapping.getBodyParam().size();
        if (bodySize > 0) {
            pic.addRect(Hue.WHITE.halfA(3), 100, ptr += 80, 1500, 160 + (bodySize * 50), 30)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "Body参数", 120, ptr += 50)
                    .addRect(Hue.PINK_END.halfA(), 120, ptr + 10, 120, 5, 0)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "参数名", 200, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "值类型", 480, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "必填", 880, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "描述", 1280, ptr + 70);
            ptr += 70;
            int line = ptr;

            for (Param param : mapping.getBodyParam()) {
                line += 50;
                pic.addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35).bold(), param.getName(), 200, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getType(), 480, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getRequired(), 880, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getRemark(), 1280, line);
            }
            ptr += (bodySize * 50);
        }

        //结果参数
        int retSize = mapping.getResultParam().size();
        retSize += mapping.getResultParam().stream().map(p -> p.getChild() == null ? 0 : p.getChild().size()).reduce(Integer::sum).get();
        if (retSize > 0) {
            pic.addRect(Hue.WHITE.halfA(2), 100, ptr += 80, 1500, 160 + (retSize * 50), 30)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "响应结构", 120, ptr += 50)
                    .addRect(Hue.PINK_END.halfA(), 120, ptr + 10, 120, 5, 0)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "参数名", 200, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "值类型", 480, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "必填", 880, ptr + 70)
                    .addText(Hue.PINK_END, Fnt.DENG_XIAN.size(35).bold(), "描述", 1280, ptr + 70);
            ptr += 70;
            int line = ptr;

            for (Param param : mapping.getResultParam()) {
                line += 50;
                pic.addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35).bold(), param.getName(), 200, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getType(), 480, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getRequired(), 880, line)
                        .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), param.getRemark(), 1280, line);
                if (param.getChild() != null) {
                    for (Param child : param.getChild()) {
                        line += 50;
                        pic.addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35).bold(), child.getName(), 200 + 30, line)
                                .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), child.getType(), 480 + 30, line)
                                .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), child.getRequired(), 880 + 30, line)
                                .addText(Hue.BLACK.halfA(), Fnt.DENG_XIAN.size(35), child.getRemark(), 1280, line);
                    }
                }
            }
        }


        //收尾
        pic.radius(100)
                .end();
        ImageDialog imageDialog = new ImageDialog(project, pic.img, mapping.toString(), 800, 600);
        imageDialog.show();

    }

}