package system.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import system.service.UserService;

@Controller
@RequestMapping("/api")
public class RootHandler {
	@Autowired
	protected UserService us;
	@Autowired
	protected HttpServletRequest request;
	
}
