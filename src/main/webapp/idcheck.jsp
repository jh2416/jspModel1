<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%
	String id = request.getParameter("id");
	System.out.println("id:" + id); 		//잘 넘어왔는지 확인

	MemberDao dao = MemberDao.getInstance();
	boolean b = dao.getId(id);
	
	if(b == true){	// id 있음
		out.println("NO");
	}else{
		out.println("YES");		//사용할 수 있는 id면 YES보내줌
	} 
%>