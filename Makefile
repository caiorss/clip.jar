all: class

class:
	scalac Clip.scala 

clean:
	rm -rf *.class *.jar 
