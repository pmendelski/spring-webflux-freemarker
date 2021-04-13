# Spring Webflux FreeMarker

Sample project that presents [Blockhound](https://github.com/reactor/BlockHound) error with
a [FreeMarker](https://freemarker.apache.org/) template engine and a
simple [Spring Boot](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)
configuration.

## How to reproduce

1. Build project `./gradlew build`
2. Start application `./gradlew run`
3. Get homepage `curl http://localhost:8080`

## Problem

Blockhound detects a blocking call when fetching an HTML page from a template. Spring WebFlux uses FreeMarker that loads
templates using blocking IO.

Error:

```
22:54:28.761 [reactor-http-epoll-2] ERROR o.s.b.a.w.r.e.AbstractErrorWebExceptionHandler - [584743de-1]  500 Server Error for HTTP GET "/"
reactor.blockhound.BlockingOperationError: Blocking call! java.io.FileInputStream#readBytes
        at java.base/java.io.FileInputStream.readBytes(FileInputStream.java)
        Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException:
Error has been observed at the following site(s):
        |_ checkpoint ⇢ Handler com.sandbox.HomeController#home() [DispatcherHandler]
        |_ checkpoint ⇢ HTTP GET "/" [ExceptionHandlingWebHandler]
Stack trace:
                at java.base/java.io.FileInputStream.readBytes(FileInputStream.java)
                at java.base/java.io.FileInputStream.read(FileInputStream.java:279)
                at java.base/java.io.BufferedInputStream.read1(BufferedInputStream.java:290)
                at java.base/java.io.BufferedInputStream.read(BufferedInputStream.java:351)
                at java.base/sun.nio.cs.StreamDecoder.readBytes(StreamDecoder.java:284)
                at java.base/sun.nio.cs.StreamDecoder.implRead(StreamDecoder.java:326)
                at java.base/sun.nio.cs.StreamDecoder.read(StreamDecoder.java:178)
                at java.base/java.io.InputStreamReader.read(InputStreamReader.java:185)
                at java.base/java.io.BufferedReader.read1(BufferedReader.java:210)
                at java.base/java.io.BufferedReader.read(BufferedReader.java:287)
                at freemarker.template.Template$LineTableBuilder.read(Template.java:863)
                at freemarker.core.SimpleCharStream.FillBuff(SimpleCharStream.java:116)
                at freemarker.core.SimpleCharStream.readChar(SimpleCharStream.java:198)
                at freemarker.core.SimpleCharStream.BeginToken(SimpleCharStream.java:138)
                at freemarker.core.FMParserTokenManager.getNextToken(FMParserTokenManager.java:8124)
                at freemarker.core.FMParser.jj_scan_token(FMParser.java:5989)
                at freemarker.core.FMParser.jj_3_17(FMParser.java:5085)
                at freemarker.core.FMParser.jj_2_17(FMParser.java:4809)
                at freemarker.core.FMParser.Root(FMParser.java:4660)
                at freemarker.template.Template.<init>(Template.java:252)
                at freemarker.cache.TemplateCache.loadTemplate(TemplateCache.java:548)
                at freemarker.cache.TemplateCache.getTemplateInternal(TemplateCache.java:439)
                at freemarker.cache.TemplateCache.getTemplate(TemplateCache.java:292)
                at freemarker.template.Configuration.getTemplate(Configuration.java:2836)
                at freemarker.template.Configuration.getTemplate(Configuration.java:2694)
                at org.springframework.web.reactive.result.view.freemarker.FreeMarkerView.getTemplate(FreeMarkerView.java:312)
                at org.springframework.web.reactive.result.view.freemarker.FreeMarkerView.checkResourceExists(FreeMarkerView.java:196)
                at org.springframework.web.reactive.result.view.UrlBasedViewResolver.resolveViewName(UrlBasedViewResolver.java:242)
                at org.springframework.web.reactive.result.view.ViewResolutionResultHandler.lambda$resolveViews$2(ViewResolutionResultHandler.java:274)

... and more

```