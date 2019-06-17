package mujava.op.util;

import openjava.ptree.*;


public class ChildrenVisitor extends ComposedStatementVisitor {

     public void visit(AllocationExpression var1) throws ParseTreeException {

    }

    public void visit(ArrayAccess var1) throws ParseTreeException {
        super.visit(var1);
    }

    public void visit(ArrayAllocationExpression var1) throws ParseTreeException {

    }

    public void visit(ArrayInitializer var1) throws ParseTreeException {

    }

    public void visit(AssignmentExpression var1) throws ParseTreeException {
        super.visit(var1);
    }

    public void visit(BinaryExpression var1) throws ParseTreeException {
        super.visit(var1);
    }

    public void visit(Block var1) throws ParseTreeException {

    }

    public void visit(BreakStatement var1) throws ParseTreeException {

    }

    public void visit(CaseGroup var1) throws ParseTreeException {

    }

    public void visit(CaseGroupList var1) throws ParseTreeException {

    }

    public void visit(CaseLabel var1) throws ParseTreeException {

    }

    public void visit(CaseLabelList var1) throws ParseTreeException {

    }

    public void visit(CastExpression var1) throws ParseTreeException {

    }

    public void visit(CatchBlock var1) throws ParseTreeException {
         
    }

    public void visit(CatchList var1) throws ParseTreeException {
         
    }

    public void visit(ClassDeclaration var1) throws ParseTreeException {
         
    }

    public void visit(ClassDeclarationList var1) throws ParseTreeException {
         
    }

    public void visit(ClassLiteral var1) throws ParseTreeException {
         
    }

    public void visit(CompilationUnit var1) throws ParseTreeException {
         
    }

    public void visit(ConditionalExpression var1) throws ParseTreeException {
         
    }

    public void visit(ConstructorDeclaration var1) throws ParseTreeException {
         
    }

    public void visit(ConstructorInvocation var1) throws ParseTreeException {
         
    }

    public void visit(ContinueStatement var1) throws ParseTreeException {
         
    }

    public void visit(DoWhileStatement var1) throws ParseTreeException {
         
    }

    public void visit(EmptyStatement var1) throws ParseTreeException {
         
    }

    public void visit(ExpressionList var1) throws ParseTreeException {
         
    }

    public void visit(ExpressionStatement var1) throws ParseTreeException {
         
    }

    public void visit(FieldAccess var1) throws ParseTreeException {
        this.addChild(var1);
    }

    public void visit(FieldDeclaration var1) throws ParseTreeException {
         
    }

    public void visit(ForStatement var1) throws ParseTreeException {
         
    }

    public void visit(IfStatement var1) throws ParseTreeException {
         
    }

    public void visit(InstanceofExpression var1) throws ParseTreeException {
         
    }

    public void visit(LabeledStatement var1) throws ParseTreeException {
         
    }

    public void visit(Literal var1) throws ParseTreeException {
        this.addChild(var1);
    }

    public void visit(MemberDeclarationList var1) throws ParseTreeException {
         
    }

    public void visit(MemberInitializer var1) throws ParseTreeException {
         
    }

    public void visit(MethodCall var1) throws ParseTreeException {
        this.addChild(var1);
    }

    public void visit(MethodDeclaration var1) throws ParseTreeException {
         
    }

    public void visit(ModifierList var1) throws ParseTreeException {
         
    }

    public void visit(Parameter var1) throws ParseTreeException {
         
    }

    public void visit(ParameterList var1) throws ParseTreeException {
         
    }

    public void visit(ReturnStatement var1) throws ParseTreeException {
         
    }

    public void visit(SelfAccess var1) throws ParseTreeException {
         
    }

    public void visit(StatementList var1) throws ParseTreeException {
         
    }

    public void visit(SwitchStatement var1) throws ParseTreeException {
         
    }

    public void visit(SynchronizedStatement var1) throws ParseTreeException {
         
    }

    public void visit(ThrowStatement var1) throws ParseTreeException {
         
    }

    public void visit(TryStatement var1) throws ParseTreeException {
         
    }

    public void visit(TypeName var1) throws ParseTreeException {
         
    }

    public void visit(UnaryExpression var1) throws ParseTreeException {
        this.addChild(var1);
    }

    public void visit(Variable var1) throws ParseTreeException {
         this.addChild(var1);
    }

    public void visit(VariableDeclaration var1) throws ParseTreeException {
         
    }

    public void visit(VariableDeclarator var1) throws ParseTreeException {
         
    }

    public void visit(WhileStatement var1) throws ParseTreeException {
         
    }

    public void visit(EnumDeclaration var1) throws ParseTreeException {
         
    }

    public void visit(EnumConstant var1) throws ParseTreeException {
         
    }

    public void visit(EnumConstantList var1) throws ParseTreeException {
         
    }

    public void visit(TypeParameter var1) throws ParseTreeException {
         
    }

    public void visit(TypeParameterList var1) throws ParseTreeException {
         
    }

    public void visit(AssertStatement var1) throws ParseTreeException {
         
    }

}
