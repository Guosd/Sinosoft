# 消息的可靠性

如果要保证消息的可靠性，需要对消息进行持久化处理，然而消息持久化除了需要代码的设置之外，还有一个重要步骤是至关重要的，那就是保证你的消息顺利进入Broker（代理服务器）。正常情况下，如果消息经过交换器进入队列就可以完成消息的持久化，但如果消息在没有到达broker之前出现意外，那就造成消息丢失，有没有办法可以解决这个问题？

RabbitMQ有两种方式来解决这个问题：

 1. 通过AMQP提供的事务机制实现。
 2. 使用发送者确认模式实现。



## 1 事务

 事务可以保证消息的传递，使得可靠消息最终一致性。

 RabbitMQ中与事务有关的主要有channel的三个方法：
 

    1. txSelect()：主要用于将当前channel设置成transaction模式。
    2. txCommit()：用于提交事务。
    3. txRollback()：用于回滚事务。

 或 使用`@Transactional(rollbackFor = Exception.class, transactionManager = "rabbitTransactionManager")`来注释方法。

 注意：

 启用事务模式,不能开确认回调`rabbitTemplate.setConfirmCallback(confirmCallback)`



## 2 Confirm：同步（单条、批量）、异步

 RabbitMQ对生产者的确认，总共分为两种模式：同步模式 与 异步模式。

 1. 同步模式分为： 单条消息确认 与 批量确认

    -  单条消息确认： `channel.waitForConfirms()` 或 `rabbitTemplate.waitForConfirms(timeout)`普通发送方确认模式；消息到达交换器，就会返回true。

    -  批量消息确认： `channel.waitForConfirmsOrDie()` 或 `rabbitTemplate.waitForConfirmsOrDie(timeout)`批量确认模式；使用同步方式等所有的消息发送之后才会执行后面代码，只要有一个消息未到达交换器就会抛出异常。

 2. 异步模式为：生产者 异步监听消息确认

      异步监听消息确认：`channel.addConfirmListener()` 或 `rabbitTemplate.setConfirmCallback(confirmCallback)`异步监听发送方确认模式。
