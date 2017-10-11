import processing.core.PApplet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class Program extends PApplet {


    @Override
    public void settings() {
        this.size(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        System.out.println("Settings");
    }

    @Override
    public void setup() {
        this.background(0);
        System.out.println("Setup");
    }
    private static String FILE_PATH = "/Users/rak/IdeaProjects/ShapeEditor/SaveFile.txt";

    private List<Shape> shapes = new ArrayList<>();

    private boolean isControlPressed = false;
    private boolean isMousePressed = false;


    @Override
    public void mouseDragged() {
        if(isMousePressed){
            for (int i = shapes.size() - 1; i >= 0; i--){
                Shape e = shapes.get(i);
                if(e.isSelected){
                    e.shapeMove(mouseX, mouseY);
                }
            }
        }
    }

    @Override
    public void draw(){
        background(255,255,255);
        for (Shape e : shapes) {
            e.draw(this);
        }
    }

    public static void main(String[] args) {
        PApplet.main("Program");
    }


    @Override
    public void keyReleased() {
        if(keyCode == CONTROL) {
            isControlPressed = false;
        }
    }

    @Override
    public void keyPressed() {
        if(keyCode == CONTROL){
            isControlPressed = true;
        }
        if(keyCode == 'D' && isControlPressed){
            for (int i = shapes.size() - 1; i >= 0; i--){
                Shape e = shapes.get(i);
                if(e.isCollision(mouseX, mouseY)){
                    shapes.add(shapes.get(i).clone());
                    shapes.get(shapes.size()-1).setX(shapes.get(shapes.size()-1).getX() + 10);
                    shapes.get(shapes.size()-1).setY(shapes.get(shapes.size()-1).getY() + 10);
                    return;
                }
            }
        }
        if(keyCode == 'S' && isControlPressed){
            saveFile();
        }
        if(keyCode == 'O' && isControlPressed){
            loadFile();
        }
    }


    @Override
    public void mouseClicked() {
        if(!keyPressed){
            return;
        }
        if(key == '1'){
            shapes.add(new Rect(mouseX, mouseY, new Color(255,0,0), Constant.RECT_TYPE));
        }else if(key == '2'){
            shapes.add(new Circle(mouseX, mouseY, new Color(0,255,0), Constant.CIRCLE_TYPE));
        }else if(key == '3'){
            shapes.add(new Triangle(mouseX, mouseY, new Color(0,0,255), Constant.TRIANGLE_TYPE));
        }
    }

    @Override
    public void mousePressed() {
        isMousePressed = true;
        if(!keyPressed){
            for (int i = shapes.size() - 1; i >= 0; i--){
                Shape e = shapes.get(i);
                if(e.isCollision(mouseX, mouseY)){
                    shapes.get(i).selectShape(mouseX, mouseY);
                    return;
                }
            }
        }
    }

    @Override
    public void mouseReleased() {
        isMousePressed = false;
        for (int i = shapes.size() - 1; i >= 0; i--){
            Shape e = shapes.get(i);
            if(e.isSelected){
                shapes.get(i).unSelect();
                return;
            }
        }
    }

    private void loadFile() {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            BufferedInputStream bis = new BufferedInputStream(fis);

            int ch;
            StringBuilder data = new StringBuilder();

            while ((ch = bis.read()) != -1) {
                data.append((char)ch);
            }
            String dataToString = data.toString();
            String dataArray[] = dataToString.split(" ");

            shapes.clear();
            for(int i = 0; i*6 < dataArray.length; i++) {
                if (dataArray[i*6].equals("1")) {
                    shapes.add(new Rect(parseInt(dataArray[i*6+1]), parseInt(dataArray[i*6+2]), new Color(parseInt(dataArray[i*6+3]), parseInt(dataArray[i*6+4]), parseInt(dataArray[i*6+5])), 1));
                } else if (dataArray[i*6].equals("2")) {
                    shapes.add(new Circle(parseInt(dataArray[i*6+1]), parseInt(dataArray[i*6+2]), new Color(parseInt(dataArray[i*6+3]), parseInt(dataArray[i*6+4]), parseInt(dataArray[i*6+5])), 2));
                } else if (dataArray[i*6].equals("3")) {
                    shapes.add(new Triangle(parseInt(dataArray[i*6+1]), parseInt(dataArray[i*6+2]), new Color(parseInt(dataArray[i*6+3]), parseInt(dataArray[i*6+4]), parseInt(dataArray[i*6+5])), 3));
                }
            }
            bis.close();
            fis.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void saveFile(){
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            StringBuilder data = new StringBuilder();
            for (int i = shapes.size() - 1; i >= 0; i--){
                Shape e = shapes.get(i);
                data.append(e.shapeType);
                data.append(" ");
                data.append(e.getX());
                data.append(" ");
                data.append(e.getY());
                data.append(" ");
                data.append(e.color.r);
                data.append(" ");
                data.append(e.color.g);
                data.append(" ");
                data.append(e.color.b);
                data.append(" ");
            }
            for (int i = 0; i < data.length(); i++){
                bos.write(data.charAt(i));
            }
            bos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
