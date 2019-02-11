package 缓存.cache.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: EventtypeCache 表缓存
 * @Author: MJ
 * @Date: Created in 2018/11/30
 */
@Service
public class EventtypeCache {
    private static final Logger logger = LoggerFactory.getLogger(EventtypeCache.class);
//    @Autowired
//    private EventtypeService eventtypeService;
//    @Autowired
//    private UrpCacheManager urpCacheManager;


    /**
     * 初始化缓存
     */
    public void init() {
        long start = System.currentTimeMillis();
        logger.info("START - 正在加载 EventtypeCache 缓存");

        EVENTTYPE_BIGTYPE_LIST();
        EVENTTYPE_PID_SMALLLIST();
        EVENTTYPE_PID_THINLIST();
        EVENTTYPE_ID_OBJECT();
        EVENTTYPE_KEY_MAP();

        long end = System.currentTimeMillis();
        logger.info("END - EventtypeCache 缓存加载完成 耗时：" + (end - start) + "ms");
    }


    /**
     * EVENTTYPE_BIGTYPE_LIST  大类
     */
    private void EVENTTYPE_BIGTYPE_LIST() {
//        Eventtype eventtypeSearch = new Eventtype();
//        eventtypeSearch.setState(EventConstants.STATE_OK);
//        eventtypeSearch.setType("1");
//        List<Eventtype> eventtypeList = eventtypeService.selectListOrderByCode(eventtypeSearch);
//        urpCacheManager.put(CacheConstants.EVENTTYPE_BIGTYPE_LIST, "1", eventtypeList);
    }


    /**
     * EVENTTYPE_PID_SMALLLIST 小类
     */
    private void EVENTTYPE_PID_SMALLLIST() {
//        List<Eventtype> bigList = (List<Eventtype>) urpCacheManager.get(CacheConstants.EVENTTYPE_BIGTYPE_LIST, "1");
//        for (Eventtype eventtype : bigList) {
//            Eventtype eventtypeSearch = new Eventtype();
//            eventtypeSearch.setParentId(eventtype.getId());
//            eventtypeSearch.setState(Constants.STATE_OK);
//            List<Eventtype> eventtypeList = eventtypeService.selectListOrderByCode(eventtypeSearch);
//            urpCacheManager.put(CacheConstants.EVENTTYPE_PID_SMALLLIST, eventtype.getId(), eventtypeList);
//        }
    }


    /**
     * EVENTTYPE_PID_THINLIST 细分类
     */
    private void EVENTTYPE_PID_THINLIST() {
//        Set<String> keys = urpCacheManager.getCache(CacheConstants.EVENTTYPE_PID_SMALLLIST).keys();
//        for (String key : keys) {
//            List<Eventtype> smallList = (List<Eventtype>) urpCacheManager.get(CacheConstants.EVENTTYPE_PID_SMALLLIST,key);
//            for (Eventtype eventtype : smallList) {
//                Eventtype eventtypeSearch = new Eventtype();
//                eventtypeSearch.setParentId(eventtype.getId());
//                eventtypeSearch.setState(Constants.STATE_OK);
//                List<Eventtype> eventtypeList = eventtypeService.selectListOrderByCode(eventtypeSearch);
//                urpCacheManager.put(CacheConstants.EVENTTYPE_PID_THINLIST, eventtype.getId(), eventtypeList);
//            }
//        }
    }

    /**
     * EVENTTYPE_ID_OBJECT
     */
    private void EVENTTYPE_ID_OBJECT() {
//        Eventtype eventtypeSearch = new Eventtype();
//        eventtypeSearch.setState(Constants.STATE_OK);
//        List<Eventtype> eventtypeList = eventtypeService.selectList(eventtypeSearch);
//        for (Eventtype eventtype : eventtypeList) {
//            urpCacheManager.put(CacheConstants.EVENTTYPE_ID_OBJECT, eventtype.getId(), eventtype);
//        }
    }


    /**
     * EVENTTYPE_KEY_MAP
     */
    private void EVENTTYPE_KEY_MAP() {
//        Eventtype eventtypeSearch = new Eventtype();
//        eventtypeSearch.setState(Constants.STATE_OK);
//        List<Eventtype> eventtypeList = eventtypeService.selectList(eventtypeSearch);
//        Map<String,String> idNameMap = new HashMap<>();
//        for (Eventtype eventtype : eventtypeList) {
//            idNameMap.put(eventtype.getId(),eventtype.getName());
//        }
//        urpCacheManager.put(CacheConstants.EVENTTYPE_KEY_MAP, "idNameMap", idNameMap);
    }
}
