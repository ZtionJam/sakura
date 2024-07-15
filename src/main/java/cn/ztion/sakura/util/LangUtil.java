package cn.ztion.sakura.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 工具
 *
 * @author ZtionJam
 * @date 2024/7/10
 */
public class LangUtil {

    private static final Set<String> PRIMITIVES_AND_WRAPPERS = new HashSet<>(Arrays.asList(
            "byte", "short", "int", "long", "boolean", "char", "float", "double", "String",
            "Byte", "Short", "Integer", "Long", "Boolean", "Character", "Float", "Double", "BigInteger", "BigDecimal"));

    private static final Set<String> MAP_NAME = new HashSet<>(Arrays.asList(
            "Map", "HashMap", "Hashtable", "HashSet", "ConcurrentHashMap", "LinkedHashMap", "TreeMap", "EnumMap"));

    private static final Set<String> LIST_NAME = new HashSet<>(Arrays.asList(
            "List", "ArrayList", "LinkedList", "Vector", "Stack"));

    /**
     * 是否基本参数类型
     */
    public static boolean isPrim(String typeName) {
        return PRIMITIVES_AND_WRAPPERS.contains(typeName);
    }

    /**
     * 是否是Map
     */
    public static boolean isMap(String typeName) {
        return MAP_NAME.contains(typeName);
    }

    /**
     * 是否是List
     */
    public static boolean isList(String typeName) {
        return LIST_NAME.contains(typeName);
    }


}