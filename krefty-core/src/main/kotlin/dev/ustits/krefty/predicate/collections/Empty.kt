package dev.ustits.krefty.predicate.collections

import dev.ustits.krefty.core.Predicate

class Empty<C : Collection<*>> : Predicate<C> {

    override fun isRefined(value: C): Boolean {
        return value.isEmpty()
    }
}
