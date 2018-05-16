package cn.gyyx.tasks;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gyyx.bean.ResultBeanNet;


@Controller
public class TestController {

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResultBeanNet<Object>> sign (HttpServletRequest req){
		
		Map<String, Object> map = new HashMap<>();
		map.put("account", "AAAAAAAA");
		map.put("phone_no", "12345678");
		map.put("user_id", 123);
		return new ResponseEntity<>(new ResultBeanNet<Object>(true,"","", map),HttpStatus.OK);
	}
}
