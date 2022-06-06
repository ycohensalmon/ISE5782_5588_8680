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
    private Point spPL2 = new Point(50, 50, 25); // Sphere test Position of Light


    private Scene scene1 = new Scene("Test scene") //
            .setAmbientLight(new AmbientLight(new Color(RED), new Double3(0.15)));

    private Camera camera1 = new Camera(new Point(-100, 100, 80), new Vector(5,-5,-4), new Vector(4,-4,10)) //
            .setViewPlaneSize(150, 150) //
            .setViewPlaneDistance(140);

    private Geometry sphere1 = new Sphere(new Point(0,0,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere2 = new Sphere(new Point(20,0,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere3 = new Sphere(new Point(40,0,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere4 = new Sphere(new Point(60,0,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere5 = new Sphere(new Point(80,0,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere6 = new Sphere(new Point(0,-20,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere7 = new Sphere(new Point(0,-40,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere8 = new Sphere(new Point(0,-60,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere9 = new Sphere(new Point(0,-80,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere10 = new Sphere(new Point(0,0,11), 5d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere100 = new Sphere(new Point(-6,2.5,11.5), 2d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere101 = new Sphere(new Point(-2,2.5,11.5), 2d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere11 = new Sphere(new Point(20,0,10.15), 5d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere12 = new Sphere(new Point(40,0,10.15), 5d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere13 = new Sphere(new Point(60,0,10.15), 5d) //
            .setEmission(new Color(PINK).reduce(1.5)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere14 = new Sphere(new Point(80,0,10.15), 5d) //
            .setEmission(new Color(PINK).reduce(1.5)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere15 = new Sphere(new Point(0,-20,10.15), 5d) //
            .setEmission(new Color(PINK).reduce(1.5)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere16 = new Sphere(new Point(0,-40,10.15), 5d) //
            .setEmission(new Color(PINK).reduce(1.5)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere17 = new Sphere(new Point(0,-60,10.15), 5d) //
            .setEmission(new Color(PINK).reduce(1.5)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere18 = new Sphere(new Point(0,-80,10.15), 5d) //
            .setEmission(new Color(PINK).reduce(1.5)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere19 = new Sphere(new Point(20,-20,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere20 = new Sphere(new Point(20,-20,10.15), 5d) //
            .setEmission(new Color(PINK).reduce(1.5)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry sphere21 = new Sphere(new Point(40,-20,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(300));
    private Geometry sphere22 = new Sphere(new Point(20,-40,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(300));
    private Geometry sphere23 = new Sphere(new Point(60,-20,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.9).setKs(0.5).setShininess(300));
    private Geometry sphere24 = new Sphere(new Point(40,-40,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.9).setKs(0.5).setShininess(300));
    private Geometry sphere25 = new Sphere(new Point(20,-60,0), 10d) //
            .setEmission(new Color(PINK).reduce(2)) //
            .setMaterial(new Material().setKd(0.9).setKs(0.5).setShininess(300));
    private Geometry sphere26 = new Sphere(new Point(40,-20,10.15), 5d) //
            .setEmission(new Color(PINK).reduce(1.5)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(300));
    private Geometry sphere27 = new Sphere(new Point(20,-40,10.15), 5d) //
            .setEmission(new Color(PINK).reduce(1.5)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(300));
    private Geometry sphere28 = new Sphere(new Point(60,-20,10.15), 5d) //
            .setEmission(new Color(PINK).reduce(1.5)) //
            .setMaterial(new Material().setKd(0.9).setKs(0.5).setShininess(300));
    private Geometry sphere29 = new Sphere(new Point(40,-40,10.15), 5d) //
            .setEmission(new Color(PINK).reduce(1.5)) //
            .setMaterial(new Material().setKd(0.9).setKs(0.5).setShininess(300));
    private Geometry sphere30 = new Sphere(new Point(20,-60,10.15), 5d) //
            .setEmission(new Color(PINK).reduce(1.5)) //
            .setMaterial(new Material().setKd(0.9).setKs(0.5).setShininess(300));

    /**
     */
    @Test
    public void mp1() {
        Vector trDL1 = new Vector(-13,-7,-10);

        Point floor1 = new Point(-50,50,-10);
        Point floor2 = new Point(-50,-110 ,-10);
        Point floor3 = new Point(110,-110,-10);
        Point floor4 = new Point(110,50,-10);

        Point up1 = new Point(-50,50,50);
        Point up2 = new Point(-70,-50 ,50);
        Point up3 = new Point(50,-50,50);
        Point up4 = new Point(50,70,50);
        Geometry floor = new Polygon(floor1 ,floor2 ,floor3 ,floor4)
                .setEmission(new Color(BLUE).reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(300));
        Geometry left = new Polygon(floor3, floor2, up2, up3)
                .setEmission(new Color(WHITE).reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
        Geometry right = new Polygon(floor4, floor3, up3, up4)
                .setEmission(new Color(RED).reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
        Geometry line = new Polygon(new Point(-10,-10,60), new Point(-10,-12,60), new Point(-10,-12,50), new Point(-10,-10,50))
                .setEmission(new Color(BLACK).reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
        Geometry light = new Sphere(new Point(-10,-11,50),5)
                .setEmission(new Color(YELLOW).reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
        scene1.geometries.add(floor, left, right,light, line);

        scene1.geometries.add(floor, left, right, sphere1, sphere2, sphere3, sphere4, sphere5, sphere6, sphere7, sphere8,
                sphere9, sphere10, sphere100, sphere101, sphere11, sphere12, sphere13, sphere14, sphere15, sphere16, sphere17, sphere18/*, sphere19,
                sphere20, sphere21, sphere22, sphere23, sphere24, sphere25, sphere26, sphere27, sphere28, sphere29, sphere30*/);
        scene1.lights.add(new DirectionalLight(new Color(200,50,80), trDL1));
        scene1.lights.add(new PointLight(spCL, spPL).setradius(5).setKl(0.001).setKq(0.0002));
        scene1.lights.add(new PointLight(spCL, spPL2).setradius(5).setKl(0.001).setKq(0.0002));

        ImageWriter imageWriter = new ImageWriter("mp1SS", 1000, 1000);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1))
                .setSoftShadow(true) //
                .renderImage() //
                .writeToImage(); //
    }
}