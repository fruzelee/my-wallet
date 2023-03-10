<p align="center">
    Visitor count<br>
    <img src="https://profile-counter.glitch.me/fruzelee/count.svg" />
</p>

<h1 align="center">Salamun 'Alaik 👋, Welcome to My Wallet</h1>
<h3 align="center">A simple wallet application for the Android platform.</h3>

# Wallet App

This is an Android application developed using Kotlin, Android SDK, Retrofit 2.x.x, Hilt DI, and
MVVM architecture. The app consists of two fragments - page1 and page2 - implemented using the
Android Navigation component. The app also features a username filter logic to ensure that users
input their username correctly, as well as balance input validation to avoid insufficient balance
errors.

*[Download the app now!](https://github.com/fruzelee/my-wallet/releases/download/v1.0/my-wallet-v1.0.apk)*

# Features

The following features were implemented in the Send Fund App:

# Page1: Login

- Username filter logic: The app allows users to input their username using only letters (a-z),
  numbers (0-9), full stop (.), and underscore (_). The username must be between 3 to 32 characters
  long, and the user cannot input successive underscores or full stops. The app also converts any
  uppercase letters to lowercase letters.
- PIN input field: After the user inputs their username, the PIN input field is automatically
  enabled
  after waiting for 2 seconds from the user's last character input.
- Continue button: The continue button is enabled only after the user inputs a valid username.

# Page2: Send Fund

- Call "login" API: After a successful response from the login API, the app takes the user to page2.
- WalletAddress in Recipient card: The app sets the user's username (WalletAddress) on the recipient
  card.
- Max button: The app allows the user to click on the max button to set the balance amount.
- Balance USDC: The app shows the user's balance in USDC after deducting the input value. The input
  value must be within the use case accuracy.
- Insufficient balance: If the user inputs an amount greater than their balance, the app shows an "
  Insufficient balance" message.
- Add fund button: If the user inputs an amount greater than their balance, the app shows an "Add
  fund" button. However, this button doesn't have any action at this phase.

# Technology Stack

- Language: Kotlin
- SDK: Android SDK
- Navigation Graph: Yes
- Single Activity Pattern: Yes
- Network Operation: Retrofit 2.x.x
- Dependency Injection: Hilt
- Architecture: MVVM

This technology stack is used for developing Android applications. Kotlin is the primary language
used for coding while the Android SDK provides the necessary tools and libraries to build Android
applications. The Navigation Graph is utilized for efficient navigation between different screens in
the application, while the Single Activity Pattern provides a streamlined and efficient approach to
designing the app's structure.

Retrofit 2.x.x is used for network operations, providing a robust and reliable way to communicate
with remote servers. Hilt is utilized for dependency injection, allowing for easier management and
organization of app components. Finally, the MVVM architecture pattern is utilized to maintain a
clean and modular architecture for the app.

# Conclusion

The Send Fund App is a fully functional Android application that implements the features required in
the PRD document. The app is implemented using Kotlin and adheres to the MVVM architecture, making
it easy to maintain and expand.

# Demo App

<a href="https://postimg.cc/Lqbx994k" target="_blank"><img src="https://i.postimg.cc/d1qp93zf/web-build-20230226-o3f4-cheetah-33-en-US-portrait-artifacts-1.png" alt="web-build-20230226-o3f4-cheetah-33-en-US-portrait-artifacts-1"/></a> <a href="https://postimg.cc/jwnZCpCp" target="_blank"><img src="https://i.postimg.cc/ydv5pBK8/web-build-20230226-o3f4-cheetah-33-en-US-portrait-artifacts-2.png" alt="web-build-20230226-o3f4-cheetah-33-en-US-portrait-artifacts-2"/></a> <a href="https://postimg.cc/m1xV9mHt" target="_blank"><img src="https://i.postimg.cc/7hhRpcPM/web-build-20230226-o3f4-cheetah-33-en-US-portrait-artifacts-3.png" alt="web-build-20230226-o3f4-cheetah-33-en-US-portrait-artifacts-3"/></a> <a href="https://postimg.cc/y3Fjy0mn" target="_blank"><img src="https://i.postimg.cc/qqFF0Lzf/web-build-20230226-o3f4-cheetah-33-en-US-portrait-artifacts-4.png" alt="web-build-20230226-o3f4-cheetah-33-en-US-portrait-artifacts-4"/></a> <a href="https://postimg.cc/vcs3HSyK" target="_blank"><img src="https://i.postimg.cc/4NKMPrSX/web-build-20230226-o3f4-cheetah-33-en-US-portrait-artifacts-5.png" alt="web-build-20230226-o3f4-cheetah-33-en-US-portrait-artifacts-5"/></a> <a href="https://postimg.cc/PNZSHngZ" target="_blank"><img src="https://i.postimg.cc/SQ13WyVZ/web-build-20230226-o3f4-cheetah-33-en-US-portrait-artifacts-6.png" alt="web-build-20230226-o3f4-cheetah-33-en-US-portrait-artifacts-6"/></a> 

# MIT License

```
Copyright (c) 2023 Muhammad Fazle Rabbi

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
