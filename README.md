[HackerRank Toolkit](http://hyamamoto.github.com/hackerrank-toolkit), API Wrappers and Bots for HackerRank
==================

HackerRank Toolkit provides a small tool set for HackerRank Sign-up Beta. This is made to aid those who have small time to write their own API wrapper to build their bot for new challenges. It's written in Java 6 and tested on Mac OS X Lion, Linux (Gentoo) and Windows 7. It's currently used and maintained by [higon](https://github.com/hyamamoto/hackerrank-core). Anyone is welcome to join.

The toolkit includes:

* API Wrappers and Utilities
    * HackerRankAPI
    * SplashAPI
* Commandline scripts
    * Userstats command
    * Leaderboard command
    * SpaceX bot
    * Candies bot


What Is HackerRank?
-------------------

HackerRank is new fun site you can play with. Basically it's a little coding competition site. Most exciting thing about HackerRank is that it has "Hack" on its name. So we can rationally safely assume we can hack their servers at will, can we.

Check out [their site](https://www.hackerrank.com/) for more detail.


Quick Start (How to run)
------------------------

There are only two ways to run.

1. Buy ([ 7 + 8 + 9 + ... + 2559 + 2560](https://google.com/search?q=\(1%2F2\)*\(2560*\(2560%2B1\)-6*\(6%2B1\)\))) reasonably priced candies from Sam's.
2. Simulate and solve all the Candies games on a table with your girlfriend joyfully.
3. Travel somewhere in the middle of PRC.
4. Employ 100 labors.
5. Let them work on 111 problems each on SpaceX.
6. Pay them with a lotta candies.
7. Now, run.

or

1. Clone the repo and build.
(Alternatively, you can just [download the zipball](https://github.com/hyamamoto/hackerrank-core/downloads).)  
    $ git clone git://github.com/hyamamoto/hackerrank-core.git  
    $ mvn install  
    $ cd target  
    $ tar xvfz *.tar.gz  
2. Create an account on [HackerRank](https://www.hackerrank.com/). In this example, let's say you took a user "Mariacchi".
3. Run a SpaceX bot, then leave your machine for 4+ hours.  
    $ cd hackerrank-toolkit/bin  	
    $ ./splash\_spacex -U Mariacchi -from 1 -to 10000 ( or .\\splash\_spacex.bat on Windows )  
4. Run a Candies bot then leave your machine for 12+ hours.  
    $ ./splash\_candies -U Mariacchi -from 1 -to 2560 ( or .\\splash\_candies.bat on Windows )

... So you see exceptions? Of course you can give me candies I can fix them. 


HackerRank API Wrappers
-----------------------

    Name     : HackerRank Core API  
    Class    : jp.freepress.hackerrank.core.HackerRankAPI  
    Includes : Calls for Sign-in, Sign-out, Sign-up, Leaderboard, User status  

    Name     : HackerRank Splash API  
    Class    : jp.freepress.hackerrank.splash.SplashAPI  
    Includes : Calls for Candies and SpaceX.  

See [SampleMain.java](https://github.com/hyamamoto/hackerrank-core/blob/master/src/main/java/jp/freepress/hackerrank/SampleMain.java) for some actual code.


Bots
----

Name      : **Candies bot**  
Main class: jp.freepress.hackerrank.SplashCandiesMain  
Description: This is a simple "pick candies" game solver. Algorythm's as follows.

 1. If the number of candies is divisible by 6, skip the game since you'll always lose.
 2. If the number is less than 6, take all candies and grin.
 3. Otherwise, the pick should be a number that takes the remaining number to a multiple of six (see rule #1). 

Usage:  

    -from        First # of the challenge  *Required*  
    -to          Last # of the challenge   *Required*  
    -U           Your username. Use with -P option.  
    -P           A password for your username  
    -slog        Log output directory 

Name      : **SpaceX bot**  
Main class: jp.freepress.hackerrank.SplashSpaceXMain  
Description: This is a SpaceX game solver.

This rotate encrypted texts, brute-forces to decipher the most of the questions the Scientist has asked without any supplemental information. Note that you need to get [Wolfram*Alpha](http://products.wolframalpha.com/api/) APPID and specify "-waId {APPID}" command line argument if you are to challenge later than 11000th problem. This bot runs unreliably from #10001 to #11100 when you get an error, you might make some changes on source code or solve unsolvable by hand. 

Usage:

    -from        First # of the challenge  *Required*  
    -to          Last # of the challenge   *Required*  
    -U           Your username. Use with -P option.  
    -P           A password for your username  
    -slog        Log output directory  
    -waId        Your Wolfram*Alpha APPID. This option is required to solve #11001+.  


Name      : **Userstats**  
Main class: jp.freepress.hackerrank.UserstatsMain  
Description: This logins and gets a single user status from the rank leader board.

Usage:

    -U           Your username. Use with -P option.  
    -P           A password for your username  


Name      : **Leaderboard**  
Main class: jp.freepress.hackerrank.LeaderboardMain  
Description: This retrieves a list of user statuses from the rank leader board.

Usage:

    -L           limit


License
-------

All source code and experimental distributable files including supplemental libraries are released as an open source software under Apache Public License, version 2.0.


Happy Hacking! **:D**
