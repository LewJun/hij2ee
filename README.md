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



