#include <stdio.h>
#include <unistd.h>
#include "mymalloc.h"

int main(){
	printf("Test 1 begin:\n");
	printf("%p\n", sbrk(0));
	
	int *a1 = my_nextfit_malloc(50);
	int *a2 = my_nextfit_malloc(10);
	int *a3 = my_nextfit_malloc(20);
	int *a4 = my_nextfit_malloc(100);
	int *a5 = my_nextfit_malloc(50);
	int *a6 = my_nextfit_malloc(30);

	print_nodes();
	
	printf("Remove node 1\n");
	my_free(a2);
	print_nodes();

	printf("Remove node 2\n");
	my_free(a3);
	print_nodes();

	printf("Remove node 5\n");
	my_free(a6);
	print_nodes();

	printf("Remove remaining nodes\n");
	my_free(a5);
	my_free(a4);
	my_free(a1);

	print_nodes();
	
	printf("%p\n", sbrk(0));
	printf("Test 1 Complete\n");	

	printf("-----------------------------------------\n");

	printf("Test 2 Begin\n");

	int *b1 = my_nextfit_malloc(20);
	int *b2 = my_nextfit_malloc(50);
	int *b3 = my_nextfit_malloc(30);

	print_nodes();
	
	printf("Remove first two nodes\n");
	my_free(b1);
	my_free(b2);

	print_nodes();
	
	printf("Deallocate remaining space\n");
	my_free(b3);
	
	print_nodes();

	printf("%p\n", sbrk(0));
	printf("Test 2 Complete\n");
	printf("----------------------------------------\n");

	printf("Test 3 Begin\n");
	
	int *c1 = my_nextfit_malloc(20);
	int *c2 = my_nextfit_malloc(20);
	int *c3 = my_nextfit_malloc(20);
	int *c4 = my_nextfit_malloc(20);
	
	print_nodes();

	printf("Free nodes 1 and 2\n");
	my_free(c1);
	my_free(c2);

	print_nodes();

	printf("Allocate more memory in empty area\n");
	int *c5 = my_nextfit_malloc(30);

	print_nodes();

	printf("Free all mallocs\n");
	my_free(c3);
	print_nodes();
	my_free(c4);
	print_nodes();
	my_free(c5);
	
	print_nodes();

	printf("%p\n", sbrk(0));
	printf("Test 3 Complete\n");
	printf("-----------------------------------------\n");

	return 0;

}
