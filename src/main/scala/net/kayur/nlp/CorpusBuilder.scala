package net.kayur.nlp

/**
  * Created by alex on 7/9/16.
  */
object CorpusBuilder {
  var cacheEnabled = true
  val cache = new scala.collection.mutable.HashMap[String, String]

  val indexToWordMap = new scala.collection.mutable.HashMap[Int, String]


  def build(documents: List[String]): Corpus = {

    countUnique(extractWords())
      .map(pair => )

    new Corpus(
      documents.map(text => new Document()),
      indexToWordMap.toMap
    )
  }

  def extractWords(text: String, tokenize: String => List[String], process: String => String): List[String] = {
    val processFunction = applyCache(process)

    tokenize(text)
      .map(token => processFunction(token))
      .filter(token => token != null)
  }

  def applyCache(f: String => String)(arg: String): String = {
    if (cacheEnabled)
      if (cache.contains(arg))
        cache(arg)
      else {
        val result = f(arg)
        cache.put(arg, result)
        result
      }
    else
      f(arg)
  }

  def countUnique[A](list: List[A]) = list.groupBy(identity).mapValues(_.size)
}
