package dev.ustits.krefty.predicate.ints

import dev.ustits.krefty.core.Predicate

class Positive : Predicate<Int> by Greater(0)
