package demoapp

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import demo.FileStorageService
import grails.plugin.awssdk.s3.AmazonS3Service
import grails.testing.mixin.integration.Integration
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.spock.Testcontainers
import org.testcontainers.utility.DockerImageName
import spock.lang.Shared
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3

@Integration
@Testcontainers
class FileStorageSpec extends Specification {

    public static final String TARGET_BUCKET = "testbucket"
    AmazonS3Service amazonS3Service
    FileStorageService fileStorageService

    @Shared
    public LocalStackContainer localstack = new LocalStackContainer(
            DockerImageName.parse("localstack/localstack:0.11.2"))
            .withServices(S3)

    void setup() {
        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(
                        localstack.getEndpointConfiguration(S3))
                .withCredentials(
                        localstack.getDefaultCredentialsProvider())
                .build();
        amazonS3Service.client = s3
        amazonS3Service.createBucket(TARGET_BUCKET)
    }

    def "Localstack S3 should contain the bucket created in setup"() {
        when:
        def bucketNames = amazonS3Service.listBucketNames()

        then:
        bucketNames.contains(TARGET_BUCKET)
    }

    def "file can be stored in s3 storage and read"() {
        given:
        def filePath = Paths.get("src/test/resources/car.jpg")
        InputStream stream = Files.newInputStream(filePath)

        when:
        fileStorageService.storeFile(TARGET_BUCKET, "vehicles/123.jpg", stream)

        then:
        amazonS3Service.exists(TARGET_BUCKET, "vehicles/123.jpg")

        when:
        File file = fileStorageService.readFile(TARGET_BUCKET, "vehicles/123.jpg",
                Files.createTempFile(null, null).toAbsolutePath().toString())

        then:
        file
        and:
        file.newInputStream().bytes == filePath.bytes
    }

}
