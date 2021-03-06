![Krefty](docs/krefty.png)

---

[![CI](https://github.com/ustits/krefty/actions/workflows/build.yml/badge.svg)](https://github.com/ustits/krefty/actions/workflows/build.yml)
[![Licence](https://img.shields.io/github/license/ustits/krefty)](https://github.com/ustits/krefty/blob/main/LICENSE)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.ustits.krefty/krefty-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.ustits.krefty/krefty-core)

Krefty is a tool for constructing concrete (refined) types specific for your domain. It uses refinement type theory,
i.e. types wrapping a predicate and a value which satisfies it. 
For DDD users refined types can be viewed as an alternative to value objects or [whole objects](http://c2.com/ppr/checks.html#1). 
Inspired by implementations in [haskell](https://github.com/nikita-volkov/refined)
and [scala](https://github.com/fthomas/refined).

Also check out [values4k](https://github.com/fork-handles/forkhandles/tree/trunk/values4k) which solves the same problem. 

## Getting started

```
implementation("dev.ustits.krefty:krefty-core:<latest_version>")
```

## Usage

To refine a type use `refined` function with a predicate:

```kotlin
val name = "Krefty" refined NotBlank()
```

Function will ensure that the value `"Krefty"` satisfies the predicate `NotBlank`. If not it will cause an error. 

Call `unrefined` to get the value back:

```kotlin
name.unrefined // "Krefty"
```

A newly created object can be used to construct new types, for example, 
by passing it in the constructor:

```kotlin
class NotBlankString private constructor(private val value: String) {

    constructor(refined: Refined<NotBlank, String>) : this(refined.unrefined)

}

val notBlank = NotBlankString(refined)
```

Construct new predicates using delegation:

```kotlin
class UserID : Predicate<Int> by Positive()
val userID = 443812 refined UserID()
```

Combine predicates using `and`, `or` functions:

```kotlin
class Percent : Predicate<Int> by GreaterOrEqual(0) and LessOrEqual(100)
val percent = 45 refined Percent()
```

Or by using `All` and `Some` classes:

```kotlin
class Percent : Predicate<Int> by All(GreaterOrEqual(0), LessOrEqual(100))
val percent = 45 refined Percent()
```

Invert predicates with `!` function or `Not` class:

```kotlin
class NotPercent : Predicate<Int> by !Percent()
// or
class NotPercent : Predicate<Int> by Not(Percent())
```
