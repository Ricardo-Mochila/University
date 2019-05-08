main:
	addi $a0, $zero, 5
	addi $t0, $zero, 1
	addi $t1, $zero, 1
	fact:
		bgt $t0, $a0, fim
		nop
		mult $t0,$t1
		mflo $t1
		j fact
		addi $t0, $t0, 1
		
	fim:
		add $v0, $zero, $t1