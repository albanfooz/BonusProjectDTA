============	Readme		============
Auteur/Dev : Alban Fourfooz


__________________
---		How To Use

Application seule:
	Run.jar


Sur Eclipse IDE :  
	Run ->
		Main			pour la version Console (non recommandé, pas user friendly, ni complètement testée)

		GuiMain 		pour la version avec la GUI (recommandé)


__________________
---		Quick Doc

GuiMain : 
	Mise	Entrer le montant à miser (0.0 minimum/ 1.0 par défaut)
	Result 	Affiche le résultat
	Gain	Affiche les gains
	Argent 	Affiche l'argent du joueur
	NbRoue	Choisir le nombre de roue (3 minimum/ 3 par défaut)
	
	ClassicGame			Lance une partie
		StopOne 		Stop 1 roue
		StopAll			Termine la partie

	QuickGame			Disabled car trop de bug / StopAll remplace très bien cette fonction
	
	Exit 				Quitte le jeu
	
	Ludicrous Mode 		Autorise à rentrer plus de 1000 roues	
		
_____________
---		Notes

L'option Quickgame a été disabled car trop buggy.

Thread/Runnable utilisé pour la class Roue et LecteurRoue

Swing (Java) 			pour l'interface graphique
Pattern/Regex 			pour les inputs 
ConcurrentHashMap 		pour stocker et afficher la valeur de chaque roue en même temps


___________________
---		A Améliorer

Uniformiser : 
	- les commentaires/(la documentation)
	- le code (nom de variable/classe/méthodes)
	- la langue (Anglais/Français)



______________________
---		Pour le future

Changer le système de coordonnées des composants de l'UI
Ou utiliser un smart-layout

Changer le système de score quand il y a plus de 3 roues



