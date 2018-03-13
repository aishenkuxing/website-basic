/* =============================================================
 * Created: [2016年3月21日] by Administrator
 * =============================================================
 *
 * Copyright 2014-2015 NetDragon Websoft Inc. All Rights Reserved
 *
 * =============================================================
 */

package com.cn.website.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import com.cn.website.common.entity.EnDataGrid;

/**
 * @author Lsx
 * @since
 */
public class ExpPoiExcel<T> {

    public void exportExcel(String title, Collection<T> dataset, OutputStream out) throws Exception {
        excelExport(title, null, dataset, out, "yyyy-MM-dd HH:mm:ss");
    }

    public void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out) throws Exception {
        excelExport(title, headers, dataset, out, "yyyy-MM-dd HH:mm:ss");
    }

    public void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern) throws Exception {
        excelExport(title, headers, dataset, out, pattern);
    }

    public void excelExport(String title,
                            List<String> headers,
                            List<HashMap<String, String>> dataset,
                            OutputStream out,
                            String pattern) throws Exception {

        // 声明一个工作薄
        HSSFWorkbook workBook = new HSSFWorkbook();

        // 生成一个表格
        HSSFSheet sheet = workBook.createSheet(title);

        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);

        // 四个参数分别是：起始行，起始列，结束行，结束列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.size() - 1));

        // 生成一个样式
        HSSFCellStyle styleTitle = workBook.createCellStyle();
       
        
        // 设置这些样式
        styleTitle.setBorderBottom(BorderStyle.THIN);
        styleTitle.setBorderLeft( BorderStyle.THIN);
        styleTitle.setBorderRight( BorderStyle.THIN);
        styleTitle.setBorderTop( BorderStyle.THIN);
        styleTitle.setAlignment( HorizontalAlignment.CENTER);
        styleTitle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 生成一个字体
        HSSFFont font = workBook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 14);// 设置字体大小
        // 把字体应用到当前的样式
        styleTitle.setFont(font);

        // 生成并设置另一个样式
        HSSFCellStyle styleHead = workBook.createCellStyle();
        styleHead.setBorderBottom(BorderStyle.THIN);
        styleHead.setBorderLeft(BorderStyle.THIN);
        styleHead.setBorderRight(BorderStyle.THIN);
        styleHead.setBorderTop(BorderStyle.THIN);
        styleHead.setAlignment(HorizontalAlignment.CENTER);
        styleHead.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成另一个字体
        HSSFFont font2 = workBook.createFont();
        font2.setFontName("黑体");
        font2.setFontHeightInPoints((short) 12);
        // 把字体应用到当前的样式
        styleHead.setFont(font2);

        HSSFCellStyle styleBody = workBook.createCellStyle();

        styleBody.setBorderBottom(BorderStyle.THIN);
        styleBody.setBorderLeft(BorderStyle.THIN);
        styleBody.setBorderRight(BorderStyle.THIN);
        styleBody.setBorderTop(BorderStyle.THIN);
        styleBody.setAlignment(HorizontalAlignment.CENTER);
        styleBody.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直

        HSSFFont font3 = workBook.createFont();
        font3.setFontHeightInPoints((short) 10);
        styleBody.setFont(font3);// 选择需要用到的字体格式

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) (40 * 20));
        for (short i = 0; i < headers.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(styleTitle);
            cell.setCellValue(title);
        }

        row = sheet.createRow(1);
        row.setHeight((short) (30 * 20));
        for (short i = 0; i < headers.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(styleHead);
            HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        // Iterator<HashMap<String, String>> it = dataset.iterator();
        int index = 2;
        for (Map<String, String> map : dataset) {
            row = sheet.createRow(index);
            row.setHeight((short) (25 * 20));

            Object[] fields = map.keySet().toArray();

            for (short i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(styleBody);
                String value = map.get(fields[i]);
                cell.setCellValue(value);
            }
            index++;
        }

        workBook.write(out);
    }


    public void excelExport(String firstTitle,
                            String secondTitle,
                            List<String> headers,
                            List<HashMap<String, String>> dataset,
                            OutputStream out,
                            String pattern) throws Exception {

        // 声明一个工作薄
        HSSFWorkbook workBook = new HSSFWorkbook();

        // 生成一个表格
        HSSFSheet sheet = workBook.createSheet(secondTitle);

        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);

        // 四个参数分别是：起始行，起始列，结束行，结束列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.size() - 1));

        // 生成一个样式
        HSSFCellStyle styleTitle = workBook.createCellStyle();

        // 设置这些样式
        styleTitle.setBorderBottom(BorderStyle.THIN);
        styleTitle.setBorderLeft(BorderStyle.THIN);
        styleTitle.setBorderRight(BorderStyle.THIN);
        styleTitle.setBorderTop(BorderStyle.THIN);
        styleTitle.setAlignment(HorizontalAlignment.CENTER);
        styleTitle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 生成一个字体
        HSSFFont font = workBook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 14);// 设置字体大小
        // 把字体应用到当前的样式
        styleTitle.setFont(font);

        // 生成并设置另一个样式
        HSSFCellStyle styleHead = workBook.createCellStyle();
        styleHead.setBorderBottom(BorderStyle.THIN);
        styleHead.setBorderLeft(BorderStyle.THIN);
        styleHead.setBorderRight(BorderStyle.THIN);
        styleHead.setBorderTop(BorderStyle.THIN);
        styleHead.setAlignment(HorizontalAlignment.CENTER);
        styleHead.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成另一个字体
        HSSFFont font2 = workBook.createFont();
        font2.setFontName("黑体");
        font2.setFontHeightInPoints((short) 12);
        // 把字体应用到当前的样式
        styleHead.setFont(font2);

        HSSFCellStyle styleBody = workBook.createCellStyle();

        styleBody.setBorderBottom(BorderStyle.THIN);
        styleBody.setBorderLeft(BorderStyle.THIN);
        styleBody.setBorderRight(BorderStyle.THIN);
        styleBody.setBorderTop(BorderStyle.THIN);
        styleBody.setAlignment(HorizontalAlignment.CENTER);
        styleBody.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直

        HSSFFont font3 = workBook.createFont();
        font3.setFontHeightInPoints((short) 10);
        styleBody.setFont(font3);// 选择需要用到的字体格式

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) (40 * 20));
        for (short i = 0; i < headers.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(styleTitle);
            cell.setCellValue(firstTitle);
        }

        HSSFRow SecondRow = sheet.createRow(1);
        row.setHeight((short) (40 * 20));
        // row.set
        for (short i = 0; i < headers.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(styleTitle);
            cell.setCellValue(secondTitle);
        }

        row = sheet.createRow(2);
        row.setHeight((short) (30 * 20));
        for (short i = 0; i < headers.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(styleHead);
            HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        // Iterator<HashMap<String, String>> it = dataset.iterator();
        int index = 3;
        for (Map<String, String> map : dataset) {
            row = sheet.createRow(index);
            row.setHeight((short) (25 * 20));

            Object[] fields = map.keySet().toArray();

            for (short i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(styleBody);
                String value = map.get(fields[i]);
                cell.setCellValue(value);
            }
            index++;
        }

        workBook.write(out);

    }

    public void excelExport(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern) throws Exception {

        // 声明一个工作薄
        HSSFWorkbook workBook = new HSSFWorkbook();

        // 生成一个表格
        HSSFSheet sheet = workBook.createSheet(title);

        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);

        // 四个参数分别是：起始行，起始列，结束行，结束列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));

        // 生成一个样式
        HSSFCellStyle styleTitle = workBook.createCellStyle();

        // 设置这些样式
        styleTitle.setBorderBottom(BorderStyle.THIN);
        styleTitle.setBorderLeft(BorderStyle.THIN);
        styleTitle.setBorderRight(BorderStyle.THIN);
        styleTitle.setBorderTop(BorderStyle.THIN);
        styleTitle.setAlignment(HorizontalAlignment.CENTER);
        styleTitle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 生成一个字体
        HSSFFont font = workBook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 14);// 设置字体大小
        // 把字体应用到当前的样式
        styleTitle.setFont(font);

        // 生成并设置另一个样式
        HSSFCellStyle styleHead = workBook.createCellStyle();
        styleHead.setBorderBottom(BorderStyle.THIN);
        styleHead.setBorderLeft(BorderStyle.THIN);
        styleHead.setBorderRight(BorderStyle.THIN);
        styleHead.setBorderTop(BorderStyle.THIN);
        styleHead.setAlignment(HorizontalAlignment.CENTER);
        styleHead.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成另一个字体
        HSSFFont font2 = workBook.createFont();
        font2.setFontName("黑体");
        font2.setFontHeightInPoints((short) 12);
        // 把字体应用到当前的样式
        styleHead.setFont(font2);

        HSSFCellStyle styleBody = workBook.createCellStyle();

        styleBody.setBorderBottom(BorderStyle.THIN);
        styleBody.setBorderLeft(BorderStyle.THIN);
        styleBody.setBorderRight(BorderStyle.THIN);
        styleBody.setBorderTop(BorderStyle.THIN);
        styleBody.setAlignment(HorizontalAlignment.CENTER);
        styleBody.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直

        HSSFFont font3 = workBook.createFont();
        font3.setFontHeightInPoints((short) 10);
        styleBody.setFont(font3);// 选择需要用到的字体格式

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) (40 * 20));
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(styleTitle);
            cell.setCellValue(title);
        }

        row = sheet.createRow(1);
        row.setHeight((short) (30 * 20));
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(styleHead);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 1;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            row.setHeight((short) (25 * 20));
            T t = (T) it.next();

            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = t.getClass().getDeclaredFields();

            // 从第二个字段开始过滤掉serialVersionUID字段
            for (short i = 1; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i - 1);
                cell.setCellStyle(styleBody);
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Class tCls = t.getClass();
                Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                Object value = getMethod.invoke(t, new Object[]{});

                if (value != null) {
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        textValue = value.toString();
                    }
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            cell.setCellValue(textValue);
                        }
                    }
                }

            }
        }

        workBook.write(out);

    }

    /**
     * 生成Excel
     *
     * @param excelSheets 工作簿数据
     * @param out         数据输出流
     */
    public void excelExport(List<ExcelSheet> excelSheets, OutputStream out) throws IOException {

        // 声明一个工作薄
        HSSFWorkbook workBook = new HSSFWorkbook();

        for (ExcelSheet sheetInfo : excelSheets) {

            String title = sheetInfo.title;
            List<EnDataGrid> headers = sheetInfo.headers;
            List<Map<String, String>> dataList = sheetInfo.mapDataList;

            // 生成一个表格
            HSSFSheet sheet = workBook.createSheet(sheetInfo.title);

            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth(15);

            // 四个参数分别是：起始行，起始列，结束行，结束列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.size() - 1));

            // 生成一个样式
            HSSFCellStyle styleTitle = workBook.createCellStyle();

            // 设置这些样式
            styleTitle.setBorderBottom(BorderStyle.THIN);
            styleTitle.setBorderLeft(BorderStyle.THIN);
            styleTitle.setBorderRight(BorderStyle.THIN);
            styleTitle.setBorderTop(BorderStyle.THIN);
            styleTitle.setAlignment(HorizontalAlignment.CENTER);
            styleTitle.setVerticalAlignment(VerticalAlignment.CENTER);

            // 生成一个字体
            HSSFFont font = workBook.createFont();
            font.setFontName("黑体");
            font.setFontHeightInPoints((short) 14);// 设置字体大小
            // 把字体应用到当前的样式
            styleTitle.setFont(font);

            // 生成并设置另一个样式
            HSSFCellStyle styleHead = workBook.createCellStyle();
            styleHead.setBorderBottom(BorderStyle.THIN);
            styleHead.setBorderLeft(BorderStyle.THIN);
            styleHead.setBorderRight(BorderStyle.THIN);
            styleHead.setBorderTop(BorderStyle.THIN);
            styleHead.setAlignment(HorizontalAlignment.CENTER);
            styleHead.setVerticalAlignment(VerticalAlignment.CENTER);
            // 生成另一个字体
            HSSFFont font2 = workBook.createFont();
            font2.setFontName("黑体");
            font2.setFontHeightInPoints((short) 12);
            // 把字体应用到当前的样式
            styleHead.setFont(font2);

            HSSFCellStyle styleBody = workBook.createCellStyle();

            styleBody.setBorderBottom(BorderStyle.THIN);
            styleBody.setBorderLeft(BorderStyle.THIN);
            styleBody.setBorderRight(BorderStyle.THIN);
            styleBody.setBorderTop(BorderStyle.THIN);
            styleBody.setAlignment(HorizontalAlignment.CENTER);
            styleBody.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直

            HSSFFont font3 = workBook.createFont();
            font3.setFontHeightInPoints((short) 10);
            styleBody.setFont(font3);// 选择需要用到的字体格式

            // 产生表格标题行
            HSSFRow row = sheet.createRow(0);
            row.setHeight((short) (40 * 20));
            for (short i = 0; i < headers.size(); i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(styleTitle);
                cell.setCellValue(title);
            }

            row = sheet.createRow(1);
            row.setHeight((short) (30 * 20));
            for (short i = 0; i < headers.size(); i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(styleHead);
                //取字段配置的中文名称
                HSSFRichTextString text = new HSSFRichTextString(headers.get(i).getStitleCn());
                cell.setCellValue(text);
            }

            // 遍历集合数据，产生数据行
            int index = 2;
            if (dataList != null) {
                for (Map<String, String> map : dataList) {
                    row = sheet.createRow(index);
                    row.setHeight((short) (25 * 20));
                    //需要按照标题的顺序写
                    for (short i = 0; i < headers.size(); i++) {
                        HSSFCell cell = row.createCell(i);
                        cell.setCellStyle(styleBody);
                        String value = String.valueOf(map.get(headers.get(i).getSfieldName()));
                        cell.setCellValue(value.equals("null") ? "" : value);
                    }
                    index++;
                }
            }
        }
        //写入输出流
        workBook.write(out);
    }


    /**
     * 工作簿的数据结构
     */
    public static class ExcelSheet {

        /**
         * 表格选项卡标题
         */
        public String title;

        /**
         * 表格标题
         */
        public List<EnDataGrid> headers;

        /**
         * 表格数据(主表查询用的是这个字段信息)
         */
        public List<Map<String, String>> mapDataList;

        public ExcelSheet(String title, List<EnDataGrid> headers, List<Map<String, String>> dataSet) {
            this.title = title;
            this.headers = headers;
            this.mapDataList = dataSet;
        }
    }
}


