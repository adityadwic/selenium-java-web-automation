#!/bin/bash

echo "======================================"
echo "Selenium Java Automation Test Runner"
echo "======================================"

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "Maven is not installed. Please install Maven first."
    exit 1
fi

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "Java is not installed. Please install Java 11 or higher."
    exit 1
fi

echo "Cleaning previous test results..."
mvn clean

echo "Compiling the project..."
mvn compile test-compile

echo "Running tests..."
mvn test

echo "======================================"
echo "Test execution completed!"
echo "Check the test-output directory for reports and screenshots."
echo "======================================"
