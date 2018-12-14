package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class PoiUtil {
//	@Autowired
//    private EventService eventService;
//    public static String NO_DEFINE = "no_define";//未定义的字段
//    public static String DEFAULT_DATE_PATTERN="yyyy年MM月dd日";//默认日期格式
//    public static int DEFAULT_COLOUMN_WIDTH = 17;
//    private static final String dowLoadExcelUrl = Message.getMessage("dowLoadExcelUrl");
//	/**
//     * 导出Excel 2007 OOXML (.xlsx)格式
//     * @param title 标题行
//     * @param headMap 属性-列头
//     * @param jsonArray 数据集
//     * @param datePattern 日期格式，传null值则默认 年月日
//     * @param colWidth 列宽 默认 至少17个字节
//     * @param out 输出流
//     */
//    public static void exportExcelX(String title,Map<String, String> headMap,JSONArray jsonArray,String datePattern,int colWidth, OutputStream out) {
//        if(datePattern==null) datePattern = DEFAULT_DATE_PATTERN;
//        // 声明一个工作薄
//        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);//缓存
//        workbook.setCompressTempFiles(true);
//         //表头样式
//        CellStyle titleStyle = workbook.createCellStyle();
//        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        Font titleFont = workbook.createFont();
//        titleFont.setFontHeightInPoints((short) 20);
//        titleFont.setBoldweight((short) 700);
//        titleStyle.setFont(titleFont);
//        // 列头样式
//        CellStyle headerStyle = workbook.createCellStyle();
//        /*headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);*/
//        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        Font headerFont = workbook.createFont();
//        headerFont.setFontHeightInPoints((short) 12);
//        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        headerStyle.setFont(headerFont);
//        // 单元格样式
//        CellStyle cellStyle = workbook.createCellStyle();
//        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//
//        Font cellFont = workbook.createFont();
//        cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        cellStyle.setFont(cellFont);
//        // 生成一个(带标题)表格
//        SXSSFSheet sheet = workbook.createSheet();
//        //设置列宽
//        int minBytes = colWidth<DEFAULT_COLOUMN_WIDTH?DEFAULT_COLOUMN_WIDTH:colWidth;//至少字节数
//        int[] arrColWidth = new int[headMap.size()];
//        // 产生表格标题行,以及设置列宽
//        String[] properties = new String[headMap.size()];
//        String[] headers = new String[headMap.size()];
//        int ii = 0;
//        for (Iterator<String> iter = headMap.keySet().iterator(); iter
//                .hasNext();) {
//            String fieldName = iter.next();
//
//            properties[ii] = fieldName;
//            headers[ii] = headMap.get(fieldName);
//
//            int bytes = fieldName.getBytes().length;
//            arrColWidth[ii] =  bytes < minBytes ? minBytes : bytes;
//            sheet.setColumnWidth(ii,arrColWidth[ii]*256);
//            ii++;
//        }
//        // 遍历集合数据，产生数据行
//        int rowIndex = 0;
//        for (Object obj : jsonArray) {
//            if(rowIndex == 65535 || rowIndex == 0){
//                if ( rowIndex != 0 ) sheet = workbook.createSheet();//如果数据超过了，则在第二页显示
//
//                SXSSFRow titleRow = sheet.createRow(0);//表头 rowIndex=0
//                titleRow.createCell(0).setCellValue(title);
//                titleRow.getCell(0).setCellStyle(titleStyle);
//                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headMap.size() - 1));
//
//                SXSSFRow headerRow = sheet.createRow(1); //列头 rowIndex =1
//                for(int i=0;i<headers.length;i++)
//                {
//                    headerRow.createCell(i).setCellValue(headers[i]);
//                    headerRow.getCell(i).setCellStyle(headerStyle);
//
//                }
//                rowIndex = 2;//数据内容从 rowIndex=2开始
//            }
//            JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
//            SXSSFRow dataRow = sheet.createRow(rowIndex);
//            for (int i = 0; i < properties.length; i++)
//            {
//                SXSSFCell newCell = dataRow.createCell(i);
//
//                Object o =  jo.get(properties[i]);
//                String cellValue = "";
//                if(o==null) cellValue = "";
//                else if(o instanceof Date) cellValue = new SimpleDateFormat(datePattern).format(o);
//                else if(o instanceof Float || o instanceof Double)
//                    cellValue= new BigDecimal(o.toString()).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
//                else cellValue = o.toString();
//
//                newCell.setCellValue(cellValue);
//                newCell.setCellStyle(cellStyle);
//            }
//            rowIndex++;
//        }
//        // 自动调整宽度
//        /*for (int i = 0; i < headers.length; i++) {
//            sheet.autoSizeColumn(i);
//        }*/
//        try {
//            workbook.write(out);
//            workbook.close();
//            workbook.dispose();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//  //Web 导出excel
//    public static void downloadExcelFile(String title,Map<String,String> headMap,JSONArray ja,HttpServletResponse response){
//        try {
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            PoiUtil.exportExcelX(title,headMap,ja,null,0,os);
//            byte[] content = os.toByteArray();
//            InputStream is = new ByteArrayInputStream(content);
//            // 设置response参数，可以打开下载页面
//            response.reset();
//
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
//            response.setHeader("Content-Disposition", "attachment;filename="+ new String((title + ".xlsx").getBytes(), "iso-8859-1"));
//            response.setContentLength(content.length);
//            ServletOutputStream outputStream = response.getOutputStream();
//            BufferedInputStream bis = new BufferedInputStream(is);
//            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
//            byte[] buff = new byte[8192];
//            int bytesRead;
//            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
//                bos.write(buff, 0, bytesRead);
//
//            }
//            bis.close();
//            bos.close();
//            outputStream.flush();
//            outputStream.close();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void downloadExcel(HttpServletResponse response,String url) throws IOException{
//    	response.reset();
//   	 response.setContentType("application/x-download");
//   	 String file =url;
//   	 String[] filename =file.split("/");
//   	 String path=dowLoadExcelUrl;
//   	 path=path+file;
//   	 response.setContentType("application/x-download");
//   	 response.addHeader("Content-Disposition", "attachment;filename="+filename[filename.length-1]);
//   	 OutputStream outp = null;
//   	 java.io.FileInputStream in = null;
//   	 try {
//   	  outp = response.getOutputStream();
//   	  in = new java.io.FileInputStream(path);
//
//   	  byte[] b = new byte[1024];
//   	  int i = 0;
//
//   	  while ((i = in.read(b)) > 0) {
//   	   outp.write(b, 0, i);
//   	  }
//
//   	 } catch (Exception e) {
//   	  System.out.println("Error!");
//   	  e.printStackTrace();
//   	 } finally {
//   	  if (in != null) {
//   	   in.close();
//   	   in = null;
//   	  }
//   	 }
//    }
//    //写出文件到excel并上传到服务器
//    public static String excle(List<Poolquery> list,Map<String, String> typemap) {
//		String exclepath=dowLoadExcelUrl;
//		Calendar ca = Calendar.getInstance();
//		ca.setTime(new Date());
//		//long time=System.currentTimeMillis();
//		Date now = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//可以方便地修改日期格式
//		String time = String.valueOf(System.currentTimeMillis());
//		String path = exclepath + "\\" + ca.get(ca.YEAR) + "\\"
//		+ (ca.get(ca.MONTH) + 1) + "\\" + ca.get(ca.DAY_OF_MONTH);
//		String url="/"+ca.get(ca.YEAR) + "/"+ (ca.get(ca.MONTH) + 1) + "/" + ca.get(ca.DAY_OF_MONTH)+"/"+time+".xls";
//		File file = null;
//		file = new File(path);
//		if (!file.exists()) {
//			file.mkdirs();
//		}
//		exclepath = path+"\\"+time+".xls";
//		try  {
//			WritableWorkbook wb=Workbook.createWorkbook(new File(exclepath));
//			WritableSheet ws=wb.createSheet("智慧城市案卷",0);
//			Set fieldSet=Constants.EXCELMAP.keySet();
//			Iterator fieldItor = fieldSet.iterator();
//			int size=0;
//			ws.setColumnView(0,18);
//			ws.setColumnView(1,15);
//			ws.setColumnView(4,18);
//			ws.setColumnView(5,15);
//			ws.setColumnView(6,15);
//			ws.setColumnView(7,20);
//			ws.setColumnView(8,30);
//			ws.setColumnView(9,18);
//			ws.setColumnView(10,15);
//			ws.setColumnView(11,30);
//			while(fieldItor.hasNext()){
//				Label label1 = new Label(size, 0, (String) Constants.EXCELMAP.get(fieldItor.next()));
//	            ws.addCell(label1);
//	            size++;
//			}
//            if(list!=null && list.size()>0){
//            	for(int i=1;i<list.size()+1;i++){
//					/*String eventid = (String)list.get(i-1);*/
//					/*Event e = (Event) s.load(Event.class, eventid);     */
//					Poolquery e = list.get(i-1);
//            		int sizemini=0;
//            		Iterator fieldItor2 = fieldSet.iterator();
//            		while(fieldItor2.hasNext()){
//            			ws.addCell(new Label(sizemini, i, getEventAttributeForEXCEL((String)fieldItor2.next(), e,typemap)));
//            			sizemini++;
//            		}
//            	}
//            }
//            wb.write(); // 写入Exel工作表
//            wb.close(); // 关闭Excel工作薄对象
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return url;
//	}
//    public static  String getEventAttributeForEXCEL(String objKey, Poolquery eventobj,Map<String,String> typemap) {
//		String result = "";
//		if (objKey.equals(com.hesc.utils.Constants.EXCEL_TYPE_TASKCODE)) {
//			return eventobj.getTaskcode();
//		}else if (objKey.equals(com.hesc.utils.Constants.EXCEL_TYPE_SOURCE)) {
//			result = EventConstants.EVENTSRCMAP.get(eventobj.getSource()==null?"":eventobj.getSource());
//			return result;
//		} else if (objKey.equals(com.hesc.utils.Constants.EXCEL_TYPE_EVENTTYPE)) {
//			result = typemap.get(eventobj.getType1Id()==null?"":eventobj.getType1Id());
//			return result;
//		} else if (objKey.equals(com.hesc.utils.Constants.EXCEL_TYPE_MAINTYPE)) {
//			result = typemap.get(eventobj.getType2Id()==null?"":eventobj.getType2Id());
//			return result ;
//		} else if (objKey.equals(com.hesc.utils.Constants.EXCEL_TYPE_SUBTYPE)) {
//			result = typemap.get(eventobj.getType3Id()==null?"":eventobj.getType3Id());
//			return result;
//		} else if (objKey.equals(com.hesc.utils.Constants.EXCEL_TYPE_STREET)) {
//			/*return result = organizationService.selectById(eventobj.getStreetId()).getName();*/
//			return result = eventobj.getStreetId();
//		} else if (objKey.equals(com.hesc.utils.Constants.EXCEL_TYPE_COMMUITY)) {
//			/*return result = organizationService.selectById(eventobj.getCountryId()).getName();*/
//			return result = eventobj.getCountryId();
//		} else if (objKey.equals(com.hesc.utils.Constants.EXCEL_TYPE_GRID)) {
//			/*return result = organizationService.selectById(eventobj.getGridId()).getName();*/
//			return result = eventobj.getGridId();
//		} else if (objKey.equals(com.hesc.utils.Constants.EXCEL_TYPE_STATE)) {
//			result = EventConstants.EVENTSTATEMAP.get(eventobj.getState()==null?"":eventobj.getState());
//			return result ;
//		}else if (objKey.equals(com.hesc.utils.Constants.EXCEL_TYPE_ADDRESS)) {
//			return result = eventobj.getAddress();
//		}else if (objKey.equals(com.hesc.utils.Constants.EXCEL_TYPE_EVENTDESC)) {
//			return result = eventobj.getEventdesc();
//		}else if (objKey.equals(com.hesc.utils.Constants.EXCEL_TYPE_LASTBM)) {
//			return result = eventobj.getLastbm();
//		}
//		else {
//			return result = eventobj.getDealresult();
//		}
//	}
}
