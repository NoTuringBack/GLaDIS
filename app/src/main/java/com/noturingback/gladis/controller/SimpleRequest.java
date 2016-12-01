package com.noturingback.gladis.controller;

import com.noturingback.gladis.model.ConvMessage;
import com.noturingback.gladis.model.Conversation;

/**
 * Created by vrong on 02/12/16.
 */

public class SimpleRequest extends RequestType
{
	@Override
	public int estimateMatching(String entry, Conversation conv)
	{
		return 0;
	}

	@Override
	public ConvMessage respondTo(String entry, Conversation conv)
	{
		//TODO si le mec dit "bonsoir", répondre un truc qui correspond genre "salut" ?
		//TODO On peut créer un fichier (json ? xml ? autre ? ) pour chaque type de demande qui regroupe les mot-clés et les réponses possibles
		//TODO Une fois qu'on a cerné le type d'échange avec les fichiers, choisir une réponse aléatoire parmi celles disponibles pour ce type d'échange
		/*
			fichier1 : bonsoir.json
				motclés : bonsoir, salut, bonjour, ...
				réponses possibles : hey, bonsoir, salut, ...

			fichier2 : Commentcava.json
				motclés : comment, ça, va
				réponses possbiels : tranquille et toi, nickel, ...

			Etape 1 :
				On reçoit "comment ça va ?"
					On test le fichier1.json : peut de correspondance avec les mot-clés.
					On test le fichier2.json : Whaouh ça match de ouf du coup on prend celui là.
			Etape 2 : On choisit une réponse aléatoire dans celles proposées dans le fichier
			Etape 3 : On crée un objet ConvMessage avec la réponse et on la renvoie.

		 */
		return null;
	}
}
