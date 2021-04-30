package it.polito.tdp.metroparis.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.metroparis.db.MetroDAO;

public class Model {
	
	//come possiamo rappresentare la mappa dentro al programma?
	
	Graph <Fermata, DefaultEdge> grafo;
	 // creazione grafo
	
	public void creaGrafo() {
		this.grafo= new SimpleGraph<>(DefaultEdge.class);
		//aggiunta vertici e archi
		//basta chiedere al Dao la lista delle fermate
		
		MetroDAO dao= new MetroDAO();
		List <Fermata> fermate= dao.getAllFermate();
		//ho già hashcode definito per Fermata, altrimenti andrebbe creatp
		
		/*for(Fermata f: fermate) {
			this.grafo.addVertex(f);
		}*/
		
		//spesso devo fare questa operazione
		//esiste proprio una classe aggiuntiva che si chiama graphs che contiene
		//dei metodi iterativi che si usano spesso con i grafi
		//è una classe di utilità che ha metodi statici, che sono scorciatoie
		
		//es. addAllVertices--> mi aggiunge tutti i  vertici, equivale al for
		
		Graphs.addAllVertices(this.grafo, fermate);
		//devo aggiungere gli archi
		//data una coppia di vertici, devono essere collegati?
		
		/*for(Fermata f1: this.grafo.vertexSet()) {
			//vertex set mi da il set che contiene tutti i vertici
			
			for(Fermata f2: this.grafo.vertexSet()) {
				//f1 è la partenza, f2 è l'arrivo
				
				//devo chiedere al dao se f1 e f2 sono collegati
				//devo vedere prima se f1 e f2 sono diversi, altrimenti non vale
				
				if(!f1.equals(f2)&&dao.fermateCollegate(f1,f2)) {
					this.grafo.addEdge(f1, f2);
				}
			}
		}*/
		
		List <Connessione> connessioni=dao.getAllConnessioni();
		for(Connessione c:connessioni) {
			this.grafo.addEdge(c.getStazP(), c.getStazA());
		}
		
		System.out.println(this.grafo);
	}
}
