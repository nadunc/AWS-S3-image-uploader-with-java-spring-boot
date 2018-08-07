# AWS S3 image uploader with java spring boot

Simple Amazon Web Services S3 Image uploader developed with java 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

* AWS S3 Documentation https://aws.amazon.com/documentation/s3/

### Prerequisites

* Apache Maven
* JDK 1.7+

### Configuration
* Modify **application.properties** file with your aws s3 credentials
```
app.aws.iam.accesskey=<add-your-access-key>
app.aws.iam.secretkey=<add-your-secret-key>
app.aws.s3.clientregion=<add-your-s3-client-region>
app.aws.s3.bucketname=<add-your-s3-bucket-name>
```

### Building & Running

* Build maven project

```
mvn clean package
```

* Run jar file

```
java -jar target\s3_image_uploader-0.0.1-SNAPSHOT.jar
```


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **[Nadun Chamikara](https://github.com/nadunc)**

## License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/nadunc/AWS-S3-image-uploader-with-java-spring-boot/blob/master/LICENSE) file for details
