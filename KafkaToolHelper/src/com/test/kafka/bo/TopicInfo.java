package com.test.kafka.bo;


public class TopicInfo {

	private String topicPartition;
	private String leaderHost;
	private int isrCount;
	private int replicaCount;
	private String status;

	public String getTopicPartition() {
		return topicPartition;
	}

	public void setTopicPartition(String topicPartition) {
		this.topicPartition = topicPartition;
	}

	public String getLeaderHost() {
		return leaderHost;
	}

	public void setLeaderHost(String leaderHost) {
		this.leaderHost = leaderHost;
	}

	public int getIsrCount() {
		return isrCount;
	}

	public void setIsrCount(int isrCount) {
		this.isrCount = isrCount;
	}

	public int getReplicaCount() {
		return replicaCount;
	}

	public void setReplicaCount(int replicaCount) {
		this.replicaCount = replicaCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TopicInfo [topicPartition=" + topicPartition + ", leaderHost=" + leaderHost + ", isrCount=" + isrCount
				+ ", replicaCount=" + replicaCount + ", status=" + status + "]";
	}

}
