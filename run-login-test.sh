#!/bin/bash

echo "🔑 Running User Login Test"
echo "=========================="
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven first."
    exit 1
fi

# Clean and compile
echo "🧹 Cleaning and compiling project..."
mvn clean compile test-compile

if [ $? -ne 0 ]; then
    echo "❌ Compilation failed!"
    exit 1
fi

echo ""
echo "🚀 Starting User Login Test..."
echo "Test Case 2: Login User with correct email and password"
echo ""

# Run the login test
mvn test -Dtest=UserLoginTest -Dsurefire.suiteXmlFiles=src/test/resources/testng-login.xml

# Check if test execution was successful
if [ $? -eq 0 ]; then
    echo ""
    echo "✅ User Login Test completed successfully!"
    echo ""
    echo "📊 Reports Generated:"
    echo "---------------------"
    echo "🌐 ExtentReport: test-output/ExtentReport_*.html"
    echo "📋 TestNG Report: test-output/index.html"
    echo ""
    echo "💡 To view ExtentReport:"
    echo "open test-output/ExtentReport_*.html"
    echo ""
    echo "💡 To view TestNG Report:"
    echo "open test-output/index.html"
    echo ""
else
    echo ""
    echo "❌ User Login Test failed!"
    echo "Check the reports for details:"
    echo "📋 TestNG Report: test-output/index.html"
    exit 1
fi
