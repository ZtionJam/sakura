package cn.ztion.sakura.util;

import com.intellij.openapi.ui.Messages;

/**
 * TODO
 *
 * @author ZtionJam
 * @date 2024/7/10
 */
public class MsgUtil {

    public static void showMsg(String text) {
        Messages.showMessageDialog(
                text,
                "提示",
                Messages.getInformationIcon()
        );
    }

}