  
  -- JavaChess --

di Bonfante Pietro (VR380370) e Mazzi Giulio (VR 378647)

Gioco degli scacchi implementato in Java e Swing.

Il gioco non implementa l'intero regolamento. In particolare
non è prevista la presa al varco e l'arrocco.
Nella cartella src/ si trovano i pacchetti e il codice che formano
il programma. In img/ ci sono invece le immagini.

- Struttura dell'applicazione:
L'applicazione è strutturata principalmente come model-view-controller,
a questo si aggiunge un package di suppporto quali rules (che contiene le
regole, ed é usato dal controller) e test (che contiene delle classi di test
JUnit).