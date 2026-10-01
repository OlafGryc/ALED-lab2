package es.upm.aled.lab2.kinematics;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the class Segment
 * 
 * @author rgarciacarmona
 * @author olafbarski
 */



public class Segment {
	/**
	 * @param length Length of a given segment.
	 * @param angle Angle between two consecutive segments.
	 * @param children List of segments that are children to the segment.
	 */
	private double length;
	private double angle;
	private List<Segment> children;
	
	
	public Segment (double length, double angle) {
		this.length = length;
		this.angle = angle;
		this.children = new ArrayList<>();
	}
	
	public double getLength() {
		return length;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public List<Segment> getChildren(){
		return children;
	}
	
	public void addChild (Segment child) {
		//si children no contiene child
		if (!children.contains(child)) {
			children.add(child);
		}
	}
	

}
