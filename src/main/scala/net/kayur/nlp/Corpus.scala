package net.kayur.nlp

/**
  * Created by alex on 7/8/16.
  */
class Word(val id: Int, val frequency: Int) {
  require(frequency >= 0)

  override def toString = s"[Word #$id, frequency: $frequency]"
}

class Document(val words: List[Word], val metadata: Map[Symbol, Any]) {
  def this(words: List[Word]) = this(words, Map())
}

class Corpus(val documents: List[Document], val indexToWordMap : Map[Int, String])


object Corpus {



  def apply(documents: List[(String, Map[Symbol, Any])]) = {
    val indexToWordMap = Map()

    //documents.map(pair => new Document(getWords(pair._1)))

    new Corpus(List(new Document(List(new Word(0, 0)))), Map(0 -> "test"))
  }

  //def getWords(s: String): (String) = ""
}

