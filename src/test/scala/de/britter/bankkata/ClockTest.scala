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

import java.time.LocalDate

import org.scalatest.{ FlatSpec, Matchers }

class ClockTest extends FlatSpec with Matchers {

  behavior of "a Clock"

  val clock: Clock = new TestableClock()

  it should "return today in mm/dd/yyyy format" in {
    clock.todayAsString() shouldBe "05/10/2016"
  }

  class TestableClock extends Clock {
    override protected def today(): LocalDate = LocalDate.of(2016, 10, 5)
  }
}
