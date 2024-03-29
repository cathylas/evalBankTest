package co.simplon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import co.simplon.entities.Operation;

public class OperationDao extends Dao<Operation> {
	
	@Override
	public Operation find(int id) {
		String str = "select * from T_Operations where NumOp=?";
		PreparedStatement ps;
		Operation compte = null;
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1,id);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()){
				compte = new Operation(resultSet.getInt(1),resultSet.getDouble(2),resultSet.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return compte;
	}

	@Override
	public boolean create(Operation obj) {
		String str = "INSERT INTO T_Operations (NumOp,Amount,NumCt) VALUES (?, ? ,? ,?);";
		PreparedStatement ps;
		boolean ok = false;
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1, obj.getNumOp());
			ps.setDouble(2,obj.getAmount());
			ps.setInt(3, obj.getNumAt());
			ps.executeQuery();
			ok = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public boolean update(Operation obj) {		
		String str = " update T_Operations set Amount=? where NumOp=?;";		
		PreparedStatement ps;
		boolean ok = false;
		try {
			ps = connection.prepareStatement(str);
			ps.setDouble(1,obj.getAmount());
			ps.setInt(2,obj.getNumOp());
			int row = ps.executeUpdate();
			if(row > 0)	ok = true;			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return ok;
	}

	@Override
	public boolean delete(Operation obj) {
		String str = "delete from T_Operations where NumAt=?;";	
		PreparedStatement ps;
		boolean ok = false;
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1,obj.getNumOp());
			int row = ps.executeUpdate();
			if(row > 0)	ok = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}

}
