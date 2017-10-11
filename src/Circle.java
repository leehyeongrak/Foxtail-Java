import processing.core.PApplet;

public class Circle extends Shape{

    public Circle(int x, int y, Color color, int shapeType) {
        super(x, y, color, shapeType);
    }

    @Override
    public boolean isCollision(int mouseX, int mouseY) {
        double distance;
        distance = Math.sqrt((double)((x-mouseX)*(x-mouseX)+(y-mouseY)*(y-mouseY)));

        return distance < Constant.RADIUS_LENGTH;
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.fill(color.r, color.g, color.b);
        pApplet.ellipse(x, y,Constant.RADIUS_LENGTH*2,Constant.RADIUS_LENGTH*2);
    }

    @Override
    public Circle clone() {
        return (Circle) super.clone();
    }
}