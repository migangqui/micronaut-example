/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/

import java.net.*
import java.io.*
import java.nio.channels.*
import java.util.Properties

object MavenWrapperDownloader {

    /**
     * Default URL to download the maven-wrapper.jar from, if no 'downloadUrl' is provided.
     */
    private val DEFAULT_DOWNLOAD_URL = "https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.4.0/maven-wrapper-0.4.0.jar"

    /**
     * Path to the maven-wrapper.properties file, which might contain a downloadUrl property to
     * use instead of the default one.
     */
    private val MAVEN_WRAPPER_PROPERTIES_PATH = ".mvn/wrapper/maven-wrapper.properties"

    /**
     * Path where the maven-wrapper.jar will be saved to.
     */
    private val MAVEN_WRAPPER_JAR_PATH = ".mvn/wrapper/maven-wrapper.jar"

    /**
     * Name of the property which should be used to override the default download url for the wrapper.
     */
    private val PROPERTY_NAME_WRAPPER_URL = "wrapperUrl"

    fun main(args: Array<String>) {
        System.out.println("- Downloader started")
        val baseDirectory = File(args[0])
        System.out.println("- Using base directory: " + baseDirectory.getAbsolutePath())

        // If the maven-wrapper.properties exists, read it and check if it contains a custom
        // wrapperUrl parameter.
        val mavenWrapperPropertyFile = File(baseDirectory, MAVEN_WRAPPER_PROPERTIES_PATH)
        var url = DEFAULT_DOWNLOAD_URL
        if (mavenWrapperPropertyFile.exists()) {
            var mavenWrapperPropertyFileInputStream: FileInputStream? = null
            try {
                mavenWrapperPropertyFileInputStream = FileInputStream(mavenWrapperPropertyFile)
                val mavenWrapperProperties = Properties()
                mavenWrapperProperties.load(mavenWrapperPropertyFileInputStream)
                url = mavenWrapperProperties.getProperty(PROPERTY_NAME_WRAPPER_URL, url)
            } catch (e: IOException) {
                System.out.println("- ERROR loading '$MAVEN_WRAPPER_PROPERTIES_PATH'")
            } finally {
                try {
                    if (mavenWrapperPropertyFileInputStream != null) {
                        mavenWrapperPropertyFileInputStream!!.close()
                    }
                } catch (e: IOException) {
                    // Ignore ...
                }

            }
        }
        System.out.println("- Downloading from: : $url")

        val outputFile = File(baseDirectory.getAbsolutePath(), MAVEN_WRAPPER_JAR_PATH)
        if (!outputFile.getParentFile().exists()) {
            if (!outputFile.getParentFile().mkdirs()) {
                System.out.println(
                        "- ERROR creating output direcrory '" + outputFile.getParentFile().getAbsolutePath() + "'")
            }
        }
        System.out.println("- Downloading to: " + outputFile.getAbsolutePath())
        try {
            downloadFileFromURL(url, outputFile)
            System.out.println("Done")
            System.exit(0)
        } catch (e: Throwable) {
            System.out.println("- Error downloading")
            e.printStackTrace()
            System.exit(1)
        }

    }

    @Throws(Exception::class)
    private fun downloadFileFromURL(urlString: String, destination: File) {
        val website = URL(urlString)
        val rbc: ReadableByteChannel
        rbc = Channels.newChannel(website.openStream())
        val fos = FileOutputStream(destination)
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE)
        fos.close()
        rbc.close()
    }

}
