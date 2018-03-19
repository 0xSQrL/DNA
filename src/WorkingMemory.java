public class WorkingMemory {

    private int[] inputs;
    private int[] outputs;
    private int[] temp;

    public WorkingMemory(Problem problem, DNA dna) {
        inputs = new int[problem.inputs + 1];
        inputs[0] = inputs.length - 1;
        outputs = new int[problem.outputs + 1];
        outputs[0] = outputs.length - 1;
        temp = new int[dna.getTemps() + 1];
        temp[0] = temp.length - 1;
    }

    public void setMemory(MemoryLocation l, int val) {

        if(l.getType() != MemoryLocation.MemoryType.CONSTANT && l.getLocation() != 0) {
            int[] arr = getPointedArray(l);
            if (arr != null) {
                System.out.println(arr.length + " " + l.getLocation() + " " + l.getType());
                arr[l.getLocation()] = val;
            }
        }
    }

    public int getMemory(MemoryLocation l) {

        if(l.getType() == MemoryLocation.MemoryType.CONSTANT)
            return l.getLocation();

        int[] arr = getPointedArray(l);
        if ( arr != null) {
            System.out.println(arr.length + " " + l.getLocation() + " " + l.getType());
            return arr[l.getLocation()];
        }
        return 0;
    }


    private int[] getPointedArray(MemoryLocation type){
        switch (type.getType()) {
            case INPUT:
                return inputs;
            case OUTPUT:
                return outputs;
            case TEMP:
                return temp;
        }
        return null;
    }

    public int getInputCount(){
        return inputs.length;
    }
    public int getOutputCount(){
        return outputs.length;
    }
    public int getTempsCount(){
        return temp.length;
    }

    public int[] getOutputs(){
        return outputs;
    }

    public void setInput(int i, int val){
        inputs[i] = val;
    }
}
