package rabbitmq.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName
 * @Description
 *  这个模式与fanout类似，fanout是发送消息，消费者都能收到
 *  直连模式是生产者向交换机发送消息的时候，指定好路由key。消费者都有自己的临时队列，该队列和交换机绑定的时候，也会有路由key。
 *  消费者收到的消息是生产者指定路由key和消费者的路由key对应上的时候，才会收到信息。
 *  比如生产者发送消息给交换机的时候routingKye是info，然后消费者1的队列绑定交换机时routingKye是info，消费者2的队列绑定交换机时routingKye是error.那么交换机发送消息给队列的时候，只有消费者1才能收到消息
 * @Autor wcy
 * @Date 2020/10/30 14:11
 */
@Component
public class DirectConsumer {

    @RabbitListener (bindings = {
        @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "directs",type = "direct"),   //指定交换机
            key = {"info","error"}    //指定队列和交换机绑定时的路由key
        )
    })
    public void recieve(String message){
        System.out.println("消费者1--"+message);
    }

    @RabbitListener (bindings = {
        @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "directs",type = "direct"),   //指定交换机
            key = {"error"}    //指定队列和交换机绑定时的路由key
        )
    })
    public void recieve2(String message){
        System.out.println("消费者2--"+message);
    }
}