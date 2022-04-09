package com.javarush.task.task37.task3710.decorators;

import com.javarush.task.task37.task3710.shapes.Shape;

public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    private void setBorderColor(Shape shape){
        System.out.printf("Setting the border color for %s to red.",shape.getClass().getSimpleName());
        System.out.println();
    }

    @Override
    public void draw() {
        setBorderColor(decoratedShape);
        super.draw();
    }
}
