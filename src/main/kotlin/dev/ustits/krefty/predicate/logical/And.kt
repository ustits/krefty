package dev.ustits.krefty.predicate.logical

import dev.ustits.krefty.core.Predicate

class And<P1 : Predicate<T>, P2 : Predicate<T>, T>(private val first: P1, private val second: P2) : Predicate<T> {
    override fun isRefined(value: T): Boolean {
        return first.isRefined(value).and(second.isRefined(value))
    }
}
