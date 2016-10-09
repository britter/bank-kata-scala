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
import org.scalatest.{ BeforeAndAfterEach, FlatSpec, Matchers }

class TransactionRepositoryTest
    extends FlatSpec
    with BeforeAndAfterEach
    with Matchers
    with MockFactory {

  behavior of "a TransactionRepository"

  val clock = stub[Clock]

  var transactionRepository: TransactionRepository = _

  override protected def beforeEach() = {
    transactionRepository = new TransactionRepository(clock)
  }

  it should "store a deposit transaction" in {
    (clock.todayAsString _).when().returns("09/10/2016")

    transactionRepository.deposit(100)

    val transactions = transactionRepository.allTransactions()

    transactions should have size 1
    transactions should contain(Transaction("09/10/2016", 100))
  }

  it should "store a withdrawal transaction" in {
    (clock.todayAsString _).when().returns("09/10/2016")

    transactionRepository.withdraw(100)

    val transactions = transactionRepository.allTransactions()

    transactions should have size 1
    transactions should contain(Transaction("09/10/2016", -100))
  }

}
