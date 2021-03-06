package americanflag;

// Flag starter kit

/*
 * Mihir Rao
 */

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JApplet;

public class AmericanFlag extends JApplet {
    private final int STRIPES = 13;

    // SCALE FACTORS (A through L)
    //
    // Note: Constants in Java should always be ALL_CAPS, even
    // if we are using single letters to represent them
    //
    // NOTE 2: Do not delete or change the names of any of the
    // variables given here
    //
    // Set the constants to exactly what is specified in the documentation
    // REMEMBER: These are scale factors. They are not numbers of pixels.
    // You will use these and the width and height of the Applet to figure
    // out how to draw the parts of the flag (stripes, stars, field).
    private final double A = 1.0; // Hoist (width) of flag
    private final double B = 1.9; // Fly (length) of flag
    private final double C = 0.5385; // Hoist of Union
    private final double D = 0.76; // Fly of Union
    private final double E = 0.054; // See flag specification
    private final double F = 0.054; // See flag specification
    private final double G = 0.063; // See flag specification
    private final double H = 0.063; // See flag specification
    private final double K = 0.0616; // Diameter of star
    private final double L = 0.0769; // Width of stripe

    // You will need to set values for these in paint()
    private double flag_width; // width of flag in pixels
    private double flag_height; // height of flag in pixels
    private double stripe_height; // height of an individual stripe in pixels

    // init() will automatically be called when an applet is run
    public void init() {
        // Choice of width = 1.9 * height to start off
        // 760 : 400 is ratio of FLY : HOIST
        setSize(760, 400);
        repaint();
    }

    // paint() will be called every time a resizing of an applet occurs
    public void paint(Graphics g) {
        flag_width = getWidth();
        flag_height = getHeight();

        if (B * flag_height > flag_width) {
            flag_height = flag_width / B;
        } else {
            flag_width = flag_height * B;
        }

        stripe_height = (L * flag_height);
        drawBackground(g);
        drawStripes(g);
        drawField(g);
        drawStars(g);
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void drawStripes(Graphics g) {
        final Color oldGloryRed = new Color(191, 10, 48);
        g.setColor(oldGloryRed);
        g.fillRect(0, 0, (int) flag_width, (int) flag_height);

        int ycor = (int) stripe_height;
        g.setColor(Color.WHITE);

        for (int i = 0; i < 6; i++) {
            g.fillRect(0, ycor, (int) flag_width, (int) stripe_height);
            ycor += 2 * stripe_height;
        }
    }

    public void drawField(Graphics g) {
        final Color oldGloryBlue = new Color(0, 40, 104);
        g.setColor(oldGloryBlue);
        g.fillRect(0, 0, (int) (D * flag_height), (int) (C * flag_height) - 2);
    }

    public void drawStars(Graphics g) {
        double xcor = (G * flag_height);
        double ycor = (E * flag_height);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                Star s = new Star(g, xcor, ycor, (K * flag_height));
                ycor += 2 * (F * flag_height);
            }
            xcor += 2 * (H * flag_height);
            ycor = (E * flag_height);
        }

        xcor = (G * flag_height) + (H * flag_height);
        ycor = (E * flag_height) + (F * flag_height);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                Star s = new Star(g, xcor, ycor, (K * flag_height));
                ycor += 2 * (F * flag_height);
            }
            xcor += 2 * (H * flag_height);
            ycor = (E * flag_height) + (F * flag_height);
        }
    }
}