package primitives;

/**
 * class foe the material of object represented by its shininess, diffuse and specular
 */
public class Material {
    public double kD = 0;
    public double kS = 0;
    public int nShininess = 0;

    /**
     * setter for kd
     *
     * @param kD the diffuse
     * @return the material
     */
    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     * setter for ks
     *
     * @param kS the specular
     * @return the material
     */
    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     * setter for shininess
     *
     * @param nShininess the shininess
     * @return the material
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * setter for kd
     *
     * @param kD the diffuse
     * @return the material
     */
    public Material setKd(Double3 kD) {
        this.kD = kD.d1;
        return this;
    }

    /**
     * setter for ks
     *
     * @param kS the specular
     * @return the material
     */
    public Material setKs(Double3 kS) {
        this.kS = kS.d1;
        return this;
    }
}