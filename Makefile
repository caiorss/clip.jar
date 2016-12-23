VERSION :=1.1.1
BUILD   := ./target/scala-2.11/Clip-assembly-$(VERSION).jar
INSTALL := ~/bin/Clip.jar 

all: ubberjar


# Build ubber jar 
ubberjar:
	sbt assembly

test1:
	java -jar ./target/scala-2.11/Clip-assembly-$(VERSION).jar --name clipboard

test2:
	java -jar $(BUILD) --uuid . 

install:
	mkdir -p ~/bin
	cp -v $(BUILD) $(INSTALL)

release:
	echo "Release "$(VERSION)
	cp -v $(BUILD) ./build/Clip.jar 
	cd ./build && git add Clip.jar && git commit -m "Release"$(VERSION)

clean:
	sbt clean 
