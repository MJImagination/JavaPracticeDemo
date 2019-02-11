package 缓存.cache.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: UserCache 表缓存
 * @Author: MJ
 * @Date: Created in 2018/11/30
 */
@Service
public class PropertiesCache {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesCache.class);
//    @Autowired
//    private PropertiesService propertiesService;
//    @Autowired
//    private UrpCacheManager urpCacheManager;


    /**
     * 初始化缓存
     */
    public void init() {
        long start = System.currentTimeMillis();
        logger.info("START - 正在加载 PropertiesCache 缓存");

        PROPERTIES_NAME_VALUE();


        long end = System.currentTimeMillis();
        logger.info("END - PropertiesCache 缓存加载完成 耗时：" + (end - start) + "ms");
    }


    /**
     * PROPERTIES_NAME_VALUE
     */
    private void PROPERTIES_NAME_VALUE() {
//        Properties propertiesSearch = new Properties();
//        propertiesSearch.setState(EventConstants.STATE_OK);
//        List<Properties> propertiesList = propertiesService.selectList(propertiesSearch);
//        for (Properties properties : propertiesList) {
//            if(StringUtils.isNotEmpty(properties.getName()))
//            urpCacheManager.put(CacheConstants.PROPERTIES_NAME_VALUE, properties.getName(), properties.getNamevalue());
//        }
    }
}
