public class Color implements Cloneable {
    int r;
    int g;
    int b;

    public Color(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
    @Override
    public Color clone() {
        try {
            return (Color) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

