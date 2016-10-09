/*
 * Copyright 2016 Benedikt Ritter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.britter.bankkata.feature

import de.britter.bankkata.{
  Account,
  Console,
  StatementPrinter,
  TransactionRepository
}
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec

class PrintStatementFeature extends FlatSpec with MockFactory {

  behavior of "an Account"

  val console = stub[Console]

  val transactionRepository = new TransactionRepository()
  val statementPrinter      = new StatementPrinter()
  val account               = new Account(transactionRepository, statementPrinter)

  it should "print statements" in {
    account.deposit(1000)
    account.withdraw(100)
    account.deposit(500)

    account.printStatement()

    inSequence {
      (console.print _).verify("DATE | AMOUNT | BALANCE")
      (console.print _).verify("10/04/2014 | 500.00 | 1400.00")
      (console.print _).verify("02/04/2014 | -100.00 | 900.00")
      (console.print _).verify("01/04/2014 | 1000.00 | 1000.00")
    }
  }
}
