package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class for a spotLight - light with direction and position
 * @author Elhanan Tweig & Yossef Cohen-Salmon
 */
public class SpotLight extends PointLight{

    private Vector direction;

    /**
     * constructor of spotLight
     * @param intensity=the color of the light
     * @param position=the position of the light
     * @param direction=the direction of the light
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        Color pointIntensity = super.getIntensity(p);
        Vector l = getL(p);
        double attenuation= l.dotProduct(direction);

        return pointIntensity.scale(Math.max(0,attenuation));
    }

    //bonus
    public SpotLight setNarrowBeam(int i) {
        return this;
    }
}