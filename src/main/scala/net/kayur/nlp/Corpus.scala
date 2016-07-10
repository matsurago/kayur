package net.kayur.nlp

/**
  * Classes that describe document corpus.
  */

/**
  * Represents a word in a document that belongs to a corpus.
  * @param id         Global word identifier
  * @param frequency  Number of occurrences of the word within the document
  */
class Word(val id: Int, val frequency: Int, val weight: Double) {
  require(frequency >= 0)
  def this(id: Int, frequency: Int) = this(id, frequency, 0)
  override def toString = s"[Word #$id, frequency: $frequency]"
}

object Word {
  def apply(id: Int, frequency: Int) = new Word(id, frequency)
}

/**
  * Represents a document in a corpus.
  * @param words     List of document words
  * @param metadata  List of document properties, such as identifier or category
  */
class Document(val words: List[Word], val metadata: Map[Symbol, Any]) {
  def this(words: List[Word]) = this(words, Map())
  def empty = words.isEmpty
  def length = words.size
}

/**
  * Represents a collection of documents.
  * @param documents       List of documents.
  * @param indexToWordMap  Map that relates word index (Int) to a a word (String)
  */
class Corpus(val documents: List[Document], val indexToWordMap : Map[Int, String]) {
  def export(format: CorpusExportFormat): String = format.export(this)
}


