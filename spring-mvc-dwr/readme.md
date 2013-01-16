# Spring MVC + DWR 整合示例说明
这个例子是作为Spring MVC 整合DWR3的一个示例应用，例子是用于后台读取一个日志文件并近实时的将日志内容打印到浏览器客户端上。当然也可以读取其他文件，这里主要是读取txt文本信息。

## 版本信息
1. Spring 版本为 3.2.0.RELEASE 
2. DWR版本为 3.0.RC2

## 示例运行说明
1. 示例结构

		.
		├── build.gradle
		├── readme.md
		├── src
		│   ├── main
		│   │   ├── java
		│   │   │   ├── controllers
		│   │   │   │   ├── IndexController.java
		│   │   │   │   └── dwr
		│   │   │   ├── models
		│   │   │   │   └── ReadLog.java
		│   │   │   └── services
		│   │   │       ├── LogReadService.java
		│   │   │       ├── event
		│   │   │       │   ├── ReadLogEvent.java
		│   │   │       │   └── ReadLogListener.java
		│   │   │       └── impl
		│   │   │           ├── LogReadRunnable.java
		│   │   │           ├── LogReadServiceImpl.java
		│   │   │           └── ReadLogService.java
		│   │   ├── resources
		│   │   │   └── application.properties
		│   │   └── webapp
		│   │       ├── WEB-INF
		│   │       │   ├── applicationContext.xml
		│   │       │   ├── dwr-servlet.xml
		│   │       │   ├── smd-servlet.xml
		│   │       │   ├── views
		│   │       │   │   └── index.ftl
		│   │       │   └── web.xml
		│   │       └── resources
		│   │           └── scripts
		│   │               └── jquery
		│   │                   ├── jquery-min.js
		│   │                   ├── jquery.js
		│   │                   ├── jqueryui-min.js
		│   │                   └── jqueryui.js
		│   └── test
		│       ├── java
		│       │   └── services
		│       │       └── impl
		│       │           └── LogReadServiceImplTest.java
		│       └── resources

    构建文件：
    
    `build.gradle`为gradle的构建脚本，如果需要，则需要安装gradle，安装gradle移步[gradle](http://www.gradle.org)观看入门指南。    
    在根目录下，可以执行 `gradle idea` 转换为idea工程.

2. 运行说明
    
    1. 在`src/main/resources/application.properties`的配置文件中找到`logFolder`选项，修改为自己的文件夹，比如示例中的`osy/logs`
    2. 启动应用程序，可以通过`gradle jetty:run`来启动jetty来运行示例
    3. 打开浏览器访问[http://localhost:3000/spring-mvc-dwr/index/{logFolder file name}](http://localhost:3000/spring-mvc-dwr/index/debug),`{logFolder file name}`这个是`logFolder`中的txt文件。
    
## DWR介绍
DWR (Direct Web Remoting) 是一个用于改善 Web 页面与 Java 类交互的远程服务器端 Ajax 开源框架。DWR 可以动态生成基于 Java 类的 JavaScript 代码。对于公开的每个类，DWR 帮我们做好了创建对象、发送数据、接受响应等许多繁琐的工作，大大节省了客户端代码和工作量。

### DWR注解
1. @RemoteProxy

    该注解表示远程调用的类，也就是告诉DWR，所注解的类是要暴露出来的。
    
2. @RemoteMethod 
    
   该注解表示要远程调用的方法。只有加了这个注解的方式才会被暴露
   
3. @DataTransferObject
    
    对JavaBean的转换
    
### DWR配置

1. <dwr:annotation-scan>

   定义一些注解扫描的参数：
   
   * base-package：指定的扫描的包；
   * regex：将扫描仪类路径中使用正则表达式
   * scanRemoteProxy：DWR扫描远程代理类，默认值为true
   * scanDataTransferObject：DWR扫描转换器，默认值为 true。
   * scanGlobalFilter-默认值为 true
   
2. `<dwr:url-mapping />`

   要求DWR将util.js和engine.js映射到dwrController，同时可以访问：` /call/**, /interface/**`路径。
   
3. `<dwr:configuration />`

   用于模仿在 dwr.xml 中可用的配置的行为
   
4. `<dwr:controller id="dwrController" debug="true" />`

   部署时将debug设置为false,debug为true，可以访问`/dwr/index.html`的测试页面。还可以设置以下参数信息
   
           <dwr:controller id="dwrController" debug="true">
		        <!-- 如果有反向Ajax调用 -->
		        <dwr:config-param name="activeReverseAjaxEnabled" value="true" />
		        <!--<dwr:config-param name="pollAndCometEnabled" value="true" />-->
	        </dwr:controller>

   具体可以配置项如下：
   
   参数名称         |      默认值     |     说明
   ------------    | -------------| ----------
   jsonpEnabled    | false        | 设置为`true`表示可以使DWR调用JSONP跨域调用
   allowGetForSafariButMakeForgeryEasier | false | 设置为`true`可以让DWR工作于Safari1.x中
   crossDomainSessionSecurity   |  true     | 设置为`false`，可以访问其他域的请求，需要注意这是一个重大的安全风险，一般不要设置为`false`
   allowScriptTagRemoting    |  true     | 一般不要设置为`false`
   scriptSessionTimeout    | 1800000（30分钟）| `scriptSession`的超时时间
   maxCallCount    |  20     | 单个批处理中，可以call的最大数目。

## 其它问题
TODO