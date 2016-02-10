Please refer the below steps to create a single node flume agent to consume Kafka messages

1. Download the Flume and install it
2. Checkout the repo
3. Copy the flume-kafka-conf.properties[available in the /src/main/resources] into FLUME_INSTALLED_DIRECTORY/conf folder. Make sure to update the zookeeper node and the topic details
4. Then run mvn package and then copy the /target/flume-kafka-agent-1.0-SNAPSHOT-jar-with-dependencies.jar into FLUME_INSTALLED_DIRECTORY/lib folder
5. Run the Flume agent by running the below command
bin/flume-ng agent --conf  conf --conf-file conf/flume-kafka-conf.properties --name a1 -Dflume.root.logger=INFO,console
