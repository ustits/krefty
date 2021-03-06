package dev.ustits.krefty.core

internal class EagerRefined<P : Predicate<T>, T> private constructor(private val value: T) : Refined<P, T> {

    constructor(
        predicate: P,
        value: T
    ) : this(
        require(predicate.isRefined(value)) { "Value $value doesn't match the predicate" }
            .let { value }
    )

    override val unrefined: T
        get() = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as EagerRefined<*, *>

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value?.hashCode() ?: 0
    }

}
