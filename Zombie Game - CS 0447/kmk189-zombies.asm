# Kevin Krause - 3522934
# kmk189@pitt.edu

.data
maze:	.ascii
	# 0123456701234567012345670123456701234567012345670123456701234567
	 "  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",    # 0
	 "       xx      xx      xx      xx      xx      xx      xx      x",    # 1
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 2
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 3
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 4
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 5
	 "x                                                              x",    # 6
	 "x xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxx",    # 7
	 "x xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxx",    # 8
	 "x      xx      xx      xx      xx      xx      xx      xx      x",    # 9
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 10
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 11
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 12
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 13
	 "x                                                              x",    # 14
	 "x xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxx",    # 15
	 "x xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxx",    # 16
	 "x      xx      xx      xx      xx      xx      xx      xx      x",    # 17
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 18
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 19
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 20
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 21
	 "x                                                              x",    # 22
	 "x xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxx",    # 23
	 "x xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxx",    # 24
	 "x      xx      xx      xx      xx      xx      xx      xx      x",    # 25
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 26
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 27
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 28
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 29
	 "x                                                              x",    # 30
	 "x xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxx",    # 31
	 "x xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxx",    # 32
	 "x      xx      xx      xx      xx      xx      xx      xx      x",    # 33
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 34
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 35
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 36
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 37
	 "x                                                              x",    # 38
	 "x xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxx",    # 39
	 "x xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxx",    # 40
	 "x      xx      xx      xx      xx      xx      xx      xx      x",    # 41
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 42
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 43
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 44
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 45
	 "x                                                              x",    # 46
	 "x xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxx",    # 47
	 "x xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxx",    # 48
	 "x      xx      xx      xx      xx      xx      xx      xx      x",    # 49
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 50
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 51
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 52
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 53
	 "x                                                              x",    # 54
	 "x xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxx",    # 55
	 "x xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxx",    # 56
	 "x      xx      xx      xx      xx      xx      xx      xx      x",    # 57
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 58
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 59
	 "x x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x xx x  x x",    # 60
	 "x xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx xx xxxx x",    # 61
	 "x                                                               ",    # 62
	 "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  "     # 63
	# for each "x", turn the corresponding LED to orange.  The other LEDs should
	# be set to off.

zombies: .byte 30, 30, 3, 62, 30, 3, 30, 62, 3, 62, 62, 3
direction: .byte 0, 1, 2, 3
lose_notice: .asciiz "You lose :("
win_notice: .asciiz "You win!!\n"
points: .asciiz "Your score was "

.text
main:
	jal	generate_maze #call the procedure to create the maze
	jal 	generate_player #call procedure to create the initial player
	jal 	generate_zombies #call procedure to create the initial zombies

begin_poll: 
	la	$v0, 0xffff0000
	lw	$t0,0($v0)		# read the key press status
	andi	$t0,$t0,1		# zero extend 
	beq	$t0,$0,begin_poll	# no key pressed
	lw	$t0,4($v0)		# read key value
begin_b:
	addi	$v0, $t0, -66		#check if keypress is b
	bne	$v0, $zero, begin_poll	#if not b, keep checking for keypress
	
	jal	zombiemove

poll:	
	li	$v0, 30			#get the time
	syscall
	sub	$t1, $a0, $s3		#check if 500ms or more have passed
	bge	$t1, 500, zombiemove	#if it has, move the zombies
	
	la	$v0,0xffff0000		# address for reading key press status
	lw	$t0,0($v0)		# read the key press status
	andi	$t0,$t0,1		# zero extend
	beq	$t0,$0,poll		# no key pressed
	lw	$t0,4($v0)		# read key value
upkey:
	addi	$v0, $t0, -224		#check for up key press
	bne	$v0, $zero, downkey	#wasn't up key, try down key
	jal 	move_up			#was up key, move up if possible
	j	poll			#check for another key press
downkey:
	addi	$v0, $t0, -225		#check for down key press
	bne	$v0, $zero, leftkey	#wasn't down key, check left key
	jal 	move_down		#was down key, move down if possible
	j	poll			#check for another key press
leftkey:
	addi	$v0,$t0,-226		# check for left key press
	bne	$v0,$0,rightkey		# wasn't left key, so try right key
	jal	move_left		#was left key, move left if possible
	j	poll			#check for another key press
rightkey:
	addi	$v0,$t0,-227		# check for right key press
	bne	$v0,$0,bkey		# wasn't right key, so check for center
	jal	move_right		# was right key, move right if possible
	j	poll			#check for another key press
bkey:
	j	poll			#not a valid key during gameplay, check for another key press
	


	li 	$v0, 10	 		#terminate the program
	syscall


#Generates the maze through a series of loops
generate_maze: 
	sw 	$ra, 4($sp) 		#save the $ra value to jump back to main 
	la 	$a3, maze 		#load the address of the maze ascii
	move 	$a0, $zero 		#x-coordinate
	move 	$a1, $zero 		#y-coordinate
	addi 	$a2, $a2, 2 		#color for LED

maze_main:
	lbu 	$t0, 0($a3) 		#load byte of the maze ascii to read
	beq 	$t0, $zero, maze_end 	#checks for the null ending
	beq 	$a0, 0x40, row_reset 	#if it has reached the end of the row, it resets to the next row
	subi 	$t0, $t0, 0x78 		#check if the byte is to be lit up or not
	bne 	$t0, $zero, row_increment #if not to be lit up, move on to next LED space
	jal 	_setLED 		#if lit up, set the LED to orange/yellow

row_increment:
	addi 	$a0, $a0, 1 		#increments the x-coordinate to the next space
	addi 	$a3, $a3, 1 		#increments the byte address to the next byte
	j 	maze_main 		#jumps back to the main part of the maze builder

row_reset:
	beq 	$a1, 0x3f, maze_end 	#this is not used since it checks for null, but left it anyway
	move	$a0, $zero 		#resets x-coordinate to zero
	addi 	$a1, $a1, 1 		#increments the y-coordinate by one
	j 	maze_main 		#jumps back to main part of maze builder

maze_end:
	lw 	$ra, 4($sp) 		#loads $ra for the original jal out of main
	jr 	$ra 			#jumps back to main to continue program

#Generates the player at (0,0)
generate_player:
	sw 	$ra, 4($sp)		#save the return address
	move 	$a0, $zero		#set x-coordinate to 0
	move 	$a1, $zero		#set y-coordinate to 0
	move 	$s0, $zero		#set the permanent x to 0
	move	$s1, $zero		#set the permanent y to 0
	addi 	$a2, $a2, 1		#set color to green
	jal 	_setLED			#turn on the LED
	lw 	$ra, 4($sp)		#load the return address
	jr 	$ra			#return to main

#Generates zombies at their positions
generate_zombies:
	sw 	$ra, 4($sp)		#save the return address
	subi 	$a2, $a2, 2		#set the color to red

	addi 	$a0, $a0, 30		#set first zombie x-coord
	addi 	$a1, $a1, 30		#set first zombie y-coord
	jal 	_setLED			#turn on LED at (30,30)

	addi 	$a0, $a0, 32		#adjust the x-coordinate
	jal 	_setLED			#turn on LED at (62,30)

	addi 	$a1, $a1, 32		#adjust y-coordinate
	subi 	$a0, $a0, 32		#adjust x-coordinate
	jal 	_setLED			#turn on LED at (30,62)

	addi 	$a0, $a0, 32		#adjust x-coordinate
	jal 	_setLED			#turn on LED at (62,62)

	lw 	$ra, 4($sp)		#load return register
	jr	$ra			#return to main
	
	
move_up:
	sw	$ra, 8($sp) 		#saves the return register
	move	$a0, $s0 		#sets x-coordinate
	move	$a1, $s1 		#sets y-coordinate
	
	beq	$a1, 0, move_fail	#checks for topmost position
	addi	$a1, $a1, -1 		#increments the y-coordinate to go up one space
	jal	_getLED 		#checks color of next spot
	beq	$v0, 2, move_fail	#if wall, return to poll
	beq	$v0, 1, lose 		#if zombie, lose
	
	#else{make current spot color 0, spot at x,y-1 green}
	addi	$a1, $a1, 1 		#gets y-coordinate back to current space
	move	$a2, $zero 		#change color code to 0 for not lit
	jal	_setLED 		#clear the spot player is currently at
	
	addi	$a1, $a1, -1 		#sets y-coordinate to future space
	addi	$a2, $a2, 3 		#change color code to 3 for green
	jal	_setLED 		#set the LED to the new spot
	
	addi	$s1, $s1, -1 		#increment the player's position register
	addi	$s2, $s2, 1		#increment the player's score
	
	lw	$ra, 8($sp) 		#load the return address
	jr	$ra 			#return back to the original location in the polling loop
	
move_down:
	sw	$ra, 8($sp) 		#saves the return register
	move	$a0, $s0 		#sets x-coordinate
	move	$a1, $s1 		#sets y-coordinate
	
	beq	$a1, 63, move_fail	#checks for bottommost position
	addi	$a1, $a1, 1 		#increments the y-coordinate to go up one space
	jal	_getLED 		#checks color of next spot
	beq	$v0, 2, move_fail	#if wall, return to poll
	beq	$v0, 1, lose 		#if zombie, lose
	
	#else{make current spot color 0, spot at x,y+1 green}
	addi	$a1, $a1, -1 		#gets y-coordinate back to current space
	move	$a2, $zero 		#change color code to 0 for not lit
	jal	_setLED 		#clear the spot player is currently at
	
	addi	$a1, $a1, 1 		#sets y-coordinate to future space
	addi	$a2, $a2, 3 		#change color code to 3 for green
	jal	_setLED 		#set the LED to the new spot
	
	addi	$s1, $s1, 1 		#increment the player's position register
	addi	$s2, $s2, 1		#increment the player's score
	
	sgt	$t1, $s0, 62		#check if at the farthest right possible
	sgt	$t2, $s1, 62		#check if at the farthest down possible
	add	$t1, $t1, $t2		#add the two results together
	beq	$t1, 2, win		#if the value is 2, then (63,63) has been reached	
	
	lw	$ra, 8($sp) 		#load the return address
	jr	$ra			#return back to the original location in the polling loop
	
move_left:
	sw	$ra, 8($sp) 		#saves the return register
	move	$a0, $s0 		#sets x-coordinate
	move	$a1, $s1 		#sets y-coordinate
	
	beq	$a0, $zero, move_fail	#checks for leftmost position
	addi	$a0, $a0, -1 		#increments it to the next space to check color
	jal	_getLED 		#checks color of next spot
	beq	$v0, 2, move_fail	#if wall, return to poll
	beq	$v0, 1, lose 		#if zombie, lose
	
	#else{make current spot color 0, spot at x-1,y green}
	addi	$a0, $a0, 1 		#gets x-coordinate back to current space
	move	$a2, $zero 		#change color code to 0 for not lit
	jal	_setLED 		#clear the spot player is currently at
	
	addi	$a0, $a0, -1 		#sets x-coordinate to future space
	addi	$a2, $a2, 3 		#change color code to 3 for green
	jal	_setLED 		#set the LED to the new spot
	
	addi	$s0, $s0, -1 		#increment the player's position register
	addi	$s2, $s2, 1		#increment the player's score
	
	lw	$ra, 8($sp) 		#load the return address
	jr	$ra 			#return back to the original location in the polling loop
				
move_right:
	sw	$ra, 8($sp) 		#saves the return register
	move	$a0, $s0 		#sets x-coordinate
	move	$a1, $s1 		#sets y-coordinate
	
	beq	$a0, 63, move_fail	#checks for rightmost position
	addi	$a0, $a0, 1 		#increments the x-coordinate to go up one space
	jal	_getLED 		#checks color of next spot
	beq	$v0, 2, move_fail	#if wall, return to poll
	beq	$v0, 1, lose 		#if zombie, lose
	
	#else{make current spot color 0, spot at x+1,y green}
	addi	$a0, $a0, -1 		#gets x-coordinate back to current space
	move	$a2, $zero 		#change color code to 0 for not lit
	jal	_setLED 		#clear the spot player is currently at
	
	addi	$a0, $a0, 1 		#sets x-coordinate to future space
	addi	$a2, $a2, 3 		#change color code to 3 for green
	jal	_setLED 		#set the LED to the new spot
	
	addi	$s0, $s0, 1 		#increment the player's position register
	addi	$s2, $s2, 1		#increment the player's score
	
	sgt	$t1, $s0, 62		#check if at the farthest right possible
	sgt	$t2, $s1, 62		#check if at the farthest down possible
	add	$t1, $t1, $t2		#add the two results together
	beq	$t1, 2, win		#if the value is 2, then (63,63) has been reached	
	
	lw	$ra, 8($sp) 		#load the return address
	jr	$ra 			#return back to the original location in the polling loop

move_fail:
	lw	$ra, 8($sp)		#loads the return address if player move fails
	jr	$ra			#return to poll after move fails
	
zombiemove:
	sw	$ra, 8($sp)		#save the return location
	
	jal	check_UD		#gets the quadrant of player	
				
	la	$a0, zombies		#load the zombie array
	jal	previous		#permutation of direction array
	la	$a0, zombies		#load the zombie array fresh
	beq	$s5, 0, curr_quad	#if player in quadrant 0, rearrange directions
return0:	
	jal	Q0_move			#move the zombie in quadrant 0
	
	la	$a0, zombies		#load the zombie array fresh
	addi	$a0, $a0, 3		#move 3 bytes ahead to deal with Q1
	jal	previous		#permutation of direction array
	la	$a0, zombies		#load the zombie array fresh
	addi	$a0, $a0, 3		#move 3 bytes ahead to deal with Q1
	beq	$s5, 1, curr_quad	#if player in quadrant 1, rearrange directions
return1:
	jal	Q1_move			#move zombie in quadrant 1
	
	la	$a0, zombies		#load zombie array fresh
	addi	$a0, $a0, 6		#move 6 bytes ahead to deal with Q2
	jal	previous		#permutation of direction array
	la	$a0, zombies		#load the zombie array fresh
	addi	$a0, $a0, 6		#move 6 bytes ahead to deal with Q2
	beq	$s5, 2, curr_quad	#if player in quadrant 2, rearrange directions
return2:
	jal	Q2_move			#move zombie in quadrant 2
	
	la	$a0, zombies		#load zombie array fresh
	addi	$a0, $a0, 9		#move 9 bytes ahead to deal with Q3
	jal	previous		#permutation of direction array
	la	$a0, zombies		#load zombie array fresh
	addi	$a0, $a0, 9		#move 9 bytes ahead to deal with Q3
	beq	$s5, 3, curr_quad	#if player in quadrant 3, rearrange directions
return3:
	jal	Q3_move			#move zombie in quadrant 3
	
	li	$v0, 30			#reset the timer
	syscall
	move 	$s3, $a0		#move time to it's safe zone
	
	lw	$ra, 8($sp)		#load the return address back to main
	jr	$ra			#jump back to main

check_UD:
	sw	$ra, 0($sp)		#save the return address
	bge	$a1, 32, check_LR	#if bottom half of maze, move to check left or right
	bge	$a0, 32, set_quad1	#if right quadrant of top, move to set_quad1
	addi	$s5, $zero, 0		#if top half and left side, set to quadrant 0
	
	lw	$ra, 0($sp)		#load the return address
	jr	$ra			#return to move zombies
	
check_LR:
	bge	$a0, 32, set_quad3	#if the right quadrant of bottom, move to set_quad3
	addi	$s5, $zero, 2		#if the bottom and left side, set quadrant to 2
	
	lw	$ra, 0($sp)		#load the return address
	jr	$ra			#return to move zombies
	
set_quad1:
	addi	$s5, $zero, 1		#if top half and right side, set quadrant to 1
	
	lw	$ra, 0($sp)		#load the return address
	jr	$ra			#return to move the zombies
	
set_quad3:
	addi	$s5, $zero, 3		#if bottom half and right side, set quadrant to 3
	
	lw	$ra, 0($sp)		#load the return address
	jr	$ra			#return to move the zombies
		
			
previous: #this is for making the previous direction lowest priority
	sw	$ra, 12($sp)		#save the return address
	
	lbu	$t0, 2($a0)		#load the previous zombie direction
	la	$a1, direction		#load the direction array

	lbu	$t1, 0($a1)		#get the first byte in the array
	move	$a2, $a1		#save the address for swap
	sub	$t2, $t0, $t1		#check if the direction is the previous direction
	beq	$t2, $zero, swap	#if this is the previous direction, swap to [3]
	
	lbu	$t1, 1($a1)		#move to second byte of array
	addi	$a2, $a2, 1		#change address for swap
	sub	$t2, $t0, $t1		#check if direction is previous direction
	beq	$t2, $zero, swap	#if this is the previous direction, swap to [3]
	
	lbu	$t1, 2($a1)		#move to third byte of array
	addi	$a2, $a2, 1		#change address for swap
	sub	$t2, $t0, $t1		#check if direction is previous direction
	beq	$t2, $zero, swap	#if this is the previous direction, swap to [3]
	
	lbu	$t1, 3($a1)		#move to fourth byte in array (last)
	sub	$t2, $t0, $t1		#check if this is direction (must be if reached)
	beq	$t2, $zero, permute	#no need to swap, just jump to permutation
	#else
	li	$v0, 10			#failsafe termination
	syscall
	
swap:	
	lbu	$t1, 3($a1)		#gets the byte currently held in [3]
	sb	$t0, 3($a1)		#saves the previous direction in [3]
	sb	$t1, 0($a2)		#puts the pulled byte into the empty space
	j	permute			#begin permutation of first 3 elements
			
permute:
	li	$v0, 42			#random number generated
	li	$a1, 3			#upper limit of 3 (0-2)
	syscall
	
	la	$a2, direction		#loads the direction array
	lbu	$t0, 0($a2)		#loads the first byte in array
	add	$a3, $a2, $a0		#finds the address to swap with
	lbu	$t1, 0($a3)		#loads the byte from the second address
	sb	$t1, 0($a2)		#saves the second byte to the first address
	sb	$t0, 0($a3)		#saves the first byte to the second address
	
	li	$v0, 42			#random number generated
	li	$a1, 3			#upper limit of 3
	syscall
	
	la	$a2, direction		#loads the direction array
	lbu	$t0, 1($a2)		#loads the second byte in array
	add	$a3, $a2, $a0		#finds the address to swap with
	lbu	$t1, 0($a3)		#loads the byte from the second address
	sb	$t1, 1($a2)		#saves the second byte to the first address
	sb	$t0, 0($a3)		#saves the first byte to the second address
		
	li	$v0, 42			#random number generated
	li	$a1, 3			#upper limit of 3
	syscall
	
	la	$a2, direction		#loads the direction array
	lbu	$t0, 2($a2)		#loads the third byte in the array
	add	$a3, $a2, $a0		#finds the address to swap with
	lbu	$t1, 0($a3)		#loads the byte from the second address
	sb	$t1, 2($a2)		#saves the second byte in the first address
	sb	$t0, 0($a3)		#saves the first byte in the second address
				
	lw	$ra, 12($sp)		#load the saved return address
	jr	$ra			#return to zombies_move with permuted array

curr_quad:
	la	$a1, direction		#loads the direction array
	lbu	$t0, 0($a1)		#load first direction byte
	lbu	$t1, 1($a1)		#load second direction byte
	lbu	$t2, 2($a1)		#load third direction byte
	lbu	$a2, 0($a0)		#loads the zombie x-coordinate
	lbu	$a3, 1($a0)		#loads the zombie y-coordinate
	
	sub	$t3, $s0, $a2 		#gets the difference in x-coordinates
	sub	$t4, $s1, $a3		#gets the difference in y-coordinates
	
	bge	$t3, $zero, to_right	#the player is to right of zombie
	blt	$t3, $zero, to_left	#the player is to left of zombie
	
	
to_right:
	bge	$t4, $zero, to_Rdown	#the player is below the zombie
	bgt	$t4, $t3, U_R_prior	#up is priority followed by right
	addi	$t5, $zero, 2		#sets priority register 1 to right
	addi	$t6, $zero, 0		#sets priority register 2 to up
	j	check0			#jumps to check order of direction array
	
to_Rdown:
	bgt	$t4, $t3, D_R_prior	#down is priority, followed by right
	addi	$t5, $zero, 2		#sets priority register 1 to right
	addi	$t6, $zero, 3		#sets priority register 2 to down
	j	check0			#jumps to check order of direction array
	
to_left:
	bge	$t4, $zero, to_Ldown	#the player is below the zombie
	bgt	$t4, $t3, U_L_prior	#up is priority followed by left
	addi	$t5, $zero, 1		#sets priority register 1 to left
	addi	$t6, $zero, 0		#sets priority register 2 to up
	j	check0			#jumps to check order of direction array

to_Ldown:
	bgt	$t4, $t3, D_L_prior	#down is priority, followed by left
	addi	$t5, $zero, 1		#sets priority register 1 to left
	addi	$t6, $zero, 3		#sets priority register 2 to down
	j	check0			#jumps to check order of direction array
	
U_R_prior:
	addi	$t5, $zero, 0		#sets priority register 1 to up
	addi	$t6, $zero, 2		#sets priority register 2 to right
	j	check0			#jumps to check order of direction array
	
U_L_prior:
	addi	$t5, $zero, 0		#sets priority register 1 to up
	addi	$t6, $zero, 1		#sets priority register 2 to up
	j	check0			#jumps to check order of direction array

D_R_prior:
	addi	$t5, $zero, 3		#sets priority register 1 to down
	addi	$t6, $zero, 2		#sets priority register 2 to right
	j	check0			#jumps to check order of direction array
	
D_L_prior:
	addi	$t5, $zero, 3		#sets priority register 1 to down
	addi	$t6, $zero, 1		#sets priority register 2 to left
	j	check0			#jumps to check order of direction array

check0:
	beq	$t5, $t0, check1	#if highest priority is already in position 0
	beq	$t5, $t1, swap_01	#top priority in position 1, swap to 0
	beq	$t5, $t2, swap_02	#top priority in position 2, swap to 0
	j	check1prior		#top priority is backtrack direction
	
check1prior:
	beq	$t6, $t0, end		#second priority is in position 0
	beq	$t6, $t1, swap_01a	#second priority in position 1, swap to 0
	beq	$t6, $t2, swap_02a	#second priority in position 2, swap to 0
	j	end
	
check1:
	beq	$t6, $t1, end		#second priority in position 1 already
	beq	$t6, $t2, swap_12	#second priority in position 2, swap to 1
	
end:
	sb	$t0, 0($a1)		#save the first direction byte
	sb	$t1, 1($a1)		#save the second direction byte
	sb	$t2, 2($a1)		#save the third direction byte

	beq	$s5, 0, return0		#returns after quadrant 0 fix
	beq	$s5, 1, return1		#returns after quadrant 1 fix
	beq	$s5, 2, return2		#returns after quadrant 2 fix
	beq	$s5, 3, return3		#returns after quadrant 3 fix
	
swap_01:
	move	$t7, $t0		#move t0 to temporary register
	move	$t0, $t1		#move t1 into t0
	move	$t1, $t7		#move the original t0 into t1
	j	check1			#check second priority direction
	
swap_02:
	move	$t7, $t0		#move t0 to temp register
	move	$t0, $t2		#move t2 into t0
	move	$t2, $t7		#move original t0 into t2
	j	check1			#check second priority direction
	
swap_01a:
	move	$t7, $t0		#move t0 to temporary register
	move	$t0, $t1		#move t1 into t0
	move	$t1, $t7		#move the original t0 into t1
	j	end			#jump to ending process
	
swap_02a:
	move	$t7, $t0		#move t0 to temp register
	move	$t0, $t2		#move t2 into t0
	move	$t2, $t7		#move original t0 into t2
	j	end			#jump to ending process
	
swap_12:
	move	$t7, $t1		#move t1 to temp register
	move	$t1, $t2		#move t2 into t1
	move	$t2, $t7		#move original t1 into t2
	j	end			#jump to the ending process

Q0_move:
	sw	$ra, 12($sp)		#saves return address
	la	$a3, zombies		#loads the zombie array
	lbu	$a0, 0($a3)		#loads the x-coordinate of Q0 zombie
	lbu	$a1, 1($a3)		#loads the y-coordinate of Q0 zombie

	la	$a2, direction		#loads the direction array
	jal	Q0_valid		#check to find valid move and do the move

Q1_move:
	sw	$ra, 12($sp)		#saves return address
	la	$a3, zombies		#loads zombie array
	addi	$a3, $a3, 3		#moves 3 bytes to get quadrant 1
	lbu	$a0, 0($a3)		#loads the x-coordinate of Q1 zombie
	lbu	$a1, 1($a3)		#loads the y-coordinate of Q1 zombie
	
	la	$a2, direction		#loads the direction array
	jal	Q1_valid		#check to find valid move and do the move
	
Q2_move:
	sw	$ra, 12($sp)		#saves return address
	la	$a3, zombies		#loads the zombie array
	addi	$a3, $a3, 6		#moves 6 bytes to get quadrant 2
	lbu	$a0, 0($a3)		#loads the x-coordinate of Q2 zombie
	lbu	$a1, 1($a3)		#loads the y-coordinate of Q2 zombie
	
	la	$a2, direction		#loads the direction array
	jal	Q2_valid		#check to find valid move and do the move
	
Q3_move:
	sw	$ra, 12($sp)		#saves the return address
	la	$a3, zombies		#loads the zombie array
	addi	$a3, $a3, 9		#moves 9 bytes to get quadrant 3
	lbu	$a0, 0($a3)		#loads the x-coordinate of Q3 zombie
	lbu	$a1, 1($a3)		#loads the y-coordinate of Q3 zombie

	la	$a2, direction		#loads the direction array
	jal	Q3_valid		#check to find valid move and do the move
	
Q0_valid:
	lbu	$t0, 0($a2)		#load the first direction in the direction array
	addi	$a2, $a2, 1		#increment the direction array address by one
	
Q0_left:
	addi	$t1, $t0, -1		#check if it is a left move
	bne	$t1, $zero, Q0_right	#if not, move on to right check
	beq	$a0, $zero, Q0_valid	#check if it is already against the wall
	addi	$a0, $a0, -1		#check if the next space to left is valid
	jal	_getLED			
	addi	$a0, $a0, 1		#increment back to original space
	beq	$v0, 2, Q0_valid	#if it is a wall, invalid move
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_left		#jumps to move left
Q0_right:
	addi	$t1, $t0, -2		#check if it is a right move
	bne	$t1, $zero, Q0_up	#if not, move to up check
	beq	$a0, 31, Q0_valid	#check if it is against border
	addi	$a0, $a0, 1		#check if the next space is valid
	jal	_getLED
	addi	$a0, $a0, -1		#increment back to original space
	beq	$v0, 2, Q0_valid	#if it is a wall, invalid move
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_right		#jumps to move right
Q0_up:
	addi	$t1, $t0, 0		#check to see if it is up move
	bne	$t1, $zero, Q0_down	#if not, move to check down
	beq	$a1, $zero, Q0_valid	#check if already at top 
	addi	$a1, $a1, -1		#check if the above space is valid
	jal	_getLED
	addi	$a1, $a1, 1		#increment back to original space
	beq	$v0, 2, Q0_valid	#if it is a wall, invalid move
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_up		#jumps to move up
Q0_down:
	addi	$t1, $t0, -3		#double check to see if it is down
	bne	$t1, $zero, lose	#this should never happen, but just in case
	beq	$a1, 31, Q0_valid	#check if already at bottom of quadrant
	addi	$a1, $a1, 1		#check if next space is valid
	jal	_getLED
	addi	$a1, $a1, -1		#increment back to original space
	beq	$v0, 2, Q0_valid	#if it is a wall, invalid move (this should also never happen)
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_down		#jumps to move down
	
Q1_valid:
	lbu	$t0, 0($a2)		#load byte from direction array
	addi	$a2, $a2, 1		#increment direction array address
	
Q1_left:
	addi	$t1, $t0, -1		#check if it is a left move
	bne	$t1, $zero, Q1_right	#if not, move on to right check
	beq	$a0, 32, Q1_valid	#check if it is already against the wall
	addi	$a0, $a0, -1		#check if the next space to left is valid
	jal	_getLED			
	addi	$a0, $a0, 1		#increment back to original space
	beq	$v0, 2, Q1_valid	#if it is a wall, invalid move
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_left		#jumps to move left
Q1_right:
	addi	$t1, $t0, -2		#check if it is a right move
	bne	$t1, $zero, Q1_up	#if not, move to up check
	beq	$a0, 63, Q1_valid	#check if it is against border
	addi	$a0, $a0, 1		#check if the next space is valid
	jal	_getLED
	addi	$a0, $a0, -1		#increment back to original space
	beq	$v0, 2, Q1_valid	#if it is a wall, invalid move
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_right		#jumps to move right
Q1_up:
	addi	$t1, $t0, 0		#check to see if it is up move
	bne	$t1, $zero, Q1_down	#if not, move to check down
	beq	$a1, $zero, Q1_valid	#check if already at top 
	addi	$a1, $a1, -1		#check if the above space is valid
	jal	_getLED
	addi	$a1, $a1, 1		#increment back to original space
	beq	$v0, 2, Q1_valid	#if it is a wall, invalid move
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_up		#jumps to move up
Q1_down:
	addi	$t1, $t0, -3		#double check to see if it is down
	bne	$t1, $zero, lose	#this should never happen, but just in case
	beq	$a1, 31, Q1_valid	#check if already at bottom of quadrant
	addi	$a1, $a1, 1		#check if next space is valid
	jal	_getLED
	addi	$a1, $a1, -1		#increment back to original space
	beq	$v0, 2, Q1_valid	#if it is a wall, invalid move (this should also never happen)
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_down		#jumps to move down
	
Q2_valid:
	lbu	$t0, 0($a2)		#load the direction from the array
	addi	$a2, $a2, 1		#increment the direction array address
	
Q2_left:
	addi	$t1, $t0, -1		#check if it is a left move
	bne	$t1, $zero, Q2_right	#if not, move on to right check
	beq	$a0, $zero, Q2_valid	#check if it is already against the wall
	addi	$a0, $a0, -1		#check if the next space to left is valid
	jal	_getLED			
	addi	$a0, $a0, 1		#increment back to original space
	beq	$v0, 2, Q2_valid	#if it is a wall, invalid move
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_left		#jumps to move left
Q2_right:
	addi	$t1, $t0, -2		#check if it is a right move
	bne	$t1, $zero, Q2_up	#if not, move to up check
	beq	$a0, 31, Q2_valid	#check if it is against border
	addi	$a0, $a0, 1		#check if the next space is valid
	jal	_getLED
	addi	$a0, $a0, -1		#increment back to original space
	beq	$v0, 2, Q2_valid	#if it is a wall, invalid move
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_right		#jumps to move right
Q2_up:
	addi	$t1, $t0, 0		#check to see if it is up move
	bne	$t1, $zero, Q2_down	#if not, move to check down
	beq	$a1, 32, Q2_valid	#check if already at top 
	addi	$a1, $a1, -1		#check if the above space is valid
	jal	_getLED
	addi	$a1, $a1, 1		#increment back to original space
	beq	$v0, 2, Q2_valid	#if it is a wall, invalid move
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_up		#jumps to move up
Q2_down:
	addi	$t1, $t0, -3		#double check to see if it is down
	bne	$t1, $zero, lose	#this should never happen, but just in case
	beq	$a1, 63, Q2_valid	#check if already at bottom of quadrant
	addi	$a1, $a1, 1		#check if next space is valid
	jal	_getLED
	addi	$a1, $a1, -1		#increment back to original space
	beq	$v0, 2, Q2_valid	#if it is a wall, invalid move (this should also never happen)
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_down		#jumps to move down
	
Q3_valid:
	lbu	$t0, 0($a2)		#load the byte in the direction array
	addi	$a2, $a2, 1		#increment the direction array address
	
Q3_left:
	addi	$t1, $t0, -1		#check if it is a left move
	bne	$t1, $zero, Q3_right	#if not, move on to right check
	beq	$a0, 32, Q3_valid	#check if it is already against the wall
	addi	$a0, $a0, -1		#check if the next space to left is valid
	jal	_getLED			
	addi	$a0, $a0, 1		#increment back to original space
	beq	$v0, 2, Q3_valid	#if it is a wall, invalid move
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_left		#jumps to move left
Q3_right:
	addi	$t1, $t0, -2		#check if it is a right move
	bne	$t1, $zero, Q3_up	#if not, move to up check
	beq	$a0, 63, Q3_valid	#check if it is against border
	addi	$a0, $a0, 1		#check if the next space is valid
	jal	_getLED
	addi	$a0, $a0, -1		#increment back to original space
	beq	$v0, 2, Q3_valid	#if it is a wall, invalid move
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_right		#jumps to move right
Q3_up:
	addi	$t1, $t0, 0		#check to see if it is up move
	bne	$t1, $zero, Q3_down	#if not, move to check down
	beq	$a1, 32, Q3_valid	#check if already at top 
	addi	$a1, $a1, -1		#check if the above space is valid
	jal	_getLED
	addi	$a1, $a1, 1		#increment back to original space
	beq	$v0, 2, Q3_valid	#if it is a wall, invalid move
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_up		#jumps to move up
Q3_down:
	addi	$t1, $t0, -3		#double check to see if it is down
	bne	$t1, $zero, lose	#this should never happen, but just in case
	beq	$a1, 63, Q3_valid	#check if already at bottom of quadrant
	addi	$a1, $a1, 1		#check if next space is valid
	jal	_getLED
	addi	$a1, $a1, -1		#increment back to original space
	beq	$v0, 2, Q3_valid	#if it is a wall, invalid move (this should also never happen)
	beq	$v0, 3, lose		#if it is the player, player loses
	jal	zombie_down		#jumps to move down
		
	
zombie_up:
	move	$a2, $zero 		#change color code to 0 for not lit
	jal	_setLED 		#clear the spot player is currently at
	
	addi	$a1, $a1, -1 		#sets y-coordinate to future space
	addi	$a2, $a2, 1 		#change color code to 3 for green
	jal	_setLED 		#set the LED to the new spot
	
	sb	$a1, 1($a3)		#increment the player's position register
	addi	$t9, $zero, 3		#change the previous direction register
	sb	$t9, 2($a3)		#change the zombie previous direction
		
	lw	$ra, 12($sp) 		#load the return address
	jr	$ra 			#return back to the original location in the polling loop
	
zombie_down:
	move	$a2, $zero 		#change color code to 0 for not lit
	jal	_setLED 		#clear the spot player is currently at
	
	addi	$a1, $a1, 1 		#sets y-coordinate to future space
	addi	$a2, $a2, 1 		#change color code to 3 for green
	jal	_setLED 		#set the LED to the new spot
	
	sb	$a1, 1($a3) 		#increment the player's position register
	move	$t9, $zero		#change the previous direction register
	sb	$t9, 2($a3)		#change the zombie previous direction
	
	lw	$ra, 12($sp) 		#load the return address
	jr	$ra			#return back to the original location in the polling loop
	
zombie_left:
	move	$a2, $zero 		#change color code to 0 for not lit
	jal	_setLED 		#clear the spot player is currently at
	
	addi	$a0, $a0, -1 		#sets x-coordinate to future space
	addi	$a2, $a2, 1 		#change color code to 3 for green
	jal	_setLED 		#set the LED to the new spot
	
	sb	$a0, 0($a3) 		#increment the player's position register
	addi	$t9, $zero, 2		#change the previous direction register
	sb	$t9, 2($a3)		#change the zombie previous direction
	
	lw	$ra, 12($sp) 		#load the return address
	jr	$ra 			#return back to the original location in the polling loop
		
zombie_right:
	move	$a2, $zero 		#change color code to 0 for not lit
	jal	_setLED 		#clear the spot player is currently at
	
	addi	$a0, $a0, 1 		#sets x-coordinate to future space
	addi	$a2, $a2, 1 		#change color code to 3 for green
	jal	_setLED 		#set the LED to the new spot
	
	sb	$a0, 0($a3) 		#increment the player's position register
	addi	$t9, $zero, 1		#set the previous direction register
	sb	$t9, 2($a3)		#change the zombie previous direction
	
	lw	$ra, 12($sp) 		#load the return address
	jr	$ra 			#return back to the original location in the polling loop
						
									
lose:
	li 	$v0, 4			#call to print a string
	la 	$a0, lose_notice	#print out the loss notice
	syscall
	
	li	$v0, 10			#terminate program after loss
	syscall

win:
	li	$v0, 4			#call to print a string
	la	$a0, win_notice		#print out win notice
	syscall
	
	li	$v0, 4			#call to print a string	
	la	$a0, points		#print out score script
	syscall
	
	li	$v0, 1			#call to print an integer
	add	$a0, $zero, $s2		#print the player's score
	syscall
	
	li	$v0, 10			#terminate program after win
	syscall

# _setLED and _getLED functions for Keypad and LED Display Simulator (64x64)
#
# These functions may be used in your CS/CoE 0447 Project 1.
# They provide a convenient interface to the Keypad and LED Display Simulator
# extension (64x64) in Mars4_5-Pitt.  For arguments and return values,
# read the comments above each; call them like any other MIPS function.
#
# If you're really interested, look through the code to show yourself
# how it works, or even practice writing these yourself!  You know
# all the pieces; try fitting them together!


	# void _setLED(int x, int y, int color)
	#   sets the LED at (x,y) to color
	#   color: 0=off, 1=red, 2=yellow, 3=green
	#
	# arguments: $a0 is x, $a1 is y, $a2 is color
	# trashes:   $t0-$t3
	# returns:   none
	#
_setLED:
	# byte offset into display = y * 16 bytes + (x / 4)
	sll	$t0,$a1,4      # y * 16 bytes
	srl	$t1,$a0,2      # x / 4
	add	$t0,$t0,$t1    # byte offset into display
	li	$t2,0xffff0008 # base address of LED display
	add	$t0,$t2,$t0    # address of byte with the LED
	# now, compute led position in the byte and the mask for it
	andi	$t1,$a0,0x3    # remainder is led position in byte
	neg	$t1,$t1        # negate position for subtraction
	addi	$t1,$t1,3      # bit positions in reverse order
	sll	$t1,$t1,1      # led is 2 bits
	# compute two masks: one to clear field, one to set new color
	li	$t2,3		
	sllv	$t2,$t2,$t1
	not	$t2,$t2        # bit mask for clearing current color
	sllv	$t1,$a2,$t1    # bit mask for setting color
	# get current LED value, set the new field, store it back to LED
	lbu	$t3,0($t0)     # read current LED value	
	and	$t3,$t3,$t2    # clear the field for the color
	or	$t3,$t3,$t1    # set color field
	sb	$t3,0($t0)     # update display
	jr	$ra
	
	# int _getLED(int x, int y)
	#   returns the value of the LED at position (x,y)
	#
	#  arguments: $a0 holds x, $a1 holds y
	#  trashes:   $t0-$t2
	#  returns:   $v0 holds the value of the LED (0, 1, 2 or 3)
	#
_getLED:
	# byte offset into display = y * 16 bytes + (x / 4)
	sll  $t0,$a1,4      # y * 16 bytes
	srl  $t1,$a0,2      # x / 4
	add  $t0,$t0,$t1    # byte offset into display
	la   $t2,0xffff0008
	add  $t0,$t2,$t0    # address of byte with the LED
	# now, compute bit position in the byte and the mask for it
	andi $t1,$a0,0x3    # remainder is bit position in byte
	neg  $t1,$t1        # negate position for subtraction
	addi $t1,$t1,3      # bit positions in reverse order
    	sll  $t1,$t1,1      # led is 2 bits
	# load LED value, get the desired bit in the loaded byte
	lbu  $t2,0($t0)
	srlv $t2,$t2,$t1    # shift LED value to lsb position
	andi $v0,$t2,0x3    # mask off any remaining upper bits
	jr   $ra
