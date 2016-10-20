BUILD   := ./target/scala-2.11/Clip-assembly-1.0.jar
INSTALL := ~/bin/Clip.jar 

all: ubberjar

# Build ubber jar 
ubberjar:
	sbt assembly

test1:
	java -jar ./target/scala-2.11/Clip-assembly-1.0.jar -file ./clipboard.png 

test2:
	java -jar $(BUILD) -uuid . 

install:
	mkdir -p ~/bin
	cp $(BUILD) $(INSTALL)

clean:
	sbt clean 
