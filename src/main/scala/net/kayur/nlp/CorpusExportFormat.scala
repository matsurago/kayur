package net.kayur.nlp

import com.persist.JsonOps._

/**
  * Classes and methods to export a Corpus structure.
  */
class CorpusExportFormat(val name: String, val ext: String, exportFunction: Corpus => String) {
  def export = exportFunction
  override def toString = name
}

object CorpusExportFormat {
  val Arff      = new CorpusExportFormat("Weka ARFF", "arff", toARFF)
  val Hdp       = new CorpusExportFormat("LDA/HDP input file", "txt", toHDP)
  val CarrotXml = new CorpusExportFormat("Carrot XML", "xml", toCarrotXML)
  val Json      = new CorpusExportFormat("JSON", "json", toJson)
  val formats = List(Arff, CarrotXml, Hdp, Json)

  /*
   *
   * built-in export functions
   *
   */

  def toARFF(corpus: Corpus): String = {
    ""
  }

  def toHDP(corpus: Corpus): String = {
    val rows =
      for (document <- corpus.documents) yield
        if (document.empty)
          "1 0:0" // we treat empty documents as containing only one word with zero frequency
        else
          document.length.toString +
            document.words.foldLeft("")(
              (acc, word) => s"$acc ${word.id}:${word.frequency.toInt}" // HDP supports only Int weights
            )
    rows.mkString("\n")
  }

  def toCarrotXML(corpus: Corpus): String = {
    ""
  }

  def toJson(corpus: Corpus): String = {
    Pretty(JsonObject(
      "corpus" -> corpus.documents.map(d => JsonObject(
        "words" -> d.words.map(word => JsonObject(
          "id" -> word.id.toString,
          "frequency" -> word.frequency.toString,
          "weight" -> word.weight.toString)
        )
      )),
      "index"  -> corpus.indexToWordMap.map(pair => (pair._1.toString, pair._2))
    ))
  }
}

