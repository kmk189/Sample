
	#!/bin/bash
	javac -sourcepath ../../Components/TrendsAnalyzer -cp "../../Components/*" ../../Components/TrendsAnalyzer/*.java
	cd ../../Components/TrendsAnalyzer;
	java -cp ".:../*" CreateTrendsAnalyzer;