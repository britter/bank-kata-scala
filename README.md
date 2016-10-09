# bank-kata-scala #

Welcome to bank-kata-scala!

My scala implementation of the Bank Account kata that [Sandro Mancuso](http://codurance.com/blog/author/sandro-mancuso/)
uses as an exercise in his [Crafting Code course](https://codurance.com/services/training/crafting-code/).

The goal is to implement an application for the following acceptance test:

```
Given a client makes a deposit of 1000.00 on 01/04/2014
And a withdrawal of 100.00 on 02/04/2014
And a deposit of 500.00 on 10/04/2014
When she prints her bank statement
Then she would see

DATE | AMOUNT | BALANCE
10/04/2014 | 500.00 | 1400.00
02/04/2014 | -100.00 | 900.00
01/04/2014 | 1000.00 | 1000.00
```

As a starting point, the Account (java) class is given. The public interface of the class must not be changed.

```java
public class Account {

  public void deposit(int amount);

  public void withdraw(int amount);

  public void printStatement();
}
```

The goal of the exercise was to understand the outside-in or London-school TDD style. Sandro has published a video
series of himself solving the problem on [YouTube](https://www.youtube.com/watch?v=XHnuMjah6ps).

## Contribution policy ##

Contributions via GitHub pull requests are gladly accepted from their original author. Along with any pull requests, please state that the contribution is your original work and that you license the work to the project under the project's open source license. Whether or not you state this explicitly, by submitting any copyrighted material via pull request, email, or other means you agree to license the material under the project's open source license and warrant that you have the legal authority to do so.

## License ##

This code is open source software licensed under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0).
