package edu.sqa.finalproject.poorstudentmis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.sqa.finalproject.poorstudentmis.entity.Student;
import edu.sqa.finalproject.poorstudentmis.entity.User;
import edu.sqa.finalproject.poorstudentmis.mapper.StudentMapper;
import edu.sqa.finalproject.poorstudentmis.mapper.UserMapper;

@Controller
public class StuInfoController {
	@Autowired
	private StudentMapper stuMapper;
	@Autowired
	private UserMapper userMapper;
	// 个人信息页面的服务 start
	@RequestMapping("stu_info")
	public ModelAndView showInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		System.out.println("u==" + u);
		Student stu = stuMapper.findById(u.getU_id());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("userInfo");
		mav.addObject("stu", stu);
		return mav;
	}

	@RequestMapping("modify_stu_info_front")
	public ModelAndView modifyInfo(Student newStu) {
		System.out.println(newStu.toString());
		// 1. 查看stu表中是否有该学生
		Student stu = stuMapper.findById(newStu.getS_id());
		// 2. 如果没有，插入该学生
		if (stu == null)
			stuMapper.insert(newStu);
		else {
			// 3. 如果有，把学生的所有信息改为当前Student对象中的数据
			stuMapper.modify(newStu);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("userInfo");
		mav.addObject("stu", newStu);
		return mav;
	}
	// 个人信息页面的服务 end

	// 后台管理修改学生信息的服务 start
	@ResponseBody
	@RequestMapping("show_stu")
	public Map<String, Object> showStu(String s_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Student stu = stuMapper.findById(s_id);
		map.put("stu", stu);
		return map;
	}
	@ResponseBody
	@RequestMapping("add_stu")
	public Map<String, Object> handleAddStu(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String s_id = request.getParameter("s_id");
		String s_name = request.getParameter("s_name");
		String s_nation = request.getParameter("s_nation");
		String s_sex = request.getParameter("s_sex");
		String s_birth = request.getParameter("s_birth");
		String s_major = request.getParameter("s_major");
		String s_phone = request.getParameter("s_phone");
		String s_address = request.getParameter("s_address");
		String income = request.getParameter("s_income");
		double s_income = 0;
		if (!income.trim().equals("")) 
			s_income = Double.parseDouble(income);
		String s_res = request.getParameter("s_res");
		String s_info = request.getParameter("s_info");
		String s_other = request.getParameter("s_other");
		System.out.println(s_income);

		Student s = stuMapper.findById(s_id);
		if (s != null) {
			map.put("message", "该学号已存在！请重新输入！");
			
		} else {
			stuMapper.insert(new Student(s_id, s_name, s_nation, s_sex, s_birth, s_major, s_phone, s_address, s_income, s_res, s_info, s_other));
			userMapper.insert(new User(s_id, s_name, "123456", 1));
			map.put("message", "添加成功！");
		}
		return map;
	}
	// 后台管理修改学生信息的服务 end
	@ResponseBody
	@RequestMapping("modify_stu_info_back")
	public Map<String, Object> handleModifyStu(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String s_id = request.getParameter("s_id");
		String s_name = request.getParameter("s_name");
		String s_nation = request.getParameter("s_nation");
		String s_sex = request.getParameter("s_sex");
		String s_birth = request.getParameter("s_birth");
		String s_major = request.getParameter("s_major");
		String s_phone = request.getParameter("s_phone");
		String s_address = request.getParameter("s_address");
		String income = request.getParameter("s_income");
		double s_income = 0;
		if (!income.trim().equals("")) 
			s_income = Double.parseDouble(income);
		String s_res = request.getParameter("s_res");
		String s_info = request.getParameter("s_info");
		String s_other = request.getParameter("s_other");
		stuMapper.modify(new Student(s_id, s_name, s_nation, s_sex, s_birth, s_major, s_phone, s_address, s_income, s_res, s_info, s_other));
		map.put("message", "修改成功！");
		return map;
	}
	@RequestMapping("del_all_stu")
	public String handleDelAllFund() {
		stuMapper.delAll();
		stuMapper.reset();
		return "redirect:/stu_manage";
	}
	@RequestMapping("del_stu")
	public String del(String s_id) {
		stuMapper.delById(s_id);
		return "redirect:/stu_manage";
	}
}
