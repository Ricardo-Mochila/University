addi $t1,$zero,-100
addi $t0,$zero,-1
addi $t2,$zero,1
slt $t3,$t0,$zero
beq $t3,$t2,if
nop
and $t1,$zero,$zero
or $t1,$t0,$t1

if: sub $t1,$zero,$t0
    j end
end: