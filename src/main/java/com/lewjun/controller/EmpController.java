/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lewjun.bean.Emp;

/**
 * 
 * @author LewJun
 * @version $Id: EmpController.java, v 0.1 2017年4月17日 上午11:12:09 LewJun Exp $
 */
@Controller
@RequestMapping("/emp")
public class EmpController extends ApplicationBaseController {
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("emp/index");
        Emp emp = new Emp();
        emp.setDeptno(23);
        emp.setEmpno(72);
        mav.addObject(emp);
        return mav;
    }
}
