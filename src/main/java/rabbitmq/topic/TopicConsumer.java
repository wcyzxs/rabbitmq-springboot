package rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName
 * @Description
 * 和direct相比，动态路由 routingKey更加方便扩展，不再是写死的了。Topic类型得Exchange可以让队列在绑定Exchange时Routing Key使用通配符！
 * 这种模型Routing key一般由一个或多个单词组成，多个单词以"."分割，如： item.insert
 *  *：匹配正好一个单词   如：audit*    则audit.pass
 *  #: 匹配0或者多个单词  如：audit.#   则aduit.pass.irs 或者audit
 * @Autor wcy
 * @Date 2020/10/30 14:24
 */
@Component
public class TopicConsumer {

    @RabbitListener(bindings = {
        @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "topics",type = "topic"),
            key = {"user.*"}
        )
    })
    public  void recieve(String message){
        System.out.println("消费者1"+message);
    }

    @RabbitListener(bindings = {
        @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "topics",type = "topic"),
            key = {"#.user.*"}
        )
    })
    public  void recieve2(String message){
        System.out.println("消费者2"+message);
    }
}