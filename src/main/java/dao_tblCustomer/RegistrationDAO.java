package dao_tblCustomer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import utils.DBUtils;

public class RegistrationDAO implements Serializable{
	public boolean checkLogin(String username, String password) throws NamingException, SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = DBUtils.makeConnection();
			if (con != null) {
				String sql = "SELECT * FROM tblCustomer WHERE id = ? AND password = ?";
				pst = con.prepareStatement(sql);
				pst.setString(1, username);
				pst.setString(2, password);
				rs = pst.executeQuery();
				if (rs.next()) {
					return true;
				}
			}
			
		} finally {
			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		}
		return false;	
	}
}
