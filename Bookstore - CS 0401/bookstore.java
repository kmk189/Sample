//Kevin Krause
//CS 0401
//Assignment 1: Book Store

import java.util.Scanner;

public class bookstore {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		//Defining variables to be used.
		int customer;
		int menuchoice = 0;
		int numbooks = 0;
		int numbookmarks = 0;
		int numpaintings = 0;
		int numsingles = 0;
		int numpacks = 0;
		int sale = 1;
		double pricebooks;
		double pricepacks;
		double pricesingles;
		double pricepaintings;
		double subtotal;
		double tax;
		double total = 0;
		double discount;
		double pricepaid;
		double change;
		int finishpay = 0;
		
		//First prompt for customer.
		//Entering initial value to enter the while loop.
		System.out.println("-----------------------------------------------");
		System.out.print("Is there a customer at the register? (1=yes, 2=no) ");
		customer = sc.nextInt();
				
				
		//Loop to deal with multiple customers.		
		while (customer == 1){ 
		
			while (menuchoice != 5){

				//Options and prices for purchase.
				System.out.println("-----------------------------------------------");
				System.out.println("1. Buy Books: $5.00 each");
				System.out.println("2. Buy Bookmarks: $1.00 each or 6 for $5.00");
				System.out.println("3. Buy Paintings: $100.00 each");
				System.out.println("4. See Current Order");
				System.out.println("5. Check Out");
				System.out.println("-----------------------------------------------");
				
				//Choose option from the menu.
				System.out.print("Please choose an option (1-5) from the above menu: ");
				menuchoice = sc.nextInt();
				
				while (menuchoice >= 1 && menuchoice <= 4){
					
					if (menuchoice == 1){
						//Get number of books being bought.
						System.out.println("-----------------------------------------------");
						System.out.print("How many books is the customer buying? (Max: 100) ");
						numbooks = sc.nextInt();
						
						while (numbooks >= 0 && numbooks <= 100 && menuchoice == 1){
							System.out.println("The customer is buying " + numbooks + " books.");
							menuchoice = 0;
						}
						
					}
				
					if (menuchoice == 2){
						//Get number of bookmarks being bought.
						System.out.println("-----------------------------------------------");
						System.out.print("How many bookmarks is the customer buying? (Max: 100) ");
						numbookmarks = sc.nextInt();
							
						while (numbookmarks >= 0 && numbookmarks <= 100 && menuchoice == 2){	
							System.out.println("The customer is buying " + numbookmarks + " bookmarks.");
							menuchoice = 0;
						}	
					
					}

					if (menuchoice == 3){
						//Get number of paintings being bought. 
						System.out.println("-----------------------------------------------");
						System.out.print("How many paintings is the customer buying? (Max: 20) ");
						numpaintings = sc.nextInt();
						
						while (numpaintings >= 0 && numpaintings <= 20 && menuchoice == 3){
							System.out.println("The customer is buying " + numpaintings + " bookmarks.");
							menuchoice = 0;
						}
						
					}	
					
					if (menuchoice == 4){			
						//Show current quantities.
						System.out.println("-----------------------------------------------");
						System.out.println("Currently in cart:");
						System.out.println("Books:     " + numbooks);
						System.out.println("Bookmarks: " + numbookmarks);
						System.out.println("Paintings: " + numpaintings);						
						menuchoice = 0;
					}
				
				}
			
				if (menuchoice == 5){
					//calculations for receipt
					pricebooks = numbooks * 5.00;
					numpacks = numbookmarks / 6;
					pricepacks = numpacks * 5.00;
					numsingles = numbookmarks % 6;
					pricesingles = numsingles * 1.00;
					pricepaintings = numpaintings * 100.00;
					
					//receipt print out for regular priced items.
					if (sale % 3 != 0){
					
						//calculations for regular priced purchase
						subtotal = pricebooks + pricepacks + pricesingles + pricepaintings;
						tax = subtotal * 0.07;
						total = subtotal + tax;
						
						//receipt print out for regular priced purchase.
						//while loops to limit what prints (if 0, don't print a line.)
						while (total > 0){
							System.out.println("-----------------------------------------------");
							System.out.println("");
							break;
						}
						
						while (numbooks > 0){
							System.out.printf("   %d Book(s):              $%.2f\n", numbooks, pricebooks);
							break;
						}
						
						while (numpacks > 0){
							System.out.printf("   %d Bookmark packs:       $%.2f\n", numpacks, pricepacks);
							break;
						}
						
						while (numsingles > 0){
							System.out.printf("   %d Single Bookmark(s):   $%.2f\n", numsingles, pricesingles);
							break;
						}
						
						while (numpaintings > 0){
							System.out.printf("   %d Painting(s):          $%,.2f\n", numpaintings, pricepaintings);
							break;
						}
						
						while (total > 0){
							System.out.println("");
							System.out.printf("Subtotal:                  $%,.2f\n", subtotal);
							System.out.printf("Tax:                       $%.2f\n", tax);
							System.out.println("");
							System.out.printf("Total:                     $%,.2f\n", total);
							System.out.println("");
							System.out.println("-----------------------------------------------");						
							menuchoice = 5;
							sale ++;
							break;
						}
						
					}
					
					//receipt print out for discount items
					else if (sale % 3 == 0){
						
						//discounted price calculations.
						subtotal = (pricebooks + pricepacks + pricesingles + pricepaintings) * 0.90;
						discount = (pricebooks + pricepacks + pricesingles + pricepaintings) * 0.10;
						tax = subtotal * 0.07;
						total = subtotal + tax;
						
						//receipt print out for discounted purchase.
						//while loops to limit what prints (not printing a line if 0 bought)
						while (total > 0){
							System.out.println("-----------------------------------------------");
							System.out.println("");
							System.out.println("You won a 10% discount!");
							System.out.println("");
							break;
						}
						
						while (numbooks > 0){
							System.out.printf("   %d Book(s):              $%.2f\n", numbooks, pricebooks);
							break;
						}
						
						while (numpacks > 0){
							System.out.printf("   %d Bookmark packs:       $%.2f\n", numpacks, pricepacks);
							break;
						}
						
						while (numsingles > 0){
							System.out.printf("   %d Single Bookmark(s):   $%.2f\n", numsingles, pricesingles);
							break;
						}
						
						while(numpaintings >0){
							System.out.printf("   %d Painting(s):          $%,.2f\n", numpaintings, pricepaintings);
							break;
						}
						
						while (total > 0){
							System.out.println("");
							System.out.printf("Discount:                 -$%,.2f\n", discount);
							System.out.println("");
							System.out.printf("Subtotal:                  $%,.2f\n", subtotal);
							System.out.printf("Tax:                       $%.2f\n", tax);
							System.out.println("");
							System.out.printf("Total:                     $%,.2f\n", total);
							System.out.println("");
							System.out.println("-----------------------------------------------");						
							menuchoice = 5;
							sale++;
							break;
						}
						
					}
				
				
					while (finishpay == 0 && total > 0){
						//payment of sale
						System.out.println("");
						System.out.print("Enter amount paid (no dollar sign): ");
						pricepaid = sc.nextDouble();
					
						//change returned
						if (pricepaid > total && finishpay != 1){
							change = pricepaid - total;
							System.out.println("");
							System.out.printf("Your change is: $%.2f\n", change);
							System.out.println("Thank you for shopping! Please come again!");
							System.out.println("");
							finishpay = 1;
						}
						
						//no change necessary
						if (pricepaid == total && finishpay != 1){
							System.out.println("");
							System.out.println("Thank you for shopping! Please come again!");
							System.out.println("");
							finishpay = 1;
						}
					
					}
					
				}
				
			}
			
			
			//Final customer check to continue or end the loop.			
			System.out.println("-----------------------------------------------");
			System.out.print("Is there another customer at the register? (1=yes, 2=no) ");
			customer = sc.nextInt();
			menuchoice = 0;
			finishpay = 0;
			numbooks = 0;
			numpaintings = 0;
			numbookmarks = 0;
		
		}
		
	}

}
