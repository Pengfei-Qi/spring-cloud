# SpringCloud教程

## 一. 搭建教程

### 1. 参考资料

> 版本信息: 
>
> ​	SpringCloud: Hoxton.SR9**
>
> ​	SpringBoot:   **2.3.5.RELEASE**

==参考链接:==

1. 官网: [Spring Cloud](https://spring.io/projects/spring-cloud)
2. 中文参考资料: [Spring Cloud Greenwich 中文文档 参考手册 中文版](https://www.springcloud.cc/spring-cloud-greenwich.html)
3. `版本参考信息`-1:  [官网介绍](https://spring.io/blog/2020/11/10/spring-cloud-hoxton-sr9-has-been-released)
4. `版本参考信息`-2: [Spring Cloud 官方文档](https://docs.spring.io/spring-cloud/docs/Hoxton.SR9/reference/html/)

![](https://s3.bmp.ovh/imgs/2022/04/18/78fe59abb8eae31a.jpg)



## 二. 基础搭建

> info: 数据库脚本
>
> ```sql
> create table company_tbl
> (
> 	id int auto_increment
> 		primary key,
> 	name varchar(30) null,
> 	count int(10) null,
> 	address varchar(64) null,
> 	db_source varchar(64) null
> );
> ```

### 2.1 parent

#### 2.1.1  模块截图

![模块截图](https://s3.bmp.ovh/imgs/2022/04/18/3d7dc8e707ff0945.jpg)

1. 父包下只有一个`pom.xml`文件, 提供项目管理
2. `spring-cloud-api`: 基础依赖包
3. `spring-cloud-provider-company-*`: provider集群
4. `spring-cloud-consumer-80`: 消费者
5. `spring-cloud-eureka-*`: Spring Cloud服务注册与发现组件集群

#### 2.1.2 pom文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.fei</groupId>
    <artifactId>springCloud</artifactId>
    <version>1.0-SNAPSHOT</version>

    <packaging>pom</packaging>

    <modules>
        <module>spring-cloud-api</module>
        <module>spring-cloud-consumer-80</module>
        <module>spring-cloud-provider-company-8001</module>
        <module>spring-cloud-eureka-01</module>
        <module>spring-cloud-eureka-02</module>
        <module>spring-cloud-eureka-03</module>
        <module>spring-cloud-provider-company-8002</module>
        <module>spring-cloud-provider-company-8003</module>
    </modules>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <springcloud.denpency.version>Hoxton.SR9</springcloud.denpency.version>
        <springboot.version>2.3.5.RELEASE</springboot.version>
        <durid.version>1.2.8</durid.version>
        <lombok.version>1.18.22</lombok.version>
        <mybatis.spring.version>2.1.4</mybatis.spring.version>
        <mysql-connect.version>8.0.28</mysql-connect.version>
        <spring.cloud.version>2.2.6.RELEASE</spring.cloud.version>
        <jackson.version>2.13.1</jackson.version>
        <gson.version>2.8.5</gson.version>
    </properties>

    <!--配置dependencyManagement-->
    <dependencyManagement>
        <dependencies>
            <!--spring-cloud-dependencies -->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${springcloud.denpency.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!-- springBoot-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${springboot.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter</artifactId>
                <version>${springboot.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <version>${springboot.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-jdbc</artifactId>
                <version>${springboot.version}</version>
            </dependency>
            <!--mysql数据库链接-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid-spring-boot-starter</artifactId>
                <version>${durid.version}</version>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>${mybatis.spring.version}</version>
            </dependency>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql-connect.version}</version>
            </dependency>
            <!--test-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-test</artifactId>
                <version>${springboot.version}</version>
            </dependency>
            <!--jackson相关-->
            <dependency>
                <groupId>com.fasterxml.jackson.core</groupId>
                <artifactId>jackson-core</artifactId>
                <version>${jackson.version}</version>
            </dependency>
            <dependency>
                <groupId>com.fasterxml.jackson.core</groupId>
                <artifactId>jackson-databind</artifactId>
                <version>${jackson.version}</version>
            </dependency>
            <dependency>
                <groupId>com.fasterxml.jackson.core</groupId>
                <artifactId>jackson-annotations</artifactId>
                <version>${jackson.version}</version>
            </dependency>
            <dependency>
                <groupId>com.google.code.gson</groupId>
                <artifactId>gson</artifactId>
                <version>${gson.version}</version>
                <scope>provided</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```

### 2.2  spring-cloud-api

#### 2.2.1 pom文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springCloud</artifactId>
        <groupId>org.fei</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-cloud-api</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <!--lombok相关-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>

</project>
```

#### 2.2.2 生成pojo类

```java
package com.fei.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/16
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CompanyTbl implements Serializable {
    private static final long serialVersionUID = 327190059566300566L;
    private int id;
    private String name;
    private Integer count;
    private String address;
    private String dbSource;
}
```

### 2.3. spring-cloud-provider

> info: 不含集群

#### 2.3.1 pom文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springCloud</artifactId>
        <groupId>org.fei</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-cloud-provider-company-8001</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <!--引入公共包-->
        <dependency>
            <groupId>org.fei</groupId>
            <artifactId>spring-cloud-api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <!--spring-boot 相关-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>slf4j-api</artifactId>
                    <groupId>org.slf4j</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-test</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>

        <!--数据库相关-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>spring-boot-starter-jdbc</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
                <exclusion>
                    <artifactId>spring-boot-autoconfigure</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
</project>
```

#### 2.3.2  yml 文件

> info: 数据库连接信息需自定义配置

```yaml
server:
  port: 8001

dbserver:
  mysql:
    host: localhost
    username: root
    password: 123456
    port: 30306
    databases: db_test

# mybatis相关
mybatis:
  type-aliases-package: com.fei.springcloud.pojo.CompanyTbl
  mapper-locations: classpath:mybatis/mapper/*.xml
  config-location: classpath:mybatis/mybatis-config.xml


spring:
  application:
    name: spring-cloud-provider
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      username: ${dbserver.mysql.username}
      password: ${dbserver.mysql.password}
      url: jdbc:mysql://${dbserver.mysql.host}:${dbserver.mysql.port}/${dbserver.mysql.databases}?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC
      #监控统计拦截的filters
      filters: stat
      #配置初始化大小/最小/最大
      initial-size: 1
      min-idle: 1
      max-active: 20
      #获取连接等待超时时间
      max-wait: 60000
      #间隔多久进行一次检测，检测需要关闭的空闲连接
      time-between-eviction-runs-millis: 60000
      #一个连接在池中最小生存的时间
      min-evictable-idle-time-millis: 300000
      validation-query: SELECT 'x'
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false
      #打开PSCache，并指定每个连接上PSCache的大小。oracle设为true，mysql设为false。分库分表较多推荐设置为false
      pool-prepared-statements: false
      max-pool-prepared-statement-per-connection-size: 20
```

#### 2.3.3 mapper.java

```java
package com.fei.springcloud.mapper;

import com.fei.springcloud.pojo.CompanyTbl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/17
 * @version: 1.0
 */
@Mapper
@Component
public interface CompanyTblMapper {

    int insert(CompanyTbl companyTbl);

    int delete(Integer id);

    int update(CompanyTbl companyTbl);

    CompanyTbl selectOneById(Integer id);

    List<CompanyTbl> selectAll();
}
```

#### 2.3.4 mapper.xml

1. 位置:

   ![mapper文件位置](https://s3.bmp.ovh/imgs/2022/04/18/3aace66ae8c8bacb.png)

2. 文件内容

   ```xml
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <mapper namespace="com.fei.springcloud.mapper.CompanyTblMapper">
       <resultMap id="resultData" type="com.fei.springcloud.pojo.CompanyTbl">
           <result property="id" column="id"/>
           <result property="address" column="address"/>
           <result property="count" column="count"/>
           <result property="name" column="name"/>
           <result property="dbSource" column="db_source"/>
       </resultMap>
       <sql id="tbl_name" >company_tbl</sql>
       <sql id="column_list">id,name, count, address, db_source</sql>
       <insert id="insert" >
           insert into <include refid="tbl_name"/> (name, count, address, db_source)
           VALUES (#{name},#{count},#{address},DATABASE());
       </insert>
       <update id="update">
           update <include refid="tbl_name"/> set name = #{name} , count = #{count} ,address = #{address},db_source = #{dbSource} where id = #{id}
       </update>
       <delete id="delete">
           delete from  <include refid="tbl_name"/> where id = #{id}
       </delete>
       <select id="selectOneById" resultMap="resultData">
           select <include refid="column_list"/> from <include refid="tbl_name"/> where id = #{id}
       </select>
       <select id="selectAll" resultMap="resultData">
           select  <include refid="column_list"/> from <include refid="tbl_name"/>
       </select>
   </mapper>
   ```

#### 2.3.5 mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <setting name="cacheEnabled" value="false"/>
    </settings>
</configuration>
```

#### 2.3.6 service

1. `interface`:

   ```java
   package com.fei.springcloud.service;
   
   import com.fei.springcloud.pojo.CompanyTbl;
   
   import java.util.List;
   
   /**
    * @description:
    * @author: qpf
    * @date: 2022/4/17
    * @version: 1.0
    */
   public interface CompanyService {
       int insert(CompanyTbl companyTbl);
   
       int delete(Integer id);
   
       int update(CompanyTbl companyTbl);
   
       CompanyTbl selectOneById(Integer id);
   
       List<CompanyTbl> selectAll();
   }
   ```

   

2. `impl`

   ```java
   package com.fei.springcloud.service.impl;
   
   import com.fei.springcloud.mapper.CompanyTblMapper;
   import com.fei.springcloud.pojo.CompanyTbl;
   import com.fei.springcloud.service.CompanyService;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.stereotype.Service;
   
   import java.util.List;
   
   /**
    * @description:
    * @author: qpf
    * @date: 2022/4/17
    * @version: 1.0
    */
   @Service
   public class CompanyServiceImpl implements CompanyService {
   
       @Autowired
       CompanyTblMapper companyTblMapper;
   
       @Override
       public int insert(CompanyTbl companyTbl) {
           int count = companyTblMapper.insert(companyTbl);
           return count;
       }
   
       @Override
       public int delete(Integer id) {
           int count = companyTblMapper.delete(id);
           return count;
       }
   
       @Override
       public int update(CompanyTbl companyTbl) {
           int count = companyTblMapper.update(companyTbl);
           return count;
       }
   
       @Override
       public CompanyTbl selectOneById(Integer id) {
           return companyTblMapper.selectOneById(id);
       }
   
       @Override
       public List<CompanyTbl> selectAll() {
           return companyTblMapper.selectAll();
       }
   }
   ```

#### 2.3.7 Controller

```java
package com.fei.springcloud.controller;

import com.fei.springcloud.pojo.CompanyTbl;
import com.fei.springcloud.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/17
 * @version: 1.0
 */
@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/cloud/one/{id}")
    public CompanyTbl selectById(@PathVariable("id") Integer id){
        return companyService.selectOneById(id);
    }

    @GetMapping("/cloud/all")
    public List<CompanyTbl> getAll(){
        return companyService.selectAll();
    }

    @PostMapping("/cloud/add")
    public Map addCompany(@RequestBody CompanyTbl companyTbl){
        Map<String, Object> map = new HashMap<>();
        int count = companyService.insert(companyTbl);
        map.put("msg","success");
        map.put("count",count);
        return map;
    }

    @PostMapping("/cloud/delete/{id}")
    public Map deleteCompany(@PathVariable("id") int id){
        Map<String, Object> map = new HashMap<>();
        int count = companyService.delete(id);
        map.put("msg","success");
        map.put("count",count);
        return map;
    }
}
```

#### 2.3.8 application.java

```java
package com.fei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/17
 * @version: 1.0
 */
@SpringBootApplication
public class ProviderApplication_8001 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication_8001.class,args);
    }
}
```

### 2.4 spring-cloud-consumer

#### 2.4.1 pom文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springCloud</artifactId>
        <groupId>org.fei</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-cloud-consumer-80</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <!--基础依赖相关-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-test</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>org.fei</groupId>
            <artifactId>spring-cloud-api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
</project>
```

#### 2.4.2 使用RestTemplate访问

1. configuration

   ```java
   package com.fei.springcloud.config;
   
   import com.fei.customeize.RibbonConfiguration;
   import org.springframework.cloud.client.loadbalancer.LoadBalanced;
   import org.springframework.cloud.netflix.ribbon.RibbonClient;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   import org.springframework.web.client.RestTemplate;
   
   @Configuration
   public class RestTemplateConfig {
   
       @Bean
       @LoadBalanced
       public RestTemplate myRestTemplate(){
           return new RestTemplate();
       }
   }
   ```

   

#### 2.4.3 controller

```java
package com.fei.springcloud.controller;

import com.fei.springcloud.pojo.CompanyTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/16
 * @version: 1.0
 */
@RestController
@RequestMapping("/consumer")
public class CompanyController {

    //使用 restTemplate 可直接访问
    @Autowired
    private RestTemplate restTemplate;
	
    private static String RESTEMPLETE_SUBFIX="http://localhost:8001";

    @GetMapping("/cloud/get/{id}")
    public ResponseEntity<CompanyTbl> getCompleteById(@PathVariable("id") int id){
        return restTemplate.getForEntity(RESTEMPLETE_SUBFIX+"/cloud/one/"+id,CompanyTbl.class);
    }

    @GetMapping("/cloud/all")
    public List<CompanyTbl> getAll(){
        return restTemplate.getForObject(RESTEMPLETE_SUBFIX+"/cloud/all",List.class);
    }

    @PostMapping("/cloud/add")
    public Map addCompany(@RequestBody CompanyTbl companyTbl){
        return restTemplate.postForObject(RESTEMPLETE_SUBFIX+"/cloud/insert",companyTbl,Map.class);
    }

    @GetMapping("/cloud/delete/{id}")
    public Map addCompany(@PathVariable("id") int id){
        return restTemplate.postForObject(RESTEMPLETE_SUBFIX+"/cloud/delete/"+id, new HashMap<String, Object>(),Map.class);
    }
}
```

#### 2.4.4 application

```java
package com.fei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/16
 * @version: 1.0
 */
@SpringBootApplication
public class SpringCloudConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerApplication.class,args);
    }
}
```

#### 2.4.5 yaml文件

```shell
server:
  port: 80

spring:
  application:
    name: spring-cloud-consumer
```



## 三. 添加eureka信息

> info:  需修改 `hosts`文件
>
> ```shell
>    # 测试eureka集群使用
>    127.0.0.1           eureka-server 
>    127.0.0.1           eureka-server2
>    127.0.0.1           eureka-server3 
> ```
>
> 

### 3.1.server端-- eureka 创建

#### 3.1.1 pom文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springCloud</artifactId>
        <groupId>org.fei</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-cloud-eureka-01</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>jackson-datatype-jsr310</artifactId>
                    <groupId>com.fasterxml.jackson.datatype</groupId>
                </exclusion>
                <exclusion>
                    <artifactId>jackson-annotations</artifactId>
                    <groupId>com.fasterxml.jackson.core</groupId>
                </exclusion>
                <exclusion>
                    <artifactId>jackson-core</artifactId>
                    <groupId>com.fasterxml.jackson.core</groupId>
                </exclusion>
                <exclusion>
                    <artifactId>jackson-databind</artifactId>
                    <groupId>com.fasterxml.jackson.core</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>jackson-annotations</artifactId>
                    <groupId>com.fasterxml.jackson.core</groupId>
                </exclusion>
                <exclusion>
                    <artifactId>jackson-core</artifactId>
                    <groupId>com.fasterxml.jackson.core</groupId>
                </exclusion>
                <exclusion>
                    <artifactId>jackson-databind</artifactId>
                    <groupId>com.fasterxml.jackson.core</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--jackson 相关-->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
        </dependency>
        <!--gson 相关-->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <scope>provided</scope>
        </dependency>
    </dependencies>

</project>
```

#### 3.1.2 yaml文件

```yaml
server:
  port: 7001

spring:
  application:
    name: spring-cloud-eureka-01
eureka:
  instance:
    hostname: eureka-server
  client:
    register-with-eureka: false #false表示不向注册中心注册自己。
    fetch-registry: false #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}
  server:
    #开启注册中心的保护机制，默认是开启
    enable-self-preservation: true
    #设置保护机制的阈值，默认是0.85。
    renewal-percent-threshold: 0.85
```

#### 3.1.3 application

> info: `@EnableEurekaServer` 启用的是服务端

```java
package com.fei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/17
 * @version: 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
```

### 3.2 client

> info:   provider 和 consumer 都需要添加以下信息

#### 3.2.1 client-pom

```xml
<!-- spring cloud 客户端依赖 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

#### 3.2.2 client-yaml文件信息

```yaml
eureka:
  instance:
    hostname: eureka-server
  client:
    register-with-eureka: false #false表示不向注册中心注册自己。
    fetch-registry: false #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}
  server:
    #开启注册中心的保护机制，默认是开启
    enable-self-preservation: true
    #设置保护机制的阈值，默认是0.85。
    renewal-percent-threshold: 0.85
```

#### 3.2.3 client-application

在主启动类上面添加==@EnableEurekaClient==

### 3.3 修改调用方式

> info:   [点击跳转至 `consumer-controller `](#2.4.3 controller)

```java
# 将
private static String RESTEMPLETE_SUBFIX="http://localhost:8001";
# 修改为
private static String RESTEMPLETE_SUBFIX="http://SPRING-CLOUD-PROVIDER";
```

### 3.4 搭建 eureka集群

> 参考链接:   [SpringCloud 微服务注册与发现Eureka | Thread丶博客 (thread-blog.org)](http://thread-blog.org/2021/08/25/SpringCloud/SpringCloud-微服务注册与发现Eureka/)

#### 3.4.1 server修改

1. yaml [点击跳转](#3.1.2 yaml文件)

```yaml
server:
  port: 80

spring:
  application:
    name: spring-cloud-consumer

#eureka配置相关
customize:
  eureka:
    host:
      host1: eureka-server
      host2: eureka-server2
      host3: eureka-server3
    port:
      port1: 7001
      port2: 7002
      port3: 7003

#eureka配置相关
eureka:
  instance:
    hostname: localhost
  client:
    # 向服务端注册自己
    register-with-eureka: true
    # 从Eureka Server获取注册的服务信息
    fetch-registry: true
    service-url:
      defaultZone: http://${customize.eureka.host.host1}:${customize.eureka.port.port1}/eureka,http://${customize.eureka.host.host2}:${customize.eureka.port.port2}/eureka,http://${customize.eureka.host.host3}:${customize.eureka.port.port3}/eureka
```

#### 3.4.2 client修改

```yaml
#eureka配置相关
eureka:
  instance:
    hostname: localhost
  client:
    # 向服务端注册自己
    register-with-eureka: true
    # 从Eureka Server获取注册的服务信息
    fetch-registry: true
    service-url:
      defaultZone: http://${customize.eureka.host.host1}:${customize.eureka.port.port1}/eureka,http://${customize.eureka.host.host2}:${customize.eureka.port.port2}/eureka,http://${customize.eureka.host.host3}:${customize.eureka.port.port3}/eureka
```



1. [点击跳转-provider-yaml文件](#2.3.2  yml 文件)

   ```yaml
   server:
     port: 8001
   
   
   dbserver:
     mysql:
       host: localhost
       username: root
       password: 123456
       port: 30306
       databases: db_test
   
   # mybatis相关
   mybatis:
     type-aliases-package: com.fei.springcloud.pojo.CompanyTbl
     mapper-locations: classpath:mybatis/mapper/*.xml
     config-location: classpath:mybatis/mybatis-config.xml
   
   
   spring:
     application:
       name: spring-cloud-provider
     datasource:
       type: com.alibaba.druid.pool.DruidDataSource
       #    driver-class-name: com.mysql.cj.jdbc.Driver
       #    username: ${dbserver.mysql.username}
       #    password: ${dbserver.mysql.password}
       #    url: jdbc:mysql://${dbserver.mysql.host}:${dbserver.mysql.port}/${dbserver.mysql.databases}?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC
       druid:
         driver-class-name: com.mysql.cj.jdbc.Driver
         username: ${dbserver.mysql.username}
         password: ${dbserver.mysql.password}
         url: jdbc:mysql://${dbserver.mysql.host}:${dbserver.mysql.port}/${dbserver.mysql.databases}?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC
         #监控统计拦截的filters
         filters: stat
         #配置初始化大小/最小/最大
         initial-size: 1
         min-idle: 1
         max-active: 20
         #获取连接等待超时时间
         max-wait: 60000
         #间隔多久进行一次检测，检测需要关闭的空闲连接
         time-between-eviction-runs-millis: 60000
         #一个连接在池中最小生存的时间
         min-evictable-idle-time-millis: 300000
         validation-query: SELECT 'x'
         test-while-idle: true
         test-on-borrow: false
         test-on-return: false
         #打开PSCache，并指定每个连接上PSCache的大小。oracle设为true，mysql设为false。分库分表较多推荐设置为false
         pool-prepared-statements: false
         max-pool-prepared-statement-per-connection-size: 20
   
   customize:
     eureka:
       host:
         host1: eureka-server
         host2: eureka-server2
         host3: eureka-server3
       port:
         port1: 7001
         port2: 7002
         port3: 7003
   
   #eureka配置相关
   eureka:
     instance:
       hostname: localhost
     client:
       # 向服务端注册自己
       register-with-eureka: true
       # 从Eureka Server获取注册的服务信息
       fetch-registry: true
       service-url:
         defaultZone: http://${customize.eureka.host.host1}:${customize.eureka.port.port1}/eureka,http://${customize.eureka.host.host2}:${customize.eureka.port.port2}/eureka,http://${customize.eureka.host.host3}:${customize.eureka.port.port3}/eureka
   ```

2. [consumer-yaml文件](#2.4.5 yaml文件)

   ```yaml
   server:
     port: 80
   
   spring:
     application:
       name: spring-cloud-consumer
   
   #eureka配置相关
   customize:
     eureka:
       host:
         host1: eureka-server
         host2: eureka-server2
         host3: eureka-server3
       port:
         port1: 7001
         port2: 7002
         port3: 7003
   
   #eureka配置相关
   eureka:
     instance:
       hostname: localhost
     client:
       # 向服务端注册自己
       register-with-eureka: true
       # 从Eureka Server获取注册的服务信息
       fetch-registry: true
       service-url:
         defaultZone: http://${customize.eureka.host.host1}:${customize.eureka.port.port1}/eureka,http://${customize.eureka.host.host2}:${customize.eureka.port.port2}/eureka,http://${customize.eureka.host.host3}:${customize.eureka.port.port3}/eureka
   ```

   

## 四. 添加Ribbon信息

> - 参考链接: [SpringCloud Ribbon实现客户端侧负载均衡 | Thread丶博客 (thread-blog.org)](http://thread-blog.org/2021/09/04/SpringCloud/SpringCloud-Ribbon实现客户端侧负载均衡/)
> - 使用Ribbon实现`客户端侧`负载均衡

### 4.1 修改provider 为集群

[点击跳转-provider](#3.4.2 client修改)

将`spring-cloud-provider-company`复制三份`并启动服务`,yaml文件分别为:

1. 端口-8001

   ```yaml
   server:
     port: 8001
     
   dbserver:
     mysql:
       host: localhost
       username: root
       password: 123456
       port: 30306
       databases: db_test
   
   # mybatis相关
   mybatis:
     type-aliases-package: com.fei.springcloud.pojo.CompanyTbl
     mapper-locations: classpath:mybatis/mapper/*.xml
     config-location: classpath:mybatis/mybatis-config.xml
   
   
   spring:
     application:
       name: spring-cloud-provider
     datasource:
       type: com.alibaba.druid.pool.DruidDataSource
       #    driver-class-name: com.mysql.cj.jdbc.Driver
       #    username: ${dbserver.mysql.username}
       #    password: ${dbserver.mysql.password}
       #    url: jdbc:mysql://${dbserver.mysql.host}:${dbserver.mysql.port}/${dbserver.mysql.databases}?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC
       druid:
         driver-class-name: com.mysql.cj.jdbc.Driver
         username: ${dbserver.mysql.username}
         password: ${dbserver.mysql.password}
         url: jdbc:mysql://${dbserver.mysql.host}:${dbserver.mysql.port}/${dbserver.mysql.databases}?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC
         #监控统计拦截的filters
         filters: stat
         #配置初始化大小/最小/最大
         initial-size: 1
         min-idle: 1
         max-active: 20
         #获取连接等待超时时间
         max-wait: 60000
         #间隔多久进行一次检测，检测需要关闭的空闲连接
         time-between-eviction-runs-millis: 60000
         #一个连接在池中最小生存的时间
         min-evictable-idle-time-millis: 300000
         validation-query: SELECT 'x'
         test-while-idle: true
         test-on-borrow: false
         test-on-return: false
         #打开PSCache，并指定每个连接上PSCache的大小。oracle设为true，mysql设为false。分库分表较多推荐设置为false
         pool-prepared-statements: false
         max-pool-prepared-statement-per-connection-size: 20
   
   customize:
     eureka:
       host:
         host1: eureka-server
         host2: eureka-server2
         host3: eureka-server3
       port:
         port1: 7001
         port2: 7002
         port3: 7003
   
   #eureka配置相关
   eureka:
     instance:
       hostname: localhost
     client:
       # 向服务端注册自己
       register-with-eureka: true
       # 从Eureka Server获取注册的服务信息
       fetch-registry: true
       service-url:
         defaultZone: http://${customize.eureka.host.host1}:${customize.eureka.port.port1}/eureka,http://${customize.eureka.host.host2}:${customize.eureka.port.port2}/eureka,http://${customize.eureka.host.host3}:${customize.eureka.port.port3}/eureka
   ```

2. port-8002

   ```yaml
   server:
     port: 8002
   
   
   dbserver:
     mysql:
       host: localhost
       username: root
       password: 123456
       port: 30306
       databases: db_test
   
   # mybatis相关
   mybatis:
     type-aliases-package: com.fei.springcloud.pojo.CompanyTbl
     mapper-locations: classpath:mybatis/mapper/*.xml
     config-location: classpath:mybatis/mybatis-config.xml
   
   
   spring:
     application:
       name: spring-cloud-provider
     datasource:
       type: com.alibaba.druid.pool.DruidDataSource
   #    driver-class-name: com.mysql.cj.jdbc.Driver
   #    username: ${dbserver.mysql.username}
   #    password: ${dbserver.mysql.password}
   #    url: jdbc:mysql://${dbserver.mysql.host}:${dbserver.mysql.port}/${dbserver.mysql.databases}?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC
       druid:
         driver-class-name: com.mysql.cj.jdbc.Driver
         username: ${dbserver.mysql.username}
         password: ${dbserver.mysql.password}
         url: jdbc:mysql://${dbserver.mysql.host}:${dbserver.mysql.port}/${dbserver.mysql.databases}?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC
         #监控统计拦截的filters
         filters: stat
         #配置初始化大小/最小/最大
         initial-size: 1
         min-idle: 1
         max-active: 20
         #获取连接等待超时时间
         max-wait: 60000
         #间隔多久进行一次检测，检测需要关闭的空闲连接
         time-between-eviction-runs-millis: 60000
         #一个连接在池中最小生存的时间
         min-evictable-idle-time-millis: 300000
         validation-query: SELECT 'x'
         test-while-idle: true
         test-on-borrow: false
         test-on-return: false
         #打开PSCache，并指定每个连接上PSCache的大小。oracle设为true，mysql设为false。分库分表较多推荐设置为false
         pool-prepared-statements: false
         max-pool-prepared-statement-per-connection-size: 20
   
   customize:
     eureka:
       host:
         host1: eureka-server
         host2: eureka-server2
         host3: eureka-server3
       port:
         port1: 7001
         port2: 7002
         port3: 7003
   
   #eureka配置相关
   eureka:
     instance:
       hostname: localhost
     client:
       # 向服务端注册自己
       register-with-eureka: true
       # 从Eureka Server获取注册的服务信息
       fetch-registry: true
       service-url:
         defaultZone: http://${customize.eureka.host.host1}:${customize.eureka.port.port1}/eureka,http://${customize.eureka.host.host2}:${customize.eureka.port.port2}/eureka,http://${customize.eureka.host.host3}:${customize.eureka.port.port3}/eureka
   ```

3. port-8003

   ```shell
   server:
     port: 8003
   
   
   dbserver:
     mysql:
       host: localhost
       username: root
       password: 123456
       port: 30306
       databases: db_test
   
   # mybatis相关
   mybatis:
     type-aliases-package: com.fei.springcloud.pojo.CompanyTbl
     mapper-locations: classpath:mybatis/mapper/*.xml
     config-location: classpath:mybatis/mybatis-config.xml
   
   
   spring:
     application:
       name: spring-cloud-provider
     datasource:
       type: com.alibaba.druid.pool.DruidDataSource
   #    driver-class-name: com.mysql.cj.jdbc.Driver
   #    username: ${dbserver.mysql.username}
   #    password: ${dbserver.mysql.password}
   #    url: jdbc:mysql://${dbserver.mysql.host}:${dbserver.mysql.port}/${dbserver.mysql.databases}?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC
       druid:
         driver-class-name: com.mysql.cj.jdbc.Driver
         username: ${dbserver.mysql.username}
         password: ${dbserver.mysql.password}
         url: jdbc:mysql://${dbserver.mysql.host}:${dbserver.mysql.port}/${dbserver.mysql.databases}?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC
         #监控统计拦截的filters
         filters: stat
         #配置初始化大小/最小/最大
         initial-size: 1
         min-idle: 1
         max-active: 20
         #获取连接等待超时时间
         max-wait: 60000
         #间隔多久进行一次检测，检测需要关闭的空闲连接
         time-between-eviction-runs-millis: 60000
         #一个连接在池中最小生存的时间
         min-evictable-idle-time-millis: 300000
         validation-query: SELECT 'x'
         test-while-idle: true
         test-on-borrow: false
         test-on-return: false
         #打开PSCache，并指定每个连接上PSCache的大小。oracle设为true，mysql设为false。分库分表较多推荐设置为false
         pool-prepared-statements: false
         max-pool-prepared-statement-per-connection-size: 20
   
   customize:
     eureka:
       host:
         host1: eureka-server
         host2: eureka-server2
         host3: eureka-server3
       port:
         port1: 7001
         port2: 7002
         port3: 7003
   
   #eureka配置相关
   eureka:
     instance:
       hostname: localhost
     client:
       # 向服务端注册自己
       register-with-eureka: true
       # 从Eureka Server获取注册的服务信息
       fetch-registry: true
       service-url:
         defaultZone: http://${customize.eureka.host.host1}:${customize.eureka.port.port1}/eureka,http://${customize.eureka.host.host2}:${customize.eureka.port.port2}/eureka,http://${customize.eureka.host.host3}:${customize.eureka.port.port3}/eureka
   ```

### 4.2 修改consumer相关配置

#### 4.2.1 pom文件

[点击跳转 consumer-pom.xml](#2.4.1 pom文件)

```xml
<!-- 客户端负载均衡 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
```

#### 4.2.2 添加Configuation

```java
package com.fei.customeize;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 自定义 Ribbon配置
 * @author: qpf
 * @date: 2022/4/18
 * @version: 1.0
 */
@Configuration
public class RibbonConfiguration {
    /**
     * 选择一个并发最小的进行分配
     * @return
     */
    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
//        return new PingUrl();
    }

    /**
     * IPing机制
     * 用来判断服务节点存活的一种机制，pringUrl 是发一次 http请求来判断是否存活
     * @return
     */
    @Bean
    public IPing ribbonPing(){
        return new DummyPing();
    }

    /**
     * 以下两种需要时再做了解
     * @return
     */
    /*
    @Bean
    public ServerListSubsetFilter serverListSubsetFilter(){
        ServerListSubsetFilter filter = new ServerListSubsetFilter();
        return filter;
    }

    @Bean
    public ServerList<Server> ribbonServerList(IClientConfig config){
        return new BazServiceList(config);
    }

    public static class BazServiceList extends ConfigurationBasedServerList{
        public BazServiceList(IClientConfig config){
            super.initWithNiwsConfig(config);
        }
    }
     */
}
```

#### 4.2.3 配置单个 RibbonClient 负载均衡策略 

> **注意：这个配置类不能放到应用@ComponentScan扫描的包下，不然策略会被Ribbon 的所有Client共享**

[点击跳转](#2.4.2 使用RestTemplate访问)

```java
package com.fei.springcloud.config;

import com.fei.customeize.RibbonConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RibbonClient(name = "thread-produce",configuration = RibbonConfiguration.class)
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate myRestTemplate(){
        return new RestTemplate();
    }
}
```

`参考位置`:

![](https://s3.bmp.ovh/imgs/2022/04/19/2ae0f749baa62a02.jpg)

#### 4.2.4 配置全局 负载均衡策略

##### Ribbon 配置类，通过配置不同接口的实现类通过改变 Ribbbon 的配置规则

```
@Configuration
public class DefaultRibbonConfig {
    /**
     * 选择一个并发最小的进行分配
     * @return
     */
    @Bean
    public IRule ribbonRule(){
        return new BestAvailableRule();
//        return new PingUrl();
    }

    /**
     * IPing机制
     * 用来判断服务节点存活的一种机制，pringUrl 是发一次 http请求来判断是否存活
     * @return
     */
    @Bean
    public IPing ribbonPing(){
        return new DummyPing();
    }

    /**
     * 剩下的这两种配置先不做了解了 书中也没有细说 应该也是一种配置的扩展
     * @return
     */
    /*
    @Bean
    public ServerListSubsetFilter serverListSubsetFilter(){
        ServerListSubsetFilter filter = new ServerListSubsetFilter();
        return filter;
    }

    @Bean
    public ServerList<Server> ribbonServerList(IClientConfig config){
        return new BazServiceList(config);
    }

    public static class BazServiceList extends ConfigurationBasedServerList{
        public BazServiceList(IClientConfig config){
            super.initWithNiwsConfig(config);
        }
    }
     */
}
```

##### 启用默认全局配置

```
//配置默认的Ribbon 配置策略
//默认的 Ribbon配置类
@RibbonClients(defaultConfiguration = DefaultRibbonConfig.class)
```

#### 4.2.5 开启饥饿加载模式，提升第一次访问的速度

```yaml
ribbon:
  #配置饥饿加载 在启动时候就全部把client加载好，默认是第一次请求的时候去创建
  eager-load:
    enabled: true
    clients: thread-produce
```

## 五. Feign配置

### 5.1 在consumer 创建 feign

> info:  
>
> 1. 创建feign消费端, 例如: spring-cloud-consumer-feign-81
> 2. 使用默认配置
> 3. 参考链接:  [SpringCloud 使用Feign实现声明式REST调用 | Thread丶博客 (thread-blog.org)](http://thread-blog.org/2021/08/25/SpringCloud/SpringCloud-使用Feign实现声明式REST调用/)
> 3. 参考链接: [Spring Cloud系列(五)：声明式 REST 客户端 Feign | 光星の博客 (gxitsky.com)](http://www.gxitsky.com/article/17)

#### 5.1.1 pom文件

```xml
<!--导入 Feign 依赖-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

#### 5.1.2 创建客户端,调用provider的Rest接口

[点击跳转-provider端](#4.1 修改provider 为集群)的服务名称: `spring-cloud-provider`

```java
package com.fei.springcloud.feign;

import com.fei.customize.FeignConfiguration;
import com.fei.springcloud.pojo.CompanyTbl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @description: 使用 feign 调用 spring-cloud-provider 服务提供者的 Restful 接口
 * @author: qpf
 * @date: 2022/4/19
 * @version: 1.0
 */
// 使用默认配置
@FeignClient("spring-cloud-provider")
public interface UseCompanyClient {

    @GetMapping("/cloud/one/{id}")
    public CompanyTbl selectById(@PathVariable("id") Integer id);

    @GetMapping("/cloud/all")
    public List<CompanyTbl> getAll();

    @PostMapping("/cloud/add")
    public Map addCompany(@RequestBody CompanyTbl companyTbl);

    @PostMapping("/cloud/delete/{id}")
    public Map deleteCompany(@PathVariable("id") int id);
}
```

#### 5.1.3 配置application

> info: 开启@EnableFeignClients

```java
/**
 * @description: 开启 @EnableFeignClients 服务
 * @author: qpf
 * @date: 2022/4/16
 * @version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SpringCloudConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerApplication.class,args);
    }
}
```

#### 5.1.4 在controller中调用

> info: 接口参考[点击跳转-Provider中的controller](#2.3.7 Controller)

```java
package com.fei.springcloud.controller;

import com.fei.springcloud.feign.UseCompanyClient;
import com.fei.springcloud.pojo.CompanyTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description: 调用 spring-cloud-api 新创建的feign接口
 * @author: qpf
 * @date: 2022/4/16
 * @version: 1.0
 */
@RestController
@RequestMapping("/consumer")
public class CompanyController {

    @Autowired
    UseCompanyClient useCompanyClient;

    @GetMapping("/cloud/get/{id}")
    public CompanyTbl getCompleteById(@PathVariable("id") int id){
        return useCompanyClient.selectById(id);
    }

    @GetMapping("/cloud/all")
    public List<CompanyTbl> getAll(){
        return useCompanyClient.getAll();
    }

    @PostMapping("/cloud/add")
    public Map addCompany(@RequestBody CompanyTbl companyTbl){
        return useCompanyClient.addCompany(companyTbl);
    }

    @GetMapping("/cloud/delete/{id}")
    public Map deleteCompany(@PathVariable("id") int id){
        return useCompanyClient.deleteCompany(id);
    }
}
```

#### 5.1.5 yaml 文件

```yaml
server:
  port: 8081

spring:
  application:
    name: spring-cloud-consumer

#eureka配置相关
customize:
  eureka:
    host:
      host1: eureka-server
      host2: eureka-server2
      host3: eureka-server3
    port:
      port1: 7001
      port2: 7002
      port3: 7003

#eureka配置相关
eureka:
  instance:
    hostname: localhost
  client:
    # 向服务端注册自己
    register-with-eureka: true
    # 从Eureka Server获取注册的服务信息
    fetch-registry: true
    service-url:
      defaultZone: http://${customize.eureka.host.host1}:${customize.eureka.port.port1}/eureka,http://${customize.eureka.host.host2}:${customize.eureka.port.port2}/eureka,http://${customize.eureka.host.host3}:${customize.eureka.port.port3}/eureka
```



### 5.2 使用自定义配置类

#### 5.2.1声明 Feign的配置类

> 配置类的@Configuration注解加和不加 取决于不要被SpringBoot默认@ComponentScan注解扫描到
>
> 如果加了注解被扫描到后，Feign所有的Client都会共享这个设置，就达不到我们为单个client加配置的目的了

![参考位置](https://s3.bmp.ovh/imgs/2022/04/19/2c83fb777214d340.jpg)

```java
package com.fei.customize;

import feign.Contract;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;

/**
 * @description: 自定义Feign配置信息
 * @author: qpf
 * @date: 2022/4/19
 * @version: 1.0
 */
public class FeignConfiguration {
    /**
     * Feign 默认的契约是 Spring MVC的注解  用的是 SpringMvcContract 所以 FeignClient 可以使用mvc注解来去定义
     * Contract.Default() 改成了 Feign默认的契约 所以这里配置了以后就要修改FeignClient的注解
     * @return
     */
    @Bean
    public Contract feignContract(){
        // return new Contract.Default();
       return new SpringMvcContract();
    }
}
```

#### 5.2.2 在 FeignCleint 中使用 Feign 配置类

[点击跳转-UseCompanyClient](#5.1.2 创建客户端,调用provider的Rest接口): 

将`@FeignClient("spring-cloud-provider")`

修改为

`@FeignClient(name = "spring-cloud-provider", configuration = FeignConfiguration.class)`

```java
package com.fei.springcloud.feign;

import com.fei.customize.FeignConfiguration;
import com.fei.springcloud.pojo.CompanyTbl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @description: 使用 feign 调用 spring-cloud-provider 服务提供者的 Restful 接口
 * @author: qpf
 * @date: 2022/4/19
 * @version: 1.0
 */
// 使用默认配置
// @FeignClient("spring-cloud-provider")
// 使用自定义配置类
@FeignClient(name = "spring-cloud-provider", configuration = FeignConfiguration.class)
public interface UseCompanyClient {

    @GetMapping("/cloud/one/{id}")
    public CompanyTbl selectById(@PathVariable("id") Integer id);

    @GetMapping("/cloud/all")
    public List<CompanyTbl> getAll();

    @PostMapping("/cloud/add")
    public Map addCompany(@RequestBody CompanyTbl companyTbl);

    @PostMapping("/cloud/delete/{id}")
    public Map deleteCompany(@PathVariable("id") int id);
}
```

#### 5.2.3 启用全局默认的配置

[点击跳转-application](#5.1.3 配置application)

```java
/**
 * @description: 全局使用配置类
 * @author: qpf
 * @date: 2022/4/16
 * @version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
//开启全局的Feign配置
@EnableFeignClients(defaultConfiguration = FeignConfiguration.class)
public class SpringCloudConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerApplication.class,args);
    }
}
```

#### 5.2.4使用SpringBoot配置文件形式

[点击跳转-yaml文件](#5.1.5 yaml 文件)

```yaml
feign:
  client:
    config:
      #为单独的服务配置Feign配置 (这里的名字是你的服务的名字)
      spring-cloud-provider:
        loggerLevel: full #配置Feign日志 为full
      #配置全局的Feign配置
      default:
        loggerLevel: BASIC
```

### 5.3 Feign的高级配置

#### 5.3.1  添加权限配置信息

> 参考链接1:    [自定义feign配置与服务调用的安全验证 - 简书 (jianshu.com)](https://www.jianshu.com/p/755b15ff0249)
>
> 参考链接2:     [SpringCloud 使用Feign实现声明式REST调用 | Thread丶博客 (thread-blog.org)](http://thread-blog.org/2021/08/25/SpringCloud/SpringCloud-使用Feign实现声明式REST调用/)

##### 5.3.1.1 修改parent的pom.xml



```xml
<!--新增-->
<springsecurity.code.version>5.3.5.RELEASE</springsecurity.code.version>


<!--SpringSecurity 权限相关-->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-core</artifactId>
    <version>${springsecurity.code.version}</version>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
    <version>${springboot.version}</version>
</dependency>
```

##### 5.3.1.2 服务提供者

a. `pom.xml`

```xml
<!--SpringSecurity 权限相关-->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-core</artifactId>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

b. `新增@Configuration类`

```java
package com.fei.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @description:
 * @author: qpf
 * @date: 2022/4/26
 * @version: 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
    //开启HttpBasic认证
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    //声明无密码加密
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    private CustomUserDetails customUserDetails;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.customUserDetails).passwordEncoder(this.passwordEncoder());
    }

    //配置授权和实体的服务
    @Component
    class CustomUserDetails implements UserDetailsService {
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            if(username.equals("xiaoming")){
                return new SecurityUser("xiaoming","123", "role-user");
            }else if(username.equals("zhangsan")){
                return new SecurityUser("zhangsan", "123", "role-admin");
            }
            return null;
        }
    }

    //配置用户和授权实体
    class SecurityUser implements UserDetails{
        private String password;
        private String username;
        private String role;

        public SecurityUser(String username, String password, String role){
            this.username = username;
            this.password = password;
            this.role = role;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.role);
            collection.add(simpleGrantedAuthority);
            return collection;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
```

c. `新增 BasicAuthController 类`

```java
package com.fei.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @description: BasicAuthController
 * @author: qpf
 * @date: 2022/4/26
 * @version: 1.0
 */
@RestController
@Slf4j
public class BasicAuthController {
    //获取登录用户
    @RequestMapping("/loginName")
    public String getLoginName(){
        String username = "";	//用户名
        String password = "";	//密码
        String role = "";		//角色
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails)principal;
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            for(GrantedAuthority grantedAuthority : authorities){
                username = userDetails.getUsername();
                password = userDetails.getPassword();
                role = grantedAuthority.getAuthority();
                log.info("用户名：{}\t密码：{}\t角色：{}", username, password, role);
            }
        }
        return "<h1>当前用户</h1><br/>用户名："+ username +"\t密码："+ password +"\t角色：" + role;
    }
}
```

##### 5.3.1.3  服务消费者

a.` 修改配置类`--> [原来配置](#5.2.1声明 Feign的配置类)

```java
package com.fei.customize;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;

/**
 * @description: 自定义Feign配置信息
 * @author: qpf
 * @date: 2022/4/19
 * @version: 1.0
 */
public class FeignConfiguration {
    /**
     * Feign 默认的契约是 Spring MVC的注解  用的是 SpringMvcContract 所以 FeignClient 可以使用mvc注解来去定义
     * Contract.Default() 改成了 Feign默认的契约 所以这里配置了以后就要修改FeignClient的注解
     * @return
     */
    @Bean
    public Contract feignContract(){
        // return new Contract.Default();
       return new SpringMvcContract();
    }

    /**
     * 新增basicAuth认证
     * @return
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("user","password");
    }
}
```

b. `新增接口`

```java
package com.fei.springcloud.feign;

import com.fei.springcloud.pojo.CompanyTbl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description: feign基于BasicAuth的客户端
 * @author: qpf
 * @date: 2022/4/30
 * @version: 1.0
 */
public interface BasicAuthClient {

    @GetMapping("/cloud/one/{id}")
    public CompanyTbl selectById(@PathVariable("id") Integer id);

    @GetMapping("/cloud/all")
    public List<CompanyTbl> getAll();

    @PostMapping("/cloud/add")
    public Map addCompany(@RequestBody CompanyTbl companyTbl);

    @PostMapping("/cloud/delete/{id}")
    public Map deleteCompany(@PathVariable("id") int id);

    @RequestMapping("/loginName")
    public String getLoginName();
}
```

c. `调用接口`

1. 基于feign的认证方式调用接口，认证方式为basicAuth
2. 请求方式为restTemplate调用消费端接口,因此不需要 :
   - a. 主启动类的 @EnableFeignClients 注解
   - b. BasicAuthClient.class 也不需要 @FeignClient("spring-cloud-provider")

```java
package com.fei.springcloud.controller;

import com.fei.springcloud.feign.BasicAuthClient;
import com.fei.springcloud.pojo.CompanyTbl;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 *                  1. 基于feign的认证方式调用接口，认证方式为basicAuth
 *                  2. 请求方式为restTemplate调用消费端接口,因此不需要 :
 *                          a. 主启动类的 @EnableFeignClients 注解
 *                          b. BasicAuthClient.class 也不需要 @FeignClient("spring-cloud-provider")
 *                  3.  参考链接1: https://www.jianshu.com/p/755b15ff0249
 *                  4.  参考链接2:
 *                  http://thread-blog.org/2021/08/25/SpringCloud/SpringCloud-%E4%BD%BF%E7%94%A8Feign%E5%AE%9E%E7%8E%B0%E5%A3%B0%E6%98%8E%E5%BC%8FREST%E8%B0%83%E7%94%A8/
 * @author: qpf
 * @date: 2022/4/27
 * @version: 1.0
 */
@Import(FeignClientsConfiguration.class)
@RestController
@RequestMapping("/basicAuth")
public class BasicAuthFeignController {
    //用户FeignClient
    // private BasicAuthClient userFeignClient;
    //管理员Feignclient
    private BasicAuthClient adminFeignClient;

    /**
     * 初始化Controller时创建 根据用户和角色的不同 创建不同形态的 Client
     * 利用BasicHttp 进行认证的方式 传输不同的用户名和密码
     *
     * @param decoder
     * @param encoder
     * @param client
     * @param contract
     */
    public BasicAuthFeignController(Decoder decoder, Encoder encoder, Client client, Contract contract) {

        /**
         * 这里的构造方法的参数 是通过 @Import(FeignClientsConfiguration.class)导入Bean的方式注入进来的
         */
        // this.userFeignClient = Feign.builder().client(client).
        //         encoder(encoder).decoder(decoder).contract(contract).
        //         requestInterceptor(new BasicAuthRequestInterceptor("xiaoming", "123")).
        //         target(BasicAuthClient.class, "http://SPRING-CLOUD-PROVIDER");

        this.adminFeignClient = Feign.builder().client(client).encoder(encoder).
                decoder(decoder).contract(contract).
                requestInterceptor(new BasicAuthRequestInterceptor("zhangsan", "123")).
                target(BasicAuthClient.class, "http://SPRING-CLOUD-PROVIDER");
    }
    //管理员调用管理员的Client
    @RequestMapping("/admin")
    public String getAdmin(){
        String loginName = adminFeignClient.getLoginName();
        return loginName;
    }
    //普通用户调用普通用户的Client
    // @RequestMapping("/user")
    // public String getUser(){
    //     String loginName = userFeignClient.getLoginName();
    //     return loginName;
    // }

    @GetMapping("/cloud/get/{id}")
    public CompanyTbl getCompleteById(@PathVariable("id") int id){
        return this.adminFeignClient.selectById(id);
    }

    @GetMapping("/cloud/all")
    public List<CompanyTbl> getAll(){
        return this.adminFeignClient.getAll();
    }

    @PostMapping("/cloud/add")
    public Map addCompany(@RequestBody CompanyTbl companyTbl){
        return this.adminFeignClient.addCompany(companyTbl);
    }

    @GetMapping("/cloud/delete/{id}")
    public Map deleteCompany(@PathVariable("id") int id){
        return this.adminFeignClient.deleteCompany(id);
    }

    @GetMapping("/loginName")
    public String getLoginName(){
        return this.adminFeignClient.getLoginName();
    }
}
```

d. `注意事项`

> 1. 由于启用了basicAuth认证,所以调用 `CompanyController 类的接口全部失败`,原因是`调用时缺少权限认证`
> 2. 规避措施为: 将`CompanyController` 类的 全部移动到 `BasicAuthFeignController`中,使用restTemplate调用
> 3. 待完善的地方还有很多

#### 5.3.2 文件上传

>  参考资料: [文件上传](http://212.64.64.68/detail.html?id=76&menuId=19)
>
> 项目参考: [文件上传的项目](https://gitee.com/zhangbin_java/springcloud-eureka-feign-ribbon-gateway-hystrix)
>
> 参考资料2: [声明式 REST 客户端 ](http://www.gxitsky.com/article/17)

`记录问题及解决方式`:

若存在相同名的 FeignClient 客户端重复注册，即 @FeignClient 注解的 name 属性存在重复。

**三种解决方案：**

1. 配置多个相同 name 的 Feign Client，使用 `@FeignClient`注解的 `contextId` 属性以避免这些配置 Bean 的名称冲突。*-- 推荐这种*

2. 同一个服务的调用，写在同一个 @FeignClient 客户端中，这样 name 就不会重复。

3. 开启 Spring Bean 允许覆盖，默认是禁止的，如下：

   ```shell
   spring.main.allow-bean-definition-overriding=true
   ```

##### 5.3.2.1 添加公共包

- `ResponseData.class`

  ```java
  package com.fei.springcloud.common.dto;
  
  import com.fei.springcloud.common.enums.ErrorCodeEnum;
  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;
  import lombok.experimental.Accessors;
  
  /*
   * @Author qpf
   * @Date 2022/4/30 10:48
   * @Description: 返回值封装
   *
   **/
  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  @Accessors(chain=true)
  public class ResponseData<T> {
      private String code;
      private String msg;
      private T data;
  
      /*
       * @Author qpf
       * @Date 2022/4/30 11:37
       * @Description: 自定义返回值
       *
       **/
      public static <T> ResponseData<T> response(ErrorCodeEnum errorCodeEnum, T data){
          return new ResponseData(errorCodeEnum.getCode(),errorCodeEnum.getMsg(),data);
      }
  }
  
  ```

- `PageDTO.class`

  ```java
  package com.fei.springcloud.common.dto;
  
  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;
  import lombok.experimental.Accessors;
  
  import java.util.List;
  
  /*
   * @Author qpf
   * @Date 2022/4/30 10:51
   * @Description: 分页封装
   *
   **/
  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  @Accessors(chain=true)
  public class PageDTO<T> {
      private Integer pageSize;//页容量
      private Integer pageNum;//页码
      private Integer totalSize;//总容量
      private List<T> pageContent;//内容列表
  }
  ```

- `UserDTO.class`

  ```java
  package com.fei.springcloud.common.dto;
  
  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.experimental.Accessors;
  
  @Accessors(chain = true)
  @Data
  @AllArgsConstructor
  public class UserDTO {
      private int id;
      private String username;
      private int age;
      private String desc;
  }
  ```

  

##### 5.3.2.2 生产者

1.  `pom.xml`

   ```xml
   <!--Feign对于上传文件的依赖-->
   <dependency>
       <groupId>io.github.openfeign.form</groupId>
       <artifactId>feign-form-spring</artifactId>
       <version>3.8.0</version>
   </dependency>
   
   ```

2. `上传文件的接口，写法就是SpringMVC的上传接口的写法`

   ```java
   package com.fei.springcloud.controller;
   
   
   
   import com.fei.springcloud.common.dto.ResponseData;
   import com.fei.springcloud.common.enums.ErrorCodeEnum;
   import lombok.extern.slf4j.Slf4j;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RequestMethod;
   import org.springframework.web.bind.annotation.RequestParam;
   import org.springframework.web.bind.annotation.RestController;
   import org.springframework.web.multipart.MultipartFile;
   
   import java.io.File;
   import java.io.IOException;
   
   /**
    * @description: 上传文件的接口，写法就是SpringMVC的上传接口的写法
    * @author: qpf
    * @date: 2022/5/1
    * @version: 1.0
    */
   @Slf4j
   @RestController
   @RequestMapping("/user")
   public class FileUploadController {
   
       @RequestMapping(value = "/fileUploadTest",method = RequestMethod.POST)
       public ResponseData<?> fileUploadTest(@RequestParam(value = "file") MultipartFile file, String remark){
           log.info("开始文件上传,remakr={}",remark);
           if(file == null || file.getSize()<=0){
               log.info("文件大小不满足要求");
               return ResponseData.response(ErrorCodeEnum.FAIL,null);
           }
           try {
               file.transferTo(new File("d://"+file.getOriginalFilename()));
           } catch (IOException e) {
               log.error("文件上传失败",e);
               return ResponseData.response(ErrorCodeEnum.FAIL,null);
           }
           log.info("文件上传结束");
           return ResponseData.response(ErrorCodeEnum.SUCCESS,null);
       }
   }
   ```

##### 5.3.2.3 消费者

1. `pom.xml`

   ```xml
           <!--Feign对于上传文件的依赖-->
           <dependency>
               <groupId>io.github.openfeign.form</groupId>
               <artifactId>feign-form-spring</artifactId>
               <version>3.8.0</version>
           </dependency>
   ```

2. 配置多个相同 name 的 `Feign Client`，使用 `@FeignClient`注解的 `contextId` 属性以避免这些配置 Bean 的名称冲突

   1. 修改UseCompanyClient的`@FeignClient`,contextId = `"useCompanyClient"`

      ```java
      @FeignClient(contextId = "useCompanyClient",
                   name = "spring-cloud-provider", 
                   configuration = FeignConfiguration.class)
      public interface UseCompanyClient
      ```

   2. 新增Feign Client的接口,contextId = `"userFileClient"`

      ```java
      package com.fei.springcloud.feign;
      
      import com.fei.customize.FeignConfiguration;
      import com.fei.springcloud.common.dto.ResponseData;
      import org.springframework.cloud.openfeign.FeignClient;
      import org.springframework.http.MediaType;
      import org.springframework.web.bind.annotation.RequestMapping;
      import org.springframework.web.bind.annotation.RequestMethod;
      import org.springframework.web.bind.annotation.RequestParam;
      import org.springframework.web.bind.annotation.RequestPart;
      import org.springframework.web.multipart.MultipartFile;
      
      /**
       * @description:  文件上传
       * @author: qpf
       * @date: 2022/5/1
       * @version: 1.0
       */
      @FeignClient(contextId = "userFileClient",name = "spring-cloud-provider", configuration = FeignConfiguration.class)
      public interface UserFileUploadClient {
      
          @RequestMapping(value = "/user/fileUploadTest",
                  method= RequestMethod.POST,
                  consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
          ResponseData<?> fileUploadTest(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "remark") String remark);
      }
      ```

   3. 新增`UserFileUploadController`

      ```java
      package com.fei.springcloud.controller;
      
      import com.fei.springcloud.common.dto.ResponseData;
      import com.fei.springcloud.common.enums.ErrorCodeEnum;
      import com.fei.springcloud.feign.UserFileUploadClient;
      import lombok.extern.slf4j.Slf4j;
      import org.springframework.beans.factory.annotation.Autowired;
      import org.springframework.web.bind.annotation.RequestMapping;
      import org.springframework.web.bind.annotation.RequestMethod;
      import org.springframework.web.bind.annotation.RequestPart;
      import org.springframework.web.bind.annotation.RestController;
      import org.springframework.web.multipart.MultipartFile;
      
      /**
       * @description:  实现文件上传功能
       * @author: qpf
       * @date: 2022/5/1
       * @version: 1.0
       */
      @Slf4j
      @RestController
      @RequestMapping("/user/file")
      public class UserFileUploadController {
      
          @Autowired
          private UserFileUploadClient feignUserService;
      
          @RequestMapping(value = "/fileUploadTest",method = RequestMethod.POST)
          public ResponseData<?> fileUploadTest(@RequestPart(value = "file") MultipartFile file, String remark){
              log.info("开始文件上传");
              if(file == null || file.getSize()<=0){
                  log.info("文件大小不满足要求");
                  return ResponseData.response(ErrorCodeEnum.FAIL,null);
              }
              ResponseData<?> responseData = feignUserService.fileUploadTest(file,remark);
              log.info("文件上传结束");
              return responseData;
          }
      }
      ```




#### 5.3.3 Hystrix 熔断机制

> 参考链接1: [SpringCloud系列之服务容错保护Netflix Hystrix - smileNicky - 博客园 (cnblogs.com)](https://www.cnblogs.com/mzq123/p/13439385.html)
>
> 参考链接2: [SpringCloud 使用Hystrix 实现容错 | Thread丶博客 (thread-blog.org)](http://thread-blog.org/2021/09/04/SpringCloud/SpringCloud-使用Hystrix-实现容错/)

##### 5.3.3.1 实现容错机制

1. 添加依赖

   ```xml
   <!--添加熔断机制 hystrix-->
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
   </dependency>
   ```

2. 在主启动类上添加注解`@EnableCircuitBreaker`

3. 在方法上实现异常时调用

   ```java
   package com.fei.springcloud.controller;
   
   import com.fei.springcloud.feign.UseCompanyClient;
   import com.fei.springcloud.pojo.CompanyTbl;
   import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.web.bind.annotation.*;
   
   import java.util.Arrays;
   import java.util.List;
   import java.util.Map;
   
   /**
    * @description:
    *                 1. 不基于BasicAuth的认证方式
    *  *              2. 使用 feign 调用 spring-cloud-provider 服务提供者的 Restful 接口
    * @author: qpf
    * @date: 2022/4/16
    * @version: 1.0
    */
   @RestController
   @RequestMapping("/consumer")
   public class CompanyController {
   
       @Autowired
       UseCompanyClient useCompanyClient;
   
       @GetMapping("/cloud/get/{id}")
       public CompanyTbl getCompleteById(@PathVariable("id") int id){
           return useCompanyClient.selectById(id);
       }
   
       @GetMapping("/cloud/all")
       public List<CompanyTbl> getAll(){
           return useCompanyClient.getAll();
       }
   
       @PostMapping("/cloud/add")
       public Map addCompany(@RequestBody CompanyTbl companyTbl){
           return useCompanyClient.addCompany(companyTbl);
       }
   
       @GetMapping("/cloud/delete/{id}")
       public Map deleteCompany(@PathVariable("id") int id){
           return useCompanyClient.deleteCompany(id);
       }
   
       /**
        * 测试熔断机制
        * @return
        */
       @GetMapping("/testHystrix/all")
       @HystrixCommand(fallbackMethod = "apiFallback")
       public List<CompanyTbl> testHystrix(){
           int i = 1/0;
           return useCompanyClient.getAll();
       }
   
       /**
        * 熔断机制的回调方法
        * @return
        */
       public List<CompanyTbl> apiFallback(){
           CompanyTbl companyTbl = new CompanyTbl();
           companyTbl.setId(0);
           companyTbl.setName("testHystrix");
           companyTbl.setAddress("熔断机制");
           companyTbl.setCount(9999999);
           companyTbl.setDbSource("this is error info");
           return Arrays.asList(companyTbl);
       }
   }
   ```

##### 5.3.3.2 监控面板

1. 添加依赖

   ```xml
           <!-- hystrix面板-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
           </dependency>
           <!--actuator 监控面板-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-actuator</artifactId>
           </dependency>
   ```

2. 添加配置信息

   ```yaml
   ###################[ spring-boot-starter-actuator 配置 ]##########################
   # 指定hystrix的配置文件
   management:
     endpoints:
       web:
         exposure:
           include: "*"  #配置暴露全部的监控接口
         # 自定义配置暴露的监控接口,默认为 /actuator
   #      base-path: /
     endpoint:
       health:
         enabled: true
         show-details: always #配置健康接口显示详细信息
   ###################[ spring-boot-starter-actuator 配置 ]##########################
   # hystrix的监控面板配置
   hystrix:
     dashboard:
       proxy-stream-allow-list: "*"
         
   ```

3. 在主启动类上面添加注解: `@EnableHystrixDashboard`

   ```java
   package com.fei.springcloud;
   
   import com.fei.customize.FeignConfiguration;
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
   import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
   import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
   import org.springframework.cloud.openfeign.EnableFeignClients;
   
   /**
    * @description:
    * @author: qpf
    * @date: 2022/4/16
    * @version: 1.0
    */
   @SpringBootApplication
   @EnableEurekaClient
   // @EnableFeignClients
   //开启全局的自定义Feign配置
   @EnableFeignClients(defaultConfiguration = FeignConfiguration.class)
   //开启全局的熔断器,支持服务降级
   @EnableCircuitBreaker
   // 开启全局的Hystrix监控,以及控制台
   @EnableHystrixDashboard
   public class SpringCloudConsumerApplication {
       public static void main(String[] args) {
           SpringApplication.run(SpringCloudConsumerApplication.class,args);
       }
   }
   ```

4. 访问地址: 

   - `Hystrix Dashboard`:  http://host:port/hystrix,例如: [Hystrix Dashboard](http://localhost:8082/hystrix)

     ![](https://s3.bmp.ovh/imgs/2022/05/01/d39980fe7b43d5a8.jpg)

   - `Single Hystrix App(单个应用):`      https://hystrix-app:port/actuator/hystrix.stream,   			例如:http://localhost:8081/actuator/hystrix.stream

     

     **a. 期待结果**

     ![](https://s3.bmp.ovh/imgs/2022/05/01/489e6150c46c4932.jpg)

     **b.   出现异常:** `Unable to connect to Command Metric Stream`

     ![](https://s3.bmp.ovh/imgs/2022/05/01/d8dddec1f451b697.jpg)

     **c. 调用熔断接口即可:**  http://localhost:8081/consumer/testHystrix/all

     ```java
         /**
          * 测试熔断机制
          * @return
          */
         @GetMapping("/testHystrix/all")
         @HystrixCommand(fallbackMethod = "apiFallback")
         public List<CompanyTbl> testHystrix(){
             int i = 1/0;
             return useCompanyClient.getAll();
         }
     
         /**
          * 熔断机制的回调方法
          * @return
          */
         public List<CompanyTbl> apiFallback(){
             CompanyTbl companyTbl = new CompanyTbl();
             companyTbl.setId(0);
             companyTbl.setName("testHystrix");
             companyTbl.setAddress("熔断机制");
             companyTbl.setCount(9999999);
             companyTbl.setDbSource("this is error info");
             return Arrays.asList(companyTbl);
         }
     ```

     **d. 响应结果**

     ```json
     [
         {
             "id": 0,
             "name": "testHystrix",
             "count": 9999999,
             "address": "熔断机制",
             "dbSource": "this is error info"
         }
     ]
     ```

     

     


##### 5.3.3.3 自定义信息

> 针对: http://host:port/actuator/info

1. 在 **application.yml** 中添加以下信息即可,`信息可自由添加`:

   ```yaml
   #  http://host:port/actuator/info 的访问信息
   info:
     name: spring-cloud-xxxxxx
     version: 1.0-SNAPSHOT
     port: 8082
     featrues: "监控集群用的...所有信息都是自定义的"
   ```

2.   响应数据

   ```json
   {
       "name": "spring-cloud-xxxxxx",
       "version": "1.0-SNAPSHOT",
       "port": 8082,
       "featrues": "监控集群用的...所有信息都是自定义的",
   }
   ```

   

##### 5.3.3.4 Turbine 聚合监控数据

1. 添加依赖

   ```xml
           <!--引入Turbine监控多个微服务-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-netflix-turbine</artifactId>
           </dependency>
   ```

2. 在 **application.yml** 中添加以下信息:

   ```yaml
   #监控集群
   turbine:
     app-config: spring-cloud-consumer,spring-cloud-provider
     cluster-name-expression: "'default'"
   ```

3. 在主启动类添加注解: `@EnableTurbine`

4. 新创建一个模块`spring-cloud-dashboard`,用于监控,以下为代码:

   **a. pom.xml**

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <parent>
           <artifactId>springCloud</artifactId>
           <groupId>org.fei</groupId>
           <version>1.0-SNAPSHOT</version>
       </parent>
       <modelVersion>4.0.0</modelVersion>
   
       <artifactId>spring-cloud-dashboard</artifactId>
   
       <properties>
           <maven.compiler.source>8</maven.compiler.source>
           <maven.compiler.target>8</maven.compiler.target>
       </properties>
   
       <dependencies>
           <!--基础依赖相关-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter</artifactId>
               <exclusions>
                   <exclusion>
                       <artifactId>slf4j-api</artifactId>
                       <groupId>org.slf4j</groupId>
                   </exclusion>
               </exclusions>
           </dependency>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-test</artifactId>
           </dependency>
           <dependency>
               <groupId>org.projectlombok</groupId>
               <artifactId>lombok</artifactId>
           </dependency>
           <dependency>
               <groupId>org.fei</groupId>
               <artifactId>spring-cloud-api</artifactId>
               <version>1.0-SNAPSHOT</version>
           </dependency>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-web</artifactId>
           </dependency>
           <!--添加熔断机制 hystrix面板-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
           </dependency>
           <!--actuator 监控面板-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-actuator</artifactId>
           </dependency>
           <!--引入Turbine监控多个微服务-->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-netflix-turbine</artifactId>
           </dependency>
       </dependencies>
   
   </project>
   ```

   b. **application.yml** 

   ```yaml
   server:
     port: 8082
   
   spring:
     application:
       name: spring-cloud-dashboard
   
   #eureka配置相关
   customize:
     eureka:
       host:
         host1: eureka-server
         host2: eureka-server2
         host3: eureka-server3
       port:
         port1: 7001
         port2: 7002
         port3: 7003
   
   #eureka配置相关
   eureka:
     instance:
       hostname: localhost
     client:
       # 向服务端注册自己
       register-with-eureka: true
       # 从Eureka Server获取注册的服务信息
       fetch-registry: true
       service-url:
         defaultZone: http://${customize.eureka.host.host1}:${customize.eureka.port.port1}/eureka,http://${customize.eureka.host.host2}:${customize.eureka.port.port2}/eureka,http://${customize.eureka.host.host3}:${customize.eureka.port.port3}/eureka
   
   # 指定hystrix的配置文件
   management:
     endpoints:
       web:
         exposure:
           include: "*"  #配置暴露全部的监控接口
         # 自定义配置暴露的监控接口,默认为 /actuator
   #      base-path: /
     endpoint:
       health:
         enabled: true
         show-details: always #配置健康接口显示详细信息
   
   
   # hystrix的监控面板配置
   hystrix:
     dashboard:
       proxy-stream-allow-list: "*"
   
   #监控集群
   turbine:
     app-config: spring-cloud-consumer,spring-cloud-provider
     cluster-name-expression: "'default'"
   
   
   #  http://host:port/actuator/info 的访问信息
   info:
     name: spring-cloud-dashboard
     version: 1.0-SNAPSHOT
     port: 8082
     featrues: "监控集群用的...所有信息都是自定义的"
     dashboard-url: http://localhost:8082/hystrix
     custom-cluster: http://localhost:8082/turbine.stream
     single-hystrix:
       consumer:
         url: http://localhost:8081/actuator/hystrix.stream
         name: spring-cloud-consumer
         cluster: default
       provider:
         url: http://localhost:8001/actuator/hystrix.stream,http://localhost:8002/actuator/hystrix.stream,http://localhost:8003/actuator/hystrix.stream
         name: spring-cloud-provider
         cluster: default
   ```

   **c. 主启动类**

   ```java
   package com.fei.springcloud;
   
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
   import org.springframework.cloud.netflix.turbine.EnableTurbine;
   
   /**
    * @description:
    * @author: qpf
    * @date: 2022/5/1
    * @version: 1.0
    */
   
   // 开启Turbine,访问集群的统计信息
   @EnableTurbine
   // 开启全局的Hystrix监控,以及控制台
   @EnableHystrixDashboard
   @SpringBootApplication
   public class SpringCloudDashboardApplication {
       public static void main(String[] args) {
           SpringApplication.run(SpringCloudDashboardApplication.class, args);
       }
   }
   ```

5. 主要用于监控,因此可访问:

   - dashboard:   http://localhost:8082/hystrix

   - 聚合链接,即包含集群信息: http://localhost:8082/turbine.stream

     

## 六. spring-cloud config 配置管理工具包

> 参考链接: [Spring Cloud Config ](https://www.cnblogs.com/fengzheng/p/11242128.html)

### 6.1 创建服务端

> 新建项目: spring-cloud-config-server , 并注册到 eureka中

#### 6.1.1 pom文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springCloud</artifactId>
        <groupId>org.fei</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-cloud-config-server</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- spring cloud config 服务端包 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>

        <!-- eureka client 端包 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--actuator 监控面板-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>

</project>
```

#### 6.1.2 application.yml 文件

```yaml
server:
  port: 8010

spring:
  application:
    name: spring-cloud-config-server
  cloud:
    config:
      server:
        git:
          uri: https://github.com/YearningLife/spring-cloud.git #配置文件所在仓库
          default-label: dev #配置文件分支
          search-paths: config  #配置文件所在根目录
          # 仓库为公开使用, 因此不需要用户名和密码,设为空即可
#          username:
#          password:
          # 参考: https://stackoverflow.com/questions/63191584/spring-cloud-config-server-multiplejgitenvironmentrepository-could-not-fetch
          refresh-rate: 5 #配置文件刷新频率,例如: 5s 刷新一次
          clone-on-start: true #启动时克隆配置文件
          skip-ssl-validation: true #跳过ssl验证
#          控制台报错: cannot open git-upload-pack, 配置本地代理,参考: https://github.com/spring-cloud/spring-cloud-config/issues/2048
          proxy: #代理
            http:
              host: localhost
              port: 7890
#eureka配置相关
customize:
  eureka:
    host:
      host1: eureka-server
      host2: eureka-server2
      host3: eureka-server3
    port:
      port1: 7001
      port2: 7002
      port3: 7003

#eureka配置相关
eureka:
  instance:
    hostname: localhost
  client:
    # 向服务端注册自己
    register-with-eureka: true
    # 从Eureka Server获取注册的服务信息
    fetch-registry: true
    service-url:
      defaultZone: http://${customize.eureka.host.host1}:${customize.eureka.port.port1}/eureka,http://${customize.eureka.host.host2}:${customize.eureka.port.port2}/eureka,http://${customize.eureka.host.host3}:${customize.eureka.port.port3}/eureka

# 指定hystrix的配置文件
management:
  endpoints:
    web:
      exposure:
        include: "*"  #配置暴露全部的监控接口
      # 自定义配置暴露的监控接口,默认为 /actuator
  #      base-path: /
  endpoint:
    health:
      enabled: true
      show-details: always #配置健康接口显示详细信息

#  http://host:port/actuator/info 的访问信息
info:
  name: ${spring.application.name}
  version: 1.0-SNAPSHOT
  port: ${server.port}
```

#### 6.1.3 创建主启动类

```java
package com.fei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description: spring cloud config server 启动类
 * @author: qpf
 * @date: 2022/5/7
 * @version: 1.0
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class CloudConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigServerApplication.class, args);
    }
}
```

### 6.2 创建客户端

> 修改项目: spring-cloud-consumer-feign-81,用作示例, 其它项目需要添加时, 用法相同

#### 6.2.1 添加依赖

```xml
        <!--spring cloud config 依赖-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>

```

#### 6.2.2 创建 bootstrap.yml 文件

> 1. eureka配置需要放在 bootstrap.yml 
> 2. 参考链接: [bootstrap.yml比application.yml优先级高 ](https://www.cnblogs.com/youcong/p/13939466.html)
>
> 

```yaml
spring:
  profiles:
    active: prod

# bootstrap.yml 比 application.yml 先启动, 所以需要在 bootstrap.yml 中配置 eureka服务器的信息

#eureka配置相关
customize:
  eureka:
    host:
      host1: eureka-server
      host2: eureka-server2
      host3: eureka-server3
    port:
      port1: 7001
      port2: 7002
      port3: 7003

#eureka配置相关
eureka:
  instance:
    hostname: localhost
  client:
    # 向服务端注册自己
    register-with-eureka: true
    # 从Eureka Server获取注册的服务信息
    fetch-registry: true
    service-url:
      defaultZone: http://${customize.eureka.host.host1}:${customize.eureka.port.port1}/eureka,http://${customize.eureka.host.host2}:${customize.eureka.port.port2}/eureka,http://${customize.eureka.host.host3}:${customize.eureka.port.port3}/eureka

---
spring:
  profiles: prod
  cloud:
    config:
      label: dev #指定远程仓库分支
      profile: prod #指定版本 本例中建立了dev 和 prod两个版本
      discovery:
        enabled: true
        service-id: spring-cloud-config-server


---
spring:
  profiles: dev
  cloud:
    config:
      label: dev  #指定远程仓库分支
      profile: dev   #指定版本 本例中建立了dev 和 prod两个版本
      discovery:
        enabled: true  # 开启
        service-id: spring-cloud-config-server # 指定配置中心服务端的server-id

```

#### 6.2.3 application.yml文件

> info : 仅涉及变动的部分

```yaml
#eureka配置相关
#customize:
#  eureka:
#    host:
#      host1: eureka-server
#      host2: eureka-server2
#      host3: eureka-server3
#    port:
#      port1: 7001
#      port2: 7002
#      port3: 7003

##eureka配置相关
#eureka:
#  instance:
#    hostname: localhost
#  client:
#    # 向服务端注册自己
#    register-with-eureka: true
#    # 从Eureka Server获取注册的服务信息
#    fetch-registry: true
#    service-url:
#      defaultZone: http://${customize.eureka.host.host1}:${customize.eureka.port.port1}/eureka,http://${customize.eureka.host.host2}:${customize.eureka.port.port2}/eureka,http://${customize.eureka.host.host3}:${customize.eureka.port.port3}/eureka

# 远程仓库返回的数据模型
data:
  env: NaN
  user:
    username: NaN
    password: NaN
    tips: 'NaN'

#  http://host:port/actuator/info 的访问信息
info:
  name: ${spring.application.name}
  version: 1.0-SNAPSHOT
  port: 8081
  config-server:
    id: spring-cloud-config-server
    port: 8010
    # 返回的数据模型
    model:
      url:
        - http://localhost:8010/${spring.application.name}/dev/master
        - http://localhost:8010/${spring.application.name}/prod
        - http://localhost:8010/${spring.application.name}-dev.yml
        - http://localhost:8010/${spring.application.name}-prod.yml
        - http://localhost:8010/master/${spring.application.name}-prod.yml
```

#### 6.2.4  添加映射对象

> info: 用作测试

```java
package com.fei.springcloud.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description: 映射 config 目录下的配置字段
 *                  例如: spring-cloud-consumer-dev.yml 里面的内容
 * @author: qpf
 * @date: 2022/5/7
 * @version: 1.0
 */

@Data
@Component
@PropertySource(encoding = "UTF-8", value = {"classpath:application.yml"})
public class GitConfig {

    @Value("${data.env}")
    private String env;

    @Value("${data.user.username}")
    private String username;

    @Value("${data.user.password}")
    private String password;

    @Value("${data.user.tips}")
    private String tips;

}
```

#### 6.2.5 添加controller

> info: 用作测试

```java
package com.fei.springcloud.controller;

import com.fei.springcloud.vo.GitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用于查询远程git配置信息
 * @author: qpf
 * @date: 2022/5/7
 * @version: 1.0
 */
@RestController
@RefreshScope
public class GitController {

    @Autowired
    private GitConfig gitConfig;

    @GetMapping(value = "show")
    public Object show(){
        return gitConfig;
    }

}
```



## 七. zuul 动态 路由

> 参考链接-1: [入门参考](https://juejin.cn/post/6844904055781720078)
>
> 参考链接-2: [官方文档](https://www.springcloud.cc/spring-cloud-greenwich.html#_router_and_filter_zuul)
>
> 参考链接-3 : [zuul参考文档.重点](https://enfangzhong.github.io/2019/12/12/Spring Cloud快速开发入门第02篇/#3-Zuul网关)

### 7.1 route配置

1. 第一种

   ```yaml
   zuul:
     ignored-services: '*'
     routes:
        server-provider:   #配置服注册在Eureka的服务名称，指的是服务中spring.application.name的配置
            #将/server-provider/开头的都转发到服务名为server-provider的服务上，
         path:   /server-provider/*  
   ```

2. 第二种

   ```yaml
   zuul:
     ignored-services: '*'
     routes:
        server-a:   #名称随便起
            #将/server-provider/开头的都转发到服务名为server-provider的服务上，
         path:   /server-provider/*  
          #配置服注册在Eureka的服务名称，指的是服务中spring.application.name的配置
         serviceId:  server-provider  
   ```

3. 第三种

   ```yaml
   zuul:
     ignored-services: '*'
     routes:
        server-b:   #名称随便起
            #将/server-provider/开头的都转发到url为 http://localhost:5168/  #的服务上，
         path:   /server-provider/*  
          #配置服注册在Eureka的服务名称，指的是服务中spring.application.name的配置
         url:  http://localhost:5168/  #不通过Eureka
   ```

### 7.2 创建新项目

> info: 
>
> 1. 项目名称为: spring-cloud-gateway-zuul
> 2. 将项目注册到: eureka,实现对应用的负载均衡调用
> 3. 使用ZuulFilter 实现过滤器操作, 例如登录时的权限认证操作

#### 7.2.1 pom 文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springCloud</artifactId>
        <groupId>org.fei</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-cloud-gateway-zuul</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
        </dependency>

    </dependencies>

</project>
```

#### 7.2.2 yaml文件

```yaml
server:
  port: 8011

spring:
  application:
    name: spring-cloud-gateway-zuul

customize:
  eureka:
    host:
      host1: eureka-server
      host2: eureka-server2
      host3: eureka-server3
    port:
      port1: 7001
      port2: 7002
      port3: 7003

#eureka配置相关
eureka:
  instance:
    hostname: localhost
  client:
    # 向服务端注册自己
    register-with-eureka: true
    # 从Eureka Server获取注册的服务信息
    fetch-registry: true
    service-url:
      defaultZone: http://${customize.eureka.host.host1}:${customize.eureka.port.port1}/eureka,http://${customize.eureka.host.host2}:${customize.eureka.port.port2}/eureka

# 路由相关 参考: https://juejin.cn/post/6844904055781720078
#zuul:
##  zuul.ignoredServices=* 表示禁用默认路由，只认我们自己配置的路由.
#  ignored-services: '*'
#  routes:
#    server-test: #名称随便起
#      #将/users/开头的都转发到url为 https://www.bing.com/?mkt=zh-CN  #的服务上，
#      path: /users/**
#      #配置服注册在Eureka的服务名称，指的是服务中spring.application.name的配置
##      url: https://www.bing.com/?mkt=zh-CN  #不通过Eureka
#      serviceId: spring-cloud-provider

# 简化写法
# 参考: https://enfangzhong.github.io/2019/12/12/Spring%20Cloud%E5%BF%AB%E9%80%9F%E5%BC%80%E5%8F%91%E5%85%A5%E9%97%A8%E7%AC%AC02%E7%AF%87/#3-Zuul%E7%BD%91%E5%85%B3
zuul:
  routes:
    spring-cloud-provider: /provider/**
    spring-cloud-consumer: /consumer/**
  prefix: /api

# 设置服务熔断时间
hystrix:
  command:
    default:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 2000 # 设置hystrix的超时时间为6000ms
```

#### 7.2.3 主程序

```java
package com.fei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @description: 测试zuul网关
 * @author: qpf
 * @date: 2022/5/9
 * @version: 1.0
 */
@EnableZuulProxy
@SpringCloudApplication
public class GatewayZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayZuulApplication.class, args);
    }
}
```

#### 7.2.4 过滤器

> info: 
>
> 1. 配置过滤器`前`, 调用接口为: http://localhost:8011/api/provider/cloud/all 
> 2. 配置过滤器`后`, 调用接口为: http://localhost:8011/api/provider/cloud/all?access-token=zhangsan

```java
package com.fei.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 定义过滤器类
 * @author: qpf
 * @date: 2022/5/18
 * @version: 1.0
 */
@Component
public class LoginFilter extends ZuulFilter {
    /**
     * 过滤器类型，前置过滤器
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 该过滤器是否生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 登陆校验逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // 获取zuul提供的上下文对象
        RequestContext context = RequestContext.getCurrentContext();
        // 从上下文对象中获取请求对象
        HttpServletRequest request = context.getRequest();
        // 获取token信息
        String token = request.getParameter("access-token");
        // 判断
        if (StringUtils.isBlank(token)) {
            // 过滤该请求，不对其进行路由
            context.setSendZuulResponse(false);
            // 设置响应状态码，401
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            // 设置响应信息
            context.setResponseBody("{\"status\":\"401\", \"text\":\"request error!\"}");
        }
        // 校验通过，把登陆信息放入上下文信息，继续向后执行
        context.set("token", token);
        return null;
    }
}
```



## 八. spring-cloud bus 事件/消息总线



















