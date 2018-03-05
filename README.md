# security
最近准备换个工作，趁着放假，把几年来的工作经验做了一下总结，尤其是技术方面，收获很多。由于时间有限，简单实现了一个用户权限管理系统。仓库中是完整的eclipse maven project，下载后稍作配置即可运行，涉及到的jar都是使用最新的，使用了jdk1.8
## 主要功能
security主要实现了如下功能：
1. 用户登录注销功能
2. 用户管理功能，包括用户的增删改查，以及赋权
3. 角色管理功能，包括角色的增删改查
4. 权限查看功能
## 页面展示
### 登录页面
### 主页面
### 用户管理页面
### 角色管理页面
### 权限查询页面

## 项目架构
security采用经典的mvc架构实现，使用了spring、springmvc、mybatis、freemarker、jquery。具体的
### 数据库
数据存储使用了mysql数据库，数据源使用了c3p0，持久化框架使用mybatis
共涉及到三张表
* 用户表 account

| Field      | Type        | Null | Key | Default           | Extra                       |  
|------------|-------------|------|-----|-------------------|-----------------------------|  
| id         | int(11)     | NO   | PRI | NULL              | auto_increment              |  
| name       | varchar(50) | NO   | PRI | NULL              |                             |  
| password   | varchar(10) | NO   |     | NULL              |                             |  
| department | varchar(50) | YES  |     | NULL              |                             |  
| enable     | tinyint(1)  | YES  |     | NULL              |                             |  
| createtime | timestamp   | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |  
* 角色表 role  

| Field      | Type        | Null | Key | Default           | Extra                       |  
|------------|-------------|------|-----|-------------------|-----------------------------|  
| id         | int(11)     | NO   | PRI | NULL              | auto_increment              |  
| name       | varchar(50) | NO   | PRI | NULL              |                             |  
| createtime | timestamp   | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |  
| permission | int(11)     | YES  |     | 0                 |                             |  
* 用户角色表 accountrole  

| Field | Type    | Null | Key | Default | Extra          |  
|-------|---------|------|-----|---------|----------------|  
| id    | int(11) | NO   | PRI | NULL    | auto_increment |  
| aid   | int(11) | NO   | PRI | NULL    |                |  
| rid   | int(11) | NO   | PRI | NULL    |                | 

权限是程序内预定的，不能增删改
### 应用
服务器应用逻辑，通过springmvc实现，通过spring将springmvc、mybatis、freemarker集成在一起
### 视图
视图层通过freemarker来实现，由于时间有限没有使用纯javascript来实现界面
## 遇到的一些有趣的问题
1. 表单提交后controller里面的实体类型参数装在失败
原因是实体中有Date属性，spring使用直接使用Date的构造函数失败，需要实现spring的Converter接口可以解决这个问题
2. 使用aop为controller定制通用的逻辑时发现拦截失败
原因默认的controller使用jdk动态代理，导致拦截失败，将<aop:aspectj-autoproxy proxy-target-class="true"/>中的proxy-target-class配置为true，使用cglib动态代理解决这个问题
