public class Runtime {
    DNA dna;
    Problem p;
    private int programCounter = 0;
    private long programLenght;
    private WorkingMemory memory;

    public Runtime(DNA dna, Problem p, int[] input){
        this.dna = dna;
        memory = new WorkingMemory(p, dna);
        for (int i = 0; i < input.length; i++) {
            memory.setInput(i + 1, input[i]);
        }
    }

    public WorkingMemory getMemory(){
        return memory;
    }

    public void runToCompletion(){
        while(programCounter < dna.instructions.length && programLenght < 4 * dna.instructions.length)
            doCycle();
    }

    public void doCycle(){
        dna.instructions[programCounter].runFunction(this);
        programCounter ++;
        programLenght ++;
    }

    public int getProgramCounter(){
        return programCounter;
    }

    public void setProgramCounter(int pc){
        if(pc < 0)
            pc = 0;
        if(pc > dna.instructions.length)
            pc = dna.instructions.length;
        programCounter = pc;
    }
}
