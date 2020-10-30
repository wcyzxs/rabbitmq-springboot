package rabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName
 * @Description
 * @
 * @Autor wcy
 * @Date 2020/10/30 13:30
 */
@Component
//(1)RabbitListener代表消费者的监听
// (2)@Queue才是创建队列，并且有其他属性，例如是否持久化，自动删除等,并且都是String类型，不能传boolean
//(3)默认是持久化、非独占队列、不是自动删除
@RabbitListener(queuesToDeclare = @Queue(value = "hello",durable = "true",autoDelete = "true"))
public class HelloWorld {

    @RabbitHandler // 代表消费者从队列取出消息后，是通过该方法处理消息的，和之前Java代码编写的时候new DefaultConsumer一样
    public void recieve(String message){
        System.out.println("message--->"+message);
    }
}