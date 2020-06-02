package edu.sqa.finalproject.poorstudentmis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.sqa.finalproject.poorstudentmis.entity.User;

@Controller
public class FrontController {
	@RequestMapping(value = { "/", "home" })
	public String showHome(HttpServletRequest request, String isLogin, String isAdmin, ModelMap modelMap) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		System.out.println("u==" + u);
		
//		if (isAdmin == null || !isAdmin.equals("0"))
//			modelMap.addAttribute("pos", "display:none;");
//		if (isLogin == null)
//			modelMap.addAttribute("notLogin", "display:none;");
//		else {
//			modelMap.addAttribute("Login", "display:none");
//			modelMap.addAttribute("s_id", isLogin);
//		}

		if (u != null) { // 如果u不为空
			modelMap.addAttribute("Login", "display:none");
			if (u.getU_power() == 1) // 如果权限为1（普通用户），不显示后台
				modelMap.addAttribute("pos", "display:none;");
		} else { // 如果u为空， 不显示后台，及个人信息按钮
			modelMap.addAttribute("notLogin", "display:none;");
			modelMap.addAttribute("pos", "display:none;");
		}
		System.out.println(isLogin);
		System.out.println(isAdmin);
		return "index";
	}

	@RequestMapping("show_fund")
	public String showFund() {
		return "fund";
	}
}