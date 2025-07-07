#!/bin/bash
set -e

TOMEE_HOME="/home/khag02/home/Khang/tomee"
WAR_NAME="tarot-healing-1.0.0.war"
APP_NAME="tarothealing"
WAR_PATH="build/libs/$WAR_NAME"

# Màu terminal
CYAN="\033[0;36m"
GREEN="\033[0;32m"
NC="\033[0m"

echo -e "${CYAN}🛑 Stopping TomEE...${NC}"
"$TOMEE_HOME/bin/shutdown.sh" || true
sleep 5  # Đợi chắc chắn TomEE dừng hẳn

echo -e "${CYAN}🧹 Cleaning old deployment...${NC}"
rm -rf "$TOMEE_HOME/webapps/$APP_NAME"
rm -f "$TOMEE_HOME/webapps/$APP_NAME.war"
rm -rf "$TOMEE_HOME/work/*"
rm -rf "$TOMEE_HOME/temp/*"

echo -e "${CYAN}🚀 Building project...${NC}"
./gradlew clean build

if [ ! -f "$WAR_PATH" ]; then
  echo -e "${RED}❌ WAR file not found at $WAR_PATH. Build failed?${NC}"
  exit 1
fi

echo -e "${CYAN}🧩 Copying datasource config...${NC}"
cp ./deploy/tomee-template.xml "$TOMEE_HOME/conf/tomee.xml"

echo -e "${CYAN}📦 Deploying new WAR...${NC}"
cp "$WAR_PATH" "$TOMEE_HOME/webapps/$APP_NAME.war"

echo -e "${CYAN}🔁 Starting TomEE...${NC}"
"$TOMEE_HOME/bin/startup.sh"

echo -e "${GREEN}✅ DONE! Visit: http://localhost:8080/$APP_NAME/api/hello-world${NC}"
