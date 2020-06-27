package com.echoworx.rules.bo;

/**
 * Data object to hold the input file information
 * @author PGPS
 *
 */
public class MessageFileData {

	private String fileName;
	private String to;
	private String from;
	private String subject;
	private String body;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * Method to populate object based on inputfile data
	 * @param fileData
	 * @param conditionData
	 * @return MessageFileData
	 */
	public static MessageFileData populateFileData(MessageFileData fileData,String inputFileData){
		if(inputFileData.startsWith("To")){
			fileData.setTo(inputFileData.split(":")[1]);
		}else if(inputFileData.startsWith("From")){
			fileData.setFrom(inputFileData.split(":")[1]);
		}else if(inputFileData.startsWith("Subject")){
			fileData.setSubject(inputFileData.split(":")[1]);
		}else if(inputFileData.startsWith("Body")){
			if(inputFileData.split(":").length > 1){
				fileData.setBody(inputFileData.split(":")[1]);
			}
		}else{
			if(null != fileData.getBody()){
				fileData.setBody(fileData.getBody().concat("\n").concat(inputFileData));
			}else{
				fileData.setBody(inputFileData);
			}
			
		}
		return fileData;
	}
	@Override
	public String toString() {
		return "FileData [fileName=" + fileName + ", to=" + to + ", from="
				+ from + ", subject=" + subject + ", body=" + body + "]";
	}
	
}
