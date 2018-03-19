import java.util.Arrays;

public class DNA {

    FunctionInstance[] instructions;
    Problem p;

    public DNA(int complexity, Problem p){
        instructions = new FunctionInstance[complexity];
        this.p = p;
    }

    public void randomInstructions(){

        for (int i = 0; i < instructions.length; i++) {
            instructions[i] = FunctionInstance.getReadyRandomFunction(this, this.p);
        }
    }

    public DNA mutate(float magnitude){
        if(magnitude == 1) {
            DNA dna = new DNA(this.instructions.length, this.p);
            dna.randomInstructions();
            return dna;
        }
        float dnaChange = (1F-magnitude) * 0.1F;
        int lengthChange = 0;
        if(dnaChange > Math.random())
            lengthChange = Math.random() < .5F ? 1 : -1;
        if(this.instructions.length + lengthChange < 2)
            lengthChange = 0;
        lengthChange = this.instructions.length + lengthChange;
        DNA dna = new DNA(lengthChange, p);
        for (int i = 0; i < dna.instructions.length; i++) {
            if((1 - magnitude) * Math.random() < .1F) {
                dna.instructions[i] = FunctionInstance.getReadyRandomFunction(this, this.p);
            }else {
                if(i >= this.instructions.length)
                    dna.instructions[i] = FunctionInstance.getReadyRandomFunction(this, this.p);
                else
                    dna.instructions[i] = this.instructions[i].mutateParameters(this, p, magnitude);
            }
        }
        return dna;
    }

    public int getTemps(){
        return Math.max(instructions.length / 2, 1);
    }

    public String toString(){
        return Arrays.toString(instructions);
    }

}
