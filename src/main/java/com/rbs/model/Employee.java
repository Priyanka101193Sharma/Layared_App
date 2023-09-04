package com.rbs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private Integer eno;
	private String ename;
	private String desg;
	private Double salary;
	private Integer deptno;

}

