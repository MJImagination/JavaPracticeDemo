package Excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PoiWriteDemo {
    public static void main(String[] args) throws Exception {


        createExcel();
//        readExcel();


    }

    public static void createExcel() throws IOException {
        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet2");
        HSSFCellStyle style = workbook.createCellStyle(); // 样式对象


//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平

//        style.setFillBackgroundColor(HSSFColor.AQUA.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);    //设置颜色为红色
//        style.setFillBackgroundColor();
        //标题设置
        for (int row = 0; row < 1; row++) {
            //创建行
            HSSFRow rows = sheet.createRow(row);
            for (int col = 0; col < 10; col++) {
                //设置改列的宽度
                sheet.setColumnWidth(col, 20 * 256);
                //设置样式
                HSSFCell cell = rows.createCell(col);
                // 创建单元格 并 向工作表中添加数据
                cell.setCellStyle(style);
                cell.setCellValue("2标题" + row + col);
            }
        }
        //数据插入
        for (int row = 1; row <= 10; row++) {
            //创建行
            HSSFRow rows = sheet.createRow(row);
            for (int col = 0; col < 10; col++) {
                // 创建单元格 并 向工作表中添加数据
                rows.createCell(col).setCellValue("data" + row + col);
            }
        }

        File xlsFile = new File("E:\\poi.xls");
        FileOutputStream xlsStream = new FileOutputStream(xlsFile);
        workbook.write(xlsStream);
    }

    public static void readExcel() throws Exception {
        File xlsFile = new File("D:\\poi.xls");
        // 获得工作簿
        Workbook workbook = WorkbookFactory.create(xlsFile);
        // 获得工作表个数
        int sheetCount = workbook.getNumberOfSheets();
        // 遍历工作表
        for (int i = 0; i < sheetCount; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            // 获得行数
            int rows = sheet.getLastRowNum() + 1;
            // 获得列数，先获得一行，在得到改行列数
            Row tmp = sheet.getRow(0);
            if (tmp == null) {
                continue;
            }
            int cols = tmp.getPhysicalNumberOfCells();
            // 读取数据
            for (int row = 0; row < rows; row++) {
                Row r = sheet.getRow(row);
                for (int col = 0; col < cols; col++) {
                    System.out.printf("%10s", r.getCell(col).getStringCellValue());
                }
                System.out.println();
            }
        }
    }


    public static void createExcel2() throws IOException {
        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet2");
        HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平

        //起始行号，终止行号， 起始列号，终止列号
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));  //先合并第一行第1列和第二行第1列
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));  //合并第一行第2列和第二行第2列
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));  //合并第一行第3列和第二行第3列
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 6, 6));  //合并第一行第7列和第二行第7列
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 7, 7));  //合并第一行第8列和第二行第8列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 5));  //先合并第一行第4列和第1行第6列

        File xlsFile = new File("D:\\poi2.xls");
        FileOutputStream xlsStream = new FileOutputStream(xlsFile);
        workbook.write(xlsStream);
    }

    public static void createExcel3() throws IOException {
        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet2");
        HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平

        HSSFFont font_head = workbook.createFont();
        font_head.setFontHeightInPoints((short) 14);// 设置字体大小
        font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        style.setFont(font_head);

        //起始行号，终止行号， 起始列号，终止列号
        sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 5));  //先合并第一行第1列和第二行第1列

        HSSFRow rows = sheet.createRow(0);
        HSSFCell cell = rows.createCell(0);
        // 创建单元格 并 向工作表中添加数据
        cell.setCellStyle(style);
        cell.setCellValue("标题标题标题标题");

        rows.createCell(6).setCellValue("2");

        sheet.createRow(3).createCell(0).setCellValue("10");

        File xlsFile = new File("E:\\poi3.xls");
        FileOutputStream xlsStream = new FileOutputStream(xlsFile);
        workbook.write(xlsStream);
    }
}