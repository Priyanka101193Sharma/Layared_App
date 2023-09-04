package com.rbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rbs.model.Employee;

@Repository("empDAO")
public class EmployeeDAOImpl implements IEmployeeDAO {
	private static final String GET_EMPS_BY_DESGS="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE JOB IN(?,?,?)ORDER BY JOB";
	@Autowired
	private DataSource ds;
	
	@Override
	public List<Employee>getEmployeesByDesgs(String desg1,String desg2,String desg3)throws Exception{
		System.out.println("EmployeeDAOImpl.getEmployeeByDesgs()::ds class name::"+ds.getClass());
		List<Employee>listEmps=null;
		//get piiled jdbc con object
		try(Connection con=ds.getConnection();
				//create PreparedStatement obj
				PreparedStatement ps=con.prepareStatement(GET_EMPS_BY_DESGS)){
			//set values to the query params
			ps.setString(1, desg1);
			ps.setString(2,desg2);
			ps.setString(3, desg3);
			//execute the Query
			try(ResultSet rs=ps.executeQuery()){
				//copy the records of ResultSet obj to List<Employee>obj
				listEmps=new ArrayList();
				while(rs.next()) {
					//copy each record of RS to Employee obj
					Employee emp=new Employee();
					emp.setEno(rs.getInt(1));
					emp.setEname(rs.getString(2));
					emp.setDesg(rs.getString(3));
					emp.setSalary(rs.getDouble(4));
					emp.setDeptno(rs.getInt(5));
					//add Employee object to List Collection
					listEmps.add(emp);
			
				}//while
			}//try 2 with resource
		}//try1 with resource
		catch(SQLException se) {
			se.printStackTrace();
			throw se;//exception propagation
		}//catch
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return listEmps;
		
	}//method

	 
}//class
