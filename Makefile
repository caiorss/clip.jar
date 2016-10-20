all: ubberjar 

# Build ubber jar 
ubberjar:
	sbt assembly

test:
	sbt run --uiid: /tmp

clean:
	sbt clean 
