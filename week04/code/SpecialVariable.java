class Variable {
    public int value;
    Variable(int value) {
        this.value = value;
    }
    int getValue() {
        return value;
    }
    void setValue(int value) {
        this.value = value;
    }
}

class SpecialVariable extends Variable {
    public int value;
    SpecialVariable(int value) {
        super(value);
        this.value = value;
    }

    public static void main(String[] args) {
        Variable x = new SpecialVariable(1);
        SpecialVariable y = new SpecialVariable(1);

        x.value = 3;
        y.value = 3;
        System.out.println("x.value=: " + x.getValue());
        System.out.println("y.value=: " + y.getValue());

        x.setValue(4);
        y.setValue(4);
        System.out.println("x.setValue: " + x.getValue());
        System.out.println("y.setValue: " + y.getValue());
    }
}
