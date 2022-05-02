package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static java.awt.Color.BLACK;

/**
 * Scene class represents a scene with a background, lights and geometries.
 *
 * @author Elhanan Tweig & Yossef Cohen-Salmon
 */
public class Scene {
    /**
     * The name of the scene.
     */
    public String name;

    /**
     * The background color of the scene.
     */
    public Color background = Color.BLACK;

    /**
     * The ambient color of the scene.
     */
    public AmbientLight ambientLight = new AmbientLight();

    /**
     * A list of all geometries in the scene.
     */
    public Geometries geometries = new Geometries();

    /**
     * A list of all kind of light
     */
    public List<LightSource> lights = new LinkedList<>();

    /**
     * set the scene`s light
     *
     * @param lights new light
     * @return the updated scene itself
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    /**
     * Constructs a new scene with a given name.
     * Sets all colors as black and creates new empty lists for geometries and lights.
     *
     * @param name The name of the scene.
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Set the scene's background color
     *
     * @param background New color for the background
     * @return the updated scene itself
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Set the scene's ambientLight
     *
     * @param ambientLight New ambientLight
     * @return the updated scene itself
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Set the scene's geometry list.
     *
     * @param geometries New list of geometries
     * @return the updated scene itself
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
