package it.polito.tdp.extflightdelays.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		System.out.println(model.grafo);
		System.out.println("Il grafo contiene " + model.grafo.vertexSet().size() + " vertici e " + model.grafo.edgeSet().size() + " archi");

	}

}
