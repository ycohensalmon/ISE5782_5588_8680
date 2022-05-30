package renderer;

import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

/**
 * Mp1test class
 */
public class Mp1test {

    private Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
    private Point spPL = new Point(-50, -50, 25); // Sphere test Position of Light

    private Scene scene1 = new Scene("Test scene") //
            .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

    private Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(150, 150) //
            .setViewPlaneDistance(1000);

    private Geometry sphere1 = new Sphere(new Point(0, -50, -50), 27d) //
            .setEmission(new Color(BLUE).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere2 = new Sphere(new Point(0, -15, -50), 20d) //
            .setEmission(new Color(BLUE).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere3 = new Sphere(new Point(5, -5, -50), 5d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere4 = new Sphere(new Point(-5, -5, -50), 5d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));



    /**
     * Produce a picture of a sphere lighted by a point light
     */
    @Test
    public void spherePoint() {
        scene1.geometries.add(sphere1, sphere2, sphere3, sphere4);
        scene1.lights.add(new PointLight(spCL, spPL).setKl(0.001).setKq(0.0002));

        ImageWriter imageWriter = new ImageWriter("mp11", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }
    /**
     */
    @Test
    public void mp1() {
        Vector trDL1 = new Vector(-13,-7,-10);
        Point p1 = new Point(6, 6, 1);
        Point p2 = new Point(5, -6, 1);
        Point p3 = new Point(-5, -6, 1);
        Point p4 = new Point(-5, -6, 1);
        Geometry p = new Triangle(p1, p2, p3)
                .setEmission(new Color(BLUE).reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
        scene1.geometries.add(p);
        scene1.lights.add(new DirectionalLight(new Color(200,50,80), trDL1));
        ;

        ImageWriter imageWriter = new ImageWriter("mp1", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }
}
