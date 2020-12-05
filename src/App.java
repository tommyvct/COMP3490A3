import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

public class App extends PApplet {
    /**
     * <h1>Entry point</h1>
     * 
     * The {@code appletArgs} must match this class's name
     * 
     * @param passedArgs
     */
    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "App" };

        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }

    public void settings() {
        size(640, 640, P3D);
    }

    // libJim.pde

    /// Custom Camera -- this doesn't do anything, really, except invert UP so it
    /// makes sense.
    // not needed to use this, but helps you simplify the mapping
    void myCamera(float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY,
            float upZ) {
        camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, -upX, -upY, -upZ);
    }

    // 3D axis-aligned box collision. Not hard, but finicky. Feel free to use it.
    // 1, 2 - boxes 1 and 2.
    // assumes that all axes increase from left->right, bottom->top, back->front
    boolean collide(float bottom1, float top1, float left1, float right1, float back1, float front1, float bottom2,
            float top2, float left2, float right2, float back2, float front2) {
        return collideDimension(bottom1, top1, bottom2, top2) && collideDimension(left1, right1, left2, right2)
                && collideDimension(back1, front1, back2, front2);
    }

    // start and stop of 2 shapes in some dimension, returns true of they overlap on
    // the number line
    boolean collideDimension(float start1, float stop1, float start2, float stop2) {
        return (start1 < stop2 && stop1 > start2);
    }

    ///////////////////// ------ ONLY useful for the bonus
    ///////////// Custom projection functions. Used to access the Processing
    ///////////////////// under-the-hood engine
    PGraphicsOpenGL pogl = null;

    void setupPOGL() {
        pogl = (PGraphicsOpenGL) g;
    }

    public void printProjection() {
        pogl.projection.print();
    }

    void setProjection(PMatrix3D mat) {
        assert pogl != null : "no PGraphics Open GL Conext";
        // pogl.setProjection(mat.get());
        pogl.projection.set(mat.get());
        pogl.updateProjmodelview();
    }

    PMatrix3D getProjection() {
        assert pogl != null : "no PGraphics Open GL Conext";
        return pogl.projection.get();
    }

    // Mpdes.pde
    final char KEY_VIEW = 'r';
    final char KEY_LEFT = 'a';
    final char KEY_RIGHT = 'd';
    final char KEY_UP = 'w';
    final char KEY_DOWN = 's';
    final char KEY_JUMP = ' ';
    final char KEY_BONUS = 'b';
    final char KEY_TEX = 't';
    final char KEY_COLLISION = 'c';

    boolean keyLeft = false;
    boolean keyRight = false;
    boolean keyDown = false;
    boolean keyUp = false;
    boolean keyJump = false;
    boolean doBonus = false;
    boolean doTextures = false;
    boolean doCollision = false;

    // false is perspective mode.
    boolean orthoMode = true;

    // Assignment3Handout.pde
    void setup() {
        size(1200, 1200, P3D);
        colorMode(RGB, 1.0f);
        textureMode(NORMAL); // uses normalized 0..1 texture coords
        textureWrap(CLAMP);
        // ONLY NEEDED FOR BONUS setupPOGL(); // setup our hack to ProcesingOpenGL to
        // let us modify the projection matrix manually

        // TODO: your setup code

        // WARNING: use loadImage to load any textures in setup or after. If you do it
        // globally / statically processing complains.
        // - just make a .setup or .init function on your world, player, etc., that
        // loads the textures, and call those from here.
    }

    void draw() {
        // don't use resetMatrix to start. It clears the modelView matrix, which
        // includes your camera.
        // if you change the camera, you need to build the model again, so only do at
        // beginning of draw.

    }
}