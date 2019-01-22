package Enum;

public enum ConsumerEnum {
    /**
     * 每次保全消费类型
     */
    SEAL(1, "普通签章"),

    CA_SEAL(2, "ca签章"),

    DATA_FlOW(3, "数据流量");



    private int code;
    private String msg;

    private ConsumerEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ConsumerEnum valueOf(int code) {
        ConsumerEnum result = null;
        ConsumerEnum[] codes = ConsumerEnum.class.getEnumConstants();
        for (ConsumerEnum temp : codes) {
            if (temp.getCode() == code) {
                result = temp;
                break;
            }
        }

        return result;
    }

    public static String getMsg(int code) {
        for (ConsumerEnum c : ConsumerEnum.values()) {
            if (c.getCode() == code) {
                return c.msg;
            }
        }
        return null;
    }

    public static int getCode(String msg) {
        for (ConsumerEnum c : ConsumerEnum.values()) {
            if (c.getMsg() == msg) {
                return c.code;
            }
        }
        return -1;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static void main(String[] args){
        String type="";
//        switch (ConsumerEnum.valueOf(type)) {
//            case SEAL:
//                if (getPackagesConsumerService().sealConsumer(tag, consumer, type, count, userId, msg)) {
//                    return 1;
//                }
//                break;
//            case CA_SEAL:
//                if (getPackagesConsumerService().sealConsumer(tag, consumer, type, count, userId, msg)) {
//                    return 1;
//                }
//                break;
//            case DATA_FlOW:
//                if (getPackagesConsumerService().dataFlow(tag, consumer, type, count, userId, msg)) {
//                    return 1;
//                }
//                break;
//            default:
//                msg.setValue(RechargeResponseInfo._200207.getMsg());
//                return 0;
//        }
    }

}