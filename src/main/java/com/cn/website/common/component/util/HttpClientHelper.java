package com.cn.website.common.component.util;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

public interface HttpClientHelper {
	public String post(final String url, String jsonParam, Map<String, String> headerMap) throws IOException;

	public String postNameValuePair(String url, Map<String, String> mapParam, Map<String, String> headerMap) throws IOException;

	public String get(final String url, Map<String, String> headerMap) throws IOException;

	//public String delete(final String url, String jsonParam, Map<String, String> headerMap);
}
