/***********************
 *Kevin Krause
 *kmk189@pitt.edu
 *CS 449
 *Project 3 - mymalloc
 **********************/

#include <stdio.h>
#include <unistd.h>

typedef struct node *Node;

/******************************************************************
 *Node structure for the linked list to keep track of allocations
 *
 *next_node - pointer to next node in list (NULL if none exists)
 *prev_node - pointer to prev node in list (NULL if none exists)
 *size - amount of space allocated 
 *used - 0 if space is free, 1 if space is allocated
 *****************************************************************/
struct node{
	struct node *next_node;
	struct node *prev_node;
	int size;
	int used;
};

//beginning and end of linked list, current position for next fit
static Node begin = 0;
static Node end = 0;
static Node current = 0;
static Node begin2 = 0;

/****************************************************************
 *Allocate the requested amount of memory for the user and 
 *	extra space for a node of a linked list to keep track of 
 *	the allocation.
 *Parameters: size -- int value for space requested
 *Returns: void pointer to beginning of the space allocated
 ***************************************************************/
void *my_nextfit_malloc(int size){

	if(begin == 0){ //if this is the first allocation being made

		begin = sbrk(size + sizeof(struct node)); //extend heap for alloc + node
		begin2 = begin;	

		//set the values for the node
		begin->size = size;
		begin->used = 1;
		begin->next_node = NULL;
		begin->prev_node = NULL;

		//adjust values for keeping track of the list
		end=begin;
		current=begin;

		return (void*)(begin + 1); //return brk after the node
		
	}

	Node n = current->next_node; //node to work with for next fit
	
	while(n != NULL){
		
		//if the node finds a perfect fit, place it and don't make a new node
		if(n->used == 0 && n->size == size + sizeof(struct node)){
			
			//set to allocated and set the size(should stay the same)
			n->used = 1;
			n->size = size;
			
			current = n; //set the new current node for next fit
			
			return (void*)(n + 1); //return pointer to the node
		
		}

		//if a large enough spot, but not perfect fit is found
		if(n->used == 0 && n->size > (size + sizeof(struct node))){
			
			current = n;

			//reset current's size
			current->size = current->size - (size + sizeof(struct node));

			//setting the new node
			n = (current + sizeof(struct node)) + current->size;

			//set the node's new info
			n->size = size;
			n->used = 1;
			n->next_node = current->next_node;
			n->prev_node = current;

			//adjust the nodes on either side to point to the new one
			current->next_node->prev_node = n;
			current->next_node = n;

			return (void*)(n + 1); //return the pointer to the node
		
		}

		//if the end of the list is reached:
		if(n->next_node == NULL){
			
			if(n == begin){ //the end is also the beginning
				n = NULL; //break from loop
			}
			
			else{ //otherwise, jump to beginning of the list, continue search
				n = begin;
			}
		
		}
		
		else{
			
			if(n == current){ //if the entire list has been searched
				n = NULL; //break from loop
			}
			
			else{ //otherwise, move on to next node and repeat
				n = n->next_node;
			}
		
		}
		
	}

	n = sbrk(size + sizeof(struct node)); //move brk allocation size + size of node

	//set the node info
	n->size = size;
	n->used = 1;
	n->next_node = NULL;
	n->prev_node = end;

	//adjust the end of the list
	end->next_node = n;
	end = n;

	return (void*)(n + 1); //return pointer to the node

}

/****************************************************************
 *Frees the area requested which has been previously allocated
 *	by my_nextfit_malloc().
 *Parameters: ptr -- void pointer to segment of memory to free
 *Returns: void -- no return supported
 ****************************************************************/
void my_free(void *ptr){
	
	Node n = (Node)ptr-1; //go to that section's node
	n->used = 0; //mark as free

	if(n->prev_node != NULL){ //make sure it's not the first in the list
		
		if(n->prev_node->used == 0){ //if previous node is free, coalesce
			n->prev_node->size += n->size + sizeof(struct node); //set new size of previous node
			n->prev_node->next_node = n->next_node; //set new next node
			
			if(n == end){ //if node being removed is the last one
				end = n->prev_node; //update last node in list
			}
			
			if(n == current){ //if node being removed is current for next fit
				current = n->prev_node; //update current node in list
			}
			
			n = n->prev_node; //update current to coalesced node
		}
		
	}

	if(n->next_node != NULL){ //check for a next node
		
		if(n->next_node->used == 0){ //check if it is free
			
			n->size = n->size + n->next_node->size + sizeof(struct node); //adjust size
			n->next_node = n->next_node->next_node; //change next's pointer to one after that
		
		}
		
	}

	if(n->next_node == NULL){ //check if free space is touching brk, remove it
		
		if(n->prev_node == NULL){ //if it's first node in the list
			begin = 0;
			current = 0;
			end = 0;
			brk((void*)(n)); //adjust brk back down 
		
		}
		
		else if(end == current){ //if it's the current node in list
			current = current->prev_node; //set current to previous
			end = end->prev_node; //set new end node
			end->next_node = NULL; //set end's next node
			brk((void*)(n-1)); //adjust brk back down
		
		}
		
		else{ //when node is not current or beginning node
			end = end->prev_node; //set new end node
			end->next_node = NULL; //set end's next node
			brk((void*)(n-1)); //adjust brk back down
		
		}
		
	}

	//This is my crude work-around for an issue I was having with free	
	n = begin;
	int count = 0;
	while(n != NULL){
		if(n->used == 1){
			count++;
		}
		n = n->next_node;
	}
	if(count == 0){
		begin = 0;
		end = 0;
		current = 0;
		brk((void*)(begin2));
	}

}

/*****************************************************************
 *Prints out the linked list one node at a time - address, 
 *	used (1) or free (0), size, next and previous node addresses.
 *Parameters: no parameters accepted
 *Returns: void - no return supported
 ****************************************************************/
void print_nodes(){
	
	Node n = begin; //set node to the first in the list
	int index = 0;
	
	if(begin == NULL){ //if the linked list does not have any active nodes
		printf("\nNo memory allocated to print out.\n\n");
	}
	
	else{
		printf("Begin: %d\tCurrent: %d\tEnd: %d\t\n");		
		while(n != NULL){ //print until out of nodes
			
			printf("Node #:   %d\t Address:   %d\t "
					"Used: %d\t Size:   %d\t "
					"Prev node:   %d\t Next node:   %d\n", 
					index, n, n->used, n->size, n->prev_node, n->next_node);

			n = n->next_node; //go to the next node
			index++; //increment the counter
		
		}

		printf("---------------------------------------------------\n");
	
	}

}
