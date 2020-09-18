package com.pg;

import java.util.Map;

import io.kubernetes.client.openapi.ApiCallback;
import io.kubernetes.client.openapi.ApiException;

public class ApiCallbackResponse<V1Pod> implements ApiCallback<V1Pod> {

	@Override
	public void onDownloadProgress(long arg0, long arg1, boolean arg2) {
	    System.out.println("onDownloadProgress");

	}

	@Override
	public void onFailure(ApiException arg0, int arg1, Map arg2) {
	    System.out.println("onFailure "+arg0.getMessage());


	}

	@Override
	public void onSuccess(Object arg0, int arg1, Map arg2) {
	    System.out.println("onSuccess "+arg0);


	}

	@Override
	public void onUploadProgress(long arg0, long arg1, boolean arg2) {
	    System.out.println("onUploadProgress "+arg0);


	}

}