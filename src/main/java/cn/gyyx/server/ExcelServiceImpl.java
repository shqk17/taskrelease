package cn.gyyx.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.gyyx.bean.ExcelDataBean;
import cn.gyyx.bean.ResultBean;
import cn.gyyx.dao.ExcelMapper;

@Component
public class ExcelServiceImpl implements ExcelService {

	@Autowired
	private ExcelMapper excelDao;

	@Override
	public SXSSFWorkbook export(List<Map<String, Object>> list, String[] headers, String sheetName) {

		SXSSFWorkbook wb = new SXSSFWorkbook();
		Sheet sheet = wb.createSheet(sheetName);
		sheet.setDefaultColumnWidth(15);
		sheet.setDefaultRowHeight((short) (256));
		CellStyle style = wb.createCellStyle();
		Row row = sheet.createRow((int) 0);
		// 设置头
		for (int i = 0; i < headers.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(style);
			XSSFRichTextString text = new XSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		// 拍入数据
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			row = sheet.createRow(i + 1);
			// 拿到第一个数据
			int j = 0;
			for (String field : headers) {
				Cell cell = row.createCell(j);
				// 遍历字段进行顺序赋值
				Object value = map.get(field);
				// 判断值的类型后进行强制类型转换
				String textValue = null;
				if (value instanceof Date) {
					Date date = (Date) value;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh");
					textValue = sdf.format(date);
				} else if (value != null) {
					// 其它数据类型都当作字符串简单处理
					textValue = value.toString();
				} else {
					cell.setCellValue("0");
					j++;
				}
				// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
				if (textValue != null) {
					Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
					Matcher matcher = p.matcher(textValue);
					if (matcher.matches()) {
						// 是数字当作double处理
						cell.setCellValue(Double.parseDouble(textValue));
						j++;
					} else {
						XSSFRichTextString richString = new XSSFRichTextString(textValue);
						cell.setCellValue(richString);
						j++;
					}
				}
			}
		}
		return wb;

	}

	@Override
	public ResultBean<Object> readerExcelToDB(MultipartFile excelFile) {
		// ExcelDataBean
		try {
			XSSFWorkbook work = new XSSFWorkbook(excelFile.getInputStream());
			List<ExcelDataBean> excelList = new ArrayList<>();
			// 循环所有sheet
			for (int i = 0; i < work.getNumberOfSheets(); i++) {
				// 得到某一个sheet
				XSSFSheet sheet = work.getSheetAt(i);
				int d = sheet.getRow(1).getLastCellNum();
				System.out.println("总行数为："+ sheet.getLastRowNum());
				for (int j = 0; j <= sheet.getLastRowNum(); j++) {
					ExcelDataBean excelDataBean = new ExcelDataBean();
					excelDataBean.setSheetName(sheet.getSheetName());
					excelDataBean.setColumnNum(d);
					XSSFRow row = sheet.getRow(j);
					StringBuilder cellStr = new StringBuilder();
					for (int c = 0; c <= d; c++) {
						try {
							cellStr.append(row.getCell(c) == null ? "" : row.getCell(c)).append("&");
						} catch (Exception e) {
							System.out.println("读写单元格发生异常：当前行号为:" + j + "当前列为:" + c + "异常为："+ e);
							continue;
						}
					}
					if (!StringUtils.isEmpty(cellStr)) {
						excelDataBean.setLineData(cellStr.toString());
					}
					excelList.add(excelDataBean);
				}
				// 得到了表里的所有行数据，插入数据库中
				excelDao.saveAll(excelList);
				return new ResultBean<>(true, "插入成功", null);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResultBean<>(false, "插入失败", null);
	}

}
