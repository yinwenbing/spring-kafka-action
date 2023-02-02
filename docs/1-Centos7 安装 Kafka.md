首先需要安装 JDK，并配置好环境变量。该部分请自行查阅资料。

Kafka 依赖 Zookeeper，安装 Kafka 之前，先安装 Zookeeper。

## 一、安装 Zookeeper

1. 下载

[下载地址](http://archive.apache.org/dist/zookeeper/zookeeper-3.5.3-beta/)

2. 解压

```
tar -xcf zookeeper-3.5.3-beta.tar.gz
mv zookeeper-3.5.3-beta /usr/local/zookeeper
```

3. 修改配置
```
cd /usr/local/zookeeper/conf
cp zoo_simple.cfg zoo.cfg
vim zoo.cfg
```

```
dataDir=/usr/local/zookeeper/data
dataLogDir=/usr/local/zookeeper/logs
```

4. 启动&停止

```
cd /usr/local/zookeeper
// 启动
bin/zkServer.sh start
// 查看状态
bin/zkServer.sh status
// 停止
bin/zkServer.sh stop
// 重启
bin/zkServer.sh restart
```

## 二、安装 Kafka

1. 下载

```
wget http://archive.apache.org/dist/kafka/2.2.0/kafka_2.12-2.2.0.tgz
```

2. 解压

```
tar -xzvf kafka_2.12-2.2.0.tgz
mv kafka_2.12-2.2.0 /usr/local/kafka
```

3. 修改配置

```
cd /usr/local/kafka/config
vim server.properties
```

```
log.dirs=/usr/local/kafka/logs
listeners=PLAINTEXT://your.host.name:9092
advertised.listeners=PLAINTEXT://your.host.name:9092
zookeeper.connect=your.host.name:2181
```

4. 启动&停止

```
cd /usr/local/kafka
// 启动
bin/kafka-server-start.sh -daemon config/server.properties
// 停止
bin/kafka-server-stop.sh
```

## 三、测试

1. 创建 topic

```
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
```

2. 查看 topic 列表

```
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

3. 生产者发送消息

```
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
```

4. 消费者消费消息

```
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
```

## 推荐阅读
* [CentOS安装ZooKeeper](https://juejin.cn/post/6844904106818011144)
* [CentOS安装Kafka](https://juejin.cn/post/6844904106826399752)
