public class MemoryLocation {
    public enum MemoryType{
        INPUT, OUTPUT, TEMP, CONSTANT
    }
    private MemoryType type;
    private int location;

    public String toString(){
        return this.type.name() + " " + location;
    }

    public MemoryLocation(MemoryType type, int location) {
        this.type = type;
        this.location = location;
    }

    public MemoryType getType() {
        return type;
    }

    public int getLocation() {
        return location;
    }

    public static MemoryLocation randomMemoryLocation(WorkingMemory memory){
        int chance = (int)(Math.random() * 100);
        if(chance <= 40)
            return randomInput(memory.getInputCount());
        else if(chance <= 80)
            return randomOutput(memory.getOutputCount());
        else if(chance <= 90)
            return randomTemp(memory.getTempsCount());
        else
            return randomConstant();
    }

    public static MemoryLocation randomInput(int inputs) {
        return new MemoryLocation(MemoryLocation.MemoryType.INPUT, (int) (Math.random() * inputs));
    }

    public static MemoryLocation randomConstant() {
        return new MemoryLocation(MemoryLocation.MemoryType.CONSTANT, (int)(20 * Math.random() - 10));
    }

    public static MemoryLocation randomOutput(int outputs) {
        return new MemoryLocation(MemoryLocation.MemoryType.OUTPUT, (int) (Math.random() * outputs));
    }

    public static MemoryLocation randomTemp(int temps) {
        return new MemoryLocation(MemoryLocation.MemoryType.TEMP, (int) (Math.random() * temps));
    }

    public static MemoryLocation mutateLocation(MemoryLocation l, float magnitude, WorkingMemory mem){

        MemoryLocation.MemoryType t = l.getType();
        if((1 - magnitude) * Math.random() < .1F)
            return randomMemoryLocation(mem);

        if(t == MemoryLocation.MemoryType.CONSTANT){
            int variance = (int)(((long)10 * Math.random() - 5) * magnitude);
            return new MemoryLocation(t,l.getLocation() + variance);
        }

        return new MemoryLocation(t, mutateArrayLocation(l.getLocation(), getArrayLength(l, mem), magnitude));

    }


    private static int mutateArrayLocation(int index, int arrayMax, float magnitude){
        magnitude = arrayMax * magnitude;
        magnitude = (int)(Math.random() * magnitude * 2) -  magnitude;
        index += magnitude;
        if (index >= arrayMax)
            index = arrayMax - 1;
        else if (index < 0)
            index = 0;
        return index;
    }
    public static int getArrayLength(MemoryLocation type, WorkingMemory memory){
        switch (type.getType()) {
            case INPUT:
                return memory.getInputCount();
            case OUTPUT:
                return memory.getOutputCount();
            case TEMP:
                return memory.getTempsCount();
        }
        return 0;
    }

}
