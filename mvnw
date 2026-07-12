#!/bin/sh
# Maven Wrapper - https://github.com/takari/maven-wrapper

# Determine the command to run
MVNW_CMD=${0##*/}

# Determine the Java command to use
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        JAVACMD="java"
    fi
else
    JAVACMD="java"
fi

if [ ! -x "$JAVACMD" ] ; then
    echo "Error: JAVA_HOME is not defined correctly."
    echo "  We cannot execute $JAVACMD"
    exit 1
fi

# Determine the Maven wrapper jar location
MVNW_REPOURL="${MVNW_REPOURL:-https://repo1.maven.org/maven2}"
MVNW_DEFAULT_MAVEN_HOME="${HOME}/.m2/wrapper/dists"
APP_HOME="$( cd "${0%[/\\]*}" > /dev/null; cd .. && pwd )"
MVNW_MODULE_DIR=".mvn"

# Determine the Maven home directory
MVNW_MAVEN_HOME="${MVNW_DEFAULT_MAVEN_HOME}"

# Download the Maven wrapper jar if it doesn't exist
MVNW_JAR="${APP_HOME}/${MVNW_MODULE_DIR}/wrapper/maven-wrapper.jar"

if [ ! -f "$MVNW_JAR" ] ; then
    if [ -z "$MVNW_REPOURL" ] ; then
        MVNW_REPOURL="https://repo1.maven.org/maven2"
    fi
    MVNW_URL="${MVNW_REPOURL}/org/apache/maven/wrapper/maven-wrapper/3.3.2/maven-wrapper-3.3.2.jar"
    echo "Downloading Maven Wrapper from ${MVNW_URL}..."
    if command -v curl > /dev/null 2>&1 ; then
        curl -sS -o "$MVNW_JAR" "$MVNW_URL"
    elif command -v wget > /dev/null 2>&1 ; then
        wget -q -O "$MVNW_JAR" "$MVNW_URL"
    else
        echo "Error: Neither curl nor wget is available."
        exit 1
    fi
fi

# Execute the Maven wrapper jar
exec "$JAVACMD" \
    $MVNW_MAVEN_OPTS \
    $MAVEN_OPTS \
    -Dmaven.home="$MVNW_MAVEN_HOME" \
    -Dmaven.multiModuleProjectDirectory="$APP_HOME" \
    -jar "$MVNW_JAR" \
    "$@"
