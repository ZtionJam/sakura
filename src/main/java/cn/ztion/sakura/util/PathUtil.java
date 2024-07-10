package cn.ztion.sakura.util;

/**
 * 路径工具
 *
 * @author ZtionJam
 * @date 2024/7/10
 */
public class PathUtil {

    /**
     * 处理路径，比如不是/开头的加/
     */
    public static String formatPath(String path) {
        return path.startsWith("/") ? path : "/" + path;
    }
}