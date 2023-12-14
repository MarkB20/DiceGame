# Dice Game App
# Overview
The Dice Game App is a mobile game application built for Android using Java. It allows a human player to compete against a computer opponent in a dice game. 
Players take turns rolling 5 dice, scoring points based on the sum of the dice faces, and rerolling dice up to 2 times per turn. 
The first player to reach a target score wins.

# Key Features
* Human vs computer gameplay
* Rolling 5 dice per turn
* Scoring based on sum of dice faces
* Option to reroll any dice up to 2 times per turn
* First to target score wins
* Tiebreaker mechanism
* Setting custom target score
* Persisting win/loss count
# User Interface
The intuitive user interface allows the user to:

* Start a new game
* View game rules and credits in the About section
* Roll dice and view dice faces
* Select dice to reroll
* Score turn or take rerolls
* View current score and win counts
* Receive win/loss notification
It displays dice rolls visually using dice face images and adapts smoothly between portrait and landscape orientations.

# Technical Details
The app makes extensive use of:

* Random - to simulate dice rolls
* Images - to represent dice faces
* Fragments - for the About section
* Activities - for game screen
* SharedPreferences - to persist win counts
* 
It follows Android best practices around layouts, styles, strings etc. for maintainability and extensibility.

# Conclusion
The Dice Game App provides a seamless and visually appealing dice game experience for mobile, 
with challenging gameplay via rerolls and scoring. 
The component-based architecture would allow enhancing it with features like online multiplayer.
