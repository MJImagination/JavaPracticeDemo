package 缓存.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * @Description:   缓存启动监听类
 * @Author: MJ
 * @Date: Created in 2018/11/30
 */
@Component
public class InitData implements ApplicationListener<ContextRefreshedEvent> {
//    @Autowired
//    private UserCache userCache;
//    @Autowired
//    private EventtypeCache eventtypeCache;
//    @Autowired
//    private OrganizationCache organizationCache;
//    @Autowired
//    private DutyregionCache dutyregionCache;
//    @Autowired
//    private PropertiesCache propertiesCache;

    private static final Logger logger = LoggerFactory.getLogger(InitData.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        if (arg0.getApplicationContext().getParent() == null) {
            loadCache();
        }
    }

    public void loadCache(){
        long start = System.currentTimeMillis();
        logger.info("START - 缓存开始加载");

//        userCache.init();
//        eventtypeCache.init();
//        organizationCache.init();
//        dutyregionCache.init();
//        propertiesCache.init();

        long end = System.currentTimeMillis();
        logger.info("END - 全部缓存加载完成 总耗时：" + (end - start) + "ms");
    }
}