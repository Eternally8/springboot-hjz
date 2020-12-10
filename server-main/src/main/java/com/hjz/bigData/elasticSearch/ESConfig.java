package com.hjz.bigData.elasticSearch;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

@Slf4j
//@Configuration
public class ESConfig {

    @Value("${elasticsearch.user}")
    private String esUserName;
    @Value("${elasticsearch.password}")
    private String esPassword;
    @Value("${elasticsearch.host}")
    private String esHost;
    @Value("${elasticsearch.port}")
    private Integer esPort;
    @Value("${elasticsearch.keystorePath}")
    private String eskeystorePath;


    @Bean(name = "ESClient")
    public RestHighLevelClient getClient() throws Exception {
        //elastic_url是以逗号分隔的地址ip+port
        String[] hosts = esHost.split(",");
        HttpHost[] httpHostsList = new HttpHost[hosts.length];
        HttpHost h;
        for (int i = 0; i < hosts.length; i++) {
            h = new HttpHost(hosts[i].split(":")[0], Integer.parseInt(hosts[i].split(":")[1]), "https");
            httpHostsList[i] = h;
        }

        RestHighLevelClient restHighLevelClient;
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(esUserName, esPassword));

        Path caCertificatePath  = Paths.get(eskeystorePath);//新生成的keystore文件路径
        try {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            Certificate trustedCa;
            try (InputStream is = Files.newInputStream(caCertificatePath)) {
                trustedCa = factory.generateCertificate(is);
            }
            KeyStore trustStore = KeyStore.getInstance("pkcs12");
            trustStore.load(null, null);
            trustStore.setCertificateEntry("ca", trustedCa);
            SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(trustStore, null);
            SSLContext sslContext = sslContextBuilder.build();

            RestClientBuilder builder = RestClient.builder(httpHostsList);
            builder.setHttpClientConfigCallback(httpClientBuilder -> {
                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
                        .setSSLContext(sslContext)
                        .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
            });

            restHighLevelClient = new RestHighLevelClient(builder);
        } catch (Exception e) {
            throw new Exception("Building elastic search rest high level client failed!", e);
        }
        return restHighLevelClient;
    }



//    @Bean(name = "ESClient")
//    public RestClient getRestClient() {
//        //基本的连接
//        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost(esHost, esPort));
//
//        // 设置请求头，每个请求都会带上这个请求头
//        Header[] defaultHeaders = {new BasicHeader("charset", "utf-8"),
//                new BasicHeader("content-type", "application/json")};
//        clientBuilder.setDefaultHeaders(defaultHeaders);
//
//
//        //配置身份验证
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(esUserName, esPassword));
//        clientBuilder.setHttpClientConfigCallback(
//                httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
//        //设置连接超时和套接字超时
//        clientBuilder.setRequestConfigCallback(builder -> builder.setConnectTimeout(10000).setSocketTimeout(60000));
//        //设置节点选择器
//        clientBuilder.setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);
//        //配置HTTP异步请求ES的线程数
//        clientBuilder.setHttpClientConfigCallback(
//                httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultIOReactorConfig(
//                        IOReactorConfig.custom().setIoThreadCount(1).build()));
//        //设置监听器，每次节点失败都可以监听到，可以作额外处理
//        clientBuilder.setFailureListener(new RestClient.FailureListener() {
//            @Override
//            public void onFailure(Node node) {
//                super.onFailure(node);
//                log.error(node.getHost() + "--->该节点失败了");
//            }
//        });
//
//        return clientBuilder.build();
//    }






}

