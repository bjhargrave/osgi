#!/bin/sh

#----------------------------------------------------------------------
# Specify the location of Sun JDK 6.x or 5.x here
#----------------------------------------------------------------------
JAVA_HOME=/usr/java/jdk1.6.0_02

#----------------------------------------------------------------------
# Specify the location of Maven 2 here
#----------------------------------------------------------------------
M2_HOME=/usr/local/apache-maven/apache-maven-2.0.9

#----------------------------------------------------------------------
# Run the site generation
#----------------------------------------------------------------------
${M2_HOME}/bin/mvn site:site

sleep 10
