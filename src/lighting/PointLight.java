package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class for a point light with position and without direction
 *
 * @author Elhanan Tweig & Yossef Cohen-Salmon
 */
public class PointLight extends Light implements LightSource {

    private Point position;
    private double kC, kL, kQ;

    /**
     * constructor of point light with position and intensity
     *
     * @param intensity=the color of the light
     * @param position=the  position of the light
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
        this.kC = 1;
        this.kL = 0;
        this.kQ = 0;
    }

    /**
     * setter for kc
     *
     * @param kC the constant attenuation
     * @return the point light
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * setter for kl
     *
     * @param kL the linear attenuation
     * @return the point light
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setter for kq
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
        double distance = p.distance(position);
        double distanceSquared = p.distanceSquared(position);

        double factor = kC + kL * distance + kQ * distanceSquared;

        return getIntensity().reduce(factor);
    }

    @Override
    public Vector getL(Point p) {
        if (!p.equals(position))
            return p.subtract(position).normalize();
        return null;
    }
}