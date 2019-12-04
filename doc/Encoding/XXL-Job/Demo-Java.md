# 0.前置说明
[xxl-job 安装说明及常见说明: `/doc/Linux/XXL-Job/1.安装过程.md`](https://github.com/Guosd/Sinosoft/blob/master/doc/Linux/4.XXL-Job/1.%E5%AE%89%E8%A3%85%E8%BF%87%E7%A8%8B.md)

[官方文档路径](https://github.com/xuxueli/xxl-job/blob/master/doc/XXL-JOB官方文档.md)

# 1. 项目依赖
```xml
	<dependency>
		<groupId>com.xuxueli</groupId>
		<artifactId>xxl-job-core</artifactId>
		<version>2.0.2</version>
	</dependency>
```

# 2.XXL配置

> application.yml 中配置

```yaml
### xxl-job admin address list, such as "http://address" or "http://address01,http://address02"
xxl:
  job:
    admin:
      addresses: http://192.168.144.136:8088/xxl-job-admin  # 控制台的地址
    executor:
      appname: xxl-job-executor-sample  # 这个是XXL-Job中配置的
      ip: 192.168.144.1									# 这个写的是本地地址，如果不写默认获取
      port: 9999
    accessToken: ''
```
> 读取application.yml 中的配置
```java
/**
 * xxl-job config
 */
@Configuration
public class XxlJobConfig {
    private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.{}",adminAddresses);
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppName(appName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken("");
        xxlJobSpringExecutor.setLogPath("/tmp/xxl.log");
        xxlJobSpringExecutor.setLogRetentionDays(1);

        return xxlJobSpringExecutor;
    }
}
```
> 开发定时任务。
```java
@Slf4j
@JobHandler(value="demoJobHandler")
@Component
public class XXLJobHandler extends IJobHandler {
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        for (int i = 0; i < 5; i++) {
            XxlJobLogger.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        return SUCCESS;
    }
}
```

# 3.xxl-job-admin中配置

## 3.1 配置执行器

![配置执行器](https://raw.githubusercontent.com/Guosd/Others/master/1575425476973.jpg)

## 3.2 配置任务

![配置配置任务](https://raw.githubusercontent.com/Guosd/Others/master/1575426116435.jpg)

## 3.3 启动任务

![启动任务](https://raw.githubusercontent.com/Guosd/Others/master/1575426264938.jpg)



