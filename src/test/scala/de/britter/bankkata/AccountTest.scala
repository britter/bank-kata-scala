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

package de.britter.bankkata

import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec

class AccountTest extends FlatSpec with MockFactory {

  behavior of "an Account"

  val transactionRepository = stub[TransactionRepository]
  val statementPrinter      = stub[StatementPrinter]

  val transactions = List(Transaction())

  val account = new Account(transactionRepository, statementPrinter)

  it should "store a deposit transaction" in {
    account.deposit(100)

    (transactionRepository.deposit _).verify(100)
  }

  it should "store a withdrawal transaction" in {
    account.withdraw(100)

    (transactionRepository.withdraw _).verify(100)
  }

  it should "print all transactions" in {
    (transactionRepository.allTransactions _).when().returns(transactions)

    account.printStatement()

    (statementPrinter.printLines _).verify(transactions)
  }
}
