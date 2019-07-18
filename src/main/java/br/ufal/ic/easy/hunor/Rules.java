package br.ufal.ic.easy.hunor;

import mujava.op.basic.Arithmetic_OP;
import mujava.op.util.ComposedStatementVisitor;
import openjava.mop.FileEnvironment;
import openjava.ptree.*;


import java.io.File;
import java.util.*;
import java.util.List;

public class Rules {

    public enum Mutation {
        AORB_PLUS, AORB_MINUS, AORB_TIMES, AORB_DIVIDE, AORB_MOD,
        CDL_LEXP, CDL_REXP,
        VDL_LEXP, VDL_REXP,
        ODL_LEXP, ODL_REXP,
        AOIU_MINUS,
        AOIS_PREINC, AOIS_PREDEC, AOIS_POSINC, AOIS_POSDEC,
        LOI_BITNOT
    }

    private static boolean enabled = true;
    private static List<String> traditionalOperatorsEnabled = Collections.emptyList();
    private static File currentFile;
    private static CompilationUnit compilationUnit;
    private static FileEnvironment fileEnvironment;
    private static Arithmetic_OP mutator;
    private static Map<File, Map<ParseTree, Set<Mutation>>> fileTargets = new HashMap<>();

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

    public static boolean operatorEnabled(String operador) {
        return !operatorDisabled(operador);
    }

    public static void setTraditionalOperatorsEnabled(String[] operators) {
        traditionalOperatorsEnabled = Arrays.asList(operators);
    }

    public static boolean canApply(ParseTree target, Mutation mutation, Arithmetic_OP m) {
        if (!enabled) return true;

        mutator = m;
        Optional<Set<Mutation>> allowedMutations = getAllowedMutations(target);

        return allowedMutations.map(mutations -> mutations.contains(mutation)).orElse(true);
    }

    private static Optional<Set<Mutation>> getAllowedMutations(ParseTree target) {
        Map<ParseTree, Set<Mutation>> currentFileTargets = getCurrentTargets();

        if (!currentFileTargets.keySet().contains(target)) {
            Optional<Set<Mutation>> allowedMutations = allowedMutationsFor(target);

            if (allowedMutations.isPresent()) {
                currentFileTargets.put(target, allowedMutations.get());
            } else {
                return Optional.empty();
            }
        }

        return Optional.of(currentFileTargets.get(target));
    }

    private static Map<ParseTree, Set<Mutation>> getCurrentTargets() {
        if (!fileTargets.keySet().contains(currentFile)) {
            fileTargets.put(currentFile, new HashMap<>());
        }

        return fileTargets.get(currentFile);
    }

    private static Optional<Set<Mutation>> allowedMutationsFor(ParseTree target) {
        if (target instanceof BinaryExpression) {
            return allowedMutationsFor((BinaryExpression) target);
        } else if (target instanceof UnaryExpression) {
            return allowedMutationsFor((UnaryExpression) target);
        } else if (target instanceof Variable || target instanceof FieldAccess) {
            return allowedMutationsFor((Expression) target);
        }


        return Optional.empty();
    }

    private static Optional<Set<Mutation>> allowedMutationsFor(BinaryExpression target) {
        int operator = target.getOperator();

        if (operator == BinaryExpression.PLUS) {
            return Optional.of(targetLExpPlusRExp(target));
        }

        return Optional.empty();
    }

    private static Optional<Set<Mutation>> allowedMutationsFor(UnaryExpression target) {
        return Optional.empty();
    }

    private static Optional<Set<Mutation>> allowedMutationsFor(Expression target) {

        if (hasArithmeticType(target)) {
            return Optional.of(targetArithmeticExp());
        }

        return Optional.empty();
    }

    private static Set<Mutation> targetArithmeticExp() {
        Set<Mutation> mutations = new HashSet<>();

        mutations.add(Mutation.AOIU_MINUS);

        return mutations;
    }

    private static Set<Mutation> targetLExpPlusRExp(BinaryExpression target) {
        Set<Mutation> mutations = new HashSet<>();

        mutations.add(Mutation.AORB_MOD);

        if (operatorEnabled("ODL")) {
            mutations.add(Mutation.ODL_LEXP);
            mutations.add(Mutation.ODL_REXP);
        } else {
            if (operatorEnabled("VDL")) {
                mutations.add(Mutation.VDL_LEXP);
                mutations.add(Mutation.VDL_REXP);
            } else {
                mutations.add(Mutation.AORB_MINUS);
            }
        }

        setMutationsTo(target.getLeft(), new HashSet<>());
        setMutationsTo(target.getRight(), new HashSet<>());

      return mutations;
    }

    private static void setMutationsTo(Expression expression, Set<Mutation> mutations) {
        getCurrentTargets().put(expression, mutations);
        if (expression instanceof UnaryExpression) {
            expression = ((UnaryExpression) expression).getExpression();
            getCurrentTargets().put(expression, mutations);
        }
    }


    public static List<ParseTree> getChildren(ParseTree p) {
        try {
            ComposedStatementVisitor visitor = new ComposedStatementVisitor();
            p.accept(visitor);

            return visitor.getChildren();
        } catch (ParseTreeException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static void setCurrentFile(File file) {
        currentFile = file;
    }

    public static void setCompilationUnit(CompilationUnit compUnit) {
        compilationUnit = compUnit;
    }

    public static void setFileEnvironment(FileEnvironment fileEnv) {
        fileEnvironment = fileEnv;
    }

    private static boolean hasArithmeticType(Expression expression) {
        try {
            return mutator.isArithmeticType(expression);
        } catch(ParseTreeException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

}
