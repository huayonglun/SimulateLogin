## 模拟登录的Java爬虫实现

### 目标网站
　　东北林业大学教务处

### 目标内容
　　目前为止大学各科成绩

### 输出形式
　　得到html文件，以表格的形式展示成绩信息，并录入到数据库中

### 配置内容
　　找到src目录下的login_info.properties文件配置用户名和密码；<br/>
　　找到src目录下的c3p0-config.xml进行数据库的配置

### 所需依赖
　　jsoup-1.8.3.jar、mysql-connector-java-5.1.28-bin.jar、c3p0-0.9.2-pre1.jar等jar包，需要的包可以在possible_used_jars目录下找到
