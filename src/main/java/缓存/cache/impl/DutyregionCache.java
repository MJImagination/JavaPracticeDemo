package 缓存.cache.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: DutyregionCache 表缓存
 * @Author: MJ
 * @Date: Created in 2018/12/03
 */
@Service
public class DutyregionCache {
    private static final Logger logger = LoggerFactory.getLogger(DutyregionCache.class);
//    @Autowired
//    private DutyregionService dutyregionService;
//    @Autowired
//    private UrpCacheManager urpCacheManager;


    /**
     * 初始化缓存
     */
    public void init() {
        long start = System.currentTimeMillis();
        logger.info("START - 正在加载 OrganizationCache 缓存");

        DUTYREGION_ID_OBJECT();


        long end = System.currentTimeMillis();
        logger.info("END - OrganizationCache 缓存加载完成 耗时：" + (end - start) + "ms");
    }


    /**
     * DUTYREGION_ID_OBJECT
     */
    private void DUTYREGION_ID_OBJECT() {
//        Dutyregion dutyregionSearch = new Dutyregion();
//        dutyregionSearch.setState(EventConstants.STATE_OK);
//        List<Dutyregion> dutyregionList = dutyregionService.selectList(dutyregionSearch);
//        for (Dutyregion dutyregion : dutyregionList) {
//            urpCacheManager.put(CacheConstants.DUTYREGION_ID_OBJECT, dutyregion.getId(), dutyregion);
//        }

    }
}
