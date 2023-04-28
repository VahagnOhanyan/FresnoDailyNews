package org.fresno.adapter.grpc;


import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ExtractKeywordsClient {
    private static final Logger logger = Logger.getLogger(ExtractKeywordsClient.class.getName());
    private final FresnoDailyNewsGrpc.FresnoDailyNewsBlockingStub blockingStub;

    public ExtractKeywordsClient(Channel channel) {
        blockingStub = FresnoDailyNewsGrpc.newBlockingStub(channel);
    }

    public String sendText(String text) {
        logger.info("Sending text to server: " + text);
        ExtractKeywordsRequest request = ExtractKeywordsRequest.newBuilder().setMessage(text).build();
        logger.info("Sending to server: " + request);
        ExtractKeywordsResponse response;
        try {
            response = blockingStub.extractKeyword(request);

        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return String.valueOf(e.getStatus());
        }
        logger.info("Got following from the server: " + response.getReply());
        return response.getReply();

    }

}