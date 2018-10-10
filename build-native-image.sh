./mvnw package
java -cp target/micronaut-example-0.1.jar io.micronaut.graal.reflect.GraalClassLoadingAnalyzer
native-image --class-path target/micronaut-example-0.1.jar \
			 -H:ReflectionConfigurationFiles=target/reflect.json \
			 -H:EnableURLProtocols=http \
			 -H:IncludeResources="logback.xml|application.yml|META-INF/services/*.*" \
			 -H:Name=complete \
			 -H:Class=com.migangqui.micronaut.example.Application \
			 -H:+ReportUnsupportedElementsAtRuntime \
			 -H:+AllowVMInspection \
			 --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder,io.netty.handler.codec.http.websocketx.WebSocket00FrameEncoder