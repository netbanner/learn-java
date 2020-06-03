package com.example.webservice.configure;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {


   /* 最大连接数 */
   @Value("${http.maxTotal}")
    private Integer maxTotal;

   /*  设置每个主机的并发数 */
   @Value("${http.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute;

   @Value("${http.connectTimeout}")
    private Integer connectTimeout;

   @Value("${http.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;

    @Value("${http.socketTimeout}")
    private Integer socketTimeout;

    @Value("${http.staleConnectionCheckEnabled}")
    private boolean staleConnectionCheckEnabled;


    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager(){
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager =  new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return  poolingHttpClientConnectionManager;
    }

    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager){
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        clientBuilder.setConnectionManager(httpClientConnectionManager);
        return  clientBuilder;
    }

    @Bean
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder){
        return httpClientBuilder.build();
    }

    @Bean(name="builder")
    public RequestConfig.Builder getBuilder(){

        RequestConfig.Builder builder = RequestConfig.custom();
        builder.setConnectTimeout(connectTimeout);
        builder.setConnectionRequestTimeout(connectionRequestTimeout);
        builder.setSocketTimeout(socketTimeout);
        builder.setStaleConnectionCheckEnabled(staleConnectionCheckEnabled);

        return builder;
    }

    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder){
      return  builder.build();
    }


}
