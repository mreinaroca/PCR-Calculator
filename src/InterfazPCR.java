public class InterfazPCR {
    public static void main(String[] args) throws Exception {

        printPCRResults(6, 30, false, "lorenz");

    }

    private static void printPCRResults(int numReactions, double reactionVolume, boolean withEnhancer, String protocol)
    {
        System.out.print("\n");
        System.out.print("For "+numReactions+" reactions, "+reactionVolume+" uL as final value and");
        if(withEnhancer){System.out.print(" enhancer: ");}
        else{System.out.print(" without enhancer:");}
        System.out.print("\n");
        
        PCR_Calculator c = new PCR_Calculator(numReactions, reactionVolume, withEnhancer,protocol);
        printPCRArray(c.giveSinglePCRVolumes());
        printPCRMasterMix(c.calculateMasterMixVolumes());
        printDivisionValue(c.giveDivisionVolume());
        System.out.println("\n");
    }

    private static void printPCRArray(double[] singlePCR)
    {   
        System.out.println("Convention:            [T1Water, buffer, MgCl2, dNTP, primerF, primerR, DNA, enhancer, Taq]");
        System.out.print("Single PCR volumes (uL): ");
        printArray(singlePCR);
        printPCRSum(singlePCR);
    }

    private static void printArray(double[] a)
    {   
        String arrayString = "[";
        for (int i = 0; i < a.length; i++) {
            arrayString += a[i];
            if (i != a.length - 1) {
                arrayString += ", ";
            }
        }
        arrayString += "]";
        System.out.print(arrayString);
    
    }

    private static void printPCRSum(double[] singlePCR)
    {   
        float volumeSum = 0;
        for(int i=0;i<singlePCR.length;i++)
        {
            volumeSum+= singlePCR[i];
        }
        System.out.print("\nTotal volume: "+volumeSum+ " (uL)");
    }

    private static void printPCRMasterMix(double[] masterMix)
    {   
        System.out.print("\nMaster mix volumes (uL): ");
        printArray(masterMix);
    }
    private static void printDivisionValue(double divisionVolume)
    {
        System.out.print("\nDivide in volumes before primers of : "+divisionVolume+" uL");

    }
}
