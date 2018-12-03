package org.woo.dubbo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hello")
public class DemoController {
	@Resource
	private IControlService controlService;

	@RequestMapping("/sayhello.do")
	@ResponseBody
	public String hello(String name) {
		return controlService.sayHello(null);
	}

}
