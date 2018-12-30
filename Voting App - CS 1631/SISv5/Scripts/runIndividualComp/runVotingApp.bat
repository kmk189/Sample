@echo off
title VotingApp

javac -sourcepath ../../Components/VotingApp -cp ../../Components/* ../../Components/VotingApp/*.java
start "VotingApp" /D"../../Components/VotingApp" java -cp .;../* CreateVotingApp