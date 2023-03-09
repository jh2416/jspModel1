/*어디서나 접근가능하도록 싱글턴으로*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

public class MemberDao {

	private static MemberDao dao = null;
	
	private MemberDao() {		//생성자 만들어서 다시는  생성되지 못하도록
		DBConnection.initConnection();
	}
	
	public static MemberDao getInstance() {
		if(dao == null) {
			dao = new MemberDao();		//없으면 생성, 여기는 처음 한번만 들어옴
		}
		return dao;		
	}
	
	public boolean getId(String id) {		//id 확인
		
		String sql = " select id "			//id만 들어오면 됨
				+ "    from member "
				+ "    where id=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		boolean findid = false;				//id있으면 true
		
		try {								//syso부분 개발자들이 많이 해두는거(깃발꽂기)
			conn = DBConnection.getConnection();
			System.out.println("1/3 getId success");
				
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);			//id 값 1개 들어감
			System.out.println("2/3 getId success");
			
			rs = psmt.executeQuery();		//결과값
			System.out.println("3/3 getId success");
			
			if(rs.next()) {					//데이터가 있을때
				findid = true;				//true로 바꿔줘
			}			
			
		} catch (SQLException e) {	
			System.out.println("getId fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return findid;		
	}
	
	public boolean addMember(MemberDto dto) {
		
		String sql = " insert into member(id, pwd, name, email, auth) "
				+ "    values(?, ?, ?, ?, 3) "; 	/* 유저니까 3 */
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
				
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 addMember success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			System.out.println("2/3 addMember success");
			
			count = psmt.executeUpdate();		
			System.out.println("3/3 addMember success");
			
		} catch (SQLException e) {
			System.out.println("addMember fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		return count>0?true:false;
	}
	
	public MemberDto login(String id, String pwd) {
		
		String sql = " select id, name, email, auth "	//노출우려로 pwd는 안받음 pwd는 session으로
				+ "    from member "
				+ "    where id=? and pwd=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberDto mem = null;
				
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 login success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pwd);
			System.out.println("2/3 login success");
			
			rs = psmt.executeQuery();
			System.out.println("3/3 login success");
			
			if(rs.next()) {		//노출우려로 pwd는 안받음 pwd는 session으로
				String _id = rs.getString(1);
				String _name = rs.getString(2);
				String _email = rs.getString(3);
				int _auth = rs.getInt(4);
				
				mem = new MemberDto(_id, null, _name, _email, _auth);	//노출우려로 pwd는 안받음 pwd는 session으로
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return mem;
	}
	
}










