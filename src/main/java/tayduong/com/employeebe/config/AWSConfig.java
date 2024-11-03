package tayduong.com.employeebe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AWSConfig {

    @Value("${aws.access.key.id}")
    private String accessKeyId;

    @Value("${aws.secret.access.key}")
    private String secretAccessKey;

    @Value("${aws.s3.region}")
    private String region;

    @Bean
    public S3Client s3Client() {
        Region awsRegion = Region.of(region);
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        return S3Client.builder()
                .region(awsRegion)
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }
}