.text
.global _start

_start:
SUB R4, R4, R4
ADD R4, R4, #50
ST R4, [FP-4]
SUB R5, R5, R5
ADD R5, R5, #25
ST R5, [FP-8]
