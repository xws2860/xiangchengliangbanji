# 乡城凉拌鸡
- 乡城凉拌鸡项目是乡城县的水利数字沙盘项目，主要利用了ArcGIS Server提供的MapServer服务，ArcGIS官方提供的MapSDK for Javascript技术、SpringBoot技术、Vue3.0开发技术。
- 数据库使用了Mysql

- 可以使用Dockerfile运行该项目，也可以直接本机运行，本项目依赖JDK11.0，但未使用超越1.8的java新特性。
- 数据库中的功能模块表中文理解参照HELP.md

- 项目相关信息参照 resources目录下的doc.

- 目录xiangchengfront是前端目录, 用vite + vue3 + element-Plus,使用pnpm工具。


## 后端如何运行
- 首先将代码clone在本地，将src/main/resources/application-example.properties复制一个改名为application.properties
- 修改下列配置：
```properties
# 配置数据源
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://[你自己的url]:3306/xiangchengliangbanji
spring.datasource.username=root
spring.datasource.password=[你自己的密码]
```
- 可选在build.gradle中修改下列配置添加aliyun的镜像下载地址:
```properties
repositories {
    mavenCentral()
}
```
修改为：
```properties
repositories {
    maven { url 'https://maven.aliyun.com/repository/public' }
    mavenCentral()
}
```
- 使用gradle同步依赖，然后直接运行后台

## 前端如何运行
- 切换至前端目录
- 执行安装命令:
```shell
npm install pnpm -g
```
- 执行安装命令
```shell
pnpm install
```
- 开始运行
```shell
pnpm run dev
```
- 需要注意：
> .env中配置了环境变量
```
VITE_BASE_URL=http://127.0.0.1:8081/
VITE_MAP_SERVER_URL=https://10.137.15.184:6443/arcgis/rest/services/xiangchengliangbanji/MapServer
```
> 其中VITE_BASE_URL为后端访问的地址
> VITE_MAP_SERVER_URL为arcgis server提供的MapServer地址。