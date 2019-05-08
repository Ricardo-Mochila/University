addi $t1,$zero,-100
addi $t0,$zero,-1

beq $t0,$t1,if
nop 
xor $t1,$t1,$t0
xor $t0,$t1,$t0
xor $t1,$t1,$t0
if: and $t0,$zero,$t0

