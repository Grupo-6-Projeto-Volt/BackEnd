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
            "ASIAXLQ6UOIFJBJUDXVU",
            "5g9FfsKmgAZ0XSe5K0r1mR7A4rU8iHJxSKBr4KOr",
            "IQoJb3JpZ2luX2VjEDkaCXVzLXdlc3QtMiJHMEUCIQC1EN64bg3rUa++fZRAklam3NXtsvU1zp0HGvPyPyc0vAIgOop72zX4CbcGdYxVLOI7suXOk8R8iRAgVaFHY0sdL9YqvwII4v//////////ARABGgw1MDU3OTY2NTM1NzgiDAvio93EejqPfsdc8yqTAgQosYvI5VNy6Q8UoiTFsoU4kZtsTTZwj67a8TW0Q+AJYk001b4J3oR/oDAoh+x9xkHqq/XxXgDodaBC3XCgLtcC5oqxwqobDvf/U9kvAAxOBoKMTlnKHDYO4a4iG/0SGJhOtGKPOM8bGcrqkEtiWj2PbVhFYSoEMZXFNSJtAnWum9OzdGBBXIuDzQxI7x0rqaRfnUCRapz8MfYG58Rw1zFBDB7yaV0bWXT1QgxVUIGldJh2qiiPj8rmKYiMN5mthypN7I1qZAvXRLVGItci945weuTcASVsaEvTU5lNYsGe2suYaEqmBwPowMnMgU6kwbvjL+L47i3t2PgA5204Besu6Yiz7NrhjIURXQz1XoL6OKVgMPrXvroGOp0BNiZ7pSBI6b0Yg3Kc79xze676qIJXKguGgGwVNmLaPEbAsEcar526racsJzLTlkzFX81v62L5LbTgRfKN3+ggQp/4+IWsnoq6MgTG/7R3w6kb6nia+2/Atu8zqFqQr3HBuLSd/SlsNpy174xqb6sQboyGc9FvP5Qgrye91xZuAAbfAobCpKEi8ZXwam4ZpwbIz3ILFkmaqfzKnMIc3g=="
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
