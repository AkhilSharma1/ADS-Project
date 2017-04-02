JC = javac
JFLAGS = -g
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
		Node.java \
		PQ_ENUM.java \
		PriorityQueue.java \
		BinaryHeap.java \
		FourWayHeap.java \
		PairingHeap.java \
		CoreUtils.java \
		FileUtils.java \
		HuffmanTree.java \
		DecoderTree.java \
		Compare.java \
		Encoder.java \
		Decoder.java
default: classes encoder.jar decoder.jar clean
classes: $(CLASSES:.java=.class)
encoder.jar: *.class
	jar -cfe $@ Encoder $^
decoder.jar: *.class
	jar -cfe $@ Decoder $^
clean:
	$(RM) *.class