package Enum;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;


/**
 * Objects.equals(a，b)和a.equals(b)
 * 2018年07月10日 18:16:36 Cy_QAQ 阅读数：447
 * 对于a.equals(b)，如果此时A为null，则在程序运行的时候，会发生空指针异常
 *
 * 对于Objects.equals(a，b),如果两个参数都为 null， Objects.equals(a，b) 调用将返回 true ;
 * 如果其中一个参数为 null ,则返回 false ; 否则，如果两个参数都不为 null， 则调用 a.equals(b)
 */

/**
 * @author jiabin.
 * @data 18-10-19.
 */
public enum StartNode {
    // 去指挥中心
    DEFAULT("QZX"),
    QZX("QZX"),
    // 街道
    JD("JD"),
    // 街道职能部门
    JDEJZYBM("JDEJZYBM"),
    // 社区
    SH("SH"),
    // 网格
    WG("WG"),
    // 去专业部门
    QZYBM("QZYBM"),
    // 二级专业部门
    QEJZYBM("QEJZYBM");

    private String type;
    /*private String index;

    StartNode(String type, String index) {
        this.type = type;
        this.index = index;
    }*/

    StartNode(String type) {
        this.type = type;
    }

    public static String getIndex(String type) {
        if (StringUtils.isBlank(type)) {
            return DEFAULT.type;
        }
        if (Objects.equals(type, JD.type)) {
            return JD.type;
        } else if (Objects.equals(type, SH.type)) {
            return SH.type;
        } else if (Objects.equals(type, WG.type)) {
            return WG.type;
        } else if (Objects.equals(type, JDEJZYBM.type)) {
            return JDEJZYBM.type;
        } else if (Objects.equals(type, QZYBM.type)) {
            return QZYBM.type;
        } else if (Objects.equals(type, QEJZYBM.type)) {
            return QEJZYBM.type;
        }
        return DEFAULT.type;
    }

    public String getType() {
        return type;
    }

    /*public String getIndex() {
        return index;
    }*/
}
