package rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName
 * @Description
 * @Autor wcy
 * @Date 2020/10/29 18:56
 */
@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //模型： 路由模式-动态路由
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topics", "user.save","这是路由key为user.save的消息");
    }


    //模型： 路由模式-直连
    @Test
    public void testRoute(){
        rabbitTemplate.convertAndSend("directs", "info","这是路由key为info的消息");
    }

    //模型： fanout
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("logs", "", "rabbitmq的fanout模型");
    }

    //模型：工作队列模式
    @Test
    public void testWorkQueue(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "rabbitmq的work模型");
        }

    }

    //模型：   点对点     注意：必须要有消费者，才会有队列产生；在没有消费者的情况下，允许生产者是不会有队列产生的。
    @Test
    public void testHelloWorld(){
        rabbitTemplate.convertAndSend( "hello", "hello world");
    }
}