public class FunctionInstance {

    private Function function;
    private MemoryLocation params[];

    private FunctionInstance(Function function) {
        params = new MemoryLocation[function.numberOfParams];
        this.function = function;
    }

    private void randomlyPopulateParams(DNA dna, Problem p){
        WorkingMemory temp = new WorkingMemory(p, dna);
        for(int i = 0; i < params.length; i++){
            params[i] = MemoryLocation.randomMemoryLocation(temp);
        }
    }

    @Override
    public String toString() {
        StringBuilder params = new StringBuilder();
        for (int i = 0; i < this.params.length; i++) {
            params.append(" " + this.params[i].toString());
        }
        return (this.function.name + " " + params.toString());
    }

    public static FunctionInstance getReadyRandomFunction(DNA dna, Problem p){
        FunctionInstance f = new FunctionInstance(Function.getRandomFunction());
        f.randomlyPopulateParams(dna, p);
        return f;
    }

    public void runFunction(Runtime runtime){
        this.function.doFunction(runtime, this);
    }

    public FunctionInstance mutateParameters(DNA dna, Problem p, float magnitude){
        WorkingMemory temp = new WorkingMemory(p, dna);
        FunctionInstance functionInstance = new FunctionInstance(this.function);
        for(int i = 0; i < params.length; i++){
            functionInstance.params[i] = MemoryLocation.mutateLocation(params[i], magnitude, temp);
        }
        return functionInstance;
    }

    public FunctionInstance clone(){

        FunctionInstance functionInstance = new FunctionInstance(this.function);
        for (int i = 0; i < functionInstance.params.length; i++) {
            functionInstance.params[i] = this.params[i];
        }
        return functionInstance;
    }

    public MemoryLocation getParams(int i) {
        return params[i];
    }
}