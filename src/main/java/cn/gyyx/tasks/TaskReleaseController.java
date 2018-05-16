package cn.gyyx.tasks;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import cn.gyyx.bean.ResultBean;
import cn.gyyx.server.ExcelService;

@Controller
public class TaskReleaseController {
	
	
	@Autowired
	private ExcelService excelService;

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/taskList")
	public String taskList() {
		return "taskList";
	}

	@RequestMapping("/taskupload")
	public String taskupload() {
		return "/releasepage";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean<Object> importExcel(@RequestParam(value = "excelFile", required = false) MultipartFile file,
			HttpServletRequest request) {
		 MultipartRequest multipartRequest=(MultipartRequest) request;
         MultipartFile excelFile=multipartRequest.getFile("excelFile");
         return excelService.readerExcelToDB(excelFile);
	}

}
