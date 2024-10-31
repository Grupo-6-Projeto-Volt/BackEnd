package sptech.school.projetovolt.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    private final S3Client s3 = S3Client.builder().region(region).build();

    @GetMapping(produces = "image/png")
    public ResponseEntity<byte[]> getFoto(@RequestParam String nameImg) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("bucket-ichiban")
                .key(nameImg + ".png")
                .build();
        byte[] byteArray = s3.getObjectAsBytes(getObjectRequest).asByteArray();
        return ResponseEntity.ok(byteArray);
    }

    @PostMapping( consumes = "image/*")
    public ResponseEntity<Void> postar(@RequestBody byte[] referenciaArquivoFoto, @RequestParam String nameImg){
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket("bucket-ichiban")
                .key(nameImg + ".png")
                .build();

        s3.putObject(putObjectRequest, fromBytes(referenciaArquivoFoto));
        return ResponseEntity.ok().build();
    }
}
