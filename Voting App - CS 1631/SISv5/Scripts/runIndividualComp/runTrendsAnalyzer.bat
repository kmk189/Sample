@echo off
title TrendsAnalyzer

javac -sourcepath ../../Components/TrendsAnalyzer -cp ../../Components/* ../../Components/TrendsAnalyzer/*.java
start "TrendsAnalyzer" /D"../../Components/TrendsAnalyzer" java -cp .;../* CreateTrendsAnalyzer