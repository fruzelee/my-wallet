# CHANGELOG

All notable changes to this project will be documented in this file.

# v1 (1.0)

- feat: create initial boilerplate.
- chore: add CHANGELOG.md to track notable project changes.
- chore: update .gitignore file with Android template to ensure that irrelevant files are not
  committed to the repository.
- feat: add project dependencies to build.gradle file for the project.
- feat: add view binding support to the project to simplify view references.
- feat: upgrade project dependencies in build.gradle file to ensure compatibility with latest
  versions.
- feat: create a navigation graph in the navigation folder to simplify navigation flow of the app.
- feat: include FragmentContainerView in main activity layout to enable fragment transactions.
- feat: integrate custom pin view library into project for improved security and user experience.
- feat: create UserLoginFragment for user login functionality.
- feat: update UI/UX of user login for better usability.
- feat: add SDP for scalable size units to ensure apps compatibility with different screen sizes.
- feat: add SSP for scalable size units for texts to ensure apps compatibility with different screen
  sizes.
- feat(login-ui): update the user login UI for a better user experience.
- feat(pin-library): updates the pin view library for improved security and usability.
- feat(layout-validation):  add validation to login layout for better UI error handling and UX.
- feat(screen-size): add support for different screen sizes to ensure compatibility across various
  devices.
- feat(send-fund): add SendFundFragment
- feat(send-fund): update UI for better user experience
- chore: update the codebase to remove any resources that are no longer in use.
- feat: implement AppApplication class for Hilt code generation

This commit add a new AppApplication class and implement the application class on it, basically the
application is going to be used to tell hilt to start generating code for us

- feat: configure Kotlin and Gradle for Android project
- refactor: clean up UserLoginFragment
- refactor(presentation): move UserLoginFragment to presentation layer within "user_login" feature
  for better organization and maintainability
- feat: implement view binding for user login screen
- feat: implement RepositoryModule class for Hilt dependency injection

This commit adds a new class called RepositoryModule and implements it for Hilt dependency
injection. The purpose of this class is to provide dependencies for the app using Hilt.

- fix: use @InstallIn annotation to define dependency scope

This commit resolves an issue by using the @InstallIn annotation to specify the scope or visibility
of dependencies in the codebase. The annotation is used to define the component where the
dependencies should be installed and made available for use.

- feat: use SingletonComponent for singleton dependencies

This commit uses the SingletonComponent to declare dependencies that should only have a single
instance throughout the application. Since the app does not need multiple instances of these
dependencies, using a singleton is more efficient.

- feat: Add logging-interceptor dependency to build.gradle

This commit adds a new dependency, logging-interceptor, to the build.gradle file. The
logging-interceptor is a useful tool for logging network requests and responses, which will aid in
debugging and monitoring network-related issues.

- feat: Add ApiClient services as companion object

This commit adds ApiClient services as a companion object. By defining the ApiClient services as a
companion object, developers can easily access and use the services without the need to create a new
instance of the ApiClient class. This change will make the code more efficient and easier to read.

- feat: Add WalletService class to fetch login response

This commit adds a new WalletService class that is responsible for fetching the login response. The
WalletService class is a useful tool for managing the login process and handling any errors that may
occur during the process. This change will make the code more modular and easier to maintain.
Developers can now use the WalletService class to handle login-related functionality without the
need to modify existing code.

- feat(user_login): Add UserLoginRequestModel to data layer

This commit adds a new UserLoginRequestModel to the data layer of the user_login feature. The
UserLoginRequestModel is a data class that contains the necessary data to perform a user login.

- feat(user_login): add UserLoginEntity to data layer

This commit adds the UserLoginEntity to the data layer for the user_login feature. The purpose of
this entity is to handle user login-related data and operations. This feature enhancement will
improve the functionality and reliability of the user login feature.

- feat: add login functionality
- feat: add send fund functionality
- feat: Add internet permission to manifest
- feat: Enable data binding in Gradle
- feat: Update login UI
- feat: Update send fund UI
- chore: Remove unused resources from codebase
- feat: Update PinView library
- feat: Update launcher icon
- feat: Add username validation with error toast
- feat: Replace error toast message with TextInputLayout error message for better user experience
- feat: Remove error message for valid username
- fix: convert username uppercase letters to lowercase

This commit updates the username filter logic to ensure that any uppercase letters entered by the
user are automatically converted to lowercase. This ensures consistency in the formatting of
usernames and improves the user experience.

- feat: implement deduction of input amount from user balance

This commit adds new logic to update the user balance by deducting the input amount. This is
achieved by subtracting the input amount from the current user balance. This feature enables the
user to send fund from their account and reflects the updated balance in real-time.

- style: update Max Button UI based on inputted and user balance

This commit updates the UI of the Max Button to reflect changes in the inputted amount and user
balance. If the inputted amount is equal to the user balance, the Max Button UI is modified to
indicate that the maximum amount has been selected. This enhancement improves the user experience by
providing a clear visual indication of the selected amount.

- feat: implement pin validation for login

This commit adds a new feature to the login process - pin validation. When a user logs in, they are
prompted to enter their pin, and the system validates it to ensure it matches the one associated
with their account. This feature enhances security by adding an extra layer of authentication to the
login process.

- refactor: optimize imports

This commit refactors the code by optimizing imports. Unused imports are removed, and the remaining
ones are sorted alphabetically to improve code readability and organization. This enhancement does
not affect the functionality of the code but improves its maintainability.

- refactor: remove unused namespace declaration from theme.xml

This commit refactors the code by removing an unused namespace declaration from the theme.xml file.
This enhancement improves code readability and organization by removing unnecessary code that does
not affect the functionality of the application.

- chore: update dependency libraries in build.gradle

This commit updates the dependency libraries in the build.gradle file. By upgrading to the latest
versions of the dependencies, the application benefits from new features and improvements, bug
fixes, and enhanced security. This chore ensures that the application is up-to-date with the latest
industry standards and best practices.

- refactor: lift return statement out of 'try' block

This commit refactors the code by lifting the return statement out of the 'try' block. By doing so,
the return statement is executed regardless of whether an exception is thrown or not. This
enhancement improves the readability and maintainability of the code by separating the error
handling logic from the main flow of the function.

- feat: implement dark theme support

This commit adds a new feature to the application - dark theme support. Users can now choose to
switch between light and dark themes based on their preference. This enhancement improves the user
experience by providing users with the option to choose a theme that best suits their needs. The
implementation of this feature may involve modifications to the UI, such as changing the color
palette and adjusting the contrast of the elements to ensure readability.

- feat: implement dynamic update of continue button on Send Fund feature

This commit adds a new feature to the Send Fund functionality that dynamically updates the state of
the Continue button based on the user's inputted amount. If the user enters an amount that exceeds
their wallet balance, the Continue button is disabled, and an error message stating "Insufficient
balance" is displayed. Additionally, an Add Fund button appears to allow the user to add funds to
their wallet. This enhancement improves the user experience by providing real-time feedback and
guidance to the user during the transaction process.

- docs: Update README.md

Update README.md, which provides a clear and concise summary of the changes made in the commit.

- chore(docs): update technology stack on readme.md

The update is related to the project's documentation and specifically, the technology stack section
of the readme.md file has been updated.

- chore(docs): add demo app screenshots for functionality emphasis

This commit adds demo app screenshots to showcase the functionality of the application. The purpose
of this change is to provide a clearer understanding of the app's features for users.

- chore: add MIT License to documentation

This commit adds the MIT License to the documentation. The purpose of this change is to ensure that
the appropriate license information is provided for the project.

- feat: add visitor counter to readme.md

This commit adds a visitor counter to the readme.md file to track the number of visitors to the
project.