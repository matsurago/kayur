package net.kayur.nlp

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
  val formats = List(Arff, CarrotXml, Hdp)

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
          "1 0:0"
        else
          document.length.toString +
            document.words.foldLeft("")(
              (acc, word) => s"$acc ${word.id}:${word.frequency.toInt}"
            )
    rows.mkString("\n")
  }

  def toCarrotXML(corpus: Corpus): String = {
    ""
  }
}



