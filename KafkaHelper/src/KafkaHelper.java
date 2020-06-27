
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListConsumerGroupOffsetsOptions;
import org.apache.kafka.clients.admin.ListConsumerGroupsOptions;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.TopicPartitionInfo;

public class KafkaHelper {
	private static Map<String, Object> props;

	private Map<String, TopicInfo> topicInfoDetails = new HashMap<String, TopicInfo>();
	private Map<String, ConsumerInfo> consumerInfoDetails = new HashMap<String, ConsumerInfo>();

	private static String bootStrapServer;
	KafkaConsumer consumer = null;
	private final static Logger LOGGER = Logger.getLogger(KafkaHelper.class.getName());
	private static Log logger = LogFactory.getLog(KafkaHelper.class);

	public static void main(String args[]) {
		bootStrapServer = args[0];
		// Create admin client
		props = new HashMap<>();
		props.put("bootstrap.servers", args[0]);
		props.put("retries", 5);
		System.out.println("Creating Kafka client for " + args[0] + " : Delay " + args[1] + " : Mode " + args[2]);
		KafkaHelper kafkaHelper = new KafkaHelper();

		while (true) {
			try {
				if (args[2] == null || args[2].equalsIgnoreCase("topic")) {
					kafkaHelper.showTopics();
				}

				if (args[2] == null || args[2].equalsIgnoreCase("consumer")) {
					kafkaHelper.showConsumers();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (null != args[1]) {
					Thread.sleep(Integer.valueOf(args[1]));
				} else {
					Thread.sleep(60000);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void showConsumers() {
		try (final AdminClient adminClient = KafkaAdminClient.create(props)) {
			try {

				Collection<ConsumerGroupListing> consumers = adminClient
						.listConsumerGroups(new ListConsumerGroupsOptions()).all().get(30, TimeUnit.SECONDS);

				for (ConsumerGroupListing cg : consumers) {
					System.out.println(cg.groupId());
					Map<TopicPartition, OffsetAndMetadata> offsets = adminClient
							.listConsumerGroupOffsets(cg.groupId(), new ListConsumerGroupOffsetsOptions())
							.partitionsToOffsetAndMetadata().get(30, TimeUnit.SECONDS);

					for (TopicPartition tp : offsets.keySet()) {
						String topicPartitionName = cg.groupId() + "-" + tp.topic() + "-" + tp.partition();
						long committedOffset = offsets.get(tp).offset();
						long finalOffset = getLogEndOffset(tp);
						long lag = finalOffset - committedOffset;

						if (consumerInfoDetails.containsKey(topicPartitionName)) {
							ConsumerInfo consumerInfo = consumerInfoDetails.get(topicPartitionName);
							if (committedOffset == consumerInfo.getCommittedOffset() && finalOffset > committedOffset) {
								// LOGGER.log(Level.SEVERE, "Offset is not Committed for "+topicPartitionName +
								// " old-committedOffset "+consumerInfo.getCommittedOffset() + "
								// new-committedOffset "+committedOffset + " finalOffset "+finalOffset + " lag
								// "+ lag);
								// logger.error("Offset is not Committed for "+topicPartitionName + "
								// old-committedOffset "+consumerInfo.getCommittedOffset() + "
								// new-committedOffset "+committedOffset + " finalOffset "+finalOffset + " lag
								// "+ lag);
								System.out.println("ERROR : " + "Offset is not Committed for " + topicPartitionName
										+ " old-committedOffset " + consumerInfo.getCommittedOffset()
										+ " new-committedOffset " + committedOffset + " finalOffset " + finalOffset
										+ " lag " + lag);

								consumerInfo.setTopicPartition(topicPartitionName);
								consumerInfo.setCommittedOffset(committedOffset);
								consumerInfo.setLag(lag);
								consumerInfo.setFinalOffset(finalOffset);
								consumerInfo.setStatus("ERROR-NOT CONSUMING");
								if (lag > 100) {
									System.out.println(
											"ERROR : " + "LAG is more for " + topicPartitionName + " committedOffset "
													+ committedOffset + " finalOffset " + finalOffset + " lag " + lag);
									consumerInfo.setStatus("ERROR-NOT CONSUMING-AND-LAG");
								}
							} else if (lag > 100) {
								// LOGGER.log(Level.SEVERE, "LAG is more for "+topicPartitionName + "
								// committedOffset "+committedOffset + " finalOffset "+finalOffset + " lag "+
								// lag);
								// logger.error("LAG is more for "+topicPartitionName + " committedOffset
								// "+committedOffset + " finalOffset "+finalOffset + " lag "+ lag);
								consumerInfo.setTopicPartition(topicPartitionName);
								System.out.println(
										"ERROR : " + "LAG is more for " + topicPartitionName + " committedOffset "
												+ committedOffset + " finalOffset " + finalOffset + " lag " + lag);

								consumerInfo.setCommittedOffset(committedOffset);
								consumerInfo.setLag(lag);
								consumerInfo.setFinalOffset(finalOffset);
								consumerInfo.setStatus("ERROR-NOT CONSUMING-AND-LAG");
							} else {
								consumerInfo.setTopicPartition(topicPartitionName);
								consumerInfo.setCommittedOffset(committedOffset);
								consumerInfo.setLag(lag);
								consumerInfo.setFinalOffset(finalOffset);
								consumerInfo.setStatus("SUCCESS");
							}
							consumerInfoDetails.put(topicPartitionName, consumerInfo);
						} else {
							ConsumerInfo consumerInfo = new ConsumerInfo();
							consumerInfo.setTopicPartition(topicPartitionName);
							consumerInfo.setCommittedOffset(committedOffset);
							consumerInfo.setLag(lag);
							consumerInfo.setFinalOffset(finalOffset);
							consumerInfo.setStatus("SUCCESS");
							consumerInfoDetails.put(topicPartitionName, consumerInfo);
						}
					}
				}

				writeConsumerDetails();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public long getLogEndOffset(TopicPartition tp) {
		if (null == consumer) {
			consumer = createNewConsumer();
		}
		Collections.singletonList(tp);
		consumer.assign(Collections.singletonList(tp));
		consumer.seekToEnd(Collections.singletonList(tp));
		return consumer.position(tp);
	}

	private KafkaConsumer<String, String> createNewConsumer() {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "g1");
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		return new KafkaConsumer(properties);
	}

	private void showTopics() {
		try (final AdminClient adminClient = KafkaAdminClient.create(props)) {

			try {
				/*
				 * for(Node n : adminClient.describeCluster().nodes().get(30,TimeUnit.SECONDS)){
				 * System.out.println(n.id()+"["+n.host()+"]"); replca_count.put(n.id(),0); }
				 */

				Set<String> topics = adminClient.listTopics().names().get(30, TimeUnit.SECONDS);
				Map<String, TopicDescription> topic_details = adminClient.describeTopics(topics).all().get(30,
						TimeUnit.SECONDS);
				for (TopicDescription td : topic_details.values()) {
					for (TopicPartitionInfo tpi : td.partitions()) {
						String topicPartitionName = td.name() + "-" + tpi.partition();
						// System.out.println(td.name()+"-"+tpi.partition()+"\t"+tpi.leader().host()+"\t"+tpi.replicas().size()+"\t"+tpi.isr().size());
						if (topicInfoDetails.containsKey(topicPartitionName)) {
							TopicInfo topicInfo = topicInfoDetails.get(topicPartitionName);
							if (null == tpi.leader() || tpi.leader().host() == null) {
								// LOGGER.log(Level.SEVERE, "NO_Leader for Topic "+topicPartitionName);
								// logger.error("NO_Leader for Topic "+topicPartitionName);
								System.out.println("ERROR : " + "NO_Leader for Topic " + topicPartitionName);
								topicInfo.setTopicPartition(topicPartitionName);
								topicInfo.setIsrCount(tpi.isr().size());
								topicInfo.setReplicaCount(tpi.replicas().size());
								topicInfo.setStatus("ERROR-NO_LEADER");
							} else if (tpi.isr().size() < topicInfo.getIsrCount()
									|| tpi.isr().size() < tpi.replicas().size() || tpi.isr().size() < 3
									|| tpi.replicas().size() < 3) {
								// LOGGER.log(Level.SEVERE, "Reduction in ISR for Topic "+topicPartitionName + "
								// old-isr "+topicInfo.getIsrCount() + " new-isr "+tpi.isr().size());
								// logger.error("Reduction in ISR for Topic "+topicPartitionName + " old-isr
								// "+topicInfo.getIsrCount() + " new-isr "+tpi.isr().size());
								System.out.println("ERROR : " + "Reduction in ISR for Topic " + topicPartitionName
										+ " old-isr " + topicInfo.getIsrCount() + " new-isr " + tpi.isr().size());
								topicInfo.setTopicPartition(topicPartitionName);
								topicInfo.setIsrCount(tpi.isr().size());
								topicInfo.setReplicaCount(tpi.replicas().size());
								topicInfo.setLeaderHost(tpi.leader().host());
								topicInfo.setStatus("ERROR-REDUCED-ISR");
							} else if (tpi.replicas().size() < topicInfo.getReplicaCount()
									|| tpi.replicas().size() < 3) {
								// LOGGER.log(Level.SEVERE, "Reduction in REPLICA Count for Topic
								// "+topicPartitionName + " old-replica "+topicInfo.getReplicaCount() + "
								// new-replica "+tpi.replicas().size());
								// logger.error("Reduction in REPLICA Count for Topic "+topicPartitionName + "
								// old-replica "+topicInfo.getReplicaCount() + " new-replica
								// "+tpi.replicas().size());
								System.out.println("ERROR : " + "Reduction in REPLICA Count for Topic "
										+ topicPartitionName + " old-replica " + topicInfo.getReplicaCount()
										+ " new-replica " + tpi.replicas().size());
								topicInfo.setTopicPartition(topicPartitionName);
								topicInfo.setIsrCount(tpi.isr().size());
								topicInfo.setReplicaCount(tpi.replicas().size());
								topicInfo.setLeaderHost(tpi.leader().host());
								topicInfo.setStatus("ERROR-REDUCED-REPLICA");
							} else {
								topicInfo.setTopicPartition(topicPartitionName);
								topicInfo.setIsrCount(tpi.isr().size());
								topicInfo.setReplicaCount(tpi.replicas().size());
								topicInfo.setLeaderHost(tpi.leader().host());
								topicInfo.setStatus("SUCCESS");
							}
							topicInfoDetails.put(topicPartitionName, topicInfo);
						} else {
							TopicInfo topicInfo = new TopicInfo();
							topicInfo.setTopicPartition(topicPartitionName);
							topicInfo.setIsrCount(tpi.isr().size());
							topicInfo.setReplicaCount(tpi.replicas().size());
							topicInfo.setLeaderHost(tpi.leader().host());
							topicInfo.setStatus("SUCCESS");
							topicInfoDetails.put(topicPartitionName, topicInfo);
						}
					}
				}

				System.out.println("topicInfoDetails " + topicInfoDetails);
				writeTopicDetails();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeConsumerDetails() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			File file = new File("props/consumerDetails.csv");
			if (!file.exists())
				file.createNewFile();

			input = new FileInputStream("props/consumerDetails.csv");
			prop.load(input);
			for (Map.Entry<String, ConsumerInfo> consumerInfoDetail : consumerInfoDetails.entrySet()) {
				String value = (null != prop.getProperty(consumerInfoDetail.getKey())
						? prop.getProperty(consumerInfoDetail.getKey()) + ","
						: "");
				/*
				 * String newValue =
				 * "CommittedOffset:"+consumerInfoDetail.getValue().getCommittedOffset() + ":" +
				 * "FinalOffset:"+consumerInfoDetail.getValue().getFinalOffset() + ":" +
				 * "Lag:"+consumerInfoDetail.getValue().getLag() + ":" +
				 * consumerInfoDetail.getValue().getStatus();
				 */

				String newValue = consumerInfoDetail.getValue().getCommittedOffset() + "  ;  "
						+ consumerInfoDetail.getValue().getFinalOffset() + "  ;  "
						+ consumerInfoDetail.getValue().getLag() + "  ;  " + consumerInfoDetail.getValue().getStatus();
				prop.put(consumerInfoDetail.getKey(), value + newValue);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		try (OutputStream output = new FileOutputStream("props/consumerDetails.csv")) {
			prop.store(output, null);
		} catch (Exception io) {
			io.printStackTrace();
		}

	}

	private void writeTopicDetails() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			File file = new File("props/topicDetails.csv");
			if (!file.exists())
				file.createNewFile();

			input = new FileInputStream("props/topicDetails.csv");
			prop.load(input);
			for (Map.Entry<String, TopicInfo> topicInfoDetail : topicInfoDetails.entrySet()) {
				String value = (null != prop.getProperty(topicInfoDetail.getKey())
						? prop.getProperty(topicInfoDetail.getKey()) + ","
						: "");
				/*
				 * String newValue = "Leader:"+topicInfoDetail.getValue().getLeaderHost() + ";"
				 * + "ReplicaCount:"+topicInfoDetail.getValue().getReplicaCount() + ";" +
				 * "IsrCount:"+topicInfoDetail.getValue().getIsrCount() + ";" +
				 * topicInfoDetail.getValue().getStatus();
				 */
				String newValue = topicInfoDetail.getValue().getLeaderHost() + "  ;  "
						+ topicInfoDetail.getValue().getReplicaCount() + "  ;  "
						+ topicInfoDetail.getValue().getIsrCount() + "  ;  " + topicInfoDetail.getValue().getStatus();
				prop.put(topicInfoDetail.getKey(), value + newValue);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		try (OutputStream output = new FileOutputStream("props/topicDetails.csv")) {
			prop.store(output, null);
		} catch (Exception io) {
			io.printStackTrace();
		}

	}
}
