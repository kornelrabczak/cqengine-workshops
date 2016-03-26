package com.thecookiezen.cqengine.workshop.entity;

import org.kohsuke.randname.RandomNameGenerator;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Car implements Serializable {

    private static RandomNameGenerator randomNameGenerator = new RandomNameGenerator();

    private final long id;
    private final String model;
    private final String description;
    private final Color color;

    public Car(long id, String model, String description, Color color) {
        this.id = id;
        this.model = model;
        this.description = description;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getDescription() {
        return description;
    }

    public Color getColor() {
        return color;
    }

    public enum Color {
        BLACK, WHITE, RED, GREEN, YELLOW, PINK;

        private static final List<Color> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Color randomColor()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    public static Car generate() {
        return new Car(new Random().nextLong(), randomNameGenerator.next(), randomNameGenerator.next(), Color.randomColor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (description != null ? !description.equals(car.description) : car.description != null) return false;
        return color == car.color;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
