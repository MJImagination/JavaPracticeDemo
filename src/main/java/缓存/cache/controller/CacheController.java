/*
 * DictionaryController.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-05-23 Created
 */
package 缓存.cache.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cache")
public class CacheController {
//
//    private Logger logger = Logger.getLogger(CacheController.class);
//
//    @Autowired
//    private InitData initData;

    /**
     * 新增
     * @param model
     * @return
    */
//    @LogMsg("刷新缓存")
    @ResponseBody
    @RequestMapping(value="/refreshAll", method=RequestMethod.GET)
    public String add(Model model, HttpServletRequest request){
//        initData.loadCache();
        return "ok";
    }
}