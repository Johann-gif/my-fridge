graph TD
  A[OS]
  B{Docker}
  C[API Rest]
  D[Code]
  E[Base de Donnée H2]
  G[Séparer les privilèges]
  F[Injection SQL]
  A -->|Lancement de l'image|B
  B -->|Tout est embarqué dans l'API|C
  C -->|Le code peut être modifié|D
  C -->|Base de donnée créée au lancement|E
  C -->|Tout le monde à accès à tout|G
  E -->F