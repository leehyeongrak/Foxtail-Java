import processing.core.PApplet;

abstract public class Shape implements Cloneable {

    protected int x;
    protected int y;
    private int offsetX;
    private int offsetY;
    int shapeType;

    boolean isSelected = false;
    Color color = new Color(0, 0, 0);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Shape(int x, int y, Color color, int shapeType) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.shapeType = shapeType;
        if (x < Constant.SIDE_LENGTH/2) {
            this.x = Constant.SIDE_LENGTH/2;
        } else if (x > Constant.WINDOW_WIDTH - Constant.SIDE_LENGTH/2) {
            this.x = Constant.WINDOW_WIDTH - Constant.SIDE_LENGTH/2;
        }
        if (y < Constant.SIDE_LENGTH/2) {
            this.y = Constant.SIDE_LENGTH/2;
        } else if (y > Constant.WINDOW_HEIGHT - Constant.SIDE_LENGTH/2) {
            this.y = Constant.WINDOW_HEIGHT - Constant.SIDE_LENGTH/2;
        }
    }


    abstract public void draw(PApplet pApplet);

    public void selectShape(int mouseX, int mouseY) {
        isSelected = true;
        offsetX = mouseX - x;
        offsetY = mouseY - y;

        color.r += 100;
        color.g += 100;
        color.b += 100;
    }

    public void unSelect() {
        isSelected = false;
        color.r -= 100;
        color.g -= 100;
        color.b -= 100;
    }

    public void shapeMove(int mouseX, int mouseY) {
        while (mouseX - x > offsetX) {
            if (x < Constant.WINDOW_WIDTH - Constant.SIDE_LENGTH/2) {
                x++;
            } else return;
        }
        while (mouseX - x < offsetX) {
            if (x > Constant.SIDE_LENGTH/2) {
                x--;
            } else return;
        }
        while (mouseY - y > offsetY) {
            if (y < Constant.WINDOW_HEIGHT - Constant.SIDE_LENGTH/2) {
                y++;
            } else return;
        }
        while (mouseY - y < offsetY) {
            if (y > Constant.SIDE_LENGTH/2) {
                y--;
            } else return;
        }
    }

    public boolean isCollision(int mouseX, int mouseY) {
        return false;
    }

    @Override
    public Shape clone() {
        try {
            Shape shape = (Shape) super.clone();
            shape.color = this.color.clone();
            return shape;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
