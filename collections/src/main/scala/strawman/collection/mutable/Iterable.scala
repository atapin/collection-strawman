package strawman.collection.mutable

import strawman.collection
import strawman.collection.{IterableFactory, IterableOnce}

import scala.{Boolean, deprecated, `inline`}

trait Iterable[A]
  extends collection.Iterable[A]
    with IterableOps[A, Iterable, Iterable[A]] {

  override def iterableFactory: IterableFactory[IterableCC] = Iterable
}

/**
  * @define coll mutable collection
  * @define Coll `mutable.Iterable`
  */
trait IterableOps[A, +CC[X], +C]
  extends collection.IterableOps[A, CC, C] {

  def mapInPlace(f: A => A): this.type

  @deprecated("Use `mapInPlace` instead", "2.13.0")
  @`inline`final def transform(f: A => A): this.type = mapInPlace(f)
}

/**
  * $factoryInfo
  * @define coll mutable collection
  * @define Coll `mutable.Iterable`
  */
object Iterable
  extends IterableFactory.Delegate[Iterable](ArrayBuffer)

/** Explicit instantiation of the `Iterable` trait to reduce class file size in subclasses. */
abstract class AbstractIterable[A] extends strawman.collection.AbstractIterable[A] with Iterable[A]
