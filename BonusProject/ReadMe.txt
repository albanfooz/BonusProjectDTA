============	Readme		============
Auteur/Dev : Alban Fourfooz


__________________
---		How To Use

Application seule:
	Run.jar


Sur Eclipse IDE :  
	Run ->
		Main			pour la version Console (non recommand�, pas user friendly, ni compl�tement test�e)

		GuiMain 		pour la version avec la GUI (recommand�)


__________________
---		Quick Doc

GuiMain : 
	Mise	Entrer le montant � miser (0.0 minimum/ 1.0 par d�faut)
	Result 	Affiche le r�sultat
	Gain	Affiche les gains
	Argent 	Affiche l'argent du joueur
	NbRoue	Choisir le nombre de roue (3 minimum/ 3 par d�faut)
	
	ClassicGame			Lance une partie
		StopOne 		Stop 1 roue
		StopAll			Termine la partie

	QuickGame			Disabled car trop de bug / StopAll remplace tr�s bien cette fonction
	
	Exit 				Quitte le jeu
	
	Ludicrous Mode 		Autorise � rentrer plus de 1000 roues	
		
_____________
---		Notes

L'option Quickgame a �t� disabled car trop buggy.

Thread/Runnable utilis� pour la class Roue et LecteurRoue

Swing (Java) 			pour l'interface graphique
Pattern/Regex 			pour les inputs 
ConcurrentHashMap 		pour stocker et afficher la valeur de chaque roue en m�me temps


___________________
---		A Am�liorer

Uniformiser : 
	- les commentaires/(la documentation)
	- le code (nom de variable/classe/m�thodes)
	- la langue (Anglais/Fran�ais)



______________________
---		Pour le future

Changer le syst�me de coordonn�es des composants de l'UI
Ou utiliser un smart-layout

Changer le syst�me de score quand il y a plus de 3 roues



