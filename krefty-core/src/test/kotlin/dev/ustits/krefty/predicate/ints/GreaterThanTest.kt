package dev.ustits.krefty.predicate.ints

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.forAll

class GreaterThanTest : StringSpec({

    "returns false if less than number" {
        val value = 10
        forAll(Arb.int(Int.MIN_VALUE until value)) {
            val predicate = GreaterThan(value)
            !predicate.isRefined(it)
        }
    }

    "returns true if greater than number" {
        val value = 10
        forAll(Arb.int((value + 1)..Int.MAX_VALUE)) {
            val predicate = GreaterThan(value)
            predicate.isRefined(it)
        }
    }

    "returns false if equal to number" {
        val predicate = GreaterThan(10)
        predicate.isRefined(10) shouldBe false
    }

})
