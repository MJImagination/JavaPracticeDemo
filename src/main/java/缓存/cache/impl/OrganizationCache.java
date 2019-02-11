package 缓存.cache.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Description: OrganizationCache 表缓存
 * @Author: MJ
 * @Date: Created in 2018/11/30
 */
@Service
public class OrganizationCache {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationCache.class);
//    @Autowired
//    private OrganizationService organizationService;
//    @Autowired
//    private UrpCacheManager urpCacheManager;
//    @Autowired
//    private UrpexService urpexService;


    /**
     * 初始化缓存
     */
    public void init() {
        long start = System.currentTimeMillis();
        logger.info("START - 正在加载 OrganizationCache 缓存");

        ORGANIZATION_ID_OBJECT();
        ORGANIZATION_STREE_LIST();
        ORGANIZATION_PID_COUNTRYLIST();
        ORGANIZATION_PID_GRIDLIST();

        long end = System.currentTimeMillis();
        logger.info("END - OrganizationCache 缓存加载完成 耗时：" + (end - start) + "ms");
    }


    /**
     * ORGANIZATION_ID_OBJECT
     */
    private void ORGANIZATION_ID_OBJECT() {
//        Organization organizationSearch = new Organization();
//        organizationSearch.setState(EventConstants.STATE_OK);
//        List<Organization> organizationList = organizationService.selectList(organizationSearch);
//        for (Organization organization : organizationList) {
//            urpCacheManager.put(CacheConstants.ORGANIZATION_ID_OBJECT, organization.getId(), organization);
//        }

    }


    /**
     * ORGANIZATION_STREE_LIST 所属街道
     */
    private void ORGANIZATION_STREE_LIST() {
//        Organization organizationSearch = new Organization();
//        organizationSearch.setState(EventConstants.STATE_OK);
//        organizationSearch.setType(UrpExConstants.ORGTYPE_GXJD_JDZX);
//        List<Organization> organizationList = organizationService.selectList(organizationSearch);
//        urpCacheManager.put(CacheConstants.ORGANIZATION_STREE_LIST, "stree", organizationList);

    }

    /**
     * ORGANIZATION_PID_COUNTRYLIST 所属社区
     */
    private void ORGANIZATION_PID_COUNTRYLIST() {
//        List<Organization> streeList = (List<Organization>) urpCacheManager.get(CacheConstants.ORGANIZATION_STREE_LIST, "stree");
//        for (Organization organization : streeList) {
//            Organization organizationSearch = organizationService.selectById(organization.getId());
//            List<Organization> countryList = urpexService.getChildOrgAsType(organizationSearch, UrpExConstants.ORGTYPE_GXSQZX);
//            urpCacheManager.put(CacheConstants.ORGANIZATION_PID_COUNTRYLIST, organization.getId(), countryList);
//        }
    }


    /**
     * ORGANIZATION_PID_GRIDLIST 所属网格
     */
    private void ORGANIZATION_PID_GRIDLIST() {
//        Set<String> keys = urpCacheManager.getCache(CacheConstants.ORGANIZATION_PID_COUNTRYLIST).keys();
//        for (String key : keys) {
//            List<Organization> countryList = (List<Organization>) urpCacheManager.get(CacheConstants.ORGANIZATION_PID_COUNTRYLIST,key);
//            for (Organization organization : countryList) {
//                Organization country = organizationService.selectById(organization.getId());
//                List<Organization> gridlist = urpexService.getChildOrgAsType(country, UrpExConstants.ORGTYPE_GXWGZX);
//                urpCacheManager.put(CacheConstants.ORGANIZATION_PID_GRIDLIST, organization.getId(), gridlist);
//            }
//
//        }
    }
}
