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
public class UserCache {
    private static final Logger logger = LoggerFactory.getLogger(UserCache.class);
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UrpCacheManager urpCacheManager;


    /**
     * 初始化缓存
     */
    public void init() {
        long start = System.currentTimeMillis();
        logger.info("START - 正在加载 UserCache 缓存");

        USER_ID_OBJECT();


        long end = System.currentTimeMillis();
        logger.info("END - UserCache 缓存加载完成 耗时：" + (end - start) + "ms");
    }


    /**
     * USER_ID_OBJECT
     */
    private void USER_ID_OBJECT() {
//        User UserSearch = new User();
//        UserSearch.setState(EventConstants.STATE_OK);
//        List<User> userList = userService.selectList(UserSearch);
//        for (User user : userList) {
//            urpCacheManager.put(CacheConstants.USER_ID_OBJECT, user.getId(), user);
//        }
    }
}
