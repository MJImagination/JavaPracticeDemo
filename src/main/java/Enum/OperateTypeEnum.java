package Enum;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/7/13
 */
public enum OperateTypeEnum {
    UPDATE, DELETE, ADD;

    /**
     * 判断给定值是否包含在枚举内
     * @param type
     * @return
     */
    public static boolean contains(String type) {
        for (OperateTypeEnum operateTypeEnum : OperateTypeEnum.values()) {
            if (operateTypeEnum.name().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
