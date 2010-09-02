package fiji.plugin.nperry.gui;

import fiji.plugin.nperry.Feature;
import fiji.plugin.nperry.Spot;
import ij3d.Content;

import java.awt.Color;
import java.util.List;

import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

import customnode.CustomMeshNode;
import customnode.CustomTriangleMesh;
import customnode.MeshMaker;

public class SpotContent extends Content {
	
	private static final float DEFAULT_RADIUS = 5;
	static final Color3f DEFAULT_COLOR = new Color3f(Color.RED);
	private static final float DEFAULT_TRANSPARENCY = 0.5f;
	
	private Spot spot;
	private float radius = DEFAULT_RADIUS;
	private Color3f color = DEFAULT_COLOR;
	private float transparency = DEFAULT_TRANSPARENCY;

	public SpotContent(Spot spot) {
		super(spot.getName(), spot.getFrame());
		this.spot = spot;
		makeMesh();
	}
	
	/*
	 * PUBLIC METHODS
	 */
	
	public final Float getFeature(Feature feature) {
		return spot.getFeature(feature);
	}
	
	
	/*
	 * PRIVATE METHODS
	 */
	
	@SuppressWarnings("unchecked")
	private void makeMesh() {
		float[] center = spot.getCoordinates();
		float x = center[0];
		float y = center[1];
		float z = center[2];
		List<Point3f> list = MeshMaker.createSphere(x, y, z, radius);
		CustomTriangleMesh mesh = new CustomTriangleMesh(list, color, 0);
		setColor(color);
		setTransparency(transparency);
		setShaded(false);
		showCoordinateSystem(false);
		display(new CustomMeshNode(mesh));
	}

}