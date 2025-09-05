# Contributing to Selenium Java Automation Framework

We love your input! We want to make contributing to this project as easy and transparent as possible, whether it's:

- Reporting a bug
- Discussing the current state of the code
- Submitting a fix
- Proposing new features
- Becoming a maintainer

## 🚀 Quick Start for Contributors

1. Fork the repository
2. Clone your fork: `git clone https://github.com/adityadwic/selenium-web-java.git`
3. Create a branch: `git checkout -b feature/my-new-feature`
4. Make your changes and test them
5. Commit: `git commit -am 'Add some feature'`
6. Push: `git push origin feature/my-new-feature`
7. Create a Pull Request

## 🛠️ Development Setup

### Prerequisites
- Java 11+
- Maven 3.6+
- Git

### Setup
```bash
# Clone the repository
git clone https://github.com/adityadwic/selenium-web-java.git
cd selenium-web-java

# Install dependencies
mvn clean install

# Run tests to ensure everything works
mvn test
```

## 📋 Code Style Guidelines

### Java Conventions
- Use **4 spaces** for indentation (no tabs)
- Follow **camelCase** naming convention
- Use meaningful variable and method names
- Add **JavaDoc** comments for public methods
- Keep methods small and focused

### Example Code Style
```java
/**
 * Clicks on the specified element after waiting for it to be clickable
 * @param element WebElement to click
 */
public void click(WebElement element) {
    wait.until(ExpectedConditions.elementToBeClickable(element)).click();
}
```

### Test Conventions
- Test method names should be descriptive: `testUserRegistrationWithValidData()`
- Use **TestNG annotations** appropriately
- Include **assertions** with meaningful messages
- Add **test descriptions** in annotations

## 🧪 Testing Guidelines

### Before Submitting
1. **Run all tests**: `mvn test`
2. **Check code coverage**: Ensure new code is covered by tests
3. **Verify cross-browser compatibility**: Test on Chrome, Firefox, Safari
4. **Update documentation**: If you change APIs or add features

### Writing Tests
```java
@Test(description = "Verify user can successfully register with valid data")
public void testUserRegistrationSuccess() {
    // Given
    String testEmail = TestDataGenerator.generateRandomEmail();
    
    // When
    homePage.clickSignupLogin();
    loginPage.performSignup("TestUser", testEmail);
    
    // Then
    Assert.assertTrue(signupPage.isEnterAccountInfoVisible(), 
        "Account information page should be visible");
}
```

## 📝 Commit Message Guidelines

### Format
```
<type>(<scope>): <subject>

<body>

<footer>
```

### Types
- **feat**: A new feature
- **fix**: A bug fix
- **docs**: Documentation only changes
- **style**: Changes that do not affect the meaning of the code
- **refactor**: A code change that neither fixes a bug nor adds a feature
- **test**: Adding missing tests or correcting existing tests
- **chore**: Changes to the build process or auxiliary tools

### Examples
```bash
feat(pages): add new account deletion page object

Add AccountDeletionPage class with methods to handle account
deletion flow including verification of deletion success message.

Closes #123
```

```bash
fix(base): resolve WebDriver initialization issue on macOS

Fixed Chrome options configuration that was causing browser
launch failures on macOS systems with latest Chrome version.

Fixes #456
```

## 🐛 Bug Reports

Create an issue with the following information:

### Bug Report Template
```markdown
**Bug Description**
A clear and concise description of what the bug is.

**Steps to Reproduce**
1. Go to '...'
2. Click on '....'
3. Scroll down to '....'
4. See error

**Expected Behavior**
A clear description of what you expected to happen.

**Screenshots**
If applicable, add screenshots to help explain your problem.

**Environment:**
- OS: [e.g. macOS, Windows, Linux]
- Browser: [e.g. Chrome 118, Firefox 115]
- Java Version: [e.g. Java 11]
- Maven Version: [e.g. 3.8.1]

**Additional Context**
Add any other context about the problem here.
```

## ✨ Feature Requests

### Feature Request Template
```markdown
**Feature Description**
A clear and concise description of what you want to happen.

**Problem Statement**
What problem does this feature solve?

**Proposed Solution**
Describe the solution you'd like to see implemented.

**Alternatives Considered**
Describe any alternative solutions you've considered.

**Additional Context**
Add any other context, mockups, or examples about the feature request here.
```

## 🔄 Pull Request Process

### Before Creating a PR
1. ✅ Ensure your code follows the style guidelines
2. ✅ Write or update tests for your changes
3. ✅ Update documentation if needed
4. ✅ Verify all tests pass locally
5. ✅ Write a clear PR description

### PR Description Template
```markdown
## 📋 Description
Brief description of changes

## 🧪 Type of Change
- [ ] Bug fix (non-breaking change which fixes an issue)
- [ ] New feature (non-breaking change which adds functionality)
- [ ] Breaking change (fix or feature that would cause existing functionality to not work as expected)
- [ ] Documentation update

## ✅ Testing
- [ ] I have added tests that prove my fix is effective or that my feature works
- [ ] New and existing unit tests pass locally with my changes
- [ ] I have tested on multiple browsers (Chrome, Firefox, Safari)

## 📚 Documentation
- [ ] I have updated the README.md
- [ ] I have updated relevant code comments
- [ ] I have updated the CHANGELOG.md (if applicable)

## 📷 Screenshots (if applicable)
Add screenshots to help explain your changes

## 🔗 Related Issues
Closes #(issue number)
```

## 🏷️ Versioning

We use [Semantic Versioning](http://semver.org/) for releases:
- **MAJOR**: Breaking changes
- **MINOR**: New features (backward compatible)
- **PATCH**: Bug fixes (backward compatible)

## 📄 License

By contributing, you agree that your contributions will be licensed under the MIT License.

## 🤝 Code of Conduct

### Our Pledge
We pledge to make participation in our project a harassment-free experience for everyone, regardless of age, body size, disability, ethnicity, gender identity and expression, level of experience, nationality, personal appearance, race, religion, or sexual identity and orientation.

### Our Standards
- Using welcoming and inclusive language
- Being respectful of differing viewpoints and experiences
- Gracefully accepting constructive criticism
- Focusing on what is best for the community
- Showing empathy towards other community members

## 💬 Questions?

Feel free to contact us by:
- Creating an [issue](https://github.com/adityadwic/selenium-web-java/issues)
- Starting a [discussion](https://github.com/adityadwic/selenium-web-java/discussions)

## 🙏 Recognition

Contributors will be recognized in our README.md file. Thank you for making this project better! 

---

**Happy Contributing! 🚀**
