import processing.core.PApplet;

public class Triangle extends Shape {

    public Triangle(int x, int y, Color color, int shapeType) {
        super(x, y, color, shapeType);
    }

    @Override
    public boolean isCollision(int mouseX, int mouseY) {
        boolean check1, check2, check3;

        check1 = (mouseX - x) * (y-Constant.SIDE_LENGTH/2 - (y+Constant.SIDE_LENGTH/2)) - (x-Constant.SIDE_LENGTH/2 - x) * (mouseY - (y + Constant.SIDE_LENGTH/2)) < 0.0f;
        check2 = (mouseX - (x+Constant.SIDE_LENGTH/2)) * (y+Constant.SIDE_LENGTH/2 - (y-Constant.SIDE_LENGTH/2)) - (x - (x+Constant.SIDE_LENGTH/2)) * (mouseY - (y-Constant.SIDE_LENGTH/2)) < 0.0f;
        check3 = (mouseX - (x-Constant.SIDE_LENGTH/2)) * (y-Constant.SIDE_LENGTH/2 - (y-Constant.SIDE_LENGTH/2)) - (x+Constant.SIDE_LENGTH/2 - (x-Constant.SIDE_LENGTH/2)) * (mouseY - (y-Constant.SIDE_LENGTH/2)) < 0.0f;

        return (check1 == check2) && (check2 == check3);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.fill(color.r, color.g, color.b);
        pApplet.triangle(x-Constant.SIDE_LENGTH/2, y-Constant.SIDE_LENGTH/2, x,y+Constant.SIDE_LENGTH/2, x+Constant.SIDE_LENGTH/2, y-Constant.SIDE_LENGTH/2);
    }
    @Override
    public Triangle clone() {
        return (Triangle) super.clone();
    }
}
