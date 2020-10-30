package rabbitmq.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName
 * @Description
 *  默认在spring AMQP实现种work这种方式就是公平调度，如果需要实现能者多劳需要额外配置
 * @Autor wcy
 * @Date 2020/10/30 13:44
 */
@Component
public class WorkQueue {

    @RabbitListener(queuesToDeclare = @Queue("work")) //该注解方法、类上面均可注解；加在方法上面则代表这个方法会处理当前@RabbitListener所监听的队列一个回调
    public void receive1(String message){
        System.out.println("message1:"+message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive2(String message){
        System.out.println("message2:"+message);
    }

}