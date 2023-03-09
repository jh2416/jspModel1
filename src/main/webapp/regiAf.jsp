<%@page import="dto.MemberDto"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");			//sql에서 한글 깨지는거 해결, DB에 넣는것들은 먼저 설정해줄것!

	String id = request.getParameter("id");			//post기에 보이지는 않음
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	
	// back-end
	MemberDao dao = MemberDao.getInstance();
	
	MemberDto dto = new MemberDto(id, pwd, name, email, 0);
	boolean isS = dao.addMember(dto);
	if(isS){
		%>    
			<script type="text/javascript">
			alert("성공적으로 가입되었습니다");
			location.href = "login.jsp";		/* 로그인화면으로 이동 */
			</script>
		<%
	}else{		
		%>
			<script type="text/javascript">
			alert("다시 가입해 주십시오");
			location.href = "regi.jsp";			/* 회원가입창으로 이동 */
			</script>	
		<%
	}
%>





