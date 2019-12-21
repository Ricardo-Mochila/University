tam([], 0).
tam([A| B], X) :- tam(B, Z) ,X is Z+1.

linha(I, QM, X) :- 