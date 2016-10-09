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

class StatementPrinterTest extends FlatSpec with MockFactory {

  behavior of "a StatementPrinter"

  val noTransactions = List[Transaction]()

  val console = stub[Console]

  val statementPrinter = new StatementPrinter(console)

  it should "always print a header row" in {
    statementPrinter.printLines(noTransactions)

    (console.print _).verify("DATE | AMOUNT | BALANCE")
  }

  it should "print statements in reverse chronological order" in {
    statementPrinter.printLines(
      List(
        Transaction("01/04/2014", 1000),
        Transaction("02/04/2014", -100),
        Transaction("10/04/2014", 500)
      ))

    inSequence {
      (console.print _).verify("DATE | AMOUNT | BALANCE")
      (console.print _).verify("10/04/2014 | 500.00 | 1400.00")
      (console.print _).verify("02/04/2014 | -100.00 | 900.00")
      (console.print _).verify("01/04/2014 | 1000.00 | 1000.00")
    }
  }
}
