package hello;

import java.util.Map;

import io.kubernetes.client.ApiCallback;
import io.kubernetes.client.ApiException;

public class ApiCallbackResponse<V1Pod> implements ApiCallback<V1Pod> {

	boolean isComplete = false;
	boolean isFailure = false;

	public boolean isFailure() {
		return isFailure;
	}

	public void setFailure(boolean isFailure) {
		this.isFailure = isFailure;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	@Override
	public void onDownloadProgress(long arg0, long arg1, boolean arg2) {
	   // System.out.println("onDownloadProgress");
		
	}

	@Override
	public void onFailure(ApiException arg0, int arg1, Map arg2) {
		arg0.printStackTrace();
	   // System.out.println("onFailure "+arg0.getMessage());
	    isFailure = true;
	}

	@Override
	public void onSuccess(Object arg0, int arg1, Map arg2) {
	   // System.out.println("onSuccess "+arg0);
	   //System.out.println("onSuccess "+arg1);
	   // System.out.println("arg2 "+arg2);
	    isComplete = true;
	}

	@Override
	public void onUploadProgress(long arg0, long arg1, boolean arg2) {
	   // System.out.println("onUploadProgress "+arg0);

		
	}

}
