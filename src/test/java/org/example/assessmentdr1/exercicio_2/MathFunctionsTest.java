package org.example.assessmentdr1.exercicio_2;

import exercicio_2.MathFunctions;
import exercicio_2.MathLogger;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.Arrays;

import static net.jqwik.api.Assume.that;

public class MathFunctionsTest {
    MathLogger mockLogger = (operation, inputs) -> {};
    MathFunctions math = new MathFunctions(mockLogger);

    @Property
    boolean multiplyByTwoShouldReturnEven(@ForAll int n) {
        int result = math.MultiplyByTwo(n);
        return result % 2 == 0;
    }

    @Property
    boolean multiplicationTableShouldContainOnlyMultiples(
            @ForAll int number,
            @ForAll @IntRange(min = 1, max = 100) int limit
    ) {
        that(number != 0);
        that(number * (long) limit < Integer.MAX_VALUE);
        that(number * (long) limit > Integer.MIN_VALUE);
        int[] table = math.GenerateMultiplicationTable(number, limit);
        return table.length == limit && Arrays.stream(table).allMatch(val -> val % number == 0);
    }


    @Property
    boolean isPrimeShouldHaveNoDivisors(@ForAll("primes") int prime) {
        that(prime > 1); // correta agora
        return math.IsPrime(prime);
    }

    @Provide
    Arbitrary<Integer> primes() {
        return Arbitraries.integers().between(2, 1000).filter(this::isPrime);
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    @Property
    boolean averageShouldBeBetweenMinMax(@ForAll("nonEmptyIntArrays") int[] values) {
        double avg = math.CalculateAverage(values);
        int min = java.util.Arrays.stream(values).min().orElseThrow();
        int max = java.util.Arrays.stream(values).max().orElseThrow();
        return avg >= min && avg <= max;
    }

    @Provide
    Arbitrary<int[]> nonEmptyIntArrays() {
        return Arbitraries.integers().between(-1000, 1000).array(int[].class).ofMinSize(1).ofMaxSize(100);
    }
}
