package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * class for a point light with position and without direction
 *
 * @author Elhanan Tweig & Yossef Cohen-Salmon
 */
public class PointLight extends Light implements LightSource {

    private final Point position;
    protected double radius;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * constructor of point light with position and intensity
     *
     * @param intensity the color of the light
     * @param position the  position of the light
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * constructor of point light with position and intensity
     *
     * @param intensity the color of the light
     * @param position the  position of the light
     * @param radius the radius of the light
     */
    public PointLight(Color intensity, Point position, double radius) {
        super(intensity);
        this.position = position;
        this.radius = radius;
    }

    /**
     * Sets the position of the light
     *
     * @param radius the radius of the light
     * @return the point light
     */
    public PointLight setradius(double radius) {
        this.radius = radius;
        return this;
    }

    /**
     * Sets the constant attenuation factor of the light
     *
     * @param kC the constant attenuation
     * @return the point light
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Sets the light's constant attenuation factor to the given value and returns this light.
     *
     * @param kL the linear attenuation
     * @return the point light
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Sets the quadratic attenuation factor of the light
     *
     * @param kQ the quadratic attenuation
     * @return the point light
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        // IL / (kc + kl *distance + kq * distanceSquared)
        double distanceSquared = p.distanceSquared(position);
        double distance = Math.sqrt(distanceSquared);
        double factor = kC + kL * distance + kQ * distanceSquared;
        return intensity.reduce(factor);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }

    @Override
    public List<Vector> getListL(Point p) {
        Random r = new Random();
        List<Vector> vectors = new LinkedList();
        for (double i = -radius; i < radius; i += radius / 15) {
            for (double j = -radius; j < radius; j += radius / 15) {
                if (i != 0 && j != 0) {
                    Point point = position.add(new Vector(i, 0.1d, j));
                    if (point.subtract(position).dotProduct(point.subtract(position)) <= radius * radius) {
                        vectors.add(p.subtract(point).normalize());
                    }
                }
            }
        }
        vectors.add(getL(p));
        return vectors;
    }
}