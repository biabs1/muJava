package br.ufal.ic.easy.hunor;

import openjava.ptree.BinaryExpression;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Rules {

    private static boolean enabled = false;
    private static List<String> traditionalOperatorsEnabled = Collections.emptyList();

    private static List<Integer> arithmeticOperators = Arrays.asList(BinaryExpression.PLUS, BinaryExpression.MINUS,
            BinaryExpression.TIMES, BinaryExpression.DIVIDE, BinaryExpression.MOD);


    public static boolean aorRules(int originalOp, int mutantOp) {

        if (!enabled) return true;

        final List<Integer> timesAllowed = Arrays.asList(BinaryExpression.TIMES, BinaryExpression.TIMES);

        HashMap<Integer, List<Integer>> rules = new HashMap<>();

        rules.put(BinaryExpression.PLUS, Collections.singletonList(BinaryExpression.TIMES));
        rules.put(BinaryExpression.MINUS, Collections.singletonList(BinaryExpression.MOD));
        rules.put(BinaryExpression.TIMES, Collections.singletonList(BinaryExpression.DIVIDE));
        rules.put(BinaryExpression.DIVIDE, Collections.singletonList(BinaryExpression.TIMES));
        rules.put(BinaryExpression.MOD, Collections.singletonList(BinaryExpression.PLUS));


        return rules.get(originalOp).contains(mutantOp);
    }

    public static boolean cdlRules(BinaryExpression exp) {

        if (!enabled) return true;

        return operatorDisabled("ODL") || !arithmeticOperators.contains(exp.getOperator());
    }

    public static boolean vdlRules(BinaryExpression exp) {

        if (!enabled) return true;

        return operatorDisabled("ODL") || !arithmeticOperators.contains(exp.getOperator());
    }

    public static boolean operatorDisabled(String operator) {
        return !traditionalOperatorsEnabled.contains(operator);
    }

    public static void setTraditionalOperatorsEnabled(String[] operators) {
        traditionalOperatorsEnabled = Arrays.asList(operators);
    }

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = true;
    }

}
