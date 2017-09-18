//this program will run the pi stuff and return the area specified
//I think pi.h just accepts a char array and fills it with all of pi
//up to the number specified
//then I'll need to go through it with a for loop ignoring everything
//up to the point that I need to start printing and then print to that
//point and terminate. 
//formatting is to just print those digits on one line and then 
//a carriage return after all the digits have been printed. 
//the end

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]){

	int start = 0; //start position 
	int end = 0; //end position
	int length = 0; //length of the requested output
	FILE *data; //file to deal with

	//checks for the correct number of command line arguments
	if((argc <= 2) || (argc > 3)){
		printf("ERROR: Wrong number of command line arguments.\n");
		return 1;
	}
	
	start = atoi(argv[1]); //integerizes the command line arguments
	end = atoi(argv[2]);

	//if the numbers given aren't valid options for any reason
	//just grouped them all together because I'm lazy
	if((start < 0) || (end < 0) || (end < start)){
		printf("ERROR: Neither start nor end position may be negative\n");
		printf("       End value must be greater than start value\n");
	}

	length = end - start + 1; //set the length of the output
	char value[length + 1]; //set the character array to hold them

	data = fopen("/dev/pi", "r"); //open the device

	//failsafe if the device doesn't work right
	if(data == NULL){
		printf("/dev/pi not found\n");
		return 1;
	}

	fseek(data, start, SEEK_SET); //seek to starting position
	fread(value, 1, length, data); //read data using the pi_read in pi_driver
	fclose(data); //close file

	value[length] = 0; //set the null terminating value
	printf("%s\n", value); //print the value
	
	return 0; //return for success :)
}
