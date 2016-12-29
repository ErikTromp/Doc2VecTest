import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConverters.seqAsJavaListConverter

import org.deeplearning4j.models.embeddings.inmemory.InMemoryLookupTable
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer
import org.deeplearning4j.models.paragraphvectors.ParagraphVectors
import org.deeplearning4j.models.word2vec.VocabWord
import org.deeplearning4j.models.embeddings.learning.impl.sequence.DM
import org.deeplearning4j.models.word2vec.wordstore.VocabCache
import org.deeplearning4j.text.documentiterator.LabelledDocument
import org.deeplearning4j.text.documentiterator.SimpleLabelAwareIterator
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory
import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import org.nd4j.linalg.ops.transforms.Transforms

object french {
	def main(args: Array[String]) {
		val br = scala.io.Source.fromFile("large_file.txt")(scala.io.Codec("UTF-8")).bufferedReader
		val docs = collection.mutable.ListBuffer[LabelledDocument]()
		var line = br.readLine
		var offset: Long = 0
		while (line != null) {
			val doc = new LabelledDocument
			doc.setContent(line)
			doc.addLabel("SENT" + offset)
			docs += doc
			
			offset = offset + 1
			line = br.readLine
		}
		br.close
		var tokenizer: TokenizerFactory = new DefaultTokenizerFactory()
		val iterator = new SimpleLabelAwareIterator(docs.asJava)
		val minWordFrequency = 10
		val iterations = 15
		val epochs = 2
		val layerSize = 300
		val learningRate = 0.025
		val windowSize = 5
		val batchSize = 1500
		val trainWordVectors = true
		val sampling = 0.0
		val parVecs = new ParagraphVectors.Builder()
			.minWordFrequency(minWordFrequency)
			.iterations(iterations)
			.epochs(epochs)
			.layerSize(layerSize)
			.learningRate(learningRate)
			.windowSize(windowSize)
			.batchSize(batchSize)
			.iterate(iterator)
			.trainWordVectors(trainWordVectors)
			.sampling(sampling)
			.tokenizerFactory(tokenizer)
			.build()
		parVecs.fit
		WordVectorSerializer.writeParagraphVectors(parVecs, "doc_vectors.bin")
	}
}