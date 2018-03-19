import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        Problem add = new Problem(5, 1);
        Test trainingData[] = new Test[]{
                new Test(add,  new int[]{0},       new int[]{0}),
                new Test(add,  new int[]{1},       new int[]{1}),
                new Test(add,  new int[]{2},       new int[]{1}),
                new Test(add,  new int[]{3},       new int[]{2}),
                new Test(add,  new int[]{4},       new int[]{3}),
                new Test(add,  new int[]{5},       new int[]{5}),
                new Test(add,  new int[]{7},       new int[]{13}),
                new Test(add,  new int[]{8},       new int[]{21}),
                new Test(add,  new int[]{9},       new int[]{34}),
                new Test(add,  new int[]{10},       new int[]{55}),
                new Test(add,  new int[]{11},       new int[]{89}),
                new Test(add,  new int[]{12},       new int[]{144}),
                new Test(add,  new int[]{14},       new int[]{377})
        };

        int testingData[][] = new int[][]{
                new int[]{6},
                new int[]{13},
                new int[]{9},
        };

        int childrenCount = 10;
        DNA children[] = new DNA[childrenCount];
        long result[] = new long[childrenCount];

        children[0] = new DNA(10, add);
        children[0].randomInstructions();
        result[0] = runTrainingTests(children[0], trainingData, add);
        long max = result[0];
        int runs = 1;
        for (long i = 0; i < 100000000 && result[0] != 0 ; i++) {
            float baseErr = (float)result[0] / ((float)max);
            float err = baseErr;
            err = (runs < 50) ? (float)Math.pow(err, 5F/((float)runs)) : 0.99F;
            //System.out.println(err);
            if(runs < 12)
                runs ++;
            for (int j = 1; j < children.length; j++) {
                children[j] = children[0].mutate(err);
            }
            for (int j = 1; j < children.length; j++) {
                //children[j].randomInstructions();
                result[j] = runTrainingTests(children[j], trainingData, add);
                //System.out.println(result[j] + " " + result[0]);
                if(result[0] > result[j] && 0 <= result[j]) {
                    result[0] = result[j];
                    children[0] = children[j];
                    System.out.println("\nError: " + baseErr + " Diff: " + result[j] + " Generation: " + i + " Complexity: " + children[j].instructions.length);
                    runTests(children[0], testingData, add);
                    runs = 1;
                }

            }
            if(i % 10000 == 0) {
                System.out.print(".");
                //System.out.println(children[0] + " \n" + result[0]);
            }
        }

        System.out.println(children[0] + " " + result[0]);

        runTests(children[0], testingData, add);


    }



    static long runTrainingTests(DNA dna, Test[] tests, Problem problem){
        long err = 0;
        for (int i = 0; i < tests.length; i++) {
            Runtime runtime = new Runtime(dna, problem, tests[i].inputs);
            runtime.runToCompletion();
            err += tests[i].accuracy(runtime);
            //System.out.println(Arrays.toString(runtime.getMemory().getOutputs()));
        }
        return err;
    }

    static void runTests(DNA dna, int[][] tests, Problem problem){
        for (int i = 0; i < tests.length; i++) {
            Runtime runtime = new Runtime(dna, problem, tests[i]);
            runtime.runToCompletion();
            System.out.println("In:" + Arrays.toString(tests[i])+ " Out:" + Arrays.toString(runtime.getMemory().getOutputs()));
        }
    }

    static Test[] generateTestCase(int cases, Problem p){
        Test[] ret = new Test[cases];

        return ret;
    }


}
