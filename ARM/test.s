.text
.global _start

_start:
MOV FP, SP
SUB R4, R4, R4
ADD R4, R4, #50
STR R4, [FP, #4]
SUB R5, R5, R5
ADD R5, R5, #42
STR R5, [FP, #8]
LDR R6, [FP, #8]
LDR R7, [FP, #4]
CMP R6, R7
BLE true0
SUB R8, R8, R8
ADD R8, R8, #0
STR R8, [FP, #12]
LDR R9, [FP, #12]
SUB R0, R0, R0
ADD R0, R0, R9
bl min_caml_print_int
b done0
true0: SUB R10, R10, R10
ADD R10, R10, #1
STR R10, [FP, #16]
LDR R11, [FP, #16]
SUB R0, R0, R0
ADD R0, R0, R11
bl min_caml_print_int

done0: NOP