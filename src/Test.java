public class Test {
    Problem p;
    int[] inputs;
    int[] outputs;

    public Test(Problem p, int[] inputs, int[] outputs) {
        this.p = p;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public long accuracy(Runtime runtime){
        long diff = 0;

        for (int i = 0; i < outputs.length; i++) {
            if(runtime.getMemory().getOutputs().length <= i) {
                diff += Math.abs(outputs[i]);
            }else
                diff += Math.abs(outputs[i] - runtime.getMemory().getOutputs()[i + 1]);
        }

        return diff;
    }

}
