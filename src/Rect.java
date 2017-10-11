import processing.core.PApplet;

public class Rect extends Shape {

    public Rect(int x, int y, Color color, int shapeType) {
        super(x, y, color, shapeType);
    }

    @Override
    public boolean isCollision(int mouseX, int mouseY) {
        return mouseX > (x - Constant.SIDE_LENGTH/2) && mouseX < (x + Constant.SIDE_LENGTH/2) && mouseY > (y - Constant.SIDE_LENGTH/2) && mouseY < (y + Constant.SIDE_LENGTH/2);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.fill(this.color.r, this.color.g, this.color.b);
        pApplet.rect(x-Constant.SIDE_LENGTH/2,y-Constant.SIDE_LENGTH/2,Constant.SIDE_LENGTH,Constant.SIDE_LENGTH);

    }

    @Override
    public Rect clone() {
        return (Rect) super.clone();
    }
}
