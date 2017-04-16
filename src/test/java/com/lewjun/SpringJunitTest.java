/**
 * Sunnysoft.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.lewjun;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author LewJun
 * @version $Id: SpringJunitTest.java, v 0.1 2017年4月13日 下午2:26:50 LewJun Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 配置了@ContextConfiguration注解并使用该注解的locations属性指明spring和配置文件之后，
@ContextConfiguration(locations = { "classpath:spring/spring.xml" })
public class SpringJunitTest {
	@Test
	public void testSpring() {
		System.out.println("只为测试spring是否配置成功");
	}
}
