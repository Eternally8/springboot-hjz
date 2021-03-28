package com.apollouse.apolloApi;

import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;

/**
 * Description： TODO
 * Author: hujingzheng
 * Date: 2021/2/8 14:29
 */
public class ApolloTool {

//    private static final String portalUrl = "http://10.0.81.97:8070"; // portal url
    private static final String portalUrl = "http://127.0.0.1:8070";
    private static final String token = "*********************"; // 申请的token

    public static ApolloOpenApiClient getClient(){
        ApolloOpenApiClient client = ApolloOpenApiClient.newBuilder()
                .withPortalUrl(portalUrl)
                .withToken(token)
                .build();
        return client;
    }


}
