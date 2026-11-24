#!/bin/bash

FRAMEWORK_DIR="/home/hatsugoki/Web-dyn/Framework-servlet"
PROJECT_DIR="/home/hatsugoki/Web-dyn/Medic"
PROJECT_NAME="Medic"
FW_BUILD="$FRAMEWORK_DIR/build"
BUILD_DIR="$PROJECT_DIR/build"
SRC_FW="$FRAMEWORK_DIR/src/main/java"
SRC_PROJECT="$PROJECT_DIR/src/main/java"
WEB_PROJECT="$PROJECT_DIR/src/main/webapp"
TOMCAT_WEBAPPS="/home/hatsugoki/tomcat/apache-tomcat-10.0.16/webapps"
SERVLET_API_JAR="/home/hatsugoki/tomcat/apache-tomcat-10.0.16/lib/servlet-api.jar"

echo "=== 1. Compilation du Framework ==="
rm -rf "$FW_BUILD"
mkdir -p "$FW_BUILD/classes"
find "$SRC_FW" -name "*.java" > "$FRAMEWORK_DIR/sources.txt"
javac -parameters -cp "$SERVLET_API_JAR" -d "$FW_BUILD/classes" @"$FRAMEWORK_DIR/sources.txt" || exit 1

echo "=== 2. Création de FW.jar ==="
cd "$FW_BUILD/classes" || exit
jar -cvf "$FW_BUILD/FW.jar" .

echo "=== 3. Copie de FW.jar dans $PROJECT_NAME/WEB-INF/lib ==="
mkdir -p "$WEB_PROJECT/WEB-INF/lib"
cp -f "$FW_BUILD/FW.jar" "$WEB_PROJECT/WEB-INF/lib/FW.jar"

echo "=== 4. Compilation de $PROJECT_NAME ==="
rm -rf "$BUILD_DIR"
mkdir -p "$BUILD_DIR/WEB-INF/classes"
cp -f "$FRAMEWORK_DIR/src/main/webapp/WEB-INF/application.properties" "$BUILD_DIR/WEB-INF/application.properties" 2>/dev/null || true
find "$SRC_PROJECT" -name "*.java" > "$PROJECT_DIR/sources.txt"
javac -parameters -cp "$SERVLET_API_JAR:$WEB_PROJECT/WEB-INF/lib/*" -d "$BUILD_DIR/WEB-INF/classes" @"$PROJECT_DIR/sources.txt" || exit 1

echo "=== 5. Génération du WAR $PROJECT_NAME.war ==="
cp -r "$WEB_PROJECT/"* "$BUILD_DIR/"
cp -r "$FRAMEWORK_DIR/src/main/webapp/WEB-INF/views" "$BUILD_DIR/WEB-INF/"
jar -cvf "$BUILD_DIR/$PROJECT_NAME.war" -C "$BUILD_DIR" .

echo "=== 6. Déploiement dans Tomcat ==="
cp -f "$BUILD_DIR/$PROJECT_NAME.war" "$TOMCAT_WEBAPPS/"

echo ""
echo "Terminé. Redémarre Tomcat si nécessaire."
