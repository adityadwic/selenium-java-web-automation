#!/bin/bash

echo "======================================"
echo "Cross-Browser Testing on macOS"
echo "======================================"

BROWSERS=("chrome" "firefox" "safari")
RESULTS_DIR="test-output/cross-browser-results"

# Create results directory
mkdir -p "$RESULTS_DIR"

for browser in "${BROWSERS[@]}"; do
    echo ""
    echo "🔄 Testing with $browser..."
    echo "======================================"
    
    # Update config file
    sed -i '' "s/browser=.*/browser=$browser/" src/test/resources/config.properties
    
    # Create browser-specific output directory
    BROWSER_OUTPUT_DIR="$RESULTS_DIR/$browser"
    mkdir -p "$BROWSER_OUTPUT_DIR"
    
    # Run tests
    mvn clean test -Dtest.output.dir="$BROWSER_OUTPUT_DIR"
    
    if [ $? -eq 0 ]; then
        echo "✅ $browser tests passed"
    else
        echo "❌ $browser tests failed"
    fi
    
    # Copy reports to browser-specific directory
    if [ -d "test-output" ]; then
        cp -r test-output/* "$BROWSER_OUTPUT_DIR/" 2>/dev/null || true
    fi
done

# Restore default browser
sed -i '' 's/browser=.*/browser=chrome/' src/test/resources/config.properties

echo ""
echo "======================================"
echo "Cross-browser testing completed!"
echo "Results saved in: $RESULTS_DIR"
echo "======================================"

# Generate summary report
echo "Browser Test Summary:" > "$RESULTS_DIR/summary.txt"
echo "=====================" >> "$RESULTS_DIR/summary.txt"
for browser in "${BROWSERS[@]}"; do
    if [ -d "$RESULTS_DIR/$browser" ]; then
        echo "✅ $browser: Tests executed" >> "$RESULTS_DIR/summary.txt"
    else
        echo "❌ $browser: Tests failed" >> "$RESULTS_DIR/summary.txt"
    fi
done

cat "$RESULTS_DIR/summary.txt"
