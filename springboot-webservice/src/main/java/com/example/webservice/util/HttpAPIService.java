package com.example.webservice.util;


import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class HttpAPIService {


    @Autowired
    private CloseableHttpClient httpClient;


    @Autowired
    private RequestConfig requestConfig;


    public String doGet(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);

        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return null;
    }

    public String doGet(String url, Map<String, Object> map) throws Exception {
        URIBuilder builder = new URIBuilder(url);

        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                builder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }


        return doGet(builder.build().toString());
    }

    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);

        if (map != null) {
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }

            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");

            httpPost.setEntity(urlEncodedFormEntity);
        }


        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    public HttpResult doPost(String url) throws Exception{
        return doPost(url,null);
    }

    public HttpResult sendXMLPost(String url,String xmlData) throws  Exception{
        HttpPost post = new HttpPost(url);
        Header header = new BasicHeader("Content-Type","xml/text");
        post.setHeader(header);
        List<BasicNameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("xml",xmlData));
        post.setEntity(new UrlEncodedFormEntity(parameters,"UTF-8"));
        CloseableHttpResponse response = this.httpClient.execute(post);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

}
