
	#!/bin/bash
	javac -sourcepath ../../Components/VotingApp -cp "../../Components/*" ../../Components/VotingApp/*.java
	cd ../../Components/VotingApp;
	java -cp ".:../*" CreateVotingApp;