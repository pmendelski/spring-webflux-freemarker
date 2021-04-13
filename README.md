# Problem with: Blockhound + Spring WebFlux + Kotlin

Sample project that presents [Blockhound](https://github.com/reactor/BlockHound) when using kotlin with a
simple [Spring WebFlux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)
configuration.

## How to reproduce

1. Build project `./gradlew build`
2. Start application `./gradlew run`
3. Kotlin endpoint produces an error `curl http://localhost:8080/kotlin/ping`
4. Java endpoint does not produce an error `curl http://localhost:8080/kotlin/ping`

## Problem

Blockhound detects a blocking call when fetching a static string from controller written in kotlin.

Error:

```
23:24:08.836 [reactor-http-epoll-2] ERROR o.s.b.a.w.r.e.AbstractErrorWebExceptionHandler - [20f1cd95-1]  500 Server Error for HTTP GET "/ping"   (v2.4.4)
reactor.blockhound.BlockingOperationError: Blocking call! java.io.RandomAccessFile#readBytes
at java.base/java.io.RandomAccessFile.readBytes(RandomAccessFile.java)tion.Companion using Java 11.0.7
Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException: ebflux-freemarker/build/classes/
Error has been observed at the following site(s):evelopment/java/spring-webflux-freemarker)
|_ checkpoint ⇢ HTTP GET "/kotlin/ping" [ExceptionHandlingWebHandler]e profile set, falling back to default pr
Stack trace:ult
at java.base/java.io.RandomAccessFile.readBytes(RandomAccessFile.java)ckson Kotlin classes supp
at java.base/java.io.RandomAccessFile.read(RandomAccessFile.java:406)
at java.base/java.io.RandomAccessFile.readFully(RandomAccessFile.java:470)
at java.base/java.util.zip.ZipFile$Source.readFullyAt(ZipFile.java:1304)ion in 2.747 seconds (J
at java.base/java.util.zip.ZipFile$ZipFileInputStream.initDataOffset(ZipFile.java:998)
at java.base/java.util.zip.ZipFile$ZipFileInputStream.read(ZipFile.java:1013)
at java.base/java.util.zip.ZipFile$ZipFileInflaterInputStream.fill(ZipFile.java:468)
at java.base/java.util.zip.InflaterInputStream.read(InflaterInputStream.java:159)
at java.base/java.util.zip.InflaterInputStream.read(InflaterInputStream.java:123)
at java.base/java.io.FilterInputStream.read(FilterInputStream.java:83)
at java.base/java.io.DataInputStream.readInt(DataInputStream.java:392)
at kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsBinaryVersion$Companion.readFrom(BuiltInsBinaryVersion.kt:29)
at kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins.BuiltInsPackageFragmentImpl$Companion.create(BuiltInsPackageFragmentImpl.kt:38)
at kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsPackageFragmentProvider.findPackage(JvmBuiltInsPackageFragmentProvider.kt:61)
at kotlin.reflect.jvm.internal.impl.serialization.deserialization.AbstractDeserializedPackageFragmentProvider$fragments$1.invoke(AbstractDeserializedPackageFragmentProvider.kt:35)
at kotlin.reflect.jvm.internal.impl.serialization.deserialization.AbstractDeserializedPackageFragmentProvider$fragments$1.invoke(AbstractDeserializedPackageFragmentProvider.kt:34)
at kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$MapBasedMemoizedFunction.invoke(LockBasedStorageManager.java:578)
at kotlin.reflect.jvm.internal.impl.serialization.deserialization.AbstractDeserializedPackageFragmentProvider.collectPackageFragments(AbstractDeserializedPackageFragmentProvider.kt:43)
at kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderKt.collectPackageFragmentsOptimizedIfPossible(PackageFragmentProvider.kt:50)
at kotlin.reflect.jvm.internal.impl.descriptors.impl.CompositePackageFragmentProvider.collectPackageFragments(CompositePackageFragmentProvider.kt:47)
at kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderKt.collectPackageFragmentsOptimizedIfPossible(PackageFragmentProvider.kt:50)
at kotlin.reflect.jvm.internal.impl.descriptors.impl.CompositePackageFragmentProvider.collectPackageFragments(CompositePackageFragmentProvider.kt:47)
at kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderKt.collectPackageFragmentsOptimizedIfPossible(PackageFragmentProvider.kt:50)
at kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderKt.packageFragments(PackageFragmentProvider.kt:41)
at kotlin.reflect.jvm.internal.impl.descriptors.impl.LazyPackageViewDescriptorImpl$fragments$2.invoke(LazyPackageViewDescriptorImpl.kt:38)
at kotlin.reflect.jvm.internal.impl.descriptors.impl.LazyPackageViewDescriptorImpl$fragments$2.invoke(LazyPackageViewDescriptorImpl.kt:37)
at kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$LockBasedLazyValue.invoke(LockBasedStorageManager.java:408)
at kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$LockBasedNotNullLazyValue.invoke(LockBasedStorageManager.java:527)
at kotlin.reflect.jvm.internal.impl.storage.StorageKt.getValue(storage.kt:42)
at kotlin.reflect.jvm.internal.impl.descriptors.impl.LazyPackageViewDescriptorImpl.getFragments(LazyPackageViewDescriptorImpl.kt:37)
at kotlin.reflect.jvm.internal.impl.descriptors.impl.LazyPackageViewDescriptorImpl$memberScope$1.invoke(LazyPackageViewDescriptorImpl.kt:42)
at kotlin.reflect.jvm.internal.impl.descriptors.impl.LazyPackageViewDescriptorImpl$memberScope$1.invoke(LazyPackageViewDescriptorImpl.kt:41)
... and more

```

The same code written in java is not detected by Blockhound as a blocking call.
See: `curl http://localhost:8080/java/ping`