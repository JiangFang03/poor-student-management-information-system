//package edu.sqa.finalproject.poorstudentmis.utils;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import edu.sqa.finalproject.poorstudentmis.entity.User;
//
//@WebFilter(filterName = "sessionFilter",urlPatterns = {"/*"})
//public class SessionFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        HttpSession session = request.getSession();
//		User u = (User)session.getAttribute("user");
//        if (u == null) {
//        	System.out.println("not login!!!");
//        }
//    }
//}
