package com.example.serviceA.model;

import java.util.List;

public class Response <T> {
	
	private int totalResponse;
	private boolean messageStatusForKafka;
	private List<T> success;
	
	public boolean isMessageStatusForKafka() {
		return messageStatusForKafka;
	}
	public void setMessageStatusForKafka(boolean messageStatusForKafka) {
		this.messageStatusForKafka = messageStatusForKafka;
	}
	public int getTotalResponse() {
		return totalResponse;
	}
	public void setTotalResponse(int totalResponse) {
		this.totalResponse = totalResponse;
	}
	public List<T> getSuccess() {
		return success;
	}
	public void setSuccess(List<T> success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "Response [totalResponse=" + totalResponse + ", success=" + success + "]";
	}
	
}
