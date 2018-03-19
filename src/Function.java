public abstract class Function {

    int numberOfParams;
    String name;
    private Function(String name, int numberOfParams) {
        this.numberOfParams = numberOfParams;
        this.name = name;
    }

    public abstract void doFunction(Runtime runtime, FunctionInstance i);

    private static Function register[] = new Function[]{
            //Move
            new Function("Move", 2) {
                @Override
                public void doFunction(Runtime runtime, FunctionInstance i) {
                    runtime.getMemory().setMemory(i.getParams(1), runtime.getMemory().getMemory(i.getParams(0)));
                }
            },
            //Add
            new Function("Add", 3) {
                @Override
                public void doFunction(Runtime runtime, FunctionInstance i) {
                    runtime.getMemory().setMemory(i.getParams(2), runtime.getMemory().getMemory(i.getParams(0)) + runtime.getMemory().getMemory(i.getParams(1)));
                }
            },
            //Subtract
            new Function("Subtract", 3) {
                @Override
                public void doFunction(Runtime runtime, FunctionInstance i) {
                    runtime.getMemory().setMemory(i.getParams(2), runtime.getMemory().getMemory(i.getParams(0)) - runtime.getMemory().getMemory(i.getParams(1)));
                }
            },
            //Multiply
            new Function("Multiply", 3) {
                @Override
                public void doFunction(Runtime runtime, FunctionInstance i) {
                    runtime.getMemory().setMemory(i.getParams(2), runtime.getMemory().getMemory(i.getParams(0)) * runtime.getMemory().getMemory(i.getParams(1)));
                }
            },
            //Divide
            new Function("Divide", 3) {
                @Override
                public void doFunction(Runtime runtime, FunctionInstance i) {
                    try {
                        runtime.getMemory().setMemory(i.getParams(2), runtime.getMemory().getMemory(i.getParams(0)) / runtime.getMemory().getMemory(i.getParams(1)));
                    } catch (ArithmeticException e) {

                    }
                }
            },
            //Modulus
            new Function("Modulus", 3) {
                @Override
                public void doFunction(Runtime runtime, FunctionInstance i) {
                    try {
                        runtime.getMemory().setMemory(i.getParams(2), runtime.getMemory().getMemory(i.getParams(0)) % runtime.getMemory().getMemory(i.getParams(1)));
                    } catch (ArithmeticException e) {

                    }
                }
            },
            //Jump forward if less
            new Function("JLT", 3) {
                @Override
                public void doFunction(Runtime runtime, FunctionInstance i) {
                    if(runtime.getMemory().getMemory(i.getParams(0)) < runtime.getMemory().getMemory(i.getParams(1)))
                        runtime.setProgramCounter(runtime.getProgramCounter() + runtime.getMemory().getMemory(i.getParams(0)));
                }
            },
            //Jump forward if less
            new Function("SQRT", 2) {
                @Override
                public void doFunction(Runtime runtime, FunctionInstance i) {
                    runtime.getMemory().setMemory(i.getParams(1), (int)Math.sqrt(runtime.getMemory().getMemory(i.getParams(0))));
                }
            },
            //Jump forward if less
            new Function("LOG", 2) {
                @Override
                public void doFunction(Runtime runtime, FunctionInstance i) {
                    runtime.getMemory().setMemory(i.getParams(1), (int)Math.log(runtime.getMemory().getMemory(i.getParams(0))));
                }
            }
//            //Modulus
//            new Function("Power", 3) {
//                @Override
//                public void doFunction(Runtime runtime, FunctionInstance i) {
//                    try {
//                        runtime.getMemory().setMemory(i.getParams(2), (int)Math.pow(runtime.getMemory().getMemory(i.getParams(0)),runtime.getMemory().getMemory(i.getParams(1))));
//                    } catch (ArithmeticException e) {
//
//                    }
//                }
//            },

    };

    public static Function getRandomFunction(){
        return register[(int)(Math.random() * register.length)];
    }

}
