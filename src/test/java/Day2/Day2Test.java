package Day2;

import lombok.val;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class Day2Test {
    @Test
    @DisplayName("Safe because the levels are all decreasing by 1 or 2.")
    public void one() {
        val result = new Day2.Row("7 6 4 2 1");
        assertThat(result.safety, is(Day2.Row.Safety.SAFE));
    }

    @Test
    @DisplayName("Unsafe because 2 7 is an increase of 5.")
    public void two() {
        val result = new Day2.Row("1 2 7 8 9");
        assertThat(result.safety, is(Day2.Row.Safety.UNSAFE));
    }

    @Test
    @DisplayName("Unsafe because 6 2 is a decrease of 4.")
    public void three() {
        val result = new Day2.Row("9 7 6 2 1");
        assertThat(result.safety, is(Day2.Row.Safety.UNSAFE));
    }

    @Test
    @DisplayName("Unsafe because 1 3 is increasing but 3 2 is decreasing.")
    public void four() {
        val result = new Day2.Row("1 3 2 4 5");
        assertThat(result.safety, is(Day2.Row.Safety.UNSAFE));
    }

    @Test
    @DisplayName("Unsafe because 4 4 is neither an increase or a decrease.")
    public void five() {
        val result = new Day2.Row("8 6 4 4 1");
        assertThat(result.safety, is(Day2.Row.Safety.UNSAFE));
    }

    @Test
    @DisplayName("Safe because the levels are all increasing by 1, 2, or 3.")
    public void six() {
        val result = new Day2.Row("1 3 6 7 9");
        assertThat(result.safety, is(Day2.Row.Safety.SAFE));
    }

    @Test
    @DisplayName("Safe without removing any level.")
    public void seven() {
        val result = new Day2.Row("7 6 4 2 1");
        assertThat(result.problemDampener, is(Day2.Row.Safety.SAFE));
        assertThat(result.numbers, is(List.of(7, 6, 4, 2, 1)));
    }

    @Test
    @DisplayName("Unsafe regardless of which level is removed.")
    public void eight() {
        val result = new Day2.Row("1 2 7 8 9");
        assertThat(result.problemDampener, is(Day2.Row.Safety.UNSAFE));
    }

    @Test
    @DisplayName("Unsafe regardless of which level is removed.")
    public void nine() {
        val result = new Day2.Row("9 7 6 2 1");
        assertThat(result.problemDampener, is(Day2.Row.Safety.UNSAFE));
    }

    @Test
    @DisplayName("Safe by removing the second level, 3.")
    public void ten() {
        val result = new Day2.Row("1 3 2 4 5");
        assertThat(result.problemDampener, is(Day2.Row.Safety.SAFE));
        assertThat(result.numbers, is(List.of(1, 2, 4, 5)));
    }

    @Test
    @DisplayName("Safe by removing the third level, 4.")
    public void eleven() {
        val result = new Day2.Row("8 6 4 4 1");
        assertThat(result.problemDampener, is(Day2.Row.Safety.SAFE));
        assertThat(result.numbers, is(List.of(8, 6, 4, 1)));
    }

    @Test
    @DisplayName("Safe without removing any level.")
    public void twelve() {
        val result = new Day2.Row("1 3 6 7 9");
        assertThat(result.problemDampener, is(Day2.Row.Safety.SAFE));
    }
}
