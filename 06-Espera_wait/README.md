# Preguntes teòriques - 06-Espera_wait

## 1. Per què s'atura l'execució al cap d'un temps?

Perquè els threads estan esperant. Quan no hi ha plaçes, es queden en wait() bloquejats fins que algú cancel·la una reserva.

## 2. Que passaria si la probabilitat fora de 70%(ferReserva)-30%(cancelar)? I si foren al revés?

Si fos 70% fer reserva:
Més gent intentaria fer reserva
Les places s'omplementarien ràpid

Si fos 30% fer reserva:
Poca gent fa reserva
Molts intent cancelar

## 3. Perquè creus que fa falta la llista i no valdria només una variable sencera de reserves?

Perquè amb una variable int no sabries qui té reserva. Quan algú intenta cancelar, has de saber si aquella persona en concret té una reserva. Sense la llista no pots verificar-ho i el sistema seria incorrecte.
