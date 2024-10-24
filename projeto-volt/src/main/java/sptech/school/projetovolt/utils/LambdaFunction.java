package sptech.school.projetovolt.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;
import software.amazon.awssdk.services.lambda.model.LambdaException;

import java.io.Serializable;
import java.util.Map;

public class LambdaFunction {

    public static LambdaResponse uploadToS3(String name, String image) {

        String function = "lambda-cadastro-imagens-s3";

        LambdaClient lambda = LambdaClient.builder()
                .region(Region.US_EAST_1)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        InvokeResponse res;

        try {
            Map<String, Object> params = Map.of("nomeImagem", name, "imagem", image);
            SdkBytes payload = SdkBytes.fromUtf8String(objectMapper.writeValueAsString(params));

            InvokeRequest req = InvokeRequest.builder()
                    .functionName(function)
                    .payload(payload)
                    .build();

            res = lambda.invoke(req);

            String value = res.payload().asUtf8String();
            return objectMapper.readValue(value, LambdaResponse.class);
        } catch (LambdaException | JsonProcessingException e) {
            System.err.println(e.getMessage());
        }

        lambda.close();
        return null;
    }

}
