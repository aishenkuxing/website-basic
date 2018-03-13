/* =============================================================
 * Created: [2016年3月30日] by ZhengChen
 * =============================================================
 *
 * Copyright 2014-2015 NetDragon Websoft Inc. All Rights Reserved
 *
 * =============================================================
 */

package com.cn.website.common.component.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 黄家成
 * @since 0.1.0
 */
@Component
public class HttpClientHelperImpl implements HttpClientHelper {

    public static final Logger logger = (Logger) LoggerFactory.getLogger(HttpClientHelperImpl.class);

    public String get(final String url, Map<String, String> headerMap) throws IOException {

       // Security.addProvider(new BouncyCastleProvider());

        CloseableHttpClient httpclient = HttpClients.createDefault();

        String responseBody = "";
        try {
            HttpGet httpGet = new HttpGet(url);

            RequestConfig requestConfig = RequestConfig.DEFAULT;
            httpGet.setConfig(requestConfig);

            if (MapUtils.isEmpty(headerMap)) {
                httpGet.setHeader(HttpHeaders.ACCEPT, "application/json");
                httpGet.setHeader(HttpHeaders.ACCEPT_CHARSET, "utf8");
            } else {
                for (Entry<String, String> entry : headerMap.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity, Charset.forName("UTF-8")) : null;
                    } else {
                        HttpEntity entity = response.getEntity();
                        throw new ClientProtocolException(entity != null ? EntityUtils.toString(entity,
                                                                                                Charset.forName("UTF-8"))
                                                                        : null);
                    }
                }

            };
            responseBody = httpclient.execute(httpGet, responseHandler);
        } catch (ClientProtocolException e) {
            throw e;
        } catch (IOException e) {
        	 throw e;
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
            	 throw e;
            }
        }
        return responseBody;
    }

    public String post(final String url, String jsonParam, Map<String, String> headerMap) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        String responseBody = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            if (jsonParam != null && !jsonParam.isEmpty()) {
                StringEntity stringEntity = new StringEntity(jsonParam, ContentType.APPLICATION_JSON);
                httpPost.setEntity(stringEntity);
            }

            RequestConfig requestConfig = RequestConfig.DEFAULT;
            httpPost.setConfig(requestConfig);

            if (MapUtils.isEmpty(headerMap)) {
            	httpPost.setHeader(HttpHeaders.ACCEPT, "application/json");
            	httpPost.setHeader(HttpHeaders.ACCEPT_CHARSET, "utf8");
            } else {
                for (Entry<String, String> entry : headerMap.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity, Charset.forName("UTF-8")) : null;
                    } else {
                        HttpEntity entity = response.getEntity();
                        throw new ClientProtocolException(entity != null ? EntityUtils.toString(entity,
                                                                                                Charset.forName("UTF-8"))
                                                                        : null);
                    }
                }

            };
            responseBody = httpclient.execute(httpPost, responseHandler);
        } catch (ClientProtocolException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
            	throw e;
            }
        }
        return responseBody;
    }

    public String postNameValuePair(String url, Map<String, String> mapParam, Map<String, String> headerMap) throws IOException {

      //  Security.addProvider(new BouncyCastleProvider());

        String responseBody = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);

            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            for (Entry<String, String> entry : mapParam.entrySet()) {
                formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity requestEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
            httpPost.setEntity(requestEntity);

            RequestConfig requestConfig = RequestConfig.DEFAULT;
            httpPost.setConfig(requestConfig);

            if (MapUtils.isEmpty(headerMap)) {
                httpPost.setHeader(HttpHeaders.ACCEPT_CHARSET, "utf8");
            } else {
                for (Entry<String, String> entry : headerMap.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity, Charset.forName("UTF-8")) : null;
                    } else {
                        HttpEntity entity = response.getEntity();
                        throw new ClientProtocolException(entity != null ? EntityUtils.toString(entity,
                                                                                                Charset.forName("UTF-8"))
                                                                        : null);
                    }
                }

            };
            responseBody = httpclient.execute(httpPost, responseHandler);
        } catch (ClientProtocolException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                throw e;
            }
        }
        return responseBody;
    }

  /*  public String delete(final String url, String jsonParam, Map<String, String> headerMap) {

      //  Security.addProvider(new BouncyCastleProvider());

        CloseableHttpClient httpclient = HttpClients.createDefault();

        String responseBody = "";
        try {

            HttpDelete httpDelete = new HttpDelete(url);

            StringEntity stringEntity = new StringEntity(jsonParam, ContentType.APPLICATION_JSON);
            httpDelete.setEntity(stringEntity);

            RequestConfig requestConfig = RequestConfig.DEFAULT;
            httpDelete.setConfig(requestConfig);

            if (MapUtils.isEmpty(headerMap)) {
                httpDelete.setHeader(HttpHeaders.ACCEPT, ConstantUtil.HTTP_ACCEPT_APPLICATION_JSON);
                httpDelete.setHeader(HttpHeaders.ACCEPT_CHARSET, ConstantUtil.HTTP_ACCEPT_CHARSET_UTF8);
            } else {
                for (Entry<String, String> entry : headerMap.entrySet()) {
                    httpDelete.setHeader(entry.getKey(), entry.getValue());
                }
            }
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity, Charset.forName("UTF-8")) : null;
                    } else {
                        HttpEntity entity = response.getEntity();
                        throw new ClientProtocolException(entity != null ? EntityUtils.toString(entity,
                                                                                                Charset.forName("UTF-8"))
                                                                        : null);
                    }
                }

            };
            responseBody = httpclient.execute(httpDelete, responseHandler);
        } catch (ClientProtocolException e) {
            throw new WafException("bussinesscommon/ClientProtocolException", e.getMessage(), e);
        } catch (IOException e) {
            throw new WafException("bussinesscommon/IOException", e.getMessage(), e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                throw new WafException("bussinesscommon/IOException", e.getMessage(), e);
            }
        }
        return responseBody;
    }*/
}
