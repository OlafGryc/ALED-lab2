package es.upm.aled.lab2.kinematics;

import es.upm.aled.lab2.gui.Node;

/**
 * This class implements a forward kinematics algorithm using recursion. It
 * expects a tree of Segments (defined by its length and angle with respect to
 * the previous Segment in the tree) and returns a tree of Nodes (defined by
 * their absolute coordinates in a 2-dimensional space).
 * 
 * @author rgarciacarmona
 */
public class ForwardKinematics {

	/**
	 * Returns a tree of Nodes to be used by SkeletonPanel to draw the position of
	 * an exoskeleton. This method is the public facade to a recursive method that
	 * builds the result from a tree of Segments defined by their angle and length,
	 * and the relationship between them (which Segment is children of which).
	 * 
	 * @param root    The root of the tree of Segments.
	 * @param originX The X coordinate for the origin point of the tree.
	 * @param originY The Y coordinate for the origin point of the tree.
	 * @return The tree of Nodes that represent the exoskeleton position in absolute
	 *         coordinates.
	 */
	// Public method: returns the root of the position tree
	
	private static double accumulatedAngle = 0;

	public static Node computePositions(Segment root, double originX, double originY) {
		return(computePositions(root, originX, originY, accumulatedAngle));
	}

	// Private helper method that implements the recursive algorithm
	private static Node computePositions(Segment link, double baseX, double baseY, double accumulatedAngle) {
		Node newNodo = new Node(baseX, baseY);
		accumulatedAngle+=link.getAngle();
		double newBaseX = baseX + link.getLength()*Math.cos(accumulatedAngle);
		double newBaseY = baseY + link.getLength()*Math.sin(accumulatedAngle);
		/**
		 * newNodo.addChild(new Node(newBaseX, newBaseY));
		 *lo eliminamos, porque este nodo que añadimos es un nodo sin ningun hijo, un nodo
		 *como un punto solitario
		 *vamos a añadir este nodo recurriendo a la propia funcion, abajo, donde llamamos un nodo hijo
		 *
		 *lo vamos a añadir en el if, porque alli si que tenemos que poner un nodo como un simple punto
		 *sin mas ramificaciones, es decir hijos
		*/
		
		if (link.getChildren().size() == 0) {
			newNodo.addChild(new Node(newBaseX, newBaseY));
			/**
			 * accumulatedAngle-=link.getAngle();
			 * 
			 * no hace falta ponerlo, ya que accumulatedAngle es un tipo primitivo (double)
			 * y se pasa como argumento al metodo recursivo. Cualquier cambio que le hagamos
			 * solo existe en la llamada concreta, por lo que, al dar return, el valor vuelve 
			 * a ser el mismo que antes. Al terminar una llamada y volver a la llamada "padre", 
			 * el valor de accumulatedAngle en el "padre" no se ha visto afectado
			 * 
			 * por tanto no hace falta poner esta linea
			 */
			return newNodo;
		}
		for (Segment child : link.getChildren()) {
			Node hijo = computePositions(child, newBaseX, newBaseY, accumulatedAngle);	
			newNodo.addChild(hijo);

		}
		return newNodo;
	}
}