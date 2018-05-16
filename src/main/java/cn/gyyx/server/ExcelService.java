package cn.gyyx.server;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import cn.gyyx.bean.ResultBean;

public interface ExcelService {

	SXSSFWorkbook export(List<Map<String, Object>> list, String[] excelHeader, String sheetName);

	ResultBean<Object> readerExcelToDB(MultipartFile excelFile);
}
