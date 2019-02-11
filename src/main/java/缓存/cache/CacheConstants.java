package 缓存.cache;


/**
 * @Description: 缓存定义常量表  需要在ehcache-shiro.xml中申明
 * @Author: MJ
 * @Date: Created in 2018/11/30
 */
public class CacheConstants {
    /*----------------------user表相关缓存-------------------------------*/

    //key:用户id value:Object
    public final static String USER_ID_OBJECT = "USER_ID_OBJECT";


    /*----------------------Eventtype表相关缓存-------------------------------*/

    //key:1  value:list （大类）
    public final static String EVENTTYPE_BIGTYPE_LIST = "EVENTTYPE_BIGTYPE_LIST";

    //key:大类id  value:list(小类)
    public final static String EVENTTYPE_PID_SMALLLIST = "EVENTTYPE_PID_SMALLLIST";

    //key:小类id  value:list(细分类)
    public final static String EVENTTYPE_PID_THINLIST = "EVENTTYPE_PID_THINLIST";

    //key:id  value:object
    public final static String EVENTTYPE_ID_OBJECT = "EVENTTYPE_ID_OBJECT";

    //key:idNameMap    value:map
    public final static String EVENTTYPE_KEY_MAP = "EVENTTYPE_KEY_MAP";






    /*---------------------- Organization表相关缓存-------------------------------*/
    //key:id value:Object
    public final static String ORGANIZATION_ID_OBJECT = "ORGANIZATION_ID_OBJECT";


    //key:stree value:list  (所属街道)
    public final static String ORGANIZATION_STREE_LIST = "ORGANIZATION_STREE_LIST";

    //key:所属街道id value:list  (所属社区)
    public final static String ORGANIZATION_PID_COUNTRYLIST = "ORGANIZATION_PID_COUNTRYLIST";

    //key:所属社区id value:list  (所属网格)
    public final static String ORGANIZATION_PID_GRIDLIST = "ORGANIZATION_PID_GRIDLIST";



    /*---------------------- Dutyregion表相关缓存-------------------------------*/
    //key:id value:Object
    public final static String DUTYREGION_ID_OBJECT = "DUTYREGION_ID_OBJECT";





    /*---------------------- properties表相关缓存-------------------------------*/
    //key:name vvlue:namevalue
    public final static String PROPERTIES_NAME_VALUE = "PROPERTIES_NAME_VALUE";
}
