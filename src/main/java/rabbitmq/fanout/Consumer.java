package rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName
 * @Description
 * 可以有多个消费者，每个消费者都有自己临时的queue,每个队列都要绑定到exchange。
 * 生产者发送的消息，只能发送到交换机，由交换机决定发送，发送给绑定过的所有队列
 * 消费者都能拿到消息，实现了一条消息被多个消费者消费
 * @Autor wcy
 * @Date 2020/10/30 14:01
 */
@Component
public class Consumer {

    @RabbitListener(bindings = {
        @QueueBinding(
            value = @Queue,       //创建临时队列
            exchange = @Exchange(value = "logs",type = "fanout") //队列绑定的交换机
        )
    })
    public void recieve(String message){
        System.out.println("消费者1--》"+message);
    }

    @RabbitListener(bindings = {
        @QueueBinding(
            value = @Queue,       //创建临时队列
            exchange = @Exchange(value = "logs",type = "fanout") //队列绑定的交换机
        )
    })
    public void recieve2(String message){
        System.out.println("消费者2--》"+message);
    }
}