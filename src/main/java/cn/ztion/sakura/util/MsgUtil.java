package cn.ztion.sakura.util;

import com.intellij.openapi.ui.Messages;

/**
 * 消息
 *
 * @author ZtionJam
 * @date 2024/7/10
 */
public class MsgUtil {

    public static void showMsg(String text) {
        Messages.showMessageDialog(
                text,
                "Tips",
                Messages.getInformationIcon()
        );
    }

}