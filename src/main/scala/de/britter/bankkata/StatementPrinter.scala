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

import java.text.{ DecimalFormat, DecimalFormatSymbols }
import java.util.Locale
import java.util.concurrent.atomic.AtomicInteger

class StatementPrinter(console: Console) {

  final val Header  = "DATE | AMOUNT | BALANCE"
  val decimalFormat = new DecimalFormat("#.00")
  decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.UK))

  def printLines(transactions: List[Transaction]): Unit = {
    console.print(Header)

    printTransactions(transactions)
  }

  private def printTransactions(transactions: List[Transaction]) = {
    val balance = new AtomicInteger(0)

    transactions
      .map(t => toStatement(t, balance))
      .reverse
      .foreach(console.print)
  }

  private def toStatement(transaction: Transaction, balance: AtomicInteger) = {
    val formattedAmount = decimalFormat.format(transaction.amount)
    val formattedBalance =
      decimalFormat.format(balance.addAndGet(transaction.amount))

    s"${transaction.date} | $formattedAmount | ${formattedBalance}"
  }
}
