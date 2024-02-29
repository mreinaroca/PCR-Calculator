package PCR;
import GeneralTools.ConsoleTable;
public class PCR_Interface {
    public static void main(String[] args) throws Exception {
        
        printPCRResults(
            5, 
            25, 
            "ambiental", 
            5, //Percentage of extra volume 10 = 10%, 100 = 100% = 2 times.
            new boolean[]
            {
                true, //T1Water
                true, //buffer
                true, //MgCl2
                true, //dNTP
                false, //primerF
                false, //primerR
                false, //DNA 
                false, //enhancer
                true  //Taq
            });

    }

    private static void printPCRResults(int numReactions, double reactionVolume, String protocol, double safeVolume, boolean[] master_mix_reagents)
    {
        //printPCRHeading(numReactions, reactionVolume, withEnhancer);
        
        PCR_Calculator pcr_calculator = new PCR_Calculator(numReactions, reactionVolume, protocol, safeVolume, master_mix_reagents);
        printPCRTable(pcr_calculator);
        //printPCRArray(pcr_calculator.giveSinglePCRVolumes());
        //printPCRMasterMix(pcr_calculator.calculateMasterMixVolumes());
        printDivisionValue(pcr_calculator.giveDivisionVolume());
        System.out.println("\n");
    }

    private static void printPCRTable(PCR_Calculator pcr_Calculator)
    {
        String[] headers = {"Reagent", "Initial concentration", "Final conc. by protocol","Single PCR volume (uL)","Master mix volume (uL)"};
        String[][] pcrTable = new String[9][headers.length];
        pcrTable[0][0] = "T1Water";
        pcrTable[1][0] = "Polymerase Buffer";
        pcrTable[2][0] = "MgCl2";
        pcrTable[3][0] = "dNTP";
        pcrTable[4][0] = "Primer F";
        pcrTable[5][0] = "Primer R";
        pcrTable[6][0] = "DNA";
        pcrTable[7][0] = "Enhancer";
        pcrTable[8][0] = "Taq Polymerase";

        double[] initial_concentrations = pcr_Calculator.giveInitialConcentrations();
        for(int i=0; i<9; i++)
        {
            pcrTable[i][1] = initial_concentrations[i] + "";
        }

        double[] final_concentrations = pcr_Calculator.giveFinalConcentrations();
        for(int i=0; i<9; i++)
        {
            pcrTable[i][2] = final_concentrations[i] + "";
        }

        double[] single_volumes = pcr_Calculator.giveSinglePCRVolumes();
        for(int i=0; i<9; i++)
        {
            pcrTable[i][3] = single_volumes[i]+"";
        }

        double[] master_mix_volumes = pcr_Calculator.giveMasterMixVolumes();
        for(int i=0; i<9; i++)
        {
            pcrTable[i][4] = master_mix_volumes[i]+"";
        }

        for(int i=0; i<9; i++)
        {
            for(int j=0; j<headers.length; j++)
            {
                if(pcrTable[i][j] == null || pcrTable[i][j].equals("0.0")) 
                {
                    pcrTable[i][j] = "---";
                }
            }
        }
        ConsoleTable.printTable(headers, pcrTable);
    }

    @SuppressWarnings("unused")
    private static void printPCRHeading(int numReactions, double reactionVolume, boolean withEnhancer)
    {
        System.out.print("\n");
        System.out.print("For "+numReactions+" reactions, "+reactionVolume+" uL as final value and");
        if(withEnhancer){System.out.print(" enhancer: ");}
        else{System.out.print(" without enhancer:");}
        System.out.print("\n");
    }
    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
    private static void printPCRMasterMix(double[] masterMix)
    {   
        System.out.print("\nMaster mix volumes (uL): ");
        printArray(masterMix);
    }
    private static void printDivisionValue(double divisionVolume)
    {
        System.out.print("Divide in volumes before primers of : "+divisionVolume+" uL");

    }
}
