package br.ufal.ic.easy.hunor;

import openjava.ptree.ParseTree;

public class Target {

    private Boolean arithmetic;
    private ParseTree target;

    public Target(ParseTree target, Boolean isArithmetic) {
        this.target = target;
        this.arithmetic = isArithmetic;
    }

    public boolean isArithmetic() {
        if (arithmetic == null)
            return false;

        return arithmetic;
    }

    public void setArithmetic(Boolean isArithmetic) {
        arithmetic = isArithmetic;
    }

    public ParseTree getTarget() {
        return target;
    }

    public void setTarget(ParseTree target) {
        this.target = target;
    }
}
