main:
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	addi $a0, $zero, 32
	addi $t0, $zero, 32
	addi $t1, $zero, 0
	addi $t2, $zero, 1
	log2:
		ble $t0, $t2, fim
		nop
		srl $t0, $t0, 1
		j log2 
		addi $t1, $t1, 1 
	fim:
		lw $ra, 0($sp)
		addi $sp, $sp, 4
		jr $ra
		add $v0, $zero, $t1