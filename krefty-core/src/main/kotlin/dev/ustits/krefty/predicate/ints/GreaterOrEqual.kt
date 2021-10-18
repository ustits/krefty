package dev.ustits.krefty.predicate.ints

import dev.ustits.krefty.core.Predicate
import dev.ustits.krefty.dsl.or
import dev.ustits.krefty.predicate.Equal

class GreaterOrEqual(value: Int) : Predicate<Int> by Greater(value) or Equal(value)
