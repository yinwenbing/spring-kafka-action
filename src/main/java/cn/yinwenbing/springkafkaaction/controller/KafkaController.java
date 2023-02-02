package cn.yinwenbing.springkafkaaction.controller;

import cn.yinwenbing.springkafkaaction.model.vo.ResponseVo;
import cn.yinwenbing.springkafkaaction.util.BuildResponseUtils;
import cn.yinwenbing.springkafkaaction.util.KafkaUtils;
import com.google.common.collect.Lists;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yinwenbing
 * @date 2023/2/1 15:41
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaUtils kafkaUtils;

    /**
     * 新增topic (支持批量，这里就单个作为演示)
     *
     * @param topic topic
     * @return ResponseVo
     */
    @PostMapping("kafka")
    public ResponseVo<?> add(String topic) {
        NewTopic newTopic = new NewTopic(topic, 3, (short) 1);
        kafkaUtils.createTopic(Lists.newArrayList(newTopic));
        return BuildResponseUtils.success();
    }

    /**
     * 查询topic信息 (支持批量，这里就单个作为演示)
     *
     * @param topic 自增主键
     * @return ResponseVo
     */
    @GetMapping("kafka/{topic}")
    public ResponseVo<String> getBytTopic(@PathVariable String topic) {
        return BuildResponseUtils.buildResponse(kafkaUtils.getTopicInfo(Lists.newArrayList(topic)));
    }

    /**
     * 删除topic (支持批量，这里就单个作为演示)
     * (注意：如果topic正在被监听会给人感觉删除不掉（但其实是删除掉后又会被创建）)
     *
     * @param topic topic
     * @return ResponseVo
     */
    @DeleteMapping("kafka/{topic}")
    public ResponseVo<?> delete(@PathVariable String topic) {
        kafkaUtils.deleteTopic(Lists.newArrayList(topic));
        return BuildResponseUtils.success();
    }

    /**
     * 查询所有topic
     *
     * @return ResponseVo
     */
    @GetMapping("kafka/allTopic")
    public ResponseVo<List<String>> getAllTopic() {
        return BuildResponseUtils.buildResponse(kafkaUtils.getAllTopic());
    }

    /**
     * 生产者往topic中发送消息demo
     *
     * @param topic
     * @param message
     * @return
     */
    @PostMapping("kafka/message")
    public ResponseVo<?> sendMessage(String topic, String message) {
        kafkaUtils.sendMessage(topic, message);
        return BuildResponseUtils.success();
    }

    /**
     * 消费者示例demo
     * <p>
     * 基于注解监听多个topic，消费topic中消息
     * （注意：如果监听的topic不存在则会自动创建）
     */
    @KafkaListener(topics = {"topic1", "topic2", "topic3"})
    public void consume(String message) {
        System.out.println("receive msg: " + message);
    }
}
