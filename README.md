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

POSTMAN
-------

To 