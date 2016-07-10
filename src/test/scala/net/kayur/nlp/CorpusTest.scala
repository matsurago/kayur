package net.kayur.nlp

import org.scalatest.FunSuite

/**
  * Created by alex on 7/10/16.
  */
class CorpusTest extends FunSuite {

  val emptyCorpus = new Corpus(List(), Map())

  val document1 = "This is a unit test. We test the value."
  val document2 = "there is a problem in the test. The test is completely dependent."

  val corpus = new Corpus(
    List(
      new Document(
        List(
          Word(0, 1), Word(1, 1), Word(2, 1), Word(3, 1), Word(4, 2), Word(5, 1), Word(6, 1), Word(7, 1)
        ),
        Map(
          'id -> "document #0",
          'category -> "testing"
        )
      ),
      new Document(
        List(
          Word(8, 1), Word(1, 2), Word(2, 1), Word(9, 1), Word(10, 1), Word(6, 2), Word(4, 2), Word(11, 1), Word(12, 1)
        ),
        Map(
          'id -> "document #1",
          'category -> "testing"
        )
      ),
      new Document(
        List(),
        Map(
          'id -> "document #2",
          'category -> "empty"
        )
      )
    ),
    Map(
      0 -> "this", 1 -> "is", 2 -> "a", 3 -> "unit", 4 -> "test", 5 -> "we", 6 -> "the",
      7 -> "value", 8 -> "there", 9 -> "problem", 10 -> "in", 11 -> "completely", 12 -> "dependent"
    )
  )

  test("Export to HDP") {
    val c = corpus
    val exportStr = c.export(CorpusExportFormat.Hdp)

    println(exportStr)

    assert(exportStr.split("\n").length === 3)
    assert(exportStr.contains("6:1"))
    assert(exportStr.contains("6:2"))
    assert(exportStr.contains("0:0"))
    assert(exportStr.contains("0:1"))
    assert(!exportStr.contains("8:2"))
    assert(!exportStr.contains("13:1"))
  }

  test("Export to JSON") {
    val c = corpus
    val str = c.export(CorpusExportFormat.Json)
    println(str)
  }
}
