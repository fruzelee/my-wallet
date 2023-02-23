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

