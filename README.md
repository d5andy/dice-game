Snake Eyes Exercise
-------------------

This is a RESTful server implementation of Snake Eyes.

As described:

* Accepts "GET" requests on url "/snakeeyes/play"
* Takes a query parameter of stake (acceptable values are 1.0,2.0,10.0)
* Collects two random integers (from an external service) to represent the dice roll 
* Responds to the caller with the following JSON:

```json
{
 "dice1"​:1,
 "dice2"​:1,
 "stake"​:1.00,
 "winnings"​:30.00,
 "payout_name":"snake_eyes"
}
```

* The player is awarded winnings depending on the outcome of the 2 die. Snake Eyes
    * (2 1s) is awarded a x30 payout. 
    * Any other pair is awarded a x7 payout.
    
How to play
-----------

It is deployed to an heroku app [d5andy-dice-game](https://d5andy-dice-game.herokuapp.com/snakeeyes/play?stake=1.0).
You can play it there.
    
How to build
------------

Run the following command to run the test and run the boot application on port 8080:
* `./gradlew clean build bootRun`

How to Run from Docker
----------------------

Run the following commands to run 
* `docker pull d5andy/dice-game:1`
* `docker run -p 8080:8080 d5andy/dice-game:1`

POSTMAN
-------

To use POSTMAN import:-
* The environment file: dice-game.postman_environment.json
* The collection file: dice-game.postman_collection.json

The action 'Play' fires a 'GET' request to the REST endpoint and displays the JSON body response

Improvements
------------

* Pull more random numbers than required for each game - to protect against outages of the random number server
* Resolve the problem with `RollOutcomeTest`
