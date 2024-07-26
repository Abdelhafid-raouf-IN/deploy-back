#!/bin/bash

# Vérifie si le fichier targets.txt existe
if [ ! -f targets.txt ]; then
  echo "Le fichier targets.txt est introuvable."
  exit 1
fi

# Exécute Vegeta en mode attaque
echo "Démarrage du test de charge avec Vegeta..."
vegeta attack -rate=100 -duration=20s -targets=targets.txt | tee results.bin | vegeta encode | jaggr -name=latency -attr=latency | jplot -title "Latency Distribution" -xlabel "Latency (ms)" -ylabel "Count" > latency.svg

if [ $? -ne 0 ]; then
  echo "Erreur lors de l'exécution du test de charge."
  exit 1
fi

# Affiche le rapport de Vegeta
echo "Génération du rapport de Vegeta..."
vegeta report results.bin

# Génère un graphique en utilisant Vegeta plot
echo "Génération du graphique HTML avec Vegeta plot..."
vegeta plot results.bin > plot.html

if [ $? -ne 0 ]; then
  echo "Erreur lors de la génération du graphique HTML."
  exit 1
fi

# Affiche les résultats
echo "Test de charge terminé. Rapport généré et graphique créé."
