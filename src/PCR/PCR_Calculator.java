package PCR;


import GeneralTools.MathTools;
/**
 * This class calculates the volumes of reagents needed for a PCR reaction
 * It is based on the following paper:
 * https://www.ncbi.nlm.nih.gov/pmc/articles/PMC4846334/
 * 
 * @author Miguel A. Reina
 */


 
public class PCR_Calculator {
    // *********************************************
    // Constants
    // *********************************************

    public final static String CODE_AMBIENTAL = "ambiental";
    public final static String CODE_LORENZ = "lorenz";
    public final static String CODE_THERMO_FISCHER = "thermo-fischer";
    public final static String CODE_DANIEL = "daniel";
    public final static String CODE_NIFH = "nifH";
    /**
     * "Ambiental" protocol
     * DO NOT CHANGE
    */
    public final static double ambiental_final_buffer_X = 1;
    public final static double ambiental_final_MgCl2_mM = 2.5;
    public final static double ambiental_final_dNTP_mM = 0.2;
    public final static double ambiental_final_primer_uM = 0.3;
    public final static double ambiental_final_crudeDNA = 0.036;
    public final static double ambiental_final_enhancer_X = 1;
    public final static double ambiental_final_Taq_u = 0.06;

    public final static double[] ambiental_PCR_concentrations = {
        0,
        ambiental_final_buffer_X,
        ambiental_final_MgCl2_mM,
        ambiental_final_dNTP_mM,
        ambiental_final_primer_uM,
        ambiental_final_primer_uM,
        ambiental_final_crudeDNA,
        ambiental_final_enhancer_X,
        ambiental_final_Taq_u
    };
    
   
    /**
     * Lorenz T. recommended concentrations
     * https://www.ncbi.nlm.nih.gov/pmc/articles/PMC4846334/
     * DO NOT CHANGE
     */
    public final static double lorenz_final_buffer_X = 1;
    public final static double lorenz_final_MgCl2_mM = 3;
    public final static double lorenz_final_dNTP_mM = 0.2;
    public final static double lorenz_final_primer_uM= 0.4;
    public final static double lorenz_final_crudeDNA = 0.036;
    public final static double lorenz_final_enhancer_X = 1;
    public final static double lorenz_final_Taq_u = 0.05;

    public final static double[] lorenz_PCR_concentrations = {
        0,
        lorenz_final_buffer_X,
        lorenz_final_MgCl2_mM,
        lorenz_final_dNTP_mM,
        lorenz_final_primer_uM,
        lorenz_final_primer_uM,
        lorenz_final_crudeDNA,
        lorenz_final_enhancer_X,
        lorenz_final_Taq_u
    };

    /**
     * Thermo Fisher recommended concentrations
     */
    public final static double thermo_fischer_final_buffer_X = 1;
    public final static double thermo_fischer_final_MgCl2_mM = 2;// between 1 and 4
    public final static double thermo_fischer_final_dNTP_mM = 0.2; 
    public final static double thermo_fischer_final_primer_uM = 0.1;
    public final static double thermo_fischer_final_crudeDNA = 0.036;
    public final static double thermo_fischer_final_enhancer_X = 1;
    public final static double thermo_fischer_final_Taq_u = 0.025;
    
    public final static double[] thermo_fischer_PCR_concentrations = {
        0,
        thermo_fischer_final_buffer_X,
        thermo_fischer_final_MgCl2_mM,
        thermo_fischer_final_dNTP_mM,
        thermo_fischer_final_primer_uM,
        thermo_fischer_final_primer_uM,
        thermo_fischer_final_crudeDNA,
        thermo_fischer_final_enhancer_X,
        thermo_fischer_final_Taq_u
    };


    public final static double daniel_buffer_X = 1;
    public final static double daniel_MgCl2_mM = 2.5;
    public final static double daniel_dNTP_mM = 0.2;
    public final static double daniel_primer_uM = 0.4;
    public final static double daniel_crudeDNA = 0.1;
    public final static double daniel_enhancer_X = 1;
    public final static double daniel_Taq_u = 0.05;

    public final static double[] daniel_PCR_concentrations = {
        0,
        daniel_buffer_X,
        daniel_MgCl2_mM,
        daniel_dNTP_mM,
        daniel_primer_uM,
        daniel_primer_uM,
        daniel_crudeDNA,
        daniel_enhancer_X,
        daniel_Taq_u
    };

    public final static double nifH_buffer_X = 1;
    public final static double nifH_MgCl2_mM = 3;
    public final static double nifH_dNTP_mM = 0.2;
    public final static double nifH_primer_mM = 0.1;
    public final static double nifH_crudeDNA = 0.036;
    public final static double nifH_enhancer_X = 1;
    public final static double nifH_Taq_u = 0.05;
    public final static double[] nifH_PCR_concentrations = {
        0,
        nifH_buffer_X,
        nifH_MgCl2_mM,
        nifH_dNTP_mM,
        nifH_primer_mM,
        nifH_primer_mM,
        nifH_crudeDNA,
        nifH_enhancer_X,
        nifH_Taq_u
    };
    
    // *********************************************
    // Atributes
    // *********************************************



    private int numReactions;
    private double reactionVolume;
    private double divisionVolume;
    private boolean withEnhancer;
    // 0 is water, 1 is buffer, 2 is MgCl2, 3 is dNTP, 4 is primers, 5 is DNA, 6 is enhancer, 7 is Taq
    private double[] final_concentrations=new double[8];
    // 0 is water, 1 is buffer, 2 is MgCl2, 3 is dNTP, 4 is primers, 5 is DNA, 6 is enhancer, 7 is Taq
    private double[] volumes;
    // 0 is water, 1 is buffer, 2 is MgCl2, 3 is dNTP, 4 is primers, 5 is DNA, 6 is enhancer, 7 is Taq
    private double[] masterMixVolumes;
    // master_mix_reagens[4] indicates that the primers are in the master mix
    private boolean[] master_mix_reagents;
    

    private double safeVolume;

    private double initial_buffer_X = 10;
    private double initial_MgCl2_mM = 50;
    private double initial_dNTP_mM = 10;
    private double initial_primerF_mM = 10;
    private double initial_primerR_mM = 10;
    private double initial_enhancer_X = 10;
    private double initial_Taq_u = 5;
    private double[] initial_concentrations= 
        {
        0, // water
        initial_buffer_X,
        initial_MgCl2_mM,
        initial_dNTP_mM,
        initial_primerF_mM,
        initial_primerR_mM,
        0, // DNA
        initial_enhancer_X,
        initial_Taq_u
        };    

    // *********************************************
    // Constructor
    // *********************************************

    /**
     * Full constructor for PCR_Calculator
     * @param numReactions
     * @param reactionVolume
     * @param withEnhancer
     * @param protocol
     */
    public PCR_Calculator(int numReactions, double reactionVolume, String protocol, double safeVolume, boolean[] master_mix_reagents)
    {   
        volumes = new double[9];
        masterMixVolumes = new double[9];
        this.numReactions = numReactions;
        this.reactionVolume = reactionVolume;
        this.master_mix_reagents = master_mix_reagents;
        this.safeVolume = safeVolume;
        assignProtocol(protocol);
        calculateSinglePCRVolumes();
        calculateMasterMixVolumes();
    }

    // *********************************************
    // Methods
    // *********************************************

    /**
     * Assigns the final concentrations to be used
     * @param protocol
     */
    private void assignProtocol(String protocol) {
        
        switch(protocol)
        {
            case CODE_AMBIENTAL:
                final_concentrations=ambiental_PCR_concentrations;
                break;
            case CODE_LORENZ:
                final_concentrations=lorenz_PCR_concentrations;
                break; 
            case CODE_THERMO_FISCHER:   
                final_concentrations=thermo_fischer_PCR_concentrations;
                break;
            case CODE_DANIEL:
                final_concentrations=daniel_PCR_concentrations;
                break;
            case CODE_NIFH:
                final_concentrations=nifH_PCR_concentrations;
                break;
            default:
                System.out.println("Protocol not recognized. Using ambiental protocol");
                final_concentrations=ambiental_PCR_concentrations;
                break;
        }

    }

    /**
     * Calculates the volumes of each reagent for a single PCR reaction
     * @return volumes, an array with the values of a single PCR reaction
     */
    private double[] calculateSinglePCRVolumes()
    {   
        double volBuffer = final_concentrations[1]*reactionVolume/initial_concentrations[1];
        volumes[1]=volBuffer;
        double volMg = final_concentrations[2]*reactionVolume/initial_concentrations[2];
        volumes[2]=volMg;
        double voldNTP = final_concentrations[3]*reactionVolume/initial_concentrations[3];
        volumes[3]=voldNTP;
        double volPrimerF = final_concentrations[4]*reactionVolume/initial_concentrations[4];
        volumes[4]=volPrimerF;
        double volPrimerR = final_concentrations[5]*reactionVolume/initial_concentrations[5];
        volumes[5]=volPrimerR;
        // 
        double volDNA = final_concentrations[6]*reactionVolume;
        volumes[6]=volDNA;

        if(master_mix_reagents[7] == true) //with enhancer or not?
        {
            double volEnhancer = final_concentrations[7]*reactionVolume/initial_concentrations[7];
            volumes[7]=volEnhancer;
        } 
        else {volumes[7]=0;}

        double volTaq = final_concentrations[8]*reactionVolume/initial_concentrations[8];
        volumes[8]=volTaq;

        double volWaterTypeI = reactionVolume;
        for(int i=1;i<volumes.length;i++)
        {
            volumes[i] = MathTools.roundTwoDecimals(volumes[i]);
            if(volumes[i] != 0) volWaterTypeI= volWaterTypeI - volumes[i];
            
        }
        volumes[0]=MathTools.roundTwoDecimals(volWaterTypeI);
        return volumes;
    }

    /**
     * Calculates the volumes of each reagent for the master mix
     * @return
     */
    public double[] calculateMasterMixVolumes()
    {   
        double sum = 0;
        // goes until the last primer i=6
        // or i=3 to before the primers
        for(int i=0;i<9;i++)
        {   
            if(master_mix_reagents[i] == true)
            {
                masterMixVolumes[i] = MathTools.roundTwoDecimals(volumes[i]*numReactions*(1+safeVolume/100));
            }
            
            sum += masterMixVolumes[i];
        }
        divisionVolume = sum/numReactions;

        
        return masterMixVolumes;
    }






    public double[] giveSinglePCRVolumes(){return volumes;}
    
    public double[] giveMasterMixVolumes(){return masterMixVolumes;}

    public double giveDivisionVolume(){return divisionVolume;}

    public double[] giveFinalConcentrations(){return final_concentrations;}
    
    public double[] giveInitialConcentrations(){return initial_concentrations;}





}
