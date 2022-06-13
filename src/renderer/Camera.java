package renderer;

import multiThreading.threadPool;
import primitives.*;
import primitives.Color;
import primitives.Point;

import java.awt.*;
import java.util.LinkedList;
import java.util.MissingResourceException;

import static java.lang.System.out;

/**
 * Camera class represents the camera through which we see the scene.
 */
public class Camera {

    /**
     * The point of view of the camera.
     */
    private Point p0;

    //The directions of the camera:
    /**
     * vUp - The "up" direction in the camera.
     */
    private Vector vUp;

    /**
     * vTo - The "to" direction in the camera, where the scene is.
     */
    private Vector vTo;

    /**
     * vRight - The "right" direction in the camera.
     */
    private Vector vRight;

    // The attributes of the view plane:
    /**
     * The width of the view plane.
     */
    private double width;

    /**
     * The height of the view plane.
     */
    private double height;

    /**
     * The distance between the p0 and the view plane (in the direction of vTo).
     */
    private double distance;

    private ImageWriter imageWriter;

    private RayTracerBase rayTracer;

    /**
    * turn on - off adaptive super sampling
     */
    private boolean isASS = false;

    /**
     * setter of adaptive super sampling
     * @param ASS is adaptive super sampling
     * @return the camera
     */
    public Camera setASS(boolean ASS) {
        isASS = ASS;
        return this;
    }

    /**
     * turn on - off soft shadow
     */
    private boolean isSoftShadow = false;

    /**
     * the number of rays
     */
    private int numOfRays = 100;

    private threadPool<Pixel> threadPool = null;
    /**
     * Next pixel of the scene
     */
    private Pixel nextPixel = null;

    /**
     * setter of softShadow
     *
     * @param softShadow is soft shadow
     * @return the camera
     */
    public Camera setSoftShadow(boolean softShadow) {
        isSoftShadow = softShadow;
        return this;
    }

    /**
     * setter of numOfRays
     *
     * @param numOfRays the number of rays
     * @return the camera
     */
    public Camera setNumOfRays(int numOfRays) {
        this.numOfRays = numOfRays;
        return this;
    }

    /**
     * Constructs an instance of Camera with point and to and up vectors.
     *
     * @param p0  The point of view of the camera.
     * @param vTo The "to" direction of the camera, where the scene is.
     * @param vUp The "up" direction of the camera.
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!(vUp.dotProduct(vTo) == 0))
            throw new IllegalArgumentException("vTo and vUp have to be orthogonal!!!");
        this.p0 = p0;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * get the width of the view plane
     *
     * @return The width from the view plane.
     */
    public double getWidth() {
        return width;
    }

    /**
     * get the height of the view plane
     *
     * @return The height from the view plane.
     */
    public double getHeight() {
        return height;
    }

    /**
     * get the distance from the p0 to the view plane
     *
     * @return The distance from the p0 to the view plane.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * init the image writer
     *
     * @param imageWriter The imageWriter to set.
     * @return The current instance (Builder pattern).
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * init the ray tracer
     *
     * @param rayTracer The rayTracer to set.
     * @return The current instance (Builder pattern).
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * init the view plane by the width and height
     *
     * @param width  The number to set as the view plane's width.
     * @param height The number to set as the view plane's height.
     * @return The current instance (Builder pattern).
     */
    public Camera setViewPlaneSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * init the distance of the view plane
     *
     * @param distance The number to set as the distance between the p0 and the view plane.
     * @return The current instance (Builder pattern).
     */
    public Camera setViewPlaneDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Creates a ray that goes through a given pixel
     *
     * @param nX number of pixels on X axis in the view plane
     * @param nY number of pixels on Y axis in the view plane
     * @param j  X coordinate of the pixel
     * @param i  Y coordinate of the pixel
     * @return The ray from the camera to the pixel
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point imgCenter = p0.add(vTo.scale(distance));
        double rY = height / nY, rX = width / nX;
        double iY = -(i - (nY - 1d) / 2) * rY, jX = (j - (nX - 1d) / 2) * rX;
        Point ijP = imgCenter;
        if (jX != 0) ijP = ijP.add(vRight.scale(jX));
        if (iY != 0) ijP = ijP.add(vUp.scale(iY));
        Vector ijV = ijP.subtract(p0);
        return new Ray(p0, ijV);
    }


    private final String RESOURCE = "Renderer resource not set";
    private final String CAMERA_CLASS = "Camera";
    private final String IMAGE_WRITER = "Image writer";
    private final String CAMERA = "Camera";
    private final String RAY_TRACER = "Ray tracer";

    /**
     * For each pixel in the image, cast a ray and write the color of the pixel to the image
     *
     * @return The current instance (Builder pattern).
     */
    public Camera renderImage() {
        if (imageWriter == null)
            throw new MissingResourceException(RESOURCE, CAMERA_CLASS, IMAGE_WRITER);
        if (p0 == null || vTo == null || vUp == null || vRight == null || width == 0 || height == 0 || distance == 0)
            throw new MissingResourceException(RESOURCE, CAMERA_CLASS, CAMERA);
        if (rayTracer == null)
            throw new MissingResourceException(RESOURCE, CAMERA_CLASS, RAY_TRACER);

        final int nX = imageWriter.getNx();
        final int nY = imageWriter.getNy();

        //rendering the image with multithreaded
        if (threadPool != null) {
            nextPixel = new Pixel(0, 0);
            threadPool.execute();

            printPercentMultithreaded(); // blocks the main thread until finished and prints the progress

            threadPool.join();
            return this;
        }

        for (int i = 0; i < nY; i = ++i) {
            out.println(i + "/" + nY);
            for (int j = 0; j < nX; ++j) {
                this.imageWriter.writePixel(j, i, castRay(nX, nY, j, i));
            }
        }
        return this;
    }

    /**
     * the method cast a ray through a pixel
     *
     * @param nX number of pixels on X axis in the view plane
     * @param nY number of pixels on Y axis in the view plane
     * @param j  X coordinate of the pixel
     * @param i  Y coordinate of the pixel
     * @return the color of the pixel
     */
    private Color castRay(int nX, int nY, int j, int i) {
        return this.rayTracer.traceRay(this.constructRayThroughPixel(nX, nY, j, i), isSoftShadow, numOfRays, isASS);
    }

    /**
     * Create a grid [over the picture] in the pixel color map. given the grid's
     * step and color.
     *
     * @param interval grid's interval
     * @param color    grid's color
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException(RESOURCE, CAMERA_CLASS, IMAGE_WRITER);

        final int nX = imageWriter.getNx();
        final int nY = imageWriter.getNy();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    /**
     * Produce a rendered image file
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException(RESOURCE, CAMERA, IMAGE_WRITER);

        imageWriter.writeToImage();
    }

    /**
     * Chaining method for setting number of threads.
     * If set to 1, the render won't use the thread pool.
     * If set to greater than 1, the render will use the thread pool with the given threads.
     * If set to 0, the thread pool will pick the number of threads.
     *
     * @param threads number of threads to use
     * @return the current render
     * @throws IllegalArgumentException when threads is less than 0
     */
    public Camera setMultithreading(int threads) {
        if (threads < 0) {
            throw new IllegalArgumentException("threads can be equals or greater to 0");
        }

        // run as single threaded without the thread pool
        if (threads == 1) {
            threadPool = null;
            return this;
        }

        threadPool = new threadPool<Pixel>() // the thread pool choose the number of threads (in0 case threads is 0)
                .setParamGetter(this::getNextPixel)
                .setTarget(this::renderImageMultithreaded);
        if (threads > 0) {
            threadPool.setNumThreads(threads);
        }

        return this;
    }

    /**
     * Returns the next pixel to draw on multithreaded rendering.
     * If finished to draw all pixels, returns {@code null}.
     */
    private synchronized Pixel getNextPixel() {

        // notifies the main thread in order to print the percent
        notifyAll();


        Pixel result = new Pixel();
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        // updates the row of the next pixel to draw
        // if got to the end, returns null
        if (nextPixel.col >= nX) {
            if (++nextPixel.row >= nY) {
                return null;
            }
            nextPixel.col = 0;
        }

        result.col = nextPixel.col++;
        result.row = nextPixel.row;
        return result;
    }

    /**
     * Renders a given pixel on multithreaded rendering.
     * If the given pixel is null, returns false which means kill the thread.
     *
     * @param p the pixel to render
     */
    private boolean renderImageMultithreaded(Pixel p) {
        if (p == null) {
            return false; // kill the thread
        }

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        this.imageWriter.writePixel(p.col, p.row, castRay(nX, nY, p.col, p.row));
        return true; // continue the rendering
    }

    /**
     * Must run on the main thread.
     * Prints the percent on multithreaded rendering.
     */
    private void printPercentMultithreaded() {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        int pixels = nX * nY;
        int lastPercent = -1;

        while (nextPixel.row < nY) {
            // waits until got update from the rendering threads
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }

            int currentPixel = nextPixel.row * nX + nextPixel.col;
            lastPercent = printPercent(currentPixel, pixels, lastPercent);
        }
    }

    /**
     * Prints the progress in percents only if it is greater than the last time printed the progress.
     *
     * @param currentPixel the index of the current pixel
     * @param pixels       the number of pixels in the image
     * @param lastPercent  the percent of the last time printed the progress
     * @return If printed the new percent, returns the new percent. Else, returns {@code lastPercent}.
     */
    private int printPercent(int currentPixel, int pixels, int lastPercent) {
        int percent = currentPixel * 100 / pixels;
        if (percent > lastPercent) {
            System.out.printf("%02d%%\n", percent);
            System.out.flush();
            return percent;
        }
        return lastPercent;
    }

    /**
     * Helper class to represent a pixel to draw in a multithreading rendering.
     */
    private static class Pixel {
        public int col, row;

        public Pixel(int col, int row) {
            this.col = col;
            this.row = row;
        }

        public Pixel() {
        }
    }

}
