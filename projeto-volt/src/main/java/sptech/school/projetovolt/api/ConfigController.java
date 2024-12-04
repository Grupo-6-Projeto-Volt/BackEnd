package sptech.school.projetovolt.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

import static software.amazon.awssdk.core.sync.RequestBody.fromBytes;

@RestController
@RequestMapping("/configuracoes")
@RequiredArgsConstructor
@Slf4j
public class ConfigController {
    private final Region region = Region.US_EAST_1;

    AwsCredentials credentials = AwsSessionCredentials.create(
            "ASIAXLQ6UOIFMKDJEZAP",
            "uEGAXzIHIk7FML7/YqEj3rlRRbz/Poal9GMYf1gH",
            "IQoJb3JpZ2luX2VjED0aCXVzLXdlc3QtMiJGMEQCICLmF56+p7w2L1zA8nkB5la6exU8iSHs37c/svowUHc4AiAokysnbCUqo0bxpUv6G0aSzo1aZaYtXWtV6EJhIOva2iq/Agjm//////////8BEAEaDDUwNTc5NjY1MzU3OCIMC49wywzDbLJROEUCKpMCxvc3GHjJPdF8ZyMh9MnN7uOeCsuiIKmAxn0yh9rhGgCh/rQFrbOWhu0SFs0WBmXA5u42WubZpUh+ALvqtBpg3GRnAMivWHBGO/Lpp1hzkVznvMzumQEF9kVArL72NLSA3kX6cbKql/7ELlzXRMZWR1cgEcL2Cy5xPcnEI1K/u9Ik2VNdNgsKQOU98aTY+YMR+gRITK72gNjepuDERqGZw5jrLsTHvQwvURAnF4kZOmu2Cx9fm+vno1wE/kbjFLctL6aKTSrVeteeKKKMVoYGzRMnH/AokRFZSrfwkLJ1F/hj7mW6gHcGjO0/zCEqmzRC6EO/+NmVbF8MI8fiIVhn61TlOPG9O1WcbcUCXN8bNm+BYgswwsm/ugY6ngHdZTj5KfMQOIuQKgs9peGYWz/qIHvfmMX6HVUsXxrRHPIEFb0nPOtsr7R6Eg02WyyVPRT4rF2bLX3IC5FbXYfUyGqKPAdmgQtf5KouvFRkJ5Y1fiiM9qOIHeCcMII2XaJVZe5ONXjRgHfDumvoC+lNgy+BsyZ1n+WJ1K4UjJPRNv1SddU0fdlfQ7ftDWFd6/9bgGcekODbrGhTFUOdRg=="
    );

    AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

    private final S3Client s3 = S3Client.builder().credentialsProvider(credentialsProvider).region(region).build();

    @GetMapping(produces = "image/png")
    public ResponseEntity<byte[]> getFoto(@RequestParam String nameImg) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("bktt-ichiban")
                .key("images/config/" + nameImg + ".png")
                .build();
        byte[] byteArray = s3.getObjectAsBytes(getObjectRequest).asByteArray();
        return ResponseEntity.ok(byteArray);
    }

    @PostMapping( consumes = "image/*")
    public ResponseEntity<Void> postar(@RequestBody byte[] referenciaArquivoFoto, @RequestParam String nameImg){
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket("bktt-ichiban")
                .key("images/config/" + nameImg + ".png")
                .build();

        s3.putObject(putObjectRequest, fromBytes(referenciaArquivoFoto));
        return ResponseEntity.ok().build();
    }
}
