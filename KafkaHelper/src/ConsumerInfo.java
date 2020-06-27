
public class ConsumerInfo {

	private String topicPartition;
	private long committedOffset;
	private long finalOffset;
	private long lag;
	private String status;

	public String getTopicPartition() {
		return topicPartition;
	}

	public void setTopicPartition(String topicPartition) {
		this.topicPartition = topicPartition;
	}

	public long getCommittedOffset() {
		return committedOffset;
	}

	public void setCommittedOffset(long committedOffset) {
		this.committedOffset = committedOffset;
	}

	public long getFinalOffset() {
		return finalOffset;
	}

	public void setFinalOffset(long finalOffset) {
		this.finalOffset = finalOffset;
	}

	public long getLag() {
		return lag;
	}

	public void setLag(long lag) {
		this.lag = lag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ConsumerInfo [topicPartition=" + topicPartition + ", committedOffset=" + committedOffset
				+ ", finalOffset=" + finalOffset + ", lag=" + lag + ", status=" + status + "]";
	}

}
