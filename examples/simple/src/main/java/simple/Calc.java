package simple;

public class Calc {

    private static int NUMBER = 0;
    private int field = 3;

    public int plus(int var1, int var2) {
        int arr[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        var1++;
        ++var1;
        var1--;
        --var1;

        var2 = var2 + -var1;

        int a = var1 * 2 + (var2 / var1) + arr[9];
        int b = 0;
        int c = 2 + 3 * (7) + NUMBER;

        b += NUMBER;

        a = ~a;
        a = -a;

        var1 += a + b / c / this.field;

        var1 >>= ~8;

        while(true)
            if (a > 1)
                break;
            else
                a += arr[1];

        var1 = var1 << 3;

        var1 = var1 & 1;
        var2 = var2 | var1;
        var2 = var2 ^ var1;

        boolean truth = var2 > 10;

        if (!(var1 == 0) || ~var2 != -1 && a == (a & b) || b == (a ^ b) && !truth) {
            return NUMBER;
        }

        return var1 + var2 + safeAdd(0, NUMBER);
    }

    public static int safeAdd(int val1, int val2) {
        int sum = val1 + val2;
        // If there is a sign change, but the two values have the same sign...
        if ((val1 ^ sum) < 0 && (val1 ^ val2) >= 0) {
            throw new ArithmeticException
                    ("The calculation caused an overflow: " + val1 + " + " + val2);
        }
        return sum;
    }

    public static long safeMultiply(long val1, long val2) {
        if (val2 == 1) {
            return val1;
        }
        if (val1 == 1) {
            return val2;
        }
        if (val1 == 0 || val2 == 0) {
            return 0;
        }
        long total = val1 * val2;
        if (total / val2 != val1 || val1 == Long.MIN_VALUE && val2 == -1 || val2 == Long.MIN_VALUE && val1 == -1) {
            throw new ArithmeticException("Multiplication overflows a long: " + val1 + " * " + val2);
        }
        return total;
    }

    public static int getWrappedValue(int value, int minValue, int maxValue) {
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("MIN > MAX");
        }

        int wrapRange = maxValue - minValue + 1;
        value -= minValue;

        if (value >= 0) {
            return (value % wrapRange) + minValue;
        }

        int remByRange = (-value) % wrapRange;

        if (remByRange == 0) {
            return 0 + minValue;
        }
        return (wrapRange - remByRange) + minValue;
    }

}