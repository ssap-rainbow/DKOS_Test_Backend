package shop.ssap.ssap.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {
    @Value("${AWS_S3_BUCKET:defaultBucket}")
    private String bucketName;

    @Value("${AWS_REGION:defaultBucket}")
    private String region;

    @Value("${AWS_ACCESS_KEY_ID:defaultBucket}")
    private String accessKeyId;

    @Value("${AWS_SECRET_ACCESS_KEY:defaultBucket}")
    private String secretKey;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretKey)))
                .build();
    }
}
