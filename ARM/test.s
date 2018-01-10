.text
.global _start

_start:
SUB R4, R4, R4
ADD R4, R4, #42
SUB R0, R0, R0
ADD R0, R0, R4
bl min_caml_print_int
