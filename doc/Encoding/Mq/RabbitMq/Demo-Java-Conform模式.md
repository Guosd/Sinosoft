# Conform模式 的Java Demo

## 1 rabbitmq-producer

### 1.1 pom.xml

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

### 1.2 application.yml

```yaml
spring:
  rabbitmq:
    addresses: 192.168.1.76:5672
    username: guest
    password: guest
    virtual-host: /
    connection-timeout: 15000
    
    publisher-confirms: true
    publisher-returns: true
    template:
      mandatory: true #使用return call back的时候，要配置成true
```

### 1.3 MyConfig.java

```java
@Configuration
@ComponentScan({"com.qihuan.rabbitmq.*"})
public class MyConfig {
}
```

### 1.4 MyEntity.java

```java
public class MyEntity implements Serializable {
	
	private int id;
	private String msg;
	
	public MyEntity() {
		super();
	}
	public MyEntity(int id, String msg) {
		super();
		this.id = id;
		this.msg = msg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
```

### 1.5 MySender.java

```java
@Component
public class MySender {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;  
	
	final ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
		@Override
		public void confirm(CorrelationData correlationData, boolean ack, String cause) {
			// 确认消息处理
			System.err.println("correlationData: " + correlationData);
			System.err.println("ack: " + ack);

			// 异常
			if(ack == false){
				System.err.println("异常处理....");
			}
			
		}
		
	};
	
	final ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
		@Override
		public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText,
				String exchange, String routingKey) {
			// 回写消息处理
			System.err.println("return exchange: " + exchange + ", routingKey: " 
				+ routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
		}
	};
	
	// 异步conform
	public void sendMessage1(Object message, Map<String, Object> properties) throws Exception {
		MessageHeaders mhs = new MessageHeaders(properties);
		Message msg = MessageBuilder.createMessage(message, mhs);
		
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.setReturnCallback(returnCallback);
		
		//全局唯一 
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend("single-exchange-1", "single.xyz", msg, correlationData);
	}
	
	// 同步conform：单条
	public void sendMessage2(Object message, Map<String, Object> properties) throws Exception {
		MessageHeaders mhs = new MessageHeaders(properties);
		Message msg = MessageBuilder.createMessage(message, mhs);
		
		rabbitTemplate.setReturnCallback(returnCallback);
		
		//全局唯一 
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend("single-exchange-1", "single.xyz", msg, correlationData);
		
		rabbitTemplate.waitForConfirms(1000);
	}
	
	// 同步conform：批量
	public void sendMessage3(Object message, Map<String, Object> properties) throws Exception {
		MessageHeaders mhs = new MessageHeaders(properties);
		Message msg = MessageBuilder.createMessage(message, mhs);
		
		rabbitTemplate.setReturnCallback(returnCallback);
		
		//全局唯一 
		for(int i = 0 ; i < 9 ; i ++) {
			CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
			rabbitTemplate.convertAndSend("single-exchange-1", "single.xyz", msg, correlationData);
		}
		
		rabbitTemplate.waitForConfirmsOrDie(1000);
	}
	
	
	public void sendEntity(MyEntity myEntity) throws Exception {
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.setReturnCallback(returnCallback);
		
		//全局唯一 
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend("single-exchange-2", "single.gsdf", myEntity, correlationData);
	}
}

```

### 1.6 Application.java

```java
@SpringBootApplication
public class Application {
    public static void main( String[] args ) {
        SpringApplication.run(Application.class, args);
    }
}
```



## 2 consumer-consumer

### 2.1 pom.xml

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

### 2.2 application.yml

```yaml
spring:
  rabbitmq:
    addresses: 192.168.1.76:5672
    username: guest
    password: guest
    virtual-host: /
    connection-timeout: 15000
    listener:
      simple:
        acknowledge-mode: manual
        concurrency: 5
      my:
        queue:
          name: single-queue-2
          durable: true
        exchange: 
          name: single-exchange-2
          durable: true
          type: topic
          ignoreDeclarationExceptions: true
        key: single.#  
```

### 2.3 MyConfig.java

```java
@Configuration
@ComponentScan({"com.qihuan.rabbitmq.*"})
public class MyConfig {
}
```

### 2.4 MyEntity.java

```java
public class MyEntity implements Serializable {
	
	private int id;
	private String msg;
	
	public MyEntity() {
		super();
	}
	public MyEntity(int id, String msg) {
		super();
		this.id = id;
		this.msg = msg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
```

### 2.5 MyReceiver.java

```java
@Component
public class MyReceiver{
	
	@RabbitListener(bindings = 
			@QueueBinding(value = 
				@Queue(
					value = "single-queue-1", 
					durable="true"
				),exchange = 
				@Exchange(
					value = "single-exchange-1", 
					durable="true", 
					type= "topic", 
					ignoreDeclarationExceptions = "true"
				),
				key = "single.#"
		)
	)
	@RabbitHandler
	public void receiveMessage(Message message, Channel channel) throws Exception {
		//消息处理
		System.err.println("receive message：" + message.getPayload());
		
		// 手工ACK
		Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
		channel.basicAck(deliveryTag, false);
	}

	
	
	@RabbitListener(bindings = 
			@QueueBinding(value = 
				@Queue(
					value = "${spring.rabbitmq.listener.my.queue.name}", 
					durable="${spring.rabbitmq.listener.my.queue.durable}"
				),exchange = 
				@Exchange(
					value = "${spring.rabbitmq.listener.my.exchange.name}", 
					durable="${spring.rabbitmq.listener.my.exchange.durable}", 
					type= "${spring.rabbitmq.listener.my.exchange.type}", 
					ignoreDeclarationExceptions = "${spring.rabbitmq.listener.my.exchange.ignoreDeclarationExceptions}"
				),
				key = "${spring.rabbitmq.listener.my.key}"
			)
	)
	@RabbitHandler
	public void receiveEntity(
			@Headers Map<String, Object> headers,
			@Payload MyEntity myEntity, 
			Channel channel) throws Exception {
		//消息处理
		System.err.println("receive entity：" + myEntity.getMsg());
		
		//手工ACK
		Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
		channel.basicAck(deliveryTag, false);
	}
}
```

### 2.6 Application.java

```java
@SpringBootApplication
public class Application {
    public static void main( String[] args ) {
        SpringApplication.run(Application.class, args);
    }
}
```

