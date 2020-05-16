package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	Graph<Airport, DefaultWeightedEdge> grafo;
	private Map<Integer, Airport> airportIdMap;
	ExtFlightDelaysDAO dao;
	private List<CoppiaAeroporti> coppie;
	
	public Model() {
		this.airportIdMap = new HashMap<Integer, Airport>();
	}
	
	public void creaGrafo(int distanza) {
		this.grafo = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		dao = new ExtFlightDelaysDAO();
			
		for(Airport a : dao.loadAllAirports()) {
			airportIdMap.put(a.getId(), a);		
		}
			
		Graphs.addAllVertices(this.grafo, dao.loadAllAirports());
			
		this.coppie = dao.getCoppieAeroporti(this.airportIdMap, distanza);
			
		for(CoppiaAeroporti c : coppie) {
			Graphs.addEdge(this.grafo, c.getA1(), c.getA2(), c.getDistance());
		}
	}

	public int getVertici() {
		return this.grafo.vertexSet().size();
	}

	public int getArchi() {
		return this.grafo.edgeSet().size();
	}

	public List<CoppiaAeroporti> getCoppie() {
		return coppie;
	}
	
	
}
