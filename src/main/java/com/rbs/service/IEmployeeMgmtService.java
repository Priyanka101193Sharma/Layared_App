package com.rbs.service;
import java.util.List;

import com.rbs.model.Employee;

public interface IEmployeeMgmtService {
	public List<Employee>fetchEmployeesByDesgs(String desg1,String desg2, String desg3)throws Exception;

}
