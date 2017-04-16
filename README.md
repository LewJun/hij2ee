# hij2ee
搭建基于maven的j2ee项目

[TOC]

## mvn maven-archetype-webapp
```
mvn archetype:generate -DgroupId=com.lewjun -DartifactId=hij2ee -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false -X -DarchetypeCatalog=local
```

## 集成spring
基于hij2ee集成spring
参照hij2se集成spring的方式
### 添加spring依赖

``` xml
<!-- spring frame start -->
<!-- 1 spring core start-->
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-core</artifactId>
	<version>${spring.version}</version>
	<exclusions>
		<!-- Exclude Commons Logging in favor of SLF4j -->
		<exclusion>
			<groupId>commons-logging</groupId>
			<artifactId>commons-logging</artifactId>
		</exclusion>
	</exclusions>
</dependency>

<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-context</artifactId>
	<version>${spring.version}</version>
</dependency>
<!-- 1 spring core end-->
<!-- spring frame end -->
```
上面排除了commons-logging，但还是要加入进去
``` xml
<dependency>
	<groupId>commons-logging</groupId>
	<artifactId>commons-logging</artifactId>
	<version>1.1.1</version>
</dependency>
```

### 配置spring.xml
创建src/main/resources/spring/spring.xml

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context-3.2.xsd">
	
	<!-- 自动扫描(自动注入)，扫描com.lewjun这个包以及它的子包的所有使用@Service, @Repository注解标注的类 -->
    <context:component-scan base-package="com.lewjun" />
</beans>
```

### 编写测试类

``` java
@RunWith(SpringJUnit4ClassRunner.class)
// 配置了@ContextConfiguration注解并使用该注解的locations属性指明spring和配置文件之后，
@ContextConfiguration(locations = { "classpath:spring/spring.xml" })
public class SpringJunitTest {
	@Test
	public void testSpring() {
		System.out.println("只为测试spring是否配置成功");
	}
}
```
### run
运行成功

## 集成springmvc
### 添加springmvc相关依赖

``` xml
<!-- 3 spring mvc start -->
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-webmvc</artifactId>
	<version>${spring.version}</version>
</dependency>
<!-- 3 spring mvc end -->
```

### 配置spring-mvc.xml
添加src/main/resources/spring/spring-web.xml

``` xml
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context-3.2.xsd">
	<!-- 1.开启SpringMVC注解模式 -->
	<mvc:annotation-driven />
	<!-- 自动扫描该包，使SpringMVC认为包下用了@controller注解的类是控制器 -->
	<context:component-scan base-package="com.lewjun.controller" />
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/views/jsp/" />
		<property name="suffix" value=".jsp" />
	</bean>
</beans>
```
并在spring.xml文件中导入spring-web.xml

### 配置web.xml

``` xml 
<web-app>
	<display-name>spring-mvc</display-name>

	<servlet>
		<servlet-name>spring-mvc</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:spring/spring.xml</param-value>
		</init-param>
	</servlet>

	<servlet-mapping>
		<servlet-name>spring-mvc</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
</web-app>
``` 

### 添加HelloController

``` java 
@Controller
@RequestMapping("/hello")
public class HelloController {

	@RequestMapping(value = "/greet", method = RequestMethod.GET)
	public String greet() {
		return "/hello/greet";
	}
}
``` 

### 添加greet.jsp
添加/WEB-INF/views/jsp/hello/greet.jsp

``` html 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>greet</title>
</head>
<body>
hello world
</body>
</html>
```

### run
* 将程序发布到tomcat
* 启动tomcat
* 访问
> http://127.0.0.1:8080/hij2ee/hello/greet


### 映射静态资源 mvc:resources

不希望js,css,png等静态资源也被spring的过滤器处理
* 修改spring-web.xml, 添加如下配置
``` xml 
	<mvc:resources location="/assets/" mapping="/assets/**"/>
``` 

* 添加assets目录
/webapp/assets

* 添加hello/greet.js
/webapp/assets/hello/greet.js

``` js
console.log("hello world")
``` 

* 使用hello/greet.js
<script type="text/javascript" src="../assets/hello/greet.js"></script>

### 使用jstl

* 导入jstl依赖

``` xml
<!-- JSTL标签类用于视图页面使用，就能使用c:forEach等这样的指令了 -->
<dependency>
	<groupId>jstl</groupId>
	<artifactId>jstl</artifactId>
	<version>1.2</version>
</dependency>
``` 

* 使用指令

``` jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<p><c:forEach items="${username }" var="u" varStatus="status">${u}</c:forEach></p>
``` 

