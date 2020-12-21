package demo

import com.amazonaws.services.s3.model.ObjectMetadata
import grails.plugin.awssdk.s3.AmazonS3Service
import groovy.util.logging.Slf4j

import java.nio.file.Files

@Slf4j
class FileStorageService {

    AmazonS3Service amazonS3Service

    void storeFile(String bucket, String s3Key, InputStream fileStream) {
        def result = amazonS3Service.storeInputStream(bucket, s3Key, fileStream, new ObjectMetadata())
        log.debug("Result: {}", result)
    }

    File readFile(String bucket, String s3Key, String outputPath) {
        log.debug("Content to be written to {}", outputPath)
        amazonS3Service.getFile(bucket, s3Key, outputPath)
    }

    File createTempFile() {
        Files.createTempFile(null, null).toAbsolutePath().toFile()
    }
}
