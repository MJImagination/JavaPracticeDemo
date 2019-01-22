/*
 * RecController.java
 * Copyright(C) 2015 杭州天翼智慧城市科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-05-25 Created
 */
package Excel.大文件读取;

//package com.hesc.event.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.hesc.base.dao.PplDao;
//import com.hesc.base.pojo.Address;
//import com.hesc.base.pojo.Ppl;
//import com.hesc.base.service.AddressService;
//import com.hesc.base.service.OrgService;
//import com.hesc.base.service.PplService;
//import com.hesc.event.pojo.Rec;
//import com.hesc.event.service.EventService;
//import com.hesc.event.service.XtService;
//import com.hesc.urp.pojo.Organization;
//import com.hesc.urpex.UrpExConstants;
//import com.hesc.util.ExcelParser;
//import org.apache.commons.lang.StringUtils;
//import org.apache.ibatis.session.ExecutorType;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.InputStream;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

/**
 * @Description: 省协同接口
 * @Author: MJ
 * @Date: Created in 2018/12/20
 */

//@Controller
//@RequestMapping("/event/excel")
public class ExcelController {

//    private Logger logger = Logger.getLogger(ExcelController.class);
//
//    @Autowired
//    private EventService eventService;
//    @Autowired
//    private XtService xtService;
//    @Autowired
//    private ExcelParser excelParser;
//    @Autowired
//    private PplService pplService;
//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;
//    @Autowired
//    private OrgService orgService;
//    @Autowired
//    private AddressService addressService;
//
//    /**
//     * 保存
//     *
//     * @param
//     * @return
//     */
//
//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String save(Model model, HttpServletRequest request) {
//
//        model.addAttribute("rec", new Rec());
//        model.addAttribute("url", "add");
//        return "/event/Excel/list";
//    }
//
//
//    /**
//     * 描述：户籍人口
//     *
//     * @param request
//     * @throws Exception
//     */
//    @ResponseBody
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public String uploadExcel(HttpServletRequest request) throws Exception {
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        InputStream in = null;
//        MultipartFile file = multipartRequest.getFile("file");
//        if (file.isEmpty()) {
//            throw new Exception("文件不存在！");
//        }
//        in = file.getInputStream();
//
//
//        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);//跟上述sql区别
//        ExcelParser parse = excelParser.parse(in);
//        List<String[]> datas = parse.getDatas();
//        PplDao mapper = sqlSession.getMapper(PplDao.class);
//        List<Ppl> pplList = new ArrayList<>();
//        int size = datas.size();
//        try {
//            for (int i = 0; i < datas.size(); i++) {
//                String[] data = datas.get(i);
//                Ppl ppl = new Ppl();
//                ppl.setId(UUID.randomUUID().toString().replace("-", ""));
//                ppl.setName(data[0]);
//                ppl.setType("1");
//                ppl.setSex(data[4].equals("男") ? "1" : "2");
//                ppl.setIdcard(data[6]);
//                if (StringUtils.isNotEmpty(data[5])) {
//                    ppl.setBirth(Long.valueOf(data[5].replace("-", "")));
//                }
//                ppl.setNational(data[20]);
//                ppl.setPhone(data[17]);
//                ppl.setMobphone(data[18]);
//                ppl.setHouseholdAddr(data[9]);
//                ppl.setHouseholdDetailaddr(data[10]);
//                ppl.setNowDetailadd(data[12]);
//                ppl.setDeath("1");
//                ppl.setState("1");
//                ppl.setUsertype(data[28]);
//                ppl.setIsclaim("0");
//                ppl.setPolitical(data[21]);
//                ppl.setEducation(data[22]);
//                ppl.setMarital(data[24]);
//                ppl.setEmail(data[19]);
//                ppl.setProtype(data[23]);
//                ppl.setBloodtype(data[25]);
//                pplList.add(ppl);
//                if ((i + 1) % 1000 == 0 || i == size - 1) {
//                    pplService.insertInBatch(pplList);
//                }
//            }
//        } catch (Exception e) {
//            sqlSession.rollback();
//            e.printStackTrace();
//        } finally {
//            sqlSession.close();
//        }
//        Result result = new Result();
//        result.setSuccess(true);
//        return JSON.toJSONString(result);
//    }
//
//
//    /**
//     * 地址
//     *
//     * @param request
//     * @throws Exception
//     */
//    @ResponseBody
//    @RequestMapping(value = "/upload2", method = RequestMethod.POST)
//    public String uploadExcel2(HttpServletRequest request) throws Exception {
//        Address addressdd = new Address();
//        addressdd.setQxuid("002001");
//        addressService.insert(addressdd);
//
//        //街道 name : object
//        Map<String, Organization> jd = this.getJd();
//
//        //社区 <街道name,<社区name,object>>
//        Map<String, Map<String, Organization>> sq = this.getShMap(jd);
//
//        //网格 <街道name, <社区name,<网格name,object>>>
//        Map<String, Map<String, Map<String, Organization>>> wg = this.getWgMap(sq);
//
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        InputStream in = null;
//        MultipartFile file = multipartRequest.getFile("file");
//        if (file.isEmpty()) {
//            throw new Exception("文件不存在！");
//        }
//        in = file.getInputStream();
//
//        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);//跟上述sql区别
////        ExcelParser parse = excelParser.parse(in);
////        List<String[]> datas = parse.getDatas();
//        List<String[]> datas = null;
//        PplDao mapper = sqlSession.getMapper(PplDao.class);
//        List<House> pplList = new ArrayList<>();
//        int size = datas.size();
//        try {
//            for (int i = 0; i < datas.size(); i++) {
//                String[] data = datas.get(i);
//
////                House house = new House();
//                Address address = new Address();
//                address.setQxuid("002001");
//
//                Organization jdTemp = jd.get(data[0]);
//                address.setJduid(jdTemp == null ? "" : jdTemp.getId());
//
//                Organization sqTemp = (jdTemp == null ? null : sq.get(data[0]).get(data[1]));
//                address.setSquid(sqTemp == null ? "" : sqTemp.getId());
//
//                Organization wqTemp = (sqTemp == null ? null : wg.get(data[0]).get(data[1]).get(data[2]));
//                address.setWguid(wqTemp == null?"":wqTemp.getId());
//
//                //'点状地址（7楼）应该有所属的块状地址（华星路99号）',
//                if(isStandardAddress(data[4])){
//                    addressService.insert(address);
//                }else{
//                    address.setType("2");
//                    addressService.insert(address);
//                }
//                address.setStandardaddress(getStandardAddress(data[4]));
//                address.setDetailaddress(data[4]);
//
//                if ((i + 1) % 500 == 0 || i == size - 1) {
////                    pplService.insertInBatch(pplList);
//                }
//            }
//        } catch (Exception e) {
//            sqlSession.rollback();
//            e.printStackTrace();
//        } finally {
//            sqlSession.close();
//        }
//
//
//        Result result = new Result();
//        result.setSuccess(true);
//        return JSON.toJSONString(result);
//    }
//
//    /**
//     * 街道
//     *
//     * @return
//     */
//    public Map<String, Organization> getJd() {
//        Map<String, Organization> result = new HashMap<>();
//        Map<Object, Object> map = orgService.getChildOrgByCode("002001", UrpExConstants.ORGTYPE_GXJD_JDZX);
//        if (map.size() > 0) {
//            List<Organization> orgList = (List) map.get("orgList");
//            for (Organization org : orgList) {
//                result.put(org.getName(), org);
//            }
//        }
//        return result;
//
//    }
//
//    /**
//     * 社区 map
//     *
//     * @param jdMap
//     * @return
//     */
//    public Map<String, Map<String, Organization>> getShMap(Map<String, Organization> jdMap) {
//        Map<String, Map<String, Organization>> shResult = new HashMap<>();
//        for (String s : jdMap.keySet()) {
//            shResult.put(s, getSh(jdMap.get(s).getPlacecode()));
//        }
//        return shResult;
//    }
//
//
//    /**
//     * 网格 map
//     *
//     * @param shMap
//     * @return
//     */
//    public Map<String, Map<String, Map<String, Organization>>> getWgMap(Map<String, Map<String, Organization>> shMap) {
//        Map<String, Map<String, Map<String, Organization>>> wgResult = new HashMap<>();
//        for (String s : shMap.keySet()) {
//            Map<String, Map<String, Organization>> temp = new HashMap<>();
//            Map<String, Organization> sqTmep = shMap.get(s);
//            for (String s1 : sqTmep.keySet()) {
//                temp.put(s1, getWg(sqTmep.get(s1).getPlacecode()));
//            }
//            wgResult.put(s, temp);
//
//        }
//        return wgResult;
//    }
//
//
//    /**
//     * 社区
//     *
//     * @param jdPlaceCode
//     * @return
//     */
//    public Map<String, Organization> getSh(String jdPlaceCode) {
//        Map<String, Organization> result = new HashMap<>();
//        Map<Object, Object> map = orgService.getChildOrgByCode(jdPlaceCode, UrpExConstants.ORGTYPE_GXSQZX);
//        if (map.size() > 0) {
//            List<Organization> orgList = (List) map.get("orgList");
//            for (Organization org : orgList) {
//                result.put(org.getName(), org);
//            }
//        }
//        return result;
//
//    }
//
//
//    /**
//     * 网格
//     *
//     * @param shPlaceCode
//     * @return
//     */
//    public Map<String, Organization> getWg(String shPlaceCode) {
//        Map<String, Organization> result = new HashMap<>();
//        Map<Object, Object> map = orgService.getChildOrgByCode(shPlaceCode, UrpExConstants.ORGTYPE_GXWGZX);
//        if (map.size() > 0) {
//            List<Organization> orgList = (List) map.get("orgList");
//            for (Organization org : orgList) {
//                result.put(org.getName(), org);
//            }
//        }
//        return result;
//
//    }
//
//    /**
//     * 获取块状数据
//     * @param detailAddress
//     * @return
//     */
//    public String getStandardAddress(String detailAddress){
//        Matcher matcher = Pattern.compile("[0-9]").matcher(detailAddress);
//        if (matcher.find()) {
//            int index = matcher.start();
//            return detailAddress.substring(0, index);
//        } else {
//            return detailAddress;
//        }
//    }
//
//
//    /**
//     * 是否是块状地址
//     * @param detailAddress
//     * @return
//     */
//
//
//    public boolean isStandardAddress(String detailAddress){
//        Matcher matcher = Pattern.compile("[0-9]").matcher(detailAddress);
//        if (matcher.find()) {
//            return true;
//        } else {
//            return false;
//        }
//    }

}